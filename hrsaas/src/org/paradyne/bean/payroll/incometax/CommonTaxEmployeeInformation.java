/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0517
 *
 */
public class CommonTaxEmployeeInformation extends BeanBase {
	
	//private Object[][] employeePerquisite = null;
	//private Object[][] employeePeriodicAllowData = null;
	//private Object[][] employeeCreditMaster = null;
	private Object[][] employeePaidSalary = null;
	//private Object[][] arrearsAprToDec = null;
	//private Object[][] arrearsJanToMar = null;
	//private Object[][] rentData = null;
	//private Object[][] empInvestments = null;
	//private Object[][] settlementCreditData = null;
	//private Object[][] empLeaveEncash = null;
	
	
	private HashMap mainMap = null;
	private HashMap employeePerquisiteMap = null;
	private HashMap employeePerquisiteInvestmentMap = null;
	
	private HashMap dynamicInvestmentMap = null;
	private HashMap dynamicIncomeMap = null;
	private HashMap previousInvestmentMap = null;
	private HashMap dynamicTaxPaidMap = null;
	private HashMap employeeCreditMasterMap = null;
	private HashMap arrearAprToDecMap = null;
	private HashMap arrearJanToMarMap = null;
	private HashMap rentDataMap = null;
	private HashMap empInvestmentsMap = null;
	private HashMap extraWorkBenefitDataMap = null;
	private HashMap settlementCreditDataMap = null;
	private HashMap settlementDebitDataMap = null;
	private HashMap empLeaveEncashMap = null;
	private HashMap employeeDebitPtaxCodeMap = null;
	private HashMap previousMonthArrearMap = null;
	private HashMap salDataAprToDecMap = null;
	private HashMap salDataJanToMarMap = null;
	private HashMap salDebitAprToDecMap = null;
	private HashMap salDebitJanToMarMap = null;
	private HashMap periodicAllowanceMap = null;
	private HashMap debitInvestmentsMap = null;
	private HashMap arrearDebitAprToDecMap = null;
	private HashMap arrearDebitJanToMar= null;
	private HashMap previousMonthDebitArrearMap = null;
	private HashMap periodicAllowanceTaxDataMap = null;
	private HashMap employeeLeaveEncashAmountMap = null;
	private HashMap employeeMonthCountMap = null;
	private HashMap employeePtaxVariableData = null;
	private HashMap employeeOfficialMonthCount = null;
	private HashMap employeeCreditMasterForLeaveMap = null;
	private HashMap employeeCreditMasterForReimbursement = null;
	private HashMap employeeReimbursementMap = null;
	private HashMap employeeDivisionMap = null;
	private HashMap empCreditTotalMap=null;
	private HashMap empIncomeMap=null;
	private HashMap empPtaxAmtMap=null;
	private HashMap employeeDonationMap=null;
	private HashMap employeeLoanPerq=null;
	private HashMap empMiscTaxPaidMap=null;
	
	//////////////////For HRA Calculation//////////////////////////////////////
	private HashMap creditMasterForHRAMap = null;
	private HashMap salDataAprToDecForHRAMap = null;
	private HashMap houseRentMontWiseMap = null;
	private HashMap salDataJanToMarForHRAMap = null;
	private HashMap arrearAprToDecForHRAMap = null;
	private HashMap arrearJanToMarForHRAMap = null;
	private HashMap arrearPreviousForHRAMap = null;
	private HashMap settleDataForHRAMap = null;
	//////////////////////////////////////////////////////////////////////////
	
	private double empPtaxGrossAmt = 0;
	private HashMap previousEmployerDeatilsMap = null;
	private double hraAmtForUnProcessedMonths = 0;
	private double hraAmtForUnProcessedMonths1 = 0;
	private double hraAmtForUnProcessedMonths2 = 0;
	private double employeeMonthCount = 0;
	private double configPfAmount = 0;
	
