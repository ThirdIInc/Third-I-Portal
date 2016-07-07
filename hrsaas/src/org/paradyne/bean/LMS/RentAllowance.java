package org.paradyne.bean.LMS;

public class RentAllowance {
	private String	AccommodationProvided;	// employer provide accommodation for employees 
	private String FreeAccommodationProvided;			//accommodation provided free and without deduction from an employee's wages
	private int NoOfEmpHRAPaid;                   // Number of emp. paid HRA
	private String HRACalculatedAs5Percent; 				// Is HRA calculated
	private String HRACalculationFormula;				// How HRA calculated
	private double MaxHRAPaid;					//Highest HRA paid
	private double MinHRAPaid;
	private double totalHRAPaid;					//Total HRA paid
	private String HRAPaymentMode;			// before, during, after
	private String AfterDays;				//  no. of days after HRA was paid
	private String HRADisputesReceived;			// Were there any disputes over house rent allowance
	private int NoOfHRADisputes;					// How many disputes
	private String WorkersRepresentedByTU; 					// Are workers represented by a trade union
	private String TUName;				// Name of trade union
	private String DisputesResolutionMethod;	
	private String rentAllowanceRadioOptionValue;  // Rent allowance Radio Button Selected value
	private String disputeRadioOptionValue;		   // Dispute Radio Button selected value
	private String MinimumHRAID;
	private String ReturnID;
	
	public String getRentAllowanceRadioOptionValue() {
		return rentAllowanceRadioOptionValue;
	}
	public void setRentAllowanceRadioOptionValue(
			String rentAllowanceRadioOptionValue) {
		this.rentAllowanceRadioOptionValue = rentAllowanceRadioOptionValue;
	}
	public String getDisputeRadioOptionValue() {
		return disputeRadioOptionValue;
	}
	public void setDisputeRadioOptionValue(String disputeRadioOptionValue) {
		this.disputeRadioOptionValue = disputeRadioOptionValue;
	}
	public double getTotalHRAPaid() {
		return totalHRAPaid;
	}
	public void setTotalHRAPaid(double totalHRAPaid) {
		this.totalHRAPaid = totalHRAPaid;
	}
	public String getWorkersRepresentedByTU() {
		return WorkersRepresentedByTU;
	}
	public void setWorkersRepresentedByTU(String workersRepresentedByTU) {
		WorkersRepresentedByTU = workersRepresentedByTU;
	}
	public String getMinimumHRAID() {
		return MinimumHRAID;
	}
	public void setMinimumHRAID(String minimumHRAID) {
		MinimumHRAID = minimumHRAID;
	}
	public String getReturnID() {
		return ReturnID;
	}
	public void setReturnID(String returnID) {
		ReturnID = returnID;
	}
	public String getAccommodationProvided() {
		return AccommodationProvided;
	}
	public void setAccommodationProvided(String accommodationProvided) {
		AccommodationProvided = accommodationProvided;
	}
	public String getFreeAccommodationProvided() {
		return FreeAccommodationProvided;
	}
	public void setFreeAccommodationProvided(String freeAccommodationProvided) {
		FreeAccommodationProvided = freeAccommodationProvided;
	}
	public int getNoOfEmpHRAPaid() {
		return NoOfEmpHRAPaid;
	}
	public void setNoOfEmpHRAPaid(int noOfEmpHRAPaid) {
		NoOfEmpHRAPaid = noOfEmpHRAPaid;
	}
	public String getHRACalculatedAs5Percent() {
		return HRACalculatedAs5Percent;
	}
	public void setHRACalculatedAs5Percent(String calculatedAs5Percent) {
		HRACalculatedAs5Percent = calculatedAs5Percent;
	}
	public String getHRACalculationFormula() {
		return HRACalculationFormula;
	}
	public void setHRACalculationFormula(String calculationFormula) {
		HRACalculationFormula = calculationFormula;
	}
	public double getMaxHRAPaid() {
		return MaxHRAPaid;
	}
	public void setMaxHRAPaid(double maxHRAPaid) {
		MaxHRAPaid = maxHRAPaid;
	}
	public double getMinHRAPaid() {
		return MinHRAPaid;
	}
	public void setMinHRAPaid(double minHRAPaid) {
		MinHRAPaid = minHRAPaid;
	}
	public String getAfterDays() {
		return AfterDays;
	}
	public void setAfterDays(String afterDays) {
		AfterDays = afterDays;
	}
	public String getHRAPaymentMode() {
		return HRAPaymentMode;
	}
	public void setHRAPaymentMode(String paymentMode) {
		HRAPaymentMode = paymentMode;
	}
	public String getHRADisputesReceived() {
		return HRADisputesReceived;
	}
	public void setHRADisputesReceived(String disputesReceived) {
		HRADisputesReceived = disputesReceived;
	}
	public int getNoOfHRADisputes() {
		return NoOfHRADisputes;
	}
	public void setNoOfHRADisputes(int noOfHRADisputes) {
		NoOfHRADisputes = noOfHRADisputes;
	}
	public String getTUName() {
		return TUName;
	}
	public void setTUName(String name) {
		TUName = name;
	}
	public String getDisputesResolutionMethod() {
		return DisputesResolutionMethod;
	}
	public void setDisputesResolutionMethod(String disputesResolutionMethod) {
		DisputesResolutionMethod = disputesResolutionMethod;
	}
	
	
}