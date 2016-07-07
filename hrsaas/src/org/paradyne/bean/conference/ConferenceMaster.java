package org.paradyne.bean.conference;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Tarun Chaturvedi
 * 
 * Bean class for Conference Master
 *
 */

public class ConferenceMaster extends BeanBase {
	
	/**
	 * Declaring bean properties
	 */
	private String accessCode = "";
	private String accessoryName = "";
	private String resPersonName = "";
	private String resPersonCode = "";
	private String resPersonToken;
	private String isActive = "";
	private ArrayList ConfList;
	
	
	
	private ArrayList iteratorlist;
	private String show;
	private String hiddencode;
	private String myPage;

	private String CodeIterator;
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

		public String getCodeIterator() {
			return CodeIterator;
		}
		public void setCodeIterator(String codeIterator) {
			CodeIterator = codeIterator;
		}
	
	/**
	 * getter/setter methods for all bean properties
	 */
	public ArrayList getConfList() {
		return ConfList;
	}

	public void setConfList(ArrayList confList) {
		ConfList = confList;
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
	
	public String getAccessCode() {
		return accessCode;
	}
	
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public String getAccessoryName() {
		return accessoryName;
	}
	
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	/**
	 * @return the resPersonToken
	 */
	public String getResPersonToken() {
		return resPersonToken;
	}

	/**
	 * @param resPersonToken the resPersonToken to set
	 */
	public void setResPersonToken(String resPersonToken) {
		this.resPersonToken = resPersonToken;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
