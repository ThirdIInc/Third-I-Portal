/**
 * 
 */
package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author mangeshj
 *
 */
public class WareHouseMaster extends BeanBase {
	private String wareHouseName;
	private String wareHousecode;
	private String respCode;
	private String respToken;
	private String respName;
	private String centralizeBranchName;
	private String centralizeBranchCode;
	private String branchName;
	private String branchCode;
	private String branchNameIterator;
	private String branchCodeIterator;
	private String tableLength;
	private String centralizeFlag="false";
	private String branchFlag="false";
	private String paraId;
	private String isActive="";
	
	private ArrayList<Object> branchList;
	
	private String myPage ;
	private String hiddencode;
	private String show;
	private String hdeleteCode;
	
	 private String totalRecords="";
	 private String recordsLength="false";
	 ArrayList recordList;
	 private String emailid="";
	 
	 private String mobileno="";

		
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
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
	
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	public String getWareHousecode() {
		return wareHousecode;
	}
	public void setWareHousecode(String wareHousecode) {
		this.wareHousecode = wareHousecode;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespToken() {
		return respToken;
	}
	public void setRespToken(String respToken) {
		this.respToken = respToken;
	}
	public String getRespName() {
		return respName;
	}
	public void setRespName(String respName) {
		this.respName = respName;
	}
	public String getCentralizeBranchName() {
		return centralizeBranchName;
	}
	public void setCentralizeBranchName(String centralizeBranchName) {
		this.centralizeBranchName = centralizeBranchName;
	}
	public String getCentralizeBranchCode() {
		return centralizeBranchCode;
	}
	public void setCentralizeBranchCode(String centralizeBranchCode) {
		this.centralizeBranchCode = centralizeBranchCode;
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
	public String getBranchNameIterator() {
		return branchNameIterator;
	}
	public void setBranchNameIterator(String branchNameIterator) {
		this.branchNameIterator = branchNameIterator;
	}
	public String getBranchCodeIterator() {
		return branchCodeIterator;
	}
	public void setBranchCodeIterator(String branchCodeIterator) {
		this.branchCodeIterator = branchCodeIterator;
	}
	public ArrayList<Object> getBranchList() {
		return branchList;
	}
	public void setBranchList(ArrayList<Object> branchList) {
		this.branchList = branchList;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getCentralizeFlag() {
		return centralizeFlag;
	}
	public void setCentralizeFlag(String centralizeFlag) {
		this.centralizeFlag = centralizeFlag;
	}
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
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
	 * @param hiddencode the hiddencode to set
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
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
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
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
