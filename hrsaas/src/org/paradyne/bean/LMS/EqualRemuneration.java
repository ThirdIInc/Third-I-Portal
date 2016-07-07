package org.paradyne.bean.LMS;

/**
 * 
 * @author REEBA_JOSEPH
 *
 */
public class EqualRemuneration {
	// Total number of employees 
	private int Emp_Male;   // Male
	private int totalNumberOfEmployeesFemale; // Female
	private int totalNumberOfEmployeesTotal; // Total
	
	// Number of employees have the same job
	private int employeeWithSameJobMale;  // Male
	private int employeeWithSameJobFeMale; // Female
	private int employeeWithSameJobTotal; // Total
	
	//How much pay does a worker in 20 above receive per pay period
	private int howMuchPayForMaleWorker; // Male
	private int howMuchPayForFemaleWorker; // Female
	
	//Number of Employee with the same job receive a HRA
	private int maleEmpWithSameJobReceiveHRA; // Male
	private int femaleEmpWithSameJobReceiveHRA; // Female
	private int totalEmpWithSameJobReceiveHRA; // Total
	
	//Number of Employee with the same job receive a Allowance
	private int maleEmpWithSameJobReceiveAllowance; // Male
	private int femaleEmpWithSameJobReceiveAllowance; // Female
	private int totalEmpWithSameJobReceiveAllowance; // Total	
	
	private String typeOfAllowance = "";			// Indicate type of allowance 
	
	private String doEmpWithSameJobReceiveSameHRA; // Do employees with the same job receive the same HRA
	
	private String doEmpWithSameJobReceiveOtherAllowance; // Do employees with the same job receive other allowances that are the same
	
	private String empWithSameJobNotSamePay="";  // male and female emp. doing same job are not paid the same pay.
	
	private String empWithSameJobNotSameAllowance = ""; // male and female emp. doing the same job are not paid the same allowances
	
	private String equalRemunerationID;
	private String equalRemunerationReturnID;
	
	public String getEqualRemunerationID() {
		return equalRemunerationID;
	}

	public void setEqualRemunerationID(String equalRemunerationID) {
		this.equalRemunerationID = equalRemunerationID;
	}

	public String getEqualRemunerationReturnID() {
		return equalRemunerationReturnID;
	}

	public void setEqualRemunerationReturnID(String equalRemunerationReturnID) {
		this.equalRemunerationReturnID = equalRemunerationReturnID;
	}


	public int getEmp_Male() {
		return Emp_Male;
	}

	public void setEmp_Male(int emp_Male) {
		Emp_Male = emp_Male;
	}

	public int getTotalNumberOfEmployeesFemale() {
		return totalNumberOfEmployeesFemale;
	}

	public void setTotalNumberOfEmployeesFemale(int totalNumberOfEmployeesFemale) {
		this.totalNumberOfEmployeesFemale = totalNumberOfEmployeesFemale;
	}

	public int getTotalNumberOfEmployeesTotal() {
		return totalNumberOfEmployeesTotal;
	}

	public void setTotalNumberOfEmployeesTotal(int totalNumberOfEmployeesTotal) {
		this.totalNumberOfEmployeesTotal = totalNumberOfEmployeesTotal;
	}

	public int getEmployeeWithSameJobMale() {
		return employeeWithSameJobMale;
	}

	public void setEmployeeWithSameJobMale(int employeeWithSameJobMale) {
		this.employeeWithSameJobMale = employeeWithSameJobMale;
	}

	public int getEmployeeWithSameJobFeMale() {
		return employeeWithSameJobFeMale;
	}

	public void setEmployeeWithSameJobFeMale(int employeeWithSameJobFeMale) {
		this.employeeWithSameJobFeMale = employeeWithSameJobFeMale;
	}

	public int getEmployeeWithSameJobTotal() {
		return employeeWithSameJobTotal;
	}

	public void setEmployeeWithSameJobTotal(int employeeWithSameJobTotal) {
		this.employeeWithSameJobTotal = employeeWithSameJobTotal;
	}

	public int getHowMuchPayForMaleWorker() {
		return howMuchPayForMaleWorker;
	}

	public void setHowMuchPayForMaleWorker(int howMuchPayForMaleWorker) {
		this.howMuchPayForMaleWorker = howMuchPayForMaleWorker;
	}

