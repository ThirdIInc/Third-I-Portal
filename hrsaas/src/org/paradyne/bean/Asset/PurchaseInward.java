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
public class PurchaseInward extends BeanBase {

	
	private String empToken;
	
	private String totalAmt;
	private String purchaseCode;
	private String empID;
	private String empName;
	private String orderDate;
	private String status;
	private String pendingFlag ="true";
	private String hiddenPurchaseCode;
	private String empCodeHidden;

	private String noData="false";
	private String listLength="0"; 
	
	
	private ArrayList<Object> recordList;


	public String getEmpToken() {
		return empToken;
	}


	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}


	public String getTotalAmt() {
		return totalAmt;
	}


	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}


	public String getPurchaseCode() {
		return purchaseCode;
	}


	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}


	public String getEmpID() {
		return empID;
	}


	public void setEmpID(String empID) {
		this.empID = empID;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getNoData() {
		return noData;
	}


	public void setNoData(String noData) {
		this.noData = noData;
	}


	public String getListLength() {
		return listLength;
	}


	public void setListLength(String listLength) {
		this.listLength = listLength;
	}


	public ArrayList<Object> getRecordList() {
		return recordList;
	}


	public void setRecordList(ArrayList<Object> recordList) {
		this.recordList = recordList;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPendingFlag() {
		return pendingFlag;
	}


	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}


	public String getHiddenPurchaseCode() {
		return hiddenPurchaseCode;
	}


	public void setHiddenPurchaseCode(String hiddenPurchaseCode) {
		this.hiddenPurchaseCode = hiddenPurchaseCode;
	}


	public String getEmpCodeHidden() {
		return empCodeHidden;
	}


	public void setEmpCodeHidden(String empCodeHidden) {
		this.empCodeHidden = empCodeHidden;
	}
}
