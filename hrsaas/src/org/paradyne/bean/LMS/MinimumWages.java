package org.paradyne.bean.LMS;

import java.util.ArrayList;

/**
 * @author REEBA JOSEPH
 */

public class MinimumWages {
	private String employeeCode;			//Employee code
	private String employeeName;			//Employee name (full name as displayed on the pay slip)
	private String dateOfBirth;				//date of birth
	private String gender;					//gender
	private String dateOfJoining;			//joining date of employee
	private String designation;				//designation
	private String totalWorkingDays;		//total number of working days in the month
	private String minimumWagesApplicable;	//minimum wages applicable
	private String actualWagesPaid;			//actual wages paid
	private String pieceRateWagesPaid;		//piece rate wages paid
	private String overTimePayRate;			//overtime pay rate 
	private String overTimeEarnings;		//overtime earnings
	private String allowanceHead;			//allowance head name
	private String allowanceAmount;			//allowance amount paid
	private String totalAllowances;			//total allowances
	private String grossEarnings;			//gross earnings
	private String totalDeductions;			//total deductions
	private String advances;				//advances
	private String pf;						//PF amount paid
	private String esi;						//ESI amount paid
	private String pt;						//PT amount paid
	private String it;						//IT amount paid
	private String lwb;						//labour welfare board
	private String otherDeductions;			//amount of other deductions
	private String netEarnings;				//amount of net earnings
	private String amountDepositedInBank;	//amount deposited in bank
	private String employersBankName;		//name of employer's bank
	private String chequeNo;				//cheque number
	private String leaveOpeningBalance;		//leave opening balance
	private String leavesTaken;				//total number of leaves taken in the reporting period
	private String leaveClosingBalance;		//leave closing balance
	
	private long minWagesId;				//primary key
	private long returnId;					//references tbl_LC_AR(ReturnID)
	private long shopId;					//return filed by a shop
	private long factoryId;					//return filed by factory
	private long bcwRegistrationId;			//return filed by construction establishment/construction contractor
	private long mtRegistrationId;			//return filed by motor transport establishment
	private long contractorId;				//return filed by contractor
	private long ismRegistrationId;			//return filed by ISM principal employer employing more than 5 ISM workers
	private long ismContractorId;			//return filed by ISM contractor employing more than 5 ISM workmen
	
	ArrayList<Object> minWagesRecord;
	private String registrationNo;
	private String overTimeHrsWorked;
	
	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getOverTimeHrsWorked() {
		return overTimeHrsWorked;
	}

	public void setOverTimeHrsWorked(String overTimeHrsWorked) {
		this.overTimeHrsWorked = overTimeHrsWorked;
	}

	/**
	 * @return the minWagesRecord
	 */
	public ArrayList<Object> getMinWagesRecord() {
		return minWagesRecord;
	}

