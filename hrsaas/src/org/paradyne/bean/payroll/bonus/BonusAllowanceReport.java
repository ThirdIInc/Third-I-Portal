package org.paradyne.bean.payroll.bonus;

import org.paradyne.lib.BeanBase;

/**Created on 30st Jan. 2012
 * @author AA1380
 */
public class BonusAllowanceReport extends BeanBase {
	/** fromMonth. * */
	private String reportType = "";
	/** fromMonth. * */
	private String fromMonth = "";
	/** fromYear. * */
	private String fromYear = "";
	/** toMonth. * */
	private String toMonth = "";
	/** toYear. * */
	private String toYear = "";
	/** bonusAllowancePeriod. * */
	private String bonusAllowancePeriod = "";
	/** bonusAllowanceID. * */
	private String bonusAllowanceID = "";
	/** bonusAllowanceStatus. * */
	private String bonusAllowanceStatus = "";
	/** divisionReportCheckBox. * */
	private String divisionReportCheckBox = "";
	/** branchReportCheckBox. * */
	private String branchReportCheckBox = "";
	/** departmentReportCheckBox. * */
	private String departmentReportCheckBox = "";
	/** applicableCreditReportCheckBox. * */
	private String applicableCreditReportCheckBox = "";
	/** gradeReportCheckBox. * */
	private String gradeReportCheckBox = "";
	/** accountNumberReportCheckBox. * */
	private String accountNumberReportCheckBox = "";
	/** bankReportCheckBox. * */
	private String bankReportCheckBox = "";
	/** individualRatingReportCheckBox. * */
	private String individualRatingReportCheckBox = "";
	/** fromBonusAllowanceFlag. * */
	private boolean fromBonusAllowanceFlag;
	/**
	 * @return the reportType
	 */
	public final String getReportType() {
		return this.reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public final void setReportType(final String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the fromMonth
	 */
	public final String getFromMonth() {
		return this.fromMonth;
	}
	/**
	 * @param fromMonth the fromMonth to set
	 */
	public final void setFromMonth(final String fromMonth) {
		this.fromMonth = fromMonth;
	}
	/**
	 * @return the fromYear
	 */
	public final String getFromYear() {
		return this.fromYear;
	}
	/**
	 * @param fromYear the fromYear to set
	 */
	public final void setFromYear(final String fromYear) {
		this.fromYear = fromYear;
	}
	/**
	 * @return the toMonth
	 */
	public final String getToMonth() {
		return this.toMonth;
	}
	/**
	 * @param toMonth the toMonth to set
	 */
	public final void setToMonth(final String toMonth) {
		this.toMonth = toMonth;
	}
	/**
	 * @return the toYear
	 */
	public final String getToYear() {
		return this.toYear;
	}
	/**
	 * @param toYear the toYear to set
	 */
	public final void setToYear(final String toYear) {
		this.toYear = toYear;
	}
	/**
	 * @return the bonusAllowancePeriod
	 */
	public final String getBonusAllowancePeriod() {
		return this.bonusAllowancePeriod;
	}
	/**
	 * @param bonusAllowancePeriod the bonusAllowancePeriod to set
	 */
	public final void setBonusAllowancePeriod(final String bonusAllowancePeriod) {
		this.bonusAllowancePeriod = bonusAllowancePeriod;
	}
	/**
	 * @return the bonusAllowanceID
	 */
	public final String getBonusAllowanceID() {
		return this.bonusAllowanceID;
	}
	/**
	 * @param bonusAllowanceID the bonusAllowanceID to set
	 */
	public final void setBonusAllowanceID(final String bonusAllowanceID) {
		this.bonusAllowanceID = bonusAllowanceID;
	}
	/**
	 * @return the bonusAllowanceStatus
	 */
	public final String getBonusAllowanceStatus() {
		return this.bonusAllowanceStatus;
	}
	/**
	 * @param bonusAllowanceStatus the bonusAllowanceStatus to set
	 */
	public final void setBonusAllowanceStatus(final String bonusAllowanceStatus) {
		this.bonusAllowanceStatus = bonusAllowanceStatus;
	}
	/**
	 * @return the divisionReportCheckBox
	 */
	public final String getDivisionReportCheckBox() {
		return this.divisionReportCheckBox;
	}
	/**
	 * @param divisionReportCheckBox the divisionReportCheckBox to set
	 */
	public final void setDivisionReportCheckBox(final String divisionReportCheckBox) {
		this.divisionReportCheckBox = divisionReportCheckBox;
	}
	/**
	 * @return the branchReportCheckBox
	 */
	public final String getBranchReportCheckBox() {
		return this.branchReportCheckBox;
	}
	/**
	 * @param branchReportCheckBox the branchReportCheckBox to set
	 */
	public final void setBranchReportCheckBox(final String branchReportCheckBox) {
		this.branchReportCheckBox = branchReportCheckBox;
	}
	/**
	 * @return the departmentReportCheckBox
	 */
	public final String getDepartmentReportCheckBox() {
		return this.departmentReportCheckBox;
	}
	/**
	 * @param departmentReportCheckBox the departmentReportCheckBox to set
	 */
	public final void setDepartmentReportCheckBox(final String departmentReportCheckBox) {
		this.departmentReportCheckBox = departmentReportCheckBox;
	}
	/**
	 * @return the applicableCreditReportCheckBox
	 */
	public final String getApplicableCreditReportCheckBox() {
		return this.applicableCreditReportCheckBox;
	}
	/**
	 * @param applicableCreditReportCheckBox the applicableCreditReportCheckBox to set
	 */
	public final void setApplicableCreditReportCheckBox(
			final String applicableCreditReportCheckBox) {
		this.applicableCreditReportCheckBox = applicableCreditReportCheckBox;
	}
	/**
	 * @return the gradeReportCheckBox
	 */
	public final String getGradeReportCheckBox() {
		return this.gradeReportCheckBox;
	}
	/**
	 * @param gradeReportCheckBox the gradeReportCheckBox to set
	 */
	public final void setGradeReportCheckBox(final String gradeReportCheckBox) {
		this.gradeReportCheckBox = gradeReportCheckBox;
	}
	/**
	 * @return the accountNumberReportCheckBox
	 */
	public final String getAccountNumberReportCheckBox() {
		return this.accountNumberReportCheckBox;
	}
	/**
	 * @param accountNumberReportCheckBox the accountNumberReportCheckBox to set
	 */
	public final void setAccountNumberReportCheckBox(
			final String accountNumberReportCheckBox) {
		this.accountNumberReportCheckBox = accountNumberReportCheckBox;
	}
	/**
	 * @return the bankReportCheckBox
	 */
	public final String getBankReportCheckBox() {
		return this.bankReportCheckBox;
	}
	/**
	 * @param bankReportCheckBox the bankReportCheckBox to set
	 */
	public final void setBankReportCheckBox(final String bankReportCheckBox) {
		this.bankReportCheckBox = bankReportCheckBox;
	}
	/**
	 * @return the individualRatingReportCheckBox
	 */
	public final String getIndividualRatingReportCheckBox() {
		return this.individualRatingReportCheckBox;
	}
	/**
	 * @param individualRatingReportCheckBox the individualRatingReportCheckBox to set
	 */
	public final void setIndividualRatingReportCheckBox(
			final String individualRatingReportCheckBox) {
		this.individualRatingReportCheckBox = individualRatingReportCheckBox;
	}
	/**
	 * @return the fromBonusAllowanceFlag
	 */
	public final boolean isFromBonusAllowanceFlag() {
		return this.fromBonusAllowanceFlag;
	}
	/**
	 * @param fromBonusAllowanceFlag the fromBonusAllowanceFlag to set
	 */
	public final void setFromBonusAllowanceFlag(final boolean fromBonusAllowanceFlag) {
		this.fromBonusAllowanceFlag = fromBonusAllowanceFlag;
	}
	
 
}
