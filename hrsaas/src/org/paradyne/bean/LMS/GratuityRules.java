package org.paradyne.bean.LMS;

public class GratuityRules {
	private int Nominations_Received; 			// No. of nominations received during the reporting period
	private int Nominations_Accepted; 			// No. of nominations accepted
	private int Applications_Received; 			// No. of applications for gratuity payment were received
	private int Applications_Approved; 			//No. of applications were approved
	private int Applications_Rejected; 			//No. of applications were rejected
	private int workerDisorderlyConduct; 		// No. of Worker's disorderly conduct
	private int workersContinuedService; 		// No. of Worker's Continued Service
	private int otherReasons; 					// No. of Worker's other reasons
	private String otherReasonsSpecify=""; 		//Other specified reasons 
	private int AmountOfGratuityPaid; 		//Total amount of Gratuity payment paid
	private int MaxGratuityAmount; 	// Maximum individual gratuity payment paid 
	private int MinGratuityAmount;  // Minimum individual gratuity payment paid
	
	//Mode of payment
	private double GratuityPaid_Cash;
	private String GratuityPaid_BankCheque;
	private String GratuityPaid_NormalCheque;
	private String GratuityPaid_CreditToAccount;	
	private String AnyCasesPending; 		//gratuity cases pending (Yes/No) 	
	private String PendingCasesDetails; 		//Reason for pending cases.
	private String gratuityID;
	private String gratuityReturnID; 
	
	public String getGratuityID() {
		return gratuityID;
	}
	public void setGratuityID(String gratuityID) {
		this.gratuityID = gratuityID;
	}
	public String getGratuityReturnID() {
		return gratuityReturnID;
	}
	public void setGratuityReturnID(String gratuityReturnID) {
		this.gratuityReturnID = gratuityReturnID;
	}
	public int getNominations_Received() {
		return Nominations_Received;
	}
	public void setNominations_Received(int nominations_Received) {
		Nominations_Received = nominations_Received;
	}
	public int getNominations_Accepted() {
		return Nominations_Accepted;
	}
	public void setNominations_Accepted(int nominations_Accepted) {
		Nominations_Accepted = nominations_Accepted;
	}
	public int getWorkerDisorderlyConduct() {
		return workerDisorderlyConduct;
	}
	public void setWorkerDisorderlyConduct(int workerDisorderlyConduct) {
		this.workerDisorderlyConduct = workerDisorderlyConduct;
	}
	public int getWorkersContinuedService() {
		return workersContinuedService;
	}
	public void setWorkersContinuedService(int workersContinuedService) {
		this.workersContinuedService = workersContinuedService;
	}
	public int getOtherReasons() {
		return otherReasons;
	}
	public void setOtherReasons(int otherReasons) {
		this.otherReasons = otherReasons;
	}
	public String getOtherReasonsSpecify() {
		return otherReasonsSpecify;
	}
	public void setOtherReasonsSpecify(String otherReasonsSpecify) {
		this.otherReasonsSpecify = otherReasonsSpecify;
	}
	public int getMinGratuityAmount() {
		return MinGratuityAmount;
	}
	public void setMinGratuityAmount(int minGratuityAmount) {
		MinGratuityAmount = minGratuityAmount;
	}
	public String getPendingCasesDetails() {
		return PendingCasesDetails;
	}
	public void setPendingCasesDetails(String pendingCasesDetails) {
		PendingCasesDetails = pendingCasesDetails;
	}
	public int getApplications_Received() {
		return Applications_Received;
	}
	public void setApplications_Received(int applications_Received) {
		Applications_Received = applications_Received;
	}
	public int getApplications_Approved() {
		return Applications_Approved;
	}
	public void setApplications_Approved(int applications_Approved) {
		Applications_Approved = applications_Approved;
	}
	public int getApplications_Rejected() {
		return Applications_Rejected;
	}
	public void setApplications_Rejected(int applications_Rejected) {
		Applications_Rejected = applications_Rejected;
	}
	public int getAmountOfGratuityPaid() {
		return AmountOfGratuityPaid;
	}
	public void setAmountOfGratuityPaid(int amountOfGratuityPaid) {
		AmountOfGratuityPaid = amountOfGratuityPaid;
	}
	public int getMaxGratuityAmount() {
		return MaxGratuityAmount;
	}
	public void setMaxGratuityAmount(int maxGratuityAmount) {
		MaxGratuityAmount = maxGratuityAmount;
	}
	public double getGratuityPaid_Cash() {
		return GratuityPaid_Cash;
	}
	public void setGratuityPaid_Cash(double gratuityPaid_Cash) {
		GratuityPaid_Cash = gratuityPaid_Cash;
	}
	public String getGratuityPaid_BankCheque() {
		return GratuityPaid_BankCheque;
	}
	public void setGratuityPaid_BankCheque(String gratuityPaid_BankCheque) {
		GratuityPaid_BankCheque = gratuityPaid_BankCheque;
	}
	public String getGratuityPaid_NormalCheque() {
		return GratuityPaid_NormalCheque;
	}
	public void setGratuityPaid_NormalCheque(String gratuityPaid_NormalCheque) {
		GratuityPaid_NormalCheque = gratuityPaid_NormalCheque;
	}
	public String getGratuityPaid_CreditToAccount() {
		return GratuityPaid_CreditToAccount;
	}
	public void setGratuityPaid_CreditToAccount(String gratuityPaid_CreditToAccount) {
		GratuityPaid_CreditToAccount = gratuityPaid_CreditToAccount;
	}
	public String getAnyCasesPending() {
		return AnyCasesPending;
	}
	public void setAnyCasesPending(String anyCasesPending) {
		AnyCasesPending = anyCasesPending;
	}
}