	/**
	 * @param minWagesRecord the minWagesRecord to set
	 */
	public void setMinWagesRecord(ArrayList<Object> minWagesRecord) {
		this.minWagesRecord = minWagesRecord;
	}
	
	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the dateOfJoining
	 */
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the totalWorkingDays
	 */
	public String getTotalWorkingDays() {
		return totalWorkingDays;
	}
	/**
	 * @param totalWorkingDays the totalWorkingDays to set
	 */
	public void setTotalWorkingDays(String totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	/**
	 * @return the minimumWagesApplicable
	 */
	public String getMinimumWagesApplicable() {
		return minimumWagesApplicable;
	}
	/**
	 * @param minimumWagesApplicable the minimumWagesApplicable to set
	 */
	public void setMinimumWagesApplicable(String minimumWagesApplicable) {
		this.minimumWagesApplicable = minimumWagesApplicable;
	}
	/**
	 * @return the actualWagesPaid
	 */
	public String getActualWagesPaid() {
		return actualWagesPaid;
	}
	/**
	 * @param actualWagesPaid the actualWagesPaid to set
	 */
	public void setActualWagesPaid(String actualWagesPaid) {
		this.actualWagesPaid = actualWagesPaid;
	}
	/**
	 * @return the pieceRateWagesPaid
	 */
	public String getPieceRateWagesPaid() {
		return pieceRateWagesPaid;
	}
	/**
	 * @param pieceRateWagesPaid the pieceRateWagesPaid to set
	 */
	public void setPieceRateWagesPaid(String pieceRateWagesPaid) {
		this.pieceRateWagesPaid = pieceRateWagesPaid;
	}
	/**
	 * @return the overTimePayRate
	 */
	public String getOverTimePayRate() {
		return overTimePayRate;
	}
	/**
	 * @param overTimePayRate the overTimePayRate to set
	 */
	public void setOverTimePayRate(String overTimePayRate) {
		this.overTimePayRate = overTimePayRate;
	}
	/**
	 * @return the overTimeEarnings
	 */
	public String getOverTimeEarnings() {
		return overTimeEarnings;
	}
	/**
	 * @param overTimeEarnings the overTimeEarnings to set
	 */
	public void setOverTimeEarnings(String overTimeEarnings) {
		this.overTimeEarnings = overTimeEarnings;
	}
	/**
	 * @return the allowanceHead
	 */
	public String getAllowanceHead() {
		return allowanceHead;
	}
	/**
	 * @param allowanceHead the allowanceHead to set
	 */
	public void setAllowanceHead(String allowanceHead) {
		this.allowanceHead = allowanceHead;
	}
	/**
	 * @return the allowanceAmount
	 */
	public String getAllowanceAmount() {
		return allowanceAmount;
	}
	/**
	 * @param allowanceAmount the allowanceAmount to set
	 */
	public void setAllowanceAmount(String allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}
	/**
	 * @return the totalAllowances
	 */
	public String getTotalAllowances() {
		return totalAllowances;
	}
	/**
	 * @param totalAllowances the totalAllowances to set
	 */
	public void setTotalAllowances(String totalAllowances) {
		this.totalAllowances = totalAllowances;
	}
	/**
	 * @return the grossEarnings
	 */
	public String getGrossEarnings() {
		return grossEarnings;
	}
	/**
	 * @param grossEarnings the grossEarnings to set
	 */
	public void setGrossEarnings(String grossEarnings) {
		this.grossEarnings = grossEarnings;
	}
	/**
	 * @return the totalDeductions
	 */
	public String getTotalDeductions() {
		return totalDeductions;
	}
	/**
	 * @param totalDeductions the totalDeductions to set
	 */
	public void setTotalDeductions(String totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	/**
	 * @return the advances
	 */
	public String getAdvances() {
		return advances;
	}
	/**
	 * @param advances the advances to set
	 */
	public void setAdvances(String advances) {
		this.advances = advances;
	}
	/**
	 * @return the pf
	 */
	public String getPf() {
		return pf;
	}
	/**
	 * @param pf the pf to set
	 */
	public void setPf(String pf) {
		this.pf = pf;
	}
	/**
	 * @return the esi
	 */
	public String getEsi() {
		return esi;
	}
	/**
	 * @param esi the esi to set
	 */
	public void setEsi(String esi) {
		this.esi = esi;
	}
	/**
	 * @return the pt
	 */
	public String getPt() {
		return pt;
	}
	/**
	 * @param pt the pt to set
	 */
	public void setPt(String pt) {
		this.pt = pt;
	}
	/**
	 * @return the it
	 */
	public String getIt() {
		return it;
	}
	/**
	 * @param it the it to set
	 */
	public void setIt(String it) {
		this.it = it;
	}
	/**
	 * @return the lwb
	 */
	public String getLwb() {
		return lwb;
	}
	/**
	 * @param lwb the lwb to set
	 */
	public void setLwb(String lwb) {
		this.lwb = lwb;
	}
	/**
	 * @return the otherDeductions
	 */
	public String getOtherDeductions() {
		return otherDeductions;
	}
	/**
	 * @param otherDeductions the otherDeductions to set
	 */
	public void setOtherDeductions(String otherDeductions) {
		this.otherDeductions = otherDeductions;
	}
	/**
	 * @return the netEarnings
	 */
	public String getNetEarnings() {
		return netEarnings;
	}
	/**
	 * @param netEarnings the netEarnings to set
	 */
	public void setNetEarnings(String netEarnings) {
		this.netEarnings = netEarnings;
	}
	/**
	 * @return the amountDepositedInBank
	 */
	public String getAmountDepositedInBank() {
		return amountDepositedInBank;
	}
	/**
	 * @param amountDepositedInBank the amountDepositedInBank to set
	 */
	public void setAmountDepositedInBank(String amountDepositedInBank) {
		this.amountDepositedInBank = amountDepositedInBank;
	}
	/**
	 * @return the employersBankName
	 */
	public String getEmployersBankName() {
		return employersBankName;
	}
	/**
	 * @param employersBankName the employersBankName to set
	 */
	public void setEmployersBankName(String employersBankName) {
		this.employersBankName = employersBankName;
	}
	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return chequeNo;
	}
	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	/**
	 * @return the leaveOpeningBalance
	 */
	public String getLeaveOpeningBalance() {
		return leaveOpeningBalance;
	}
	/**
	 * @param leaveOpeningBalance the leaveOpeningBalance to set
	 */
	public void setLeaveOpeningBalance(String leaveOpeningBalance) {
		this.leaveOpeningBalance = leaveOpeningBalance;
	}
	/**
	 * @return the leavesTaken
	 */
	public String getLeavesTaken() {
		return leavesTaken;
	}
	/**
	 * @param leavesTaken the leavesTaken to set
	 */
	public void setLeavesTaken(String leavesTaken) {
		this.leavesTaken = leavesTaken;
	}
	/**
	 * @return the leaveClosingBalance
	 */
	public String getLeaveClosingBalance() {
		return leaveClosingBalance;
	}
	/**
	 * @param leaveClosingBalance the leaveClosingBalance to set
	 */
	public void setLeaveClosingBalance(String leaveClosingBalance) {
		this.leaveClosingBalance = leaveClosingBalance;
	}
	/**
	 * @return the minWagesId
	 */
	public long getMinWagesId() {
		return minWagesId;
	}
	/**
	 * @param minWagesId the minWagesId to set
	 */
	public void setMinWagesId(long minWagesId) {
		this.minWagesId = minWagesId;
	}
	/**
	 * @return the returnId
	 */
	public long getReturnId() {
		return returnId;
	}
	/**
	 * @param returnId the returnId to set
	 */
	public void setReturnId(long returnId) {
		this.returnId = returnId;
	}
	/**
	 * @return the shopId
	 */
	public long getShopId() {
		return shopId;
	}
	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	/**
	 * @return the factoryId
	 */
	public long getFactoryId() {
		return factoryId;
	}
	/**
	 * @param factoryId the factoryId to set
	 */
	public void setFactoryId(long factoryId) {
		this.factoryId = factoryId;
	}
	/**
	 * @return the bcwRegistrationId
	 */
	public long getBcwRegistrationId() {
		return bcwRegistrationId;
	}
	/**
	 * @param bcwRegistrationId the bcwRegistrationId to set
	 */
	public void setBcwRegistrationId(long bcwRegistrationId) {
		this.bcwRegistrationId = bcwRegistrationId;
	}
	/**
	 * @return the mtRegistrationId
	 */
	public long getMtRegistrationId() {
		return mtRegistrationId;
	}
	/**
	 * @param mtRegistrationId the mtRegistrationId to set
	 */
	public void setMtRegistrationId(long mtRegistrationId) {
		this.mtRegistrationId = mtRegistrationId;
	}
	/**
	 * @return the contractorId
	 */
	public long getContractorId() {
		return contractorId;
	}
	/**
	 * @param contractorId the contractorId to set
	 */
	public void setContractorId(long contractorId) {
		this.contractorId = contractorId;
	}
	/**
	 * @return the ismRegistrationId
	 */
	public long getIsmRegistrationId() {
		return ismRegistrationId;
	}
	/**
	 * @param ismRegistrationId the ismRegistrationId to set
	 */
	public void setIsmRegistrationId(long ismRegistrationId) {
		this.ismRegistrationId = ismRegistrationId;
	}
	/**
	 * @return the ismContractorId
	 */
	public long getIsmContractorId() {
		return ismContractorId;
	}
	/**
	 * @param ismContractorId the ismContractorId to set
	 */
	public void setIsmContractorId(long ismContractorId) {
		this.ismContractorId = ismContractorId;
	}
	
	
	
	
}
