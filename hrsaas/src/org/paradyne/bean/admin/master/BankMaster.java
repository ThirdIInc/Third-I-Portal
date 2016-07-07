/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 * @modified by Lakkichand
 * 
 */
public class BankMaster extends BeanBase implements Serializable {

	/**
	 * @param bankMicrCode
	 * @param bankName
	 * @param branchName
	 * @param branchAddress
	 * @param branchCity
	 * @param bankList
	 */

	String bankMicrCode = "";
	String bankName = "";
	String branchName = "";
	String branchAddress = "";
	String branchCity = "";
	String branchCode = "";
	String bsrCode = "";
	String isActive = "";
	String templateCode = "";
	String templateName = "";

	private String myPage;
	private String hiddencode;
	private String show;
	private String hiddenBankMicrCode;
	private String modeLength = "false";
	private String totalRecords = "";
	ArrayList iteratorlist;
	private String hdeleteCode;

	// added by vishwambhar

	private String ifscCode = "";

	// added by Ganesh
	boolean manageBranchFlag = false;
	boolean bankNameFlag = false;
	private String bankId = "";
	private String bankType = "";
	private String ittbankId = "";
	private String ittbranchName = "";
	private String ittbankMicrCode = "";
	private String ittbsrCode = "";
	private String ittifscCode = "";
	private String ittbranchCity = "";
	private String ittbranchAddress = "";
	private String ittbranchCode = "";
	ArrayList branchesList = null;
	private String myPageInProcess = "";
	private String hiddenBankId = "";
	String paracode = "";
	String flag = "false";
	boolean insertBranchFlag = false;
	boolean updateFlag = false;
	boolean editDeleteFlag = false;

	private String statementIfscCode = "";
	private String statementCrDr = "";
	private String statementAccount = "";
	private String statementAmount = "";
	private String statementEmpCode = "";
	private String statementName = "";
	private String statementNarration = "";
	private String statementSolId = "";
	private String statementTransDesc = "";
	private String statementTransPart = "";
	private String statementTitle = "";
	private String statementCurrency = "";
	private String statementDrAccount = "";

	public String getStatementIfscCode() {
		return statementIfscCode;
	}

	public void setStatementIfscCode(String statementIfscCode) {
		this.statementIfscCode = statementIfscCode;
	}

	public String getStatementCrDr() {
		return statementCrDr;
	}

	public void setStatementCrDr(String statementCrDr) {
		this.statementCrDr = statementCrDr;
	}

	public String getStatementAccount() {
		return statementAccount;
	}

	public void setStatementAccount(String statementAccount) {
		this.statementAccount = statementAccount;
	}

	public String getStatementAmount() {
		return statementAmount;
	}

	public void setStatementAmount(String statementAmount) {
		this.statementAmount = statementAmount;
	}

	public String getStatementEmpCode() {
		return statementEmpCode;
	}

	public void setStatementEmpCode(String statementEmpCode) {
		this.statementEmpCode = statementEmpCode;
	}

	public String getStatementName() {
		return statementName;
	}

	public void setStatementName(String statementName) {
		this.statementName = statementName;
	}

	public String getStatementNarration() {
		return statementNarration;
	}

	public void setStatementNarration(String statementNarration) {
		this.statementNarration = statementNarration;
	}

	public String getStatementSolId() {
		return statementSolId;
	}

	public void setStatementSolId(String statementSolId) {
		this.statementSolId = statementSolId;
	}

	public String getStatementTransDesc() {
		return statementTransDesc;
	}

	public void setStatementTransDesc(String statementTransDesc) {
		this.statementTransDesc = statementTransDesc;
	}

	public String getStatementTransPart() {
		return statementTransPart;
	}

	public void setStatementTransPart(String statementTransPart) {
		this.statementTransPart = statementTransPart;
	}

	public String getStatementTitle() {
		return statementTitle;
	}

	public void setStatementTitle(String statementTitle) {
		this.statementTitle = statementTitle;
	}

	public String getStatementCurrency() {
		return statementCurrency;
	}

	public void setStatementCurrency(String statementCurrency) {
		this.statementCurrency = statementCurrency;
	}

	public boolean isEditDeleteFlag() {
		return editDeleteFlag;
	}

	public void setEditDeleteFlag(boolean editDeleteFlag) {
		this.editDeleteFlag = editDeleteFlag;
	}

