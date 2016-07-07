package org.paradyne.bean.admin.transfer;
import org.paradyne.lib.BeanBase;

/*
 * Pradeep K Sahoo
 * 
 */
public class transferCompassionate extends BeanBase{
	
	
	private String empId;
	private String empName;
	private String repType;
	private String empToken;
	private String appDate;
	private String signAuthCode;
	private String signAuthToken;
	private String signAuthName;
	private String tempCode;
	private String tempName;
	private String transferCode;
	
	
	
	public transferCompassionate(){ }
	public transferCompassionate(String appDate,String empId, String empName, String repType,String empToken) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
		this.empToken=empToken;
		this.appDate=appDate;
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
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getSignAuthCode() {
		return signAuthCode;
	}
	public void setSignAuthCode(String signAuthCode) {
		this.signAuthCode = signAuthCode;
	}
	public String getSignAuthToken() {
		return signAuthToken;
	}
	public void setSignAuthToken(String signAuthToken) {
		this.signAuthToken = signAuthToken;
	}
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getTempCode() {
		return tempCode;
	}
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTransferCode() {
		return transferCode;
	}
	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}
	
	
	
	
	

}

