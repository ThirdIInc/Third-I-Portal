package org.paradyne.bean.admin.increment;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AnnualIncrement extends BeanBase implements Serializable {
	
	String month = "";
	String year = "";
	String payBill = "";
	String payBillName = "";
	String empId = "";
	String empName = "";
	String prevDate = "";
	String dueDate = "";
	ArrayList incrList = null;
	String currBasic = "";
	String currDate = "";
	String nonQualified="";
	String newBasic = "";
	String dceList ="";
	String payScale ="";
	String payScaleName = "";
	String dceDate ="";
	String dceSrl ="";
	String lock ="";
	String removeChk ="";
	String islockFlag ="false";
	String isRemoveFlag ="false";
	String arrearTo = "";
	
	String empToken ="";
	String hiddenEmp ="";
	String hiddenEmpCode ="";
	String hiddenEmpName ="";
	String hiddenPrevDate ="";
	String hiddenPayScale ="";
	
	
	public String getArrearTo() {
		return arrearTo;
	}

	public void setArrearTo(String arrearTo) {
		this.arrearTo = arrearTo;
	}

	public String getPayScaleName() {
		return payScaleName;
	}

	public void setPayScaleName(String payScaleName) {
		this.payScaleName = payScaleName;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getHiddenEmpCode() {
		return hiddenEmpCode;
	}

	public void setHiddenEmpCode(String hiddenEmpCode) {
		this.hiddenEmpCode = hiddenEmpCode;
	}

	public String getHiddenEmpName() {
		return hiddenEmpName;
	}

	public void setHiddenEmpName(String hiddenEmpName) {
		this.hiddenEmpName = hiddenEmpName;
	}

	public String getHiddenPrevDate() {
		return hiddenPrevDate;
	}

	public void setHiddenPrevDate(String hiddenPrevDate) {
		this.hiddenPrevDate = hiddenPrevDate;
	}

	public String getHiddenPayScale() {
		return hiddenPayScale;
	}

	public void setHiddenPayScale(String hiddenPayScale) {
		this.hiddenPayScale = hiddenPayScale;
	}

	public  AnnualIncrement(){
		
	}

	public String getRemoveChk() {
		return removeChk;
	}

	public void setRemoveChk(String removeChk) {
		this.removeChk = removeChk;
	}

		

	public String getIsRemoveFlag() {
		return isRemoveFlag;
	}




	public void setIsRemoveFlag(String isRemoveFlag) {
		this.isRemoveFlag = isRemoveFlag;
	}




	public String getHiddenEmp() {
		return hiddenEmp;
	}




	public void setHiddenEmp(String hiddenEmp) {
		this.hiddenEmp = hiddenEmp;
	}




	public String getIslockFlag() {
		return islockFlag;
	}




	public void setIslockFlag(String islockFlag) {
		this.islockFlag = islockFlag;
	}	
	
	
	
	public String getLock() {
		return lock;
	}




	public void setLock(String lock) {
		this.lock = lock;
	}




	public String getDceSrl() {
		return dceSrl;
	}




	public void setDceSrl(String dceSrl) {
		this.dceSrl = dceSrl;
	}




	/**
	 * @return the newBasic
	 */
	public String getNewBasic() {
		return newBasic;
	}



	/**
	 * @param newBasic the newBasic to set
	 */
	public void setNewBasic(String newBasic) {
		this.newBasic = newBasic;
	}



	/**
	 * @return the nonQualified
	 */
	public String getNonQualified() {
		return nonQualified;
	}



	/**
	 * @param nonQualified the nonQualified to set
	 */
	public void setNonQualified(String nonQualified) {
		this.nonQualified = nonQualified;
	}



	/**
	 * @return the currDate
	 */
	public String getCurrDate() {
		return currDate;
	}



	/**
	 * @param currDate the currDate to set
	 */
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}



	/**
	 * @return the incrList
	 */
	public ArrayList getIncrList() {
		return incrList;
	}



	/**
	 * @param incrList the incrList to set
	 */
	public void setIncrList(ArrayList incrList) {
		this.incrList = incrList;
	}



	/**
	 * @return the currBasic
	 */
	public String getCurrBasic() {
		return currBasic;
	}



	/**
	 * @param currBasic the currBasic to set
	 */
	public void setCurrBasic(String currBasic) {
		this.currBasic = currBasic;
	}



	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}



	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}



	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}



	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}



	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}



	/**
	 * @return the prevDate
	 */
	public String getPrevDate() {
		return prevDate;
	}



	/**
	 * @param prevDate the prevDate to set
	 */
	public void setPrevDate(String prevDate) {
		this.prevDate = prevDate;
	}



	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}





	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}





	/**
	 * @return the payBill
	 */
	public String getPayBill() {
		return payBill;
	}





	/**
	 * @param payBill the payBill to set
	 */
	public void setPayBill(String payBill) {
		this.payBill = payBill;
	}





	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}





	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}





	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}





	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}



	public String getDceList() {
		return dceList;
	}



	public void setDceList(String dceList) {
		this.dceList = dceList;
	}



	public String getPayScale() {
		return payScale;
	}



	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}



	public String getDceDate() {
		return dceDate;
	}



	public void setDceDate(String dceDate) {
		this.dceDate = dceDate;
	}





	
}	