	public boolean isInsertBranchFlag() {
		return insertBranchFlag;
	}

	public void setInsertBranchFlag(boolean insertBranchFlag) {
		this.insertBranchFlag = insertBranchFlag;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHiddenBankId() {
		return hiddenBankId;
	}

	public void setHiddenBankId(String hiddenBankId) {
		this.hiddenBankId = hiddenBankId;
	}

	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	public String getIttbranchName() {
		return ittbranchName;
	}

	public void setIttbranchName(String ittbranchName) {
		this.ittbranchName = ittbranchName;
	}

	public String getIttbankMicrCode() {
		return ittbankMicrCode;
	}

	public void setIttbankMicrCode(String ittbankMicrCode) {
		this.ittbankMicrCode = ittbankMicrCode;
	}

	public String getIttbsrCode() {
		return ittbsrCode;
	}

	public void setIttbsrCode(String ittbsrCode) {
		this.ittbsrCode = ittbsrCode;
	}

	public String getIttifscCode() {
		return ittifscCode;
	}

	public void setIttifscCode(String ittifscCode) {
		this.ittifscCode = ittifscCode;
	}

	public String getIttbranchCity() {
		return ittbranchCity;
	}

	public void setIttbranchCity(String ittbranchCity) {
		this.ittbranchCity = ittbranchCity;
	}

	public String getIttbranchAddress() {
		return ittbranchAddress;
	}

	public void setIttbranchAddress(String ittbranchAddress) {
		this.ittbranchAddress = ittbranchAddress;
	}

	public String getIttbranchCode() {
		return ittbranchCode;
	}

	public void setIttbranchCode(String ittbranchCode) {
		this.ittbranchCode = ittbranchCode;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public boolean isManageBranchFlag() {
		return manageBranchFlag;
	}

	public void setManageBranchFlag(boolean manageBranchFlag) {
		this.manageBranchFlag = manageBranchFlag;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

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

	private ArrayList bankList = null;

	/*
	 * public BankMaster(){
	 *  }
	 * 
	 * public BankMaster(String bankMicrCode, String bankName, String
	 * branchName, String branchAddress, String branchCity) {
	 * 
	 * this.bankMicrCode = bankMicrCode; this.bankName = bankName;
	 * this.branchName = branchName; this.branchAddress = branchAddress;
	 * this.branchCity = branchCity; }
	 */
	/**
	 * @return the bankMicrCode
	 */
	public String getBankMicrCode() {
		return bankMicrCode;
	}

	/**
	 * @param bankMicrCode
	 *            the bankMicrCode to set
	 */
	public void setBankMicrCode(String bankMicrCode) {
		this.bankMicrCode = bankMicrCode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress
	 *            the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	/**
	 * @return the branchCity
	 */
	public String getBranchCity() {
		return branchCity;
	}

	/**
	 * @param branchCity
	 *            the branchCity to set
	 */
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName
	 *            the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the bankList
	 */
	public ArrayList getBankList() {
		return bankList;
	}

	/**
	 * @param bankList
	 *            the bankList to set
	 */
	public void setBankList(ArrayList bankList) {
		this.bankList = bankList;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode
	 *            the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getHiddenBankMicrCode() {
		return hiddenBankMicrCode;
	}

	public void setHiddenBankMicrCode(String hiddenBankMicrCode) {
		this.hiddenBankMicrCode = hiddenBankMicrCode;
	}

	public String getBsrCode() {
		return bsrCode;
	}

	public void setBsrCode(String bsrCode) {
		this.bsrCode = bsrCode;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public boolean isBankNameFlag() {
		return bankNameFlag;
	}

	public void setBankNameFlag(boolean bankNameFlag) {
		this.bankNameFlag = bankNameFlag;
	}

	public ArrayList getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(ArrayList branchesList) {
		this.branchesList = branchesList;
	}

	public String getIttbankId() {
		return ittbankId;
	}

	public void setIttbankId(String ittbankId) {
		this.ittbankId = ittbankId;
	}

	public String getParacode() {
		return paracode;
	}

	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getStatementDrAccount() {
		return statementDrAccount;
	}

	public void setStatementDrAccount(String statementDrAccount) {
		this.statementDrAccount = statementDrAccount;
	}

}