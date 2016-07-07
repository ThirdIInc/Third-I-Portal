package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class SalaryStatementBank extends BeanBase {
	private String divisionName="";
	private String divisionCode="";
	private String earningType="";
	private String earningName="";
	private String earningCode="";
	private String earningMonth="";
	private String earningYear="";
	private String fromYearning="";
	private String toYearning="";
	private String totalRecords="";
	private String totalAmount="";
	private String hiddenMonth="";
	private String earningTypeDisplay = "";
	private ArrayList bankwiseList =null ;
	private String bankCodeItt = "";
	private String bankNameItt = "";
	private String totalEmpItt = "";
	private String totalEmpAmountItt = "";
	private String chequeNoItt = "";
	private String chequeDateItt = "";
	private String linkSource = "";
	private boolean fromBonusAllowanceFlag = false;
	private String bonusAllowanceStatus = "";
	private String bankTemplateCodeItt = "";
	private String bankTemplateNameItt = "";
	
	public String getBonusAllowanceStatus() {
		return bonusAllowanceStatus;
	}
	public void setBonusAllowanceStatus(String bonusAllowanceStatus) {
		this.bonusAllowanceStatus = bonusAllowanceStatus;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getEarningType() {
		return earningType;
	}
	public void setEarningType(String earningType) {
		this.earningType = earningType;
	}
	public String getEarningName() {
		return earningName;
	}
	public void setEarningName(String earningName) {
		this.earningName = earningName;
	}
	public String getEarningCode() {
		return earningCode;
	}
	public void setEarningCode(String earningCode) {
		this.earningCode = earningCode;
	}
	public String getEarningMonth() {
		return earningMonth;
	}
	public void setEarningMonth(String earningMonth) {
		this.earningMonth = earningMonth;
	}
	public String getEarningYear() {
		return earningYear;
	}
	public void setEarningYear(String earningYear) {
		this.earningYear = earningYear;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public ArrayList getBankwiseList() {
		return bankwiseList;
	}
	public void setBankwiseList(ArrayList bankwiseList) {
		this.bankwiseList = bankwiseList;
	}
	public String getBankCodeItt() {
		return bankCodeItt;
	}
	public void setBankCodeItt(String bankCodeItt) {
		this.bankCodeItt = bankCodeItt;
	}
	public String getBankNameItt() {
		return bankNameItt;
	}
	public void setBankNameItt(String bankNameItt) {
		this.bankNameItt = bankNameItt;
	}
	public String getTotalEmpItt() {
		return totalEmpItt;
	}
	public void setTotalEmpItt(String totalEmpItt) {
		this.totalEmpItt = totalEmpItt;
	}
	public String getTotalEmpAmountItt() {
		return totalEmpAmountItt;
	}
	public void setTotalEmpAmountItt(String totalEmpAmountItt) {
		this.totalEmpAmountItt = totalEmpAmountItt;
	}
	public String getChequeNoItt() {
		return chequeNoItt;
	}
	public void setChequeNoItt(String chequeNoItt) {
		this.chequeNoItt = chequeNoItt;
	}
	public String getChequeDateItt() {
		return chequeDateItt;
	}
	public void setChequeDateItt(String chequeDateItt) {
		this.chequeDateItt = chequeDateItt;
	}
	public String getHiddenMonth() {
		return hiddenMonth;
	}
	public void setHiddenMonth(String hiddenMonth) {
		this.hiddenMonth = hiddenMonth;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getLinkSource() {
		return linkSource;
	}
	public void setLinkSource(String linkSource) {
		this.linkSource = linkSource;
	}
	public String getFromYearning() {
		return fromYearning;
	}
	public void setFromYearning(String fromYearning) {
		this.fromYearning = fromYearning;
	}
	public String getToYearning() {
		return toYearning;
	}
	public void setToYearning(String toYearning) {
		this.toYearning = toYearning;
	}
	public boolean isFromBonusAllowanceFlag() {
		return fromBonusAllowanceFlag;
	}
	public void setFromBonusAllowanceFlag(boolean fromBonusAllowanceFlag) {
		this.fromBonusAllowanceFlag = fromBonusAllowanceFlag;
	}
	public String getEarningTypeDisplay() {
		return earningTypeDisplay;
	}
	public void setEarningTypeDisplay(String earningTypeDisplay) {
		this.earningTypeDisplay = earningTypeDisplay;
	}
	public String getBankTemplateCodeItt() {
		return bankTemplateCodeItt;
	}
	public void setBankTemplateCodeItt(String bankTemplateCodeItt) {
		this.bankTemplateCodeItt = bankTemplateCodeItt;
	}
	public String getBankTemplateNameItt() {
		return bankTemplateNameItt;
	}
	public void setBankTemplateNameItt(String bankTemplateNameItt) {
		this.bankTemplateNameItt = bankTemplateNameItt;
	}
}