	private HashMap employeeServiceDaysMap = null;
	
	
	public double getEmpPtaxGrossAmt() {
		return empPtaxGrossAmt;
	}
	public void setEmpPtaxGrossAmt(double empPtaxGrossAmt) {
		this.empPtaxGrossAmt = empPtaxGrossAmt;
	}
	public HashMap getEmployeePerquisiteMap() {
		return employeePerquisiteMap;
	}
	public void setEmployeePerquisiteMap(HashMap employeePerquisiteMap) {
		this.employeePerquisiteMap = employeePerquisiteMap;
	}
	public Object[][] getEmployeePaidSalary() {
		return employeePaidSalary;
	}
	public void setEmployeePaidSalary(Object[][] employeePaidSalary) {
		this.employeePaidSalary = employeePaidSalary;
	}
	public HashMap getSettlementCreditDataMap() {
		return settlementCreditDataMap;
	}
	public void setSettlementCreditDataMap(HashMap settlementCreditDataMap) {
		this.settlementCreditDataMap = settlementCreditDataMap;
	}
	public HashMap getMainMap() {
		return mainMap;
	}
	public void setMainMap(HashMap mainMap) {
		this.mainMap = mainMap;
	}
	public HashMap getEmployeeCreditMasterMap() {
		return employeeCreditMasterMap;
	}
	public void setEmployeeCreditMasterMap(HashMap employeeCreditMasterMap) {
		this.employeeCreditMasterMap = employeeCreditMasterMap;
	}
	public HashMap getArrearAprToDecMap() {
		return arrearAprToDecMap;
	}
	public void setArrearAprToDecMap(HashMap arrearAprToDecMap) {
		this.arrearAprToDecMap = arrearAprToDecMap;
	}
	public HashMap getArrearJanToMarMap() {
		return arrearJanToMarMap;
	}
	public void setArrearJanToMarMap(HashMap arrearJanToMarMap) {
		this.arrearJanToMarMap = arrearJanToMarMap;
	}
	public HashMap getRentDataMap() {
		return rentDataMap;
	}
	public void setRentDataMap(HashMap rentDataMap) {
		this.rentDataMap = rentDataMap;
	}
	public HashMap getEmpInvestmentsMap() {
		return empInvestmentsMap;
	}
	public void setEmpInvestmentsMap(HashMap empInvestmentsMap) {
		this.empInvestmentsMap = empInvestmentsMap;
	}
	public HashMap getEmpLeaveEncashMap() {
		return empLeaveEncashMap;
	}
	public void setEmpLeaveEncashMap(HashMap empLeaveEncashMap) {
		this.empLeaveEncashMap = empLeaveEncashMap;
	}
	public HashMap getEmployeeDebitPtaxCodeMap() {
		return employeeDebitPtaxCodeMap;
	}
	public void setEmployeeDebitPtaxCodeMap(HashMap employeeDebitPtaxCodeMap) {
		this.employeeDebitPtaxCodeMap = employeeDebitPtaxCodeMap;
	}
	public HashMap getPreviousMonthArrearMap() {
		return previousMonthArrearMap;
	}
	public void setPreviousMonthArrearMap(HashMap previousMonthArrearMap) {
		this.previousMonthArrearMap = previousMonthArrearMap;
	}
	public HashMap getSalDataAprToDecMap() {
		return salDataAprToDecMap;
	}
	public void setSalDataAprToDecMap(HashMap salDataAprToDecMap) {
		this.salDataAprToDecMap = salDataAprToDecMap;
	}
	public HashMap getSalDataJanToMarMap() {
		return salDataJanToMarMap;
	}
	public void setSalDataJanToMarMap(HashMap salDataJanToMarMap) {
		this.salDataJanToMarMap = salDataJanToMarMap;
	}
	public HashMap getPeriodicAllowanceMap() {
		return periodicAllowanceMap;
	}
	public void setPeriodicAllowanceMap(HashMap periodicAllowanceMap) {
		this.periodicAllowanceMap = periodicAllowanceMap;
	}
	public double getHraAmtForUnProcessedMonths() {
		return hraAmtForUnProcessedMonths;
	}
	public void setHraAmtForUnProcessedMonths(double hraAmtForUnProcessedMonths) {
		this.hraAmtForUnProcessedMonths = hraAmtForUnProcessedMonths;
	}
	public double getHraAmtForUnProcessedMonths1() {
		return hraAmtForUnProcessedMonths1;
	}
	public void setHraAmtForUnProcessedMonths1(double hraAmtForUnProcessedMonths1) {
		this.hraAmtForUnProcessedMonths1 = hraAmtForUnProcessedMonths1;
	}
	public double getHraAmtForUnProcessedMonths2() {
		return hraAmtForUnProcessedMonths2;
	}
	public void setHraAmtForUnProcessedMonths2(double hraAmtForUnProcessedMonths2) {
		this.hraAmtForUnProcessedMonths2 = hraAmtForUnProcessedMonths2;
	}
	public HashMap getSalDebitAprToDecMap() {
		return salDebitAprToDecMap;
	}
	public void setSalDebitAprToDecMap(HashMap salDebitAprToDecMap) {
		this.salDebitAprToDecMap = salDebitAprToDecMap;
	}
	public HashMap getSalDebitJanToMarMap() {
		return salDebitJanToMarMap;
	}
	public void setSalDebitJanToMarMap(HashMap salDebitJanToMarMap) {
		this.salDebitJanToMarMap = salDebitJanToMarMap;
	}
	public HashMap getDebitInvestmentsMap() {
		return debitInvestmentsMap;
	}
	public void setDebitInvestmentsMap(HashMap debitInvestmentsMap) {
		this.debitInvestmentsMap = debitInvestmentsMap;
	}
	public HashMap getArrearDebitAprToDecMap() {
		return arrearDebitAprToDecMap;
	}
	public void setArrearDebitAprToDecMap(HashMap arrearDebitAprToDecMap) {
		this.arrearDebitAprToDecMap = arrearDebitAprToDecMap;
	}
	public HashMap getArrearDebitJanToMar() {
		return arrearDebitJanToMar;
	}
	public void setArrearDebitJanToMar(HashMap arrearDebitJanToMar) {
		this.arrearDebitJanToMar = arrearDebitJanToMar;
	}
	public HashMap getPreviousMonthDebitArrearMap() {
		return previousMonthDebitArrearMap;
	}
	public void setPreviousMonthDebitArrearMap(HashMap previousMonthDebitArrearMap) {
		this.previousMonthDebitArrearMap = previousMonthDebitArrearMap;
	}
	public HashMap getPeriodicAllowanceTaxDataMap() {
		return periodicAllowanceTaxDataMap;
	}
	public void setPeriodicAllowanceTaxDataMap(HashMap periodicAllowanceTaxDataMap) {
		this.periodicAllowanceTaxDataMap = periodicAllowanceTaxDataMap;
	}
	public HashMap getEmployeeLeaveEncashAmountMap() {
		return employeeLeaveEncashAmountMap;
	}
	public void setEmployeeLeaveEncashAmountMap(HashMap employeeLeaveEncashAmountMap) {
		this.employeeLeaveEncashAmountMap = employeeLeaveEncashAmountMap;
	}
	public HashMap getEmployeeMonthCountMap() {
		return employeeMonthCountMap;
	}
	public void setEmployeeMonthCountMap(HashMap employeeMonthCountMap) {
		this.employeeMonthCountMap = employeeMonthCountMap;
	}
	public HashMap getEmployeePtaxVariableData() {
		return employeePtaxVariableData;
	}
	public void setEmployeePtaxVariableData(HashMap employeePtaxVariableData) {
		this.employeePtaxVariableData = employeePtaxVariableData;
	}
	public HashMap getEmployeeOfficialMonthCount() {
		return employeeOfficialMonthCount;
	}
	public void setEmployeeOfficialMonthCount(HashMap employeeOfficialMonthCount) {
		this.employeeOfficialMonthCount = employeeOfficialMonthCount;
	}
	public double getEmployeeMonthCount() {
		return employeeMonthCount;
	}
	public void setEmployeeMonthCount(double employeeMonthCount) {
		this.employeeMonthCount = employeeMonthCount;
	}
	public HashMap getEmployeeCreditMasterForLeaveMap() {
		return employeeCreditMasterForLeaveMap;
	}
	public void setEmployeeCreditMasterForLeaveMap(
			HashMap employeeCreditMasterForLeaveMap) {
		this.employeeCreditMasterForLeaveMap = employeeCreditMasterForLeaveMap;
	}
	public HashMap getEmployeeCreditMasterForReimbursement() {
		return employeeCreditMasterForReimbursement;
	}
	public void setEmployeeCreditMasterForReimbursement(
			HashMap employeeCreditMasterForReimbursement) {
		this.employeeCreditMasterForReimbursement = employeeCreditMasterForReimbursement;
	}
	public HashMap getEmployeeReimbursementMap() {
		return employeeReimbursementMap;
	}
	public void setEmployeeReimbursementMap(HashMap employeeReimbursementMap) {
		this.employeeReimbursementMap = employeeReimbursementMap;
	}
	public HashMap getCreditMasterForHRAMap() {
		return creditMasterForHRAMap;
	}
	public void setCreditMasterForHRAMap(HashMap creditMasterForHRAMap) {
		this.creditMasterForHRAMap = creditMasterForHRAMap;
	}
	public HashMap getSalDataAprToDecForHRAMap() {
		return salDataAprToDecForHRAMap;
	}
	public void setSalDataAprToDecForHRAMap(HashMap salDataAprToDecForHRAMap) {
		this.salDataAprToDecForHRAMap = salDataAprToDecForHRAMap;
	}
	public HashMap getHouseRentMontWiseMap() {
		return houseRentMontWiseMap;
	}
	public void setHouseRentMontWiseMap(HashMap houseRentMontWiseMap) {
		this.houseRentMontWiseMap = houseRentMontWiseMap;
	}
	public HashMap getSalDataJanToMarForHRAMap() {
		return salDataJanToMarForHRAMap;
	}
	public void setSalDataJanToMarForHRAMap(HashMap salDataJanToMarForHRAMap) {
		this.salDataJanToMarForHRAMap = salDataJanToMarForHRAMap;
	}
	public HashMap getArrearAprToDecForHRAMap() {
		return arrearAprToDecForHRAMap;
	}
	public void setArrearAprToDecForHRAMap(HashMap arrearAprToDecForHRAMap) {
		this.arrearAprToDecForHRAMap = arrearAprToDecForHRAMap;
	}
	public HashMap getArrearJanToMarForHRAMap() {
		return arrearJanToMarForHRAMap;
	}
	public void setArrearJanToMarForHRAMap(HashMap arrearJanToMarForHRAMap) {
		this.arrearJanToMarForHRAMap = arrearJanToMarForHRAMap;
	}
	public HashMap getSettlementDebitDataMap() {
		return settlementDebitDataMap;
	}
	public void setSettlementDebitDataMap(HashMap settlementDebitDataMap) {
		this.settlementDebitDataMap = settlementDebitDataMap;
	}
	public HashMap getSettleDataForHRAMap() {
		return settleDataForHRAMap;
	}
	public void setSettleDataForHRAMap(HashMap settleDataForHRAMap) {
		this.settleDataForHRAMap = settleDataForHRAMap;
	}
	public HashMap getExtraWorkBenefitDataMap() {
		return extraWorkBenefitDataMap;
	}
	public void setExtraWorkBenefitDataMap(HashMap extraWorkBenefitDataMap) {
		this.extraWorkBenefitDataMap = extraWorkBenefitDataMap;
	}
	public void setEmployeeServiceDaysMap(HashMap employeeServiceDaysMap) {
		this.employeeServiceDaysMap = employeeServiceDaysMap;
	}
	public HashMap getEmployeeServiceDaysMap() {
		return employeeServiceDaysMap;
	}
	public double getConfigPfAmount() {
		return configPfAmount;
	}
	public void setConfigPfAmount(double configPfAmount) {
		this.configPfAmount = configPfAmount;
	}
	public HashMap getArrearPreviousForHRAMap() {
		return arrearPreviousForHRAMap;
	}
	public void setArrearPreviousForHRAMap(HashMap arrearPreviousForHRAMap) {
		this.arrearPreviousForHRAMap = arrearPreviousForHRAMap;
	}
	