	public int getHowMuchPayForFemaleWorker() {
		return howMuchPayForFemaleWorker;
	}

	public void setHowMuchPayForFemaleWorker(int howMuchPayForFemaleWorker) {
		this.howMuchPayForFemaleWorker = howMuchPayForFemaleWorker;
	}

	public int getMaleEmpWithSameJobReceiveHRA() {
		return maleEmpWithSameJobReceiveHRA;
	}

	public void setMaleEmpWithSameJobReceiveHRA(int maleEmpWithSameJobReceiveHRA) {
		this.maleEmpWithSameJobReceiveHRA = maleEmpWithSameJobReceiveHRA;
	}

	public int getFemaleEmpWithSameJobReceiveHRA() {
		return femaleEmpWithSameJobReceiveHRA;
	}

	public void setFemaleEmpWithSameJobReceiveHRA(int femaleEmpWithSameJobReceiveHRA) {
		this.femaleEmpWithSameJobReceiveHRA = femaleEmpWithSameJobReceiveHRA;
	}

	public int getTotalEmpWithSameJobReceiveHRA() {
		return totalEmpWithSameJobReceiveHRA;
	}

	public void setTotalEmpWithSameJobReceiveHRA(int totalEmpWithSameJobReceiveHRA) {
		this.totalEmpWithSameJobReceiveHRA = totalEmpWithSameJobReceiveHRA;
	}

	public int getMaleEmpWithSameJobReceiveAllowance() {
		return maleEmpWithSameJobReceiveAllowance;
	}

	public void setMaleEmpWithSameJobReceiveAllowance(
			int maleEmpWithSameJobReceiveAllowance) {
		this.maleEmpWithSameJobReceiveAllowance = maleEmpWithSameJobReceiveAllowance;
	}

	public int getFemaleEmpWithSameJobReceiveAllowance() {
		return femaleEmpWithSameJobReceiveAllowance;
	}

	public void setFemaleEmpWithSameJobReceiveAllowance(
			int femaleEmpWithSameJobReceiveAllowance) {
		this.femaleEmpWithSameJobReceiveAllowance = femaleEmpWithSameJobReceiveAllowance;
	}

	public int getTotalEmpWithSameJobReceiveAllowance() {
		return totalEmpWithSameJobReceiveAllowance;
	}

	public void setTotalEmpWithSameJobReceiveAllowance(
			int totalEmpWithSameJobReceiveAllowance) {
		this.totalEmpWithSameJobReceiveAllowance = totalEmpWithSameJobReceiveAllowance;
	}

	public String getTypeOfAllowance() {
		return typeOfAllowance;
	}

	public void setTypeOfAllowance(String typeOfAllowance) {
		this.typeOfAllowance = typeOfAllowance;
	}

	public String getDoEmpWithSameJobReceiveSameHRA() {
		return doEmpWithSameJobReceiveSameHRA;
	}

	public void setDoEmpWithSameJobReceiveSameHRA(
			String doEmpWithSameJobReceiveSameHRA) {
		this.doEmpWithSameJobReceiveSameHRA = doEmpWithSameJobReceiveSameHRA;
	}

	public String getDoEmpWithSameJobReceiveOtherAllowance() {
		return doEmpWithSameJobReceiveOtherAllowance;
	}

	public void setDoEmpWithSameJobReceiveOtherAllowance(
			String doEmpWithSameJobReceiveOtherAllowance) {
		this.doEmpWithSameJobReceiveOtherAllowance = doEmpWithSameJobReceiveOtherAllowance;
	}

	public String getEmpWithSameJobNotSamePay() {
		return empWithSameJobNotSamePay;
	}

	public void setEmpWithSameJobNotSamePay(String empWithSameJobNotSamePay) {
		this.empWithSameJobNotSamePay = empWithSameJobNotSamePay;
	}

	public String getEmpWithSameJobNotSameAllowance() {
		return empWithSameJobNotSameAllowance;
	}

	public void setEmpWithSameJobNotSameAllowance(
			String empWithSameJobNotSameAllowance) {
		this.empWithSameJobNotSameAllowance = empWithSameJobNotSameAllowance;
	}
}
