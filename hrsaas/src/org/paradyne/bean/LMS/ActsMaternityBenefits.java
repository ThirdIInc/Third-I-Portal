package org.paradyne.bean.LMS;

/**
 * @author REEBA_JOSEPH
 */

public class ActsMaternityBenefits {
	
	private String ReturnID="";
	private String MaternityBenefitsID="";
	private String MaternityLeavesApplied="";// Did any woman employed apply for maternity leave during the reporting period?
	private String maternityLeavePeriodReason=""; // If YES, how many? 

	private String AllApplicationsApproved="";// Were all applications by any woman employed for maternity leave approved for the payment of benefits by the establishment?
	private String NoOfRejectedApplications="";// If NO, how many applications were rejected?
	private String	ReasonsForRejection="";// Why?

	private String MedicalBonusPaid="";// Were women workers who took maternity leave during the period paid a medical bonus? (Section 8)
	private String	MedicalBonusAmountPerPerson="";// If Yes, what was the amount of bonus per person?

	private String MaternityBenefitPaid="";// Were workers who took maternity leave during the period paid a maternity benefit? (Section 5)
	private String TotalAmountOfMaternityBenefitPaid="";// If Yes, What was the total amount of maternity benefit payments (as distinct from bonus) for the establishment as a whole?                                                                                                  establishment as a whole?  
			   
	private String BasisDays="";// In calculating the maternity benefit, how many days were used as the basis for calculation?
	private String numofDays=""; // Number of days 

	private String	HighestMaternityBenefitPaid="" ;// What was the highest maternity benefit paid to any one worker during the reporting period?
	private String	LowestMaternityBenefitPaid=""; // What was the lowest maternity benefit paid to any one worker during the reporting period?
	private String	NoOfDismissedEmployees=""; // Was any worker who was on maternity leave dismissed from employment during the period of her leave?
	private String	DismissalReasons="";// If Yes, what were the reasons for the dismissal? 

	
	private Boolean flgStatus = false;

	
	private String pageToShow;
    private String previousPage;
    
    
	public String getMaternityLeavesApplied() {
		return MaternityLeavesApplied;
	}
	public void setMaternityLeavesApplied(String maternityLeavesApplied) {
		MaternityLeavesApplied = maternityLeavesApplied;
	}
	public String getMaternityLeavePeriodReason() {
		return maternityLeavePeriodReason;
	}
	public void setMaternityLeavePeriodReason(String maternityLeavePeriodReason) {
		this.maternityLeavePeriodReason = maternityLeavePeriodReason;
	}
	public String getAllApplicationsApproved() {
		return AllApplicationsApproved;
	}
	public void setAllApplicationsApproved(String allApplicationsApproved) {
		AllApplicationsApproved = allApplicationsApproved;
	}
	public String getNoOfRejectedApplications() {
		return NoOfRejectedApplications;
	}
	public void setNoOfRejectedApplications(String noOfRejectedApplications) {
		NoOfRejectedApplications = noOfRejectedApplications;
	}
	public String getReasonsForRejection() {
		return ReasonsForRejection;
	}
	public void setReasonsForRejection(String reasonsForRejection) {
		ReasonsForRejection = reasonsForRejection;
	}
	public String getMedicalBonusPaid() {
		return MedicalBonusPaid;
	}
	public void setMedicalBonusPaid(String medicalBonusPaid) {
		MedicalBonusPaid = medicalBonusPaid;
	}
	public String getMedicalBonusAmountPerPerson() {
		return MedicalBonusAmountPerPerson;
	}
	public void setMedicalBonusAmountPerPerson(String medicalBonusAmountPerPerson) {
		MedicalBonusAmountPerPerson = medicalBonusAmountPerPerson;
	}
	public String getMaternityBenefitPaid() {
		return MaternityBenefitPaid;
	}
	public void setMaternityBenefitPaid(String maternityBenefitPaid) {
		MaternityBenefitPaid = maternityBenefitPaid;
	}
	public String getTotalAmountOfMaternityBenefitPaid() {
		return TotalAmountOfMaternityBenefitPaid;
	}
	public void setTotalAmountOfMaternityBenefitPaid(
			String totalAmountOfMaternityBenefitPaid) {
		TotalAmountOfMaternityBenefitPaid = totalAmountOfMaternityBenefitPaid;
	}
	public String getBasisDays() {
		return BasisDays;
	}
	public void setBasisDays(String basisDays) {
		BasisDays = basisDays;
	}
	public String getNumofDays() {
		return numofDays;
	}
	public void setNumofDays(String numofDays) {
		this.numofDays = numofDays;
	}
	public String getHighestMaternityBenefitPaid() {
		return HighestMaternityBenefitPaid;
	}
	public void setHighestMaternityBenefitPaid(String highestMaternityBenefitPaid) {
		HighestMaternityBenefitPaid = highestMaternityBenefitPaid;
	}
	public String getLowestMaternityBenefitPaid() {
		return LowestMaternityBenefitPaid;
	}
	public void setLowestMaternityBenefitPaid(String lowestMaternityBenefitPaid) {
		LowestMaternityBenefitPaid = lowestMaternityBenefitPaid;
	}
	public String getNoOfDismissedEmployees() {
		return NoOfDismissedEmployees;
	}
	public void setNoOfDismissedEmployees(String noOfDismissedEmployees) {
		NoOfDismissedEmployees = noOfDismissedEmployees;
	}
	public String getDismissalReasons() {
		return DismissalReasons;
	}
	public void setDismissalReasons(String dismissalReasons) {
		DismissalReasons = dismissalReasons;
	}
	public Boolean getFlgStatus() {
		return flgStatus;
	}
	public void setFlgStatus(Boolean flgStatus) {
		this.flgStatus = flgStatus;
	}
	public String getPageToShow() {
		return pageToShow;
	}
	public void setPageToShow(String pageToShow) {
		this.pageToShow = pageToShow;
	}
	public String getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	public String getReturnID() {
		return ReturnID;
	}
	public void setReturnID(String returnID) {
		ReturnID = returnID;
	}
	public String getMaternityBenefitsID() {
		return MaternityBenefitsID;
	}
	public void setMaternityBenefitsID(String maternityBenefitsID) {
		MaternityBenefitsID = maternityBenefitsID;
	}
	
	
	
}