	public HashMap getDynamicInvestmentMap() {
		return dynamicInvestmentMap;
	}
	public void setDynamicInvestmentMap(HashMap dynamicInvestmentMap) {
		this.dynamicInvestmentMap = dynamicInvestmentMap;
	}
	public HashMap getDynamicIncomeMap() {
		return dynamicIncomeMap;
	}
	public void setDynamicIncomeMap(HashMap dynamicIncomeMap) {
		this.dynamicIncomeMap = dynamicIncomeMap;
	}
	public HashMap getEmployeePerquisiteInvestmentMap() {
		return employeePerquisiteInvestmentMap;
	}
	public void setEmployeePerquisiteInvestmentMap(
			HashMap employeePerquisiteInvestmentMap) {
		this.employeePerquisiteInvestmentMap = employeePerquisiteInvestmentMap;
	}
	public HashMap getEmployeeDivisionMap() {
		return employeeDivisionMap;
	}
	public void setEmployeeDivisionMap(HashMap employeeDivisionMap) {
		this.employeeDivisionMap = employeeDivisionMap;
	}
	public HashMap getEmpCreditTotalMap() {
		return empCreditTotalMap;
	}
	public void setEmpCreditTotalMap(HashMap empCreditTotalMap) {
		this.empCreditTotalMap = empCreditTotalMap;
	}
	public HashMap getEmpIncomeMap() {
		return empIncomeMap;
	}
	public void setEmpIncomeMap(HashMap empIncomeMap) {
		this.empIncomeMap = empIncomeMap;
	}
	public HashMap getEmpPtaxAmtMap() {
		return empPtaxAmtMap;
	}
	public void setEmpPtaxAmtMap(HashMap empPtaxAmtMap) {
		this.empPtaxAmtMap = empPtaxAmtMap;
	}
	public HashMap getPreviousInvestmentMap() {
		return previousInvestmentMap;
	}
	public void setPreviousInvestmentMap(HashMap previousInvestmentMap) {
		this.previousInvestmentMap = previousInvestmentMap;
	}
	public HashMap getPreviousEmployerDeatilsMap() {
		return previousEmployerDeatilsMap;
	}
	public void setPreviousEmployerDeatilsMap(HashMap previousEmployerDeatilsMap) {
		this.previousEmployerDeatilsMap = previousEmployerDeatilsMap;
	}
	public HashMap getEmployeeDonationMap() {
		return employeeDonationMap;
	}
	public void setEmployeeDonationMap(HashMap employeeDonationMap) {
		this.employeeDonationMap = employeeDonationMap;
	}
	public HashMap getEmployeeLoanPerq() {
		return employeeLoanPerq;
	}
	public void setEmployeeLoanPerq(HashMap employeeLoanPerq) {
		this.employeeLoanPerq = employeeLoanPerq;
	}
	public HashMap getEmpMiscTaxPaidMap() {
		return empMiscTaxPaidMap;
	}
	public void setEmpMiscTaxPaidMap(HashMap empMiscTaxPaidMap) {
		this.empMiscTaxPaidMap = empMiscTaxPaidMap;
	}
	public HashMap getDynamicTaxPaidMap() {
		return dynamicTaxPaidMap;
	}
	public void setDynamicTaxPaidMap(HashMap dynamicTaxPaidMap) {
		this.dynamicTaxPaidMap = dynamicTaxPaidMap;
	}
}
