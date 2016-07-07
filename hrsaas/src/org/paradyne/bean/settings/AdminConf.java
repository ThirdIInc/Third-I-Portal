package org.paradyne.bean.settings;

import java.util.ArrayList;

import javax.management.loading.PrivateClassLoader;

import org.paradyne.lib.BeanBase;

public class AdminConf extends BeanBase {
	private String divCode="";
	private String division="";
	private String empToken="";
	private String empName="";
	private String empId="";
	private String managerFlag="";
	private String adminFlag="";
	private String acctFlag="";
	private String srNoIt="";
	private String divNameIt="";
	private String adminNameIt="";
	ArrayList adminConfList;
	private String accDivCode="";
	private String accDivision="";
	private String accEmpToken="";
	private String accEmpName="";
	private String accEmpId="";
	ArrayList accConfList;
	private String accSrNoIt="";
	private String accDivNameIt="";
	private String accAdminNameIt="";
	private String paraId="";
	
	//BUSINESS CARD INFORMATION
	private String bCardManagerFlag="";
	private String bCardDivCode="";
	private String bCardDivision="";
	private String bCardEmpToken="";
	private String bCardEmpName="";
	private String bCardEmpId="";
	ArrayList adminBCardConfList;
	private String bCardSrNoIt="";
	private String bCardDivNameIt="";
	private String bCardAdminNameIt="";
	private String bCardVndrName="";
	private String bCardVndrEmail="";
	private String bCardVndrPhNum="";
	private String 	bCardVendorFlag="";
	
	private String bCardVndrNameIt="";
	private String bCardVndrEmailIt="";
	private String bCardVndrPhNumIt="";
	//EMPLOYEE CARD INFORMATION
	//private String eCardManagerFlag="";
	private String eCardDivCode="";
	private String eCardDivision="";
	private String eCardEmpToken="";
	private String eCardEmpName="";
	private String eCardEmpId="";
	ArrayList adminECardConfList;
	private String eCardSrNoIt="";
	private String eCardDivNameIt="";
	private String eCardAdminNameIt="";
	ArrayList ECardVendorConfList;
	private String eCardVndrName="";
	private String eCardVndrEmail="";
	private String eCardVndrPhNum="";
	private String eCardVendorFlag="";
	private String eCardVndrNameIt="";
	private String eCardVndrEmailIt="";
	private String eCardVndrPhNumIt="";
	private String divCodeIt="";
	private String adminCodeIt="";
	private String accDivCodeIt="";
	private String accAdminCodeIt="";
	
	
	private String bCardAdminCodeIt="";
	private String bCardDivCodeIt="";
	private String eCardAdminCodeIt="";
	private String eCardDivCodeIt="";
	
	private String bCardConfIdIt="";
	private String eCardConfIdIt="";
	private String accConfIdIt="";
	private String adminConfIdIt="";
	
