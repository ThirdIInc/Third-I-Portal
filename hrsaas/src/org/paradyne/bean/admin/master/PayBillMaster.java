package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**@author pranali
 * @updated by priyanka (Date 28-02-13)
 * Date 21-04-07
 */
public class PayBillMaster extends BeanBase implements Serializable {

	
	String payID="";
	String payGrp="";	
	ArrayList pay=null;
	

	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	private String report="";
	
	private String hdeleteCode;
	private boolean insertFlag = false;
	private boolean updateFlag = false;
	private boolean deleteFlag = false;
	private boolean viewFlag=false;
	String isNotGeneralUser="false";
	private boolean generalFlag = true;
	
	/**
	 * Mode Length for paging.
	 */
	private String modeLength  = "";
	/**
	 * Total no of records display field.
	 */
	private String totalNoOfRecords = "";

	
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
    

	
	public PayBillMaster(){
		
	}

	public PayBillMaster(String payID, String payGrp) {
		super();
		this.payID = payID;
		this.payGrp = payGrp;	
	}

	public ArrayList getPay() {
		return pay;
	}

	public void setPay(ArrayList pay) {
		this.pay = pay;
	}

	public String getPayGrp() {
		return payGrp;
	}

	public void setPayGrp(String payGrp) {
		this.payGrp = payGrp;
	}

	public String getPayID() {
		return payID;
	}

	public void setPayID(String payID) {
		this.payID = payID;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
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
		return totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}

	public boolean isInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public boolean isViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	public boolean isGeneralFlag() {
		return generalFlag;
	}

	public void setGeneralFlag(boolean generalFlag) {
		this.generalFlag = generalFlag;
	}		
}