package org.paradyne.bean.LMS;

/**
 * @author Ganesh
 */

public class ActsPaymentOfBonus {
	
	private String ReturnID="";
	
	private String PaymentOfBonusID="";
	private String NoOfEmployees_Male="";// Total number of employees
	private String NoOfEmployees_Female="";

	private String EmpReceivingLT10K_Male="";// Total number of employees
	// receiving less that Rs. 10000
	// per month
	private String EmpReceivingLT10K_Female="";

	private String EmpWorkingLT30Days_Male="";// Total number of
	// employees working
	// less than 30 days in
	// the accounting period
	private String EmpWorkingLT30Days_Female="";

	private String Disqualified_Male="";// Total number of employees
	// disqualified from bonus
	private String Disqualified_Female="";

	private String Eligible_Male="";// Total number eligible for
	// bonus (19 minus 20 minus 21
	// minus 22 )
	private String Eligible_Female="";

	private String TotalEligible="";// Total number eligible (male plus female)
	private String totalNoFemale="";

	private String RateOfBonusPayment=""; // What was the percentage rate (or
	// alternatively the flat rate) of the
	// bonus payment
	private String TotalBonusPaid="";// What was the total amount of bonus
	// payment for the accounting year?
	private String AdvanceBonusPaid="";// How much of the total amount of bonus
	// payment was paid as an interim or
	// advance bonus?
	private String NoOfEmpReceivingAdvBonus="";// How many employees received an
	// interim or advance bonus payment?
	private String BonusPaymentDate="";// Date on which final bonus was paid
	private String ReasonsForNonPayment="";// If bonus was not paid, please state
	// reasons.Reasons not paid

	private String ExGratiaPaid="";// Whether any ex gratia payment is paid to
	// the employees ? If so
	private String ExGratiaPercent="";// Percentage of ex gratia
	private String NoOfEmp="";// No. of employees
	private String ExGratiaAmount=""; // Amount paid
	private String ExGratiaPaymentDate=""; // Date of payment
	
	//private String flatRateBonusPeriod=""; //Flat rate of the bonus period (Rs.) 
	
	
	
	public String getEmpReceivingLT10K_Female() {
		return EmpReceivingLT10K_Female;
	}
	public void setEmpReceivingLT10K_Female(String empReceivingLT10K_Female) {
		EmpReceivingLT10K_Female = empReceivingLT10K_Female;
	}
	public String getEmpWorkingLT30Days_Female() {
		return EmpWorkingLT30Days_Female;
	}
	public void setEmpWorkingLT30Days_Female(String empWorkingLT30Days_Female) {
		EmpWorkingLT30Days_Female = empWorkingLT30Days_Female;
	}
	public String getEligible_Male() {
		return Eligible_Male;
	}
	public String getDisqualified_Female() {
		return Disqualified_Female;
	}
	public void setDisqualified_Female(String disqualified_Female) {
		Disqualified_Female = disqualified_Female;
	}
	public void setEligible_Male(String eligible_Male) {
		Eligible_Male = eligible_Male;
	}
	public String getTotalEligible() {
		return TotalEligible;
	}
	public void setTotalEligible(String totalEligible) {
		TotalEligible = totalEligible;
	}
	public String getTotalNoFemale() {
		return totalNoFemale;
	}
	public void setTotalNoFemale(String totalNoFemale) {
		this.totalNoFemale = totalNoFemale;
	}
	public String getRateOfBonusPayment() {
		return RateOfBonusPayment;
	}
	public void setRateOfBonusPayment(String rateOfBonusPayment) {
		RateOfBonusPayment = rateOfBonusPayment;
	}
	public String getTotalBonusPaid() {
		return TotalBonusPaid;
	}
	public void setTotalBonusPaid(String totalBonusPaid) {
		TotalBonusPaid = totalBonusPaid;
	}
	public String getAdvanceBonusPaid() {
		return AdvanceBonusPaid;
	}
	public void setAdvanceBonusPaid(String advanceBonusPaid) {
		AdvanceBonusPaid = advanceBonusPaid;
	}
	public String getNoOfEmpReceivingAdvBonus() {
		return NoOfEmpReceivingAdvBonus;
	}
	public void setNoOfEmpReceivingAdvBonus(String noOfEmpReceivingAdvBonus) {
		NoOfEmpReceivingAdvBonus = noOfEmpReceivingAdvBonus;
	}
	public String getBonusPaymentDate() {
		return BonusPaymentDate;
	}
	public void setBonusPaymentDate(String bonusPaymentDate) {
		BonusPaymentDate = bonusPaymentDate;
	}
	public String getReasonsForNonPayment() {
		return ReasonsForNonPayment;
	}
	public void setReasonsForNonPayment(String reasonsForNonPayment) {
		ReasonsForNonPayment = reasonsForNonPayment;
	}
	public String getExGratiaPaid() {
		return ExGratiaPaid;
	}
	public void setExGratiaPaid(String exGratiaPaid) {
		ExGratiaPaid = exGratiaPaid;
	}
	public String getExGratiaPercent() {
		return ExGratiaPercent;
	}
	public void setExGratiaPercent(String exGratiaPercent) {
		ExGratiaPercent = exGratiaPercent;
	}
	public String getNoOfEmp() {
		return NoOfEmp;
	}
	public void setNoOfEmp(String noOfEmp) {
		NoOfEmp = noOfEmp;
	}
	public String getExGratiaAmount() {
		return ExGratiaAmount;
	}
	public void setExGratiaAmount(String exGratiaAmount) {
		ExGratiaAmount = exGratiaAmount;
	}
	public String getPaymentOfBonusID() {
		return PaymentOfBonusID;
	}
	public void setPaymentOfBonusID(String paymentOfBonusID) {
		PaymentOfBonusID = paymentOfBonusID;
	}
	public String getReturnID() {
		return ReturnID;
	}
	public void setReturnID(String returnID) {
		ReturnID = returnID;
	}
	public String getNoOfEmployees_Male() {
		return NoOfEmployees_Male;
	}
	public void setNoOfEmployees_Male(String noOfEmployees_Male) {
		NoOfEmployees_Male = noOfEmployees_Male;
	}
	public String getEmpReceivingLT10K_Male() {
		return EmpReceivingLT10K_Male;
	}
	public void setEmpReceivingLT10K_Male(String empReceivingLT10K_Male) {
		EmpReceivingLT10K_Male = empReceivingLT10K_Male;
	}
	public String getEmpWorkingLT30Days_Male() {
		return EmpWorkingLT30Days_Male;
	}
	public void setEmpWorkingLT30Days_Male(String empWorkingLT30Days_Male) {
		EmpWorkingLT30Days_Male = empWorkingLT30Days_Male;
	}
	public String getDisqualified_Male() {
		return Disqualified_Male;
	}
	public void setDisqualified_Male(String disqualified_Male) {
		Disqualified_Male = disqualified_Male;
	}
	public String getNoOfEmployees_Female() {
		return NoOfEmployees_Female;
	}
	public void setNoOfEmployees_Female(String noOfEmployees_Female) {
		NoOfEmployees_Female = noOfEmployees_Female;
	}
	public String getEligible_Female() {
		return Eligible_Female;
	}
	public void setEligible_Female(String eligible_Female) {
		Eligible_Female = eligible_Female;
	}
	public String getExGratiaPaymentDate() {
		return ExGratiaPaymentDate;
	}
	public void setExGratiaPaymentDate(String exGratiaPaymentDate) {
		ExGratiaPaymentDate = exGratiaPaymentDate;
	}
	
	
	
}