	public String getBCardAdminCodeIt() {
		return bCardAdminCodeIt;
	}
	public void setBCardAdminCodeIt(String cardAdminCodeIt) {
		bCardAdminCodeIt = cardAdminCodeIt;
	}
	public String getBCardDivCodeIt() {
		return bCardDivCodeIt;
	}
	public void setBCardDivCodeIt(String cardDivCodeIt) {
		bCardDivCodeIt = cardDivCodeIt;
	}
	public String getECardAdminCodeIt() {
		return eCardAdminCodeIt;
	}
	public void setECardAdminCodeIt(String cardAdminCodeIt) {
		eCardAdminCodeIt = cardAdminCodeIt;
	}
	public String getECardDivCodeIt() {
		return eCardDivCodeIt;
	}
	public void setECardDivCodeIt(String cardDivCodeIt) {
		eCardDivCodeIt = cardDivCodeIt;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getManagerFlag() {
		return managerFlag;
	}
	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
	}
	public String getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	public String getDivNameIt() {
		return divNameIt;
	}
	public void setDivNameIt(String divNameIt) {
		this.divNameIt = divNameIt;
	}
	public String getAdminNameIt() {
		return adminNameIt;
	}
	public void setAdminNameIt(String adminNameIt) {
		this.adminNameIt = adminNameIt;
	}
	public ArrayList getAdminConfList() {
		return adminConfList;
	}
	public void setAdminConfList(ArrayList adminConfList) {
		this.adminConfList = adminConfList;
	}
	public String getSrNoIt() {
		return srNoIt;
	}
	public void setSrNoIt(String srNoIt) {
		this.srNoIt = srNoIt;
	}
	public String getAccDivCode() {
		return accDivCode;
	}
	public void setAccDivCode(String accDivCode) {
		this.accDivCode = accDivCode;
	}
	public String getAccDivision() {
		return accDivision;
	}
	public void setAccDivision(String accDivision) {
		this.accDivision = accDivision;
	}
	public String getAccEmpToken() {
		return accEmpToken;
	}
	public void setAccEmpToken(String accEmpToken) {
		this.accEmpToken = accEmpToken;
	}
	public String getAccEmpName() {
		return accEmpName;
	}
	public void setAccEmpName(String accEmpName) {
		this.accEmpName = accEmpName;
	}
	public String getAccEmpId() {
		return accEmpId;
	}
	public void setAccEmpId(String accEmpId) {
		this.accEmpId = accEmpId;
	}
	public ArrayList getAccConfList() {
		return accConfList;
	}
	public void setAccConfList(ArrayList accConfList) {
		this.accConfList = accConfList;
	}
	public String getAccSrNoIt() {
		return accSrNoIt;
	}
	public void setAccSrNoIt(String accSrNoIt) {
		this.accSrNoIt = accSrNoIt;
	}
	public String getAccDivNameIt() {
		return accDivNameIt;
	}
	public void setAccDivNameIt(String accDivNameIt) {
		this.accDivNameIt = accDivNameIt;
	}
	public String getAccAdminNameIt() {
		return accAdminNameIt;
	}
	public void setAccAdminNameIt(String accAdminNameIt) {
		this.accAdminNameIt = accAdminNameIt;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getBCardManagerFlag() {
		return bCardManagerFlag;
	}
	public void setBCardManagerFlag(String cardManagerFlag) {
		bCardManagerFlag = cardManagerFlag;
	}
	public String getBCardDivCode() {
		return bCardDivCode;
	}
	public void setBCardDivCode(String cardDivCode) {
		bCardDivCode = cardDivCode;
	}
	public String getBCardDivision() {
		return bCardDivision;
	}
	public void setBCardDivision(String cardDivision) {
		bCardDivision = cardDivision;
	}
	public String getBCardEmpToken() {
		return bCardEmpToken;
	}
	public void setBCardEmpToken(String cardEmpToken) {
		bCardEmpToken = cardEmpToken;
	}
	public String getBCardEmpName() {
		return bCardEmpName;
	}
	public void setBCardEmpName(String cardEmpName) {
		bCardEmpName = cardEmpName;
	}
	public String getBCardEmpId() {
		return bCardEmpId;
	}
	public void setBCardEmpId(String cardEmpId) {
		bCardEmpId = cardEmpId;
	}
	
	public String getBCardSrNoIt() {
		return bCardSrNoIt;
	}
	public void setBCardSrNoIt(String cardSrNoIt) {
		bCardSrNoIt = cardSrNoIt;
	}
	public String getBCardDivNameIt() {
		return bCardDivNameIt;
	}
	public void setBCardDivNameIt(String cardDivNameIt) {
		bCardDivNameIt = cardDivNameIt;
	}
	public String getBCardAdminNameIt() {
		return bCardAdminNameIt;
	}
	public void setBCardAdminNameIt(String cardAdminNameIt) {
		bCardAdminNameIt = cardAdminNameIt;
	}
	public ArrayList getAdminBCardConfList() {
		return adminBCardConfList;
	}
	public void setAdminBCardConfList(ArrayList adminBCardConfList) {
		this.adminBCardConfList = adminBCardConfList;
	}
	/*public String getECardManagerFlag() {
		return eCardManagerFlag;
	}
	public void setECardManagerFlag(String cardManagerFlag) {
		eCardManagerFlag = cardManagerFlag;
	}*/
	public String getECardDivCode() {
		return eCardDivCode;
	}
	public void setECardDivCode(String cardDivCode) {
		eCardDivCode = cardDivCode;
	}
	public String getECardDivision() {
		return eCardDivision;
	}
	public void setECardDivision(String cardDivision) {
		eCardDivision = cardDivision;
	}
	public String getECardEmpToken() {
		return eCardEmpToken;
	}
	public void setECardEmpToken(String cardEmpToken) {
		eCardEmpToken = cardEmpToken;
	}
	public String getECardEmpName() {
		return eCardEmpName;
	}
	public void setECardEmpName(String cardEmpName) {
		eCardEmpName = cardEmpName;
	}
	public String getECardEmpId() {
		return eCardEmpId;
	}
	public void setECardEmpId(String cardEmpId) {
		eCardEmpId = cardEmpId;
	}
	public ArrayList getAdminECardConfList() {
		return adminECardConfList;
	}
	public void setAdminECardConfList(ArrayList adminECardConfList) {
		this.adminECardConfList = adminECardConfList;
	}
	public String getECardSrNoIt() {
		return eCardSrNoIt;
	}
	public void setECardSrNoIt(String cardSrNoIt) {
		eCardSrNoIt = cardSrNoIt;
	}
	public String getECardDivNameIt() {
		return eCardDivNameIt;
	}
	public void setECardDivNameIt(String cardDivNameIt) {
		eCardDivNameIt = cardDivNameIt;
	}
	public String getECardAdminNameIt() {
		return eCardAdminNameIt;
	}
	public void setECardAdminNameIt(String cardAdminNameIt) {
		eCardAdminNameIt = cardAdminNameIt;
	}
	public String getBCardVndrName() {
		return bCardVndrName;
	}
	public void setBCardVndrName(String cardVndrName) {
		bCardVndrName = cardVndrName;
	}
	public String getBCardVndrEmail() {
		return bCardVndrEmail;
	}
	public void setBCardVndrEmail(String cardVndrEmail) {
		bCardVndrEmail = cardVndrEmail;
	}
	public String getBCardVndrPhNum() {
		return bCardVndrPhNum;
	}
	public void setBCardVndrPhNum(String cardVndrPhNum) {
		bCardVndrPhNum = cardVndrPhNum;
	}
	public String getECardVndrName() {
		return eCardVndrName;
	}
	public void setECardVndrName(String cardVndrName) {
		eCardVndrName = cardVndrName;
	}
	public String getECardVndrEmail() {
		return eCardVndrEmail;
	}
	public void setECardVndrEmail(String cardVndrEmail) {
		eCardVndrEmail = cardVndrEmail;
	}
	public String getECardVndrPhNum() {
		return eCardVndrPhNum;
	}
	public void setECardVndrPhNum(String cardVndrPhNum) {
		eCardVndrPhNum = cardVndrPhNum;
	}
	public String getBCardVendorFlag() {
		return bCardVendorFlag;
	}
	public void setBCardVendorFlag(String cardVendorFlag) {
		bCardVendorFlag = cardVendorFlag;
	}
	public String getECardVendorFlag() {
		return eCardVendorFlag;
	}
	public void setECardVendorFlag(String cardVendorFlag) {
		eCardVendorFlag = cardVendorFlag;
	}
	public String getAcctFlag() {
		return acctFlag;
	}
	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}
	public String getDivCodeIt() {
		return divCodeIt;
	}
	public void setDivCodeIt(String divCodeIt) {
		this.divCodeIt = divCodeIt;
	}
	public String getAdminCodeIt() {
		return adminCodeIt;
	}
	public void setAdminCodeIt(String adminCodeIt) {
		this.adminCodeIt = adminCodeIt;
	}
	public String getAccDivCodeIt() {
		return accDivCodeIt;
	}
	public void setAccDivCodeIt(String accDivCodeIt) {
		this.accDivCodeIt = accDivCodeIt;
	}
	public String getAccAdminCodeIt() {
		return accAdminCodeIt;
	}
	public void setAccAdminCodeIt(String accAdminCodeIt) {
		this.accAdminCodeIt = accAdminCodeIt;
	}
	public String getBCardConfIdIt() {
		return bCardConfIdIt;
	}
	public void setBCardConfIdIt(String cardConfIdIt) {
		bCardConfIdIt = cardConfIdIt;
	}
	public String getECardConfIdIt() {
		return eCardConfIdIt;
	}
	public void setECardConfIdIt(String cardConfIdIt) {
		eCardConfIdIt = cardConfIdIt;
	}
	public String getAccConfIdIt() {
		return accConfIdIt;
	}
	public void setAccConfIdIt(String accConfIdIt) {
		this.accConfIdIt = accConfIdIt;
	}
	public String getAdminConfIdIt() {
		return adminConfIdIt;
	}
	public void setAdminConfIdIt(String adminConfIdIt) {
		this.adminConfIdIt = adminConfIdIt;
	}
	public ArrayList getECardVendorConfList() {
		return ECardVendorConfList;
	}
	public void setECardVendorConfList(ArrayList cardVendorConfList) {
		ECardVendorConfList = cardVendorConfList;
	}
	public String getECardVndrNameIt() {
		return eCardVndrNameIt;
	}
	public void setECardVndrNameIt(String cardVndrNameIt) {
		eCardVndrNameIt = cardVndrNameIt;
	}
	public String getECardVndrEmailIt() {
		return eCardVndrEmailIt;
	}
	public void setECardVndrEmailIt(String cardVndrEmailIt) {
		eCardVndrEmailIt = cardVndrEmailIt;
	}
	public String getECardVndrPhNumIt() {
		return eCardVndrPhNumIt;
	}
	public void setECardVndrPhNumIt(String cardVndrPhNumIt) {
		eCardVndrPhNumIt = cardVndrPhNumIt;
	}
	public String getBCardVndrNameIt() {
		return bCardVndrNameIt;
	}
	public void setBCardVndrNameIt(String cardVndrNameIt) {
		bCardVndrNameIt = cardVndrNameIt;
	}
	public String getBCardVndrEmailIt() {
		return bCardVndrEmailIt;
	}
	public void setBCardVndrEmailIt(String cardVndrEmailIt) {
		bCardVndrEmailIt = cardVndrEmailIt;
	}
	public String getBCardVndrPhNumIt() {
		return bCardVndrPhNumIt;
	}
	public void setBCardVndrPhNumIt(String cardVndrPhNumIt) {
		bCardVndrPhNumIt = cardVndrPhNumIt;
	}
}
