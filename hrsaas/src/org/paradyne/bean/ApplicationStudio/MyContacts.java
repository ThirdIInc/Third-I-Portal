package org.paradyne.bean.ApplicationStudio;

import java.util.LinkedHashMap;
import org.paradyne.lib.BeanBase;

/**
 * @author tinshuk.banafar
 *
 */
public class MyContacts  extends BeanBase{

	private String hiddenConId ="";
	private String hiddenEditCode ="";
	private String deptName="";
	private String deptId="";
	
	private String contactName="";
	private String contactNo="";
	
	private String emailID="";
	private String contactDivision= "";
	private String contactDivCode="";
	
	LinkedHashMap tmap=null;
	private boolean chkFlag=false;
	
	public String getHiddenEditCode() {
		return hiddenEditCode;
	}
	public void setHiddenEditCode(String hiddenEditCode) {
		this.hiddenEditCode = hiddenEditCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public LinkedHashMap getTmap() {
		return tmap;
	}
	public void setTmap(LinkedHashMap tmap) {
		this.tmap = tmap;
	}
	public boolean isChkFlag() {
		return chkFlag;
	}
	public void setChkFlag(boolean chkFlag) {
		this.chkFlag = chkFlag;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getHiddenConId() {
		return hiddenConId;
	}
	public void setHiddenConId(String hiddenConId) {
		this.hiddenConId = hiddenConId;
	}
	public String getContactDivision() {
		return contactDivision;
	}
	public void setContactDivision(String contactDivision) {
		this.contactDivision = contactDivision;
	}
	public String getContactDivCode() {
		return contactDivCode;
	}
	public void setContactDivCode(String contactDivCode) {
		this.contactDivCode = contactDivCode;
	}
}
