package org.paradyne.bean.admin.transfer;

import java.io.Serializable;
import java.util.ArrayList;
/*
 * author:Pradeep Kumar Sahoo
 * Date:05-10-2007
 */

import org.paradyne.lib.BeanBase;
public class TransferPmApproval extends BeanBase implements Serializable{
	
	String empId;
	String empToken;
	String empName;
	String appDate;
	String transferId;
	String trfId;
	ArrayList transferList=null;
	String pmChkFlag;
	String dceDate;
	String dceNo;
	public TransferPmApproval() {}
	public TransferPmApproval(String trfId,String empId, String empToken, String empName, String appDate, String transferId) {
		super();
		this.trfId=trfId;
		this.empId = empId;
		this.empToken = empToken;
		this.empName = empName;
		this.appDate = appDate;
		this.transferId = transferId;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public ArrayList getTransferList() {
		return transferList;
	}
	public void setTransferList(ArrayList transferList) {
		this.transferList = transferList;
	}
	public String getTrfId() {
		return trfId;
	}
	public void setTrfId(String trfId) {
		this.trfId = trfId;
	}
	public String getPmChkFlag() {
		return pmChkFlag;
	}
	public void setPmChkFlag(String pmChkFlag) {
		this.pmChkFlag = pmChkFlag;
	}
	public String getDceDate() {
		return dceDate;
	}
	public void setDceDate(String dceDate) {
		this.dceDate = dceDate;
	}
	public String getDceNo() {
		return dceNo;
	}
	public void setDceNo(String dceNo) {
		this.dceNo = dceNo;
	}
	

	
	
	
	

}
