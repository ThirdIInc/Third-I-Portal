package org.paradyne.bean.conference;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Tarun Chaturvedi
 * 
 * Bean class for Venue Master
 *
 */
public class VenueMaster extends BeanBase {
	
	/**
	 * Declaring bean properties
	 */
	private String venueCode = "";
	private String venueName = "";
	private String branchName = "";
	private String branchCode = "";
	private String resPersonCode = "";
	private String resPersonName = "";
	private String resPersonToken ="";
	private String isActive = "";
	
	private ArrayList venueList;
	
	private String show   ;
	private String hiddencode ;
	private String myPage ;
	
	ArrayList   iteratorlist;
	private String hdeleteCode;
	
	private String totalRecords="";
	 private String listLength="false";
	
	 ArrayList recordList;

		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}

		public String getListLength() {
			return listLength;
		}

		public void setListLength(String listLength) {
			this.listLength = listLength;
		}

		
		public ArrayList getRecordList() {
			return recordList;
		}

		public void setRecordList(ArrayList recordList) {
			this.recordList = recordList;
		}


	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	
	/**
	 * getter/setter methods for all bean properties
	 */
	public ArrayList getVenueList() {
		return venueList;
	}

	public void setVenueList(ArrayList venueList) {
		this.venueList = venueList;
	}

	public String getVenueCode() {
		return venueCode;
	}
	
	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getResPersonCode() {
		return resPersonCode;
	}
	public void setResPersonCode(String resPersonCode) {
		this.resPersonCode = resPersonCode;
	}
	public String getResPersonName() {
		return resPersonName;
	}
	public void setResPersonName(String resPersonName) {
		this.resPersonName = resPersonName;
	}
	public String getResPersonToken() {
		return resPersonToken;
	}
	public void setResPersonToken(String resPersonToken) {
		this.resPersonToken = resPersonToken;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
