package org.paradyne.bean.LMS;

/**
 * @author REEBA JOSEPH
 */

public class GeneralInfo {
	private String EstablishmentName;				//name of shop/construction establishment/construction contractor
	private String EstablishmentAddress;			//address of shop/construction establishment/construction contractor
	private String state;
	private String city;
	private String pincode;
	private String RegistrationNumber;					//registration number
	private String TitleOfAct;						//title of act under which registration is held
	private String RegistrationExpiryDate;			//registration expiry date
	private String licenseExpiryDate;	
	private String Name_Occupier;					//name of occupier
	private String Designation_Occupier;				//designation of occupier
	private String Phone_Occupier;				//phone number of occupier
	private String Mobile_Occupier;					//mobile number of occupier
	private String Fax_Occupier;						//fax of occupier
	private String Email_Occupier;					//email of occupier
	private String Name_Manager;						//name of manager
	private String Designation_Manager;				//designation of manager
	private String Phone_Manager;				//phone number of manager
	private String Mobile_Manager;					//mobile number of manager
	private String Fax_Manager;						//fax of manager
	private String Email_Manager;					//email of manager
	private String establishmentCode;				
	private String dateofCommencement;				//date of commencement of business
	private String legalStatusOfEstablishment;	//Legal status of establishment
	private String ownership;
	private String typeOfEmployment;             //Type of eastablishment
	private String weeklyOffId;
	private String natureOfBusiness;				//Nature of business
	
	private String GeneralInfoID;
	private String ReturnID;
	//added ganesh
	private String employerAddress;					// Employer Address
	private String managerAddress;  				// Manager Address
	private String digitCodeManufacturingProcess;//3 digit code as per attached Classification of Manufacturing Processes
	private String planApprovalNumber;			// Plan approval number
	private String planApprovalDate;				// Plan approval Date
	private String factoryCertificateFlag;			//Does the factory have a Certificate of Stability?
	private String factoryCertificateDate;			//date of the certificate 
	
	//private String generalInfoReturnId = "";
	//private String typeOfEstablishment = "";		//Type of eastablishment
	private String employmentTypeAsPerShcedule = "";//Type of employment as per the Schedule in the Minimum Wages
	private String numberOfBranchOrBusiness = ""; //Number of branches of business, names and addresses 
	private String nameOfBranchOrBusiness="";    //Name of branches of business, names and addresses 
	private String addressofBranchOrBusiness= ""; //Address Of branches of business
	private String contactPersonOfBranchOrBusiness="";//contact Person Of Branch Or Business
	private String telephoneNumberOfBranchOrBusiness="";//telephone Number Of Branch Or Business
	private String permanentManagersMale = "";			//Number of Managers and supervisors Male
	private String permanentManagersFeMale = "";	 	//Number of Managers and supervisors FeMale
	private String permanenetManagersTotal= "";			//  Total permanenet Managers
	private String workersMale="";						//Workers over 18 years Male
	private String workersFemale="";					//Workers over 18 years FeMale
	private String workersTotal="";						//Total Workers over 18 years 
	private String workersOver15ButLess18Male="";				//Workers over 15 years but < 18 years  Male 
	private String workersOver15ButLess18FeMale = "";			//Workers over 15 years but < 18 years FeMale
	private String workersOver15ButLess18Total="";				//Workers over 15 years but < 18 years Total
	private String ownershipOptionValue="";						// ownershipOptionValue
	private String national="";									// OwnerShip national
	private String foreign="";									// OwnerShip foreign
	private String jointNationalForeign="";						// OwnerShip jointNationalForeign
	private String contractWorkersMale="";						// contractWorkersMale
	private String contractWorkersFemale="";					//contractWorkersFemale
	private String contractWorkersTotal="";						//contractWorkersTotal
	private String dailywageWorkersMale="";						//dailywageWorkersMale
	private String dailywageWorkersFemale="";					//dailywageWorkersFemale
	private String dailywageWorkersTotal="";					//dailywageWorkersTotal
	
	private String tempWorkersMale="";							//tempWorkersMale
	private String tempWorkersFemale="";						//tempWorkersFemale
	private String tempWorkersTotal="";							//tempWorkersTotal
	private String casualWorkersMale="";						//casualWorkersMale
	private String casualWorkersFemale="";						//casualWorkersFemale
	private String casualWorkersTotal="";						//casualWorkersTotal
	private String apprenticeMale="";							//apprenticeMale
	private String apprenticeFemale="";							//apprenticeFemale
	private String apprenticeTotal="";							//apprenticeTotal
	private String traineeMale="";								//traineeMale
	private String traineeFemale="";							//traineeFemale
	private String traineeTotal="";								//traineeTotal
	private String familyMembersMalePaid="";					//familyMembersMalePaid
	private String familyMembersFemalePaid="";					//familyMembersFemalePaid
	private String familyMembersTotalPaid="";						//familyMembersTotalPaid
	private String familyMembersMaleUnpaid="";						//familyMembersMaleUnpaid
	private String familyMembersFemaleUnpaid="";				//familyMembersFemaleUnpaid
	private String familyMembersTotalUnpaid="";					//familyMembersTotalUnpaid
	private String permWorkersMaleLessThanOne="";				//permWorkersMaleLessThanOne
	private String permWorkersFemaleLessThanOne="";				//permWorkersFemaleLessThanOne
	private String permWorkersTotalLessThanOne="";				//permWorkersTotalLessThanOne
	private String permWorkersMaleOneToFive="";					//permWorkersMaleOneToFive
	private String permWorkersFemaleOneToFive="";					//permWorkersFemaleOneToFive
	private String permWorkersTotalOneToFive="";				//permWorkersTotalOneToFive
	private String permWorkersMaleFiveToTen="";					//permWorkersMaleFiveToTen
	private String permWorkersFemaleFiveToTen="";				//permWorkersFemaleFiveToTen
	private String permWorkersTotalFiveToTen="";					//permWorkersTotalFiveToTen
	private String permWorkersMaleMoreThanTen="";				//permWorkersMaleMoreThanTen
	private String permWorkersFemaleMoreThanTen="";				//permWorkersFemaleMoreThanTen
	private String permWorkersTotalMoreThanTen="";				//permWorkersTotalMoreThanTen
	
	
	
	
	
	public String getPermWorkersMaleOneToFive() {
		return permWorkersMaleOneToFive;
	}
	public void setPermWorkersMaleOneToFive(String permWorkersMaleOneToFive) {
		this.permWorkersMaleOneToFive = permWorkersMaleOneToFive;
	}
	public String getPermWorkersFemaleOneToFive() {
		return permWorkersFemaleOneToFive;
	}
	public void setPermWorkersFemaleOneToFive(String permWorkersFemaleOneToFive) {
		this.permWorkersFemaleOneToFive = permWorkersFemaleOneToFive;
	}
	public String getPermWorkersTotalOneToFive() {
		return permWorkersTotalOneToFive;
	}
	public void setPermWorkersTotalOneToFive(String permWorkersTotalOneToFive) {
		this.permWorkersTotalOneToFive = permWorkersTotalOneToFive;
	}
	public String getFamilyMembersMaleUnpaid() {
		return familyMembersMaleUnpaid;
	}
	public void setFamilyMembersMaleUnpaid(String familyMembersMaleUnpaid) {
		this.familyMembersMaleUnpaid = familyMembersMaleUnpaid;
	}
	public String getFamilyMembersFemaleUnpaid() {
		return familyMembersFemaleUnpaid;
	}
	public void setFamilyMembersFemaleUnpaid(String familyMembersFemaleUnpaid) {
		this.familyMembersFemaleUnpaid = familyMembersFemaleUnpaid;
	}
	public String getFamilyMembersTotalUnpaid() {
		return familyMembersTotalUnpaid;
	}
	public void setFamilyMembersTotalUnpaid(String familyMembersTotalUnpaid) {
		this.familyMembersTotalUnpaid = familyMembersTotalUnpaid;
	}
	public String getFamilyMembersMalePaid() {
		return familyMembersMalePaid;
	}
	public void setFamilyMembersMalePaid(String familyMembersMalePaid) {
		this.familyMembersMalePaid = familyMembersMalePaid;
	}
	public String getFamilyMembersFemalePaid() {
		return familyMembersFemalePaid;
	}
	public void setFamilyMembersFemalePaid(String familyMembersFemalePaid) {
		this.familyMembersFemalePaid = familyMembersFemalePaid;
	}
	public String getFamilyMembersTotalPaid() {
		return familyMembersTotalPaid;
	}
	public void setFamilyMembersTotalPaid(String familyMembersTotalPaid) {
		this.familyMembersTotalPaid = familyMembersTotalPaid;
	}
	public String getTraineeMale() {
		return traineeMale;
	}
	public void setTraineeMale(String traineeMale) {
		this.traineeMale = traineeMale;
	}
	public String getTraineeFemale() {
		return traineeFemale;
	}
	public void setTraineeFemale(String traineeFemale) {
		this.traineeFemale = traineeFemale;
	}
	public String getTraineeTotal() {
		return traineeTotal;
	}
	public void setTraineeTotal(String traineeTotal) {
		this.traineeTotal = traineeTotal;
	}
	public String getApprenticeMale() {
		return apprenticeMale;
	}
	public void setApprenticeMale(String apprenticeMale) {
		this.apprenticeMale = apprenticeMale;
	}
	public String getApprenticeFemale() {
		return apprenticeFemale;
	}
	public void setApprenticeFemale(String apprenticeFemale) {
		this.apprenticeFemale = apprenticeFemale;
	}
	public String getApprenticeTotal() {
		return apprenticeTotal;
	}
	public void setApprenticeTotal(String apprenticeTotal) {
		this.apprenticeTotal = apprenticeTotal;
	}
	public String getCasualWorkersMale() {
		return casualWorkersMale;
	}
	public void setCasualWorkersMale(String casualWorkersMale) {
		this.casualWorkersMale = casualWorkersMale;
	}
	public String getCasualWorkersFemale() {
		return casualWorkersFemale;
	}
	public void setCasualWorkersFemale(String casualWorkersFemale) {
		this.casualWorkersFemale = casualWorkersFemale;
	}
	public String getCasualWorkersTotal() {
		return casualWorkersTotal;
	}
	public void setCasualWorkersTotal(String casualWorkersTotal) {
		this.casualWorkersTotal = casualWorkersTotal;
	}
	public String getTempWorkersMale() {
		return tempWorkersMale;
	}
	public void setTempWorkersMale(String tempWorkersMale) {
		this.tempWorkersMale = tempWorkersMale;
	}
	public String getTempWorkersFemale() {
		return tempWorkersFemale;
	}
	public void setTempWorkersFemale(String tempWorkersFemale) {
		this.tempWorkersFemale = tempWorkersFemale;
	}
	public String getTempWorkersTotal() {
		return tempWorkersTotal;
	}
	public void setTempWorkersTotal(String tempWorkersTotal) {
		this.tempWorkersTotal = tempWorkersTotal;
	}
	public String getContractWorkersMale() {
		return contractWorkersMale;
	}
	public void setContractWorkersMale(String contractWorkersMale) {
		this.contractWorkersMale = contractWorkersMale;
	}
	public String getContractWorkersFemale() {
		return contractWorkersFemale;
	}
	public void setContractWorkersFemale(String contractWorkersFemale) {
		this.contractWorkersFemale = contractWorkersFemale;
	}
	public String getContractWorkersTotal() {
		return contractWorkersTotal;
	}
	public void setContractWorkersTotal(String contractWorkersTotal) {
		this.contractWorkersTotal = contractWorkersTotal;
	}
	public String getOwnershipOptionValue() {
		return ownershipOptionValue;
	}
	public void setOwnershipOptionValue(String ownershipOptionValue) {
		this.ownershipOptionValue = ownershipOptionValue;
	}
	public String getWorkersOver15ButLess18Male() {
		return workersOver15ButLess18Male;
	}
	public void setWorkersOver15ButLess18Male(String workersOver15ButLess18Male) {
		this.workersOver15ButLess18Male = workersOver15ButLess18Male;
	}
	public String getWorkersOver15ButLess18FeMale() {
		return workersOver15ButLess18FeMale;
	}
	public void setWorkersOver15ButLess18FeMale(String workersOver15ButLess18FeMale) {
		this.workersOver15ButLess18FeMale = workersOver15ButLess18FeMale;
	}
	public String getWorkersOver15ButLess18Total() {
		return workersOver15ButLess18Total;
	}
	public void setWorkersOver15ButLess18Total(String workersOver15ButLess18Total) {
		this.workersOver15ButLess18Total = workersOver15ButLess18Total;
	}
	public String getPermanentManagersMale() {
		return permanentManagersMale;
	}
	public void setPermanentManagersMale(String permanentManagersMale) {
		this.permanentManagersMale = permanentManagersMale;
	}
	public String getPermanentManagersFeMale() {
		return permanentManagersFeMale;
	}
	public void setPermanentManagersFeMale(String permanentManagersFeMale) {
		this.permanentManagersFeMale = permanentManagersFeMale;
	}
	public String getPermanenetManagersTotal() {
		return permanenetManagersTotal;
	}
	public void setPermanenetManagersTotal(String permanenetManagersTotal) {
		this.permanenetManagersTotal = permanenetManagersTotal;
	}
	public String getWorkersMale() {
		return workersMale;
	}
	public void setWorkersMale(String workersMale) {
		this.workersMale = workersMale;
	}
	public String getWorkersFemale() {
		return workersFemale;
	}
	public void setWorkersFemale(String workersFemale) {
		this.workersFemale = workersFemale;
	}
	public String getWorkersTotal() {
		return workersTotal;
	}
	public void setWorkersTotal(String workersTotal) {
		this.workersTotal = workersTotal;
	}
	public String getEmploymentTypeAsPerShcedule() {
		return employmentTypeAsPerShcedule;
	}
	public void setEmploymentTypeAsPerShcedule(String employmentTypeAsPerShcedule) {
		this.employmentTypeAsPerShcedule = employmentTypeAsPerShcedule;
	}
	public String getNumberOfBranchOrBusiness() {
		return numberOfBranchOrBusiness;
	}
	public void setNumberOfBranchOrBusiness(String numberOfBranchOrBusiness) {
		this.numberOfBranchOrBusiness = numberOfBranchOrBusiness;
	}
	public String getNameOfBranchOrBusiness() {
		return nameOfBranchOrBusiness;
	}
	public void setNameOfBranchOrBusiness(String nameOfBranchOrBusiness) {
		this.nameOfBranchOrBusiness = nameOfBranchOrBusiness;
	}
	public String getAddressofBranchOrBusiness() {
		return addressofBranchOrBusiness;
	}
	public void setAddressofBranchOrBusiness(String addressofBranchOrBusiness) {
		this.addressofBranchOrBusiness = addressofBranchOrBusiness;
	}
	public String getContactPersonOfBranchOrBusiness() {
		return contactPersonOfBranchOrBusiness;
	}
	public void setContactPersonOfBranchOrBusiness(
			String contactPersonOfBranchOrBusiness) {
		this.contactPersonOfBranchOrBusiness = contactPersonOfBranchOrBusiness;
	}
	public String getTelephoneNumberOfBranchOrBusiness() {
		return telephoneNumberOfBranchOrBusiness;
	}
	public void setTelephoneNumberOfBranchOrBusiness(
			String telephoneNumberOfBranchOrBusiness) {
		this.telephoneNumberOfBranchOrBusiness = telephoneNumberOfBranchOrBusiness;
	}
	public String getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		RegistrationNumber = registrationNumber;
	}
	public String getDigitCodeManufacturingProcess() {
		return digitCodeManufacturingProcess;
	}
	public void setDigitCodeManufacturingProcess(
			String digitCodeManufacturingProcess) {
		this.digitCodeManufacturingProcess = digitCodeManufacturingProcess;
	}
	public String getPlanApprovalNumber() {
		return planApprovalNumber;
	}
	public void setPlanApprovalNumber(String planApprovalNumber) {
		this.planApprovalNumber = planApprovalNumber;
	}
	public String getPlanApprovalDate() {
		return planApprovalDate;
	}
	public void setPlanApprovalDate(String planApprovalDate) {
		this.planApprovalDate = planApprovalDate;
	}
	public String getFactoryCertificateFlag() {
		return factoryCertificateFlag;
	}
	public void setFactoryCertificateFlag(String factoryCertificateFlag) {
		this.factoryCertificateFlag = factoryCertificateFlag;
	}
	public String getFactoryCertificateDate() {
		return factoryCertificateDate;
	}
	public void setFactoryCertificateDate(String factoryCertificateDate) {
		this.factoryCertificateDate = factoryCertificateDate;
	}
	public String getEmployerAddress() {
		return employerAddress;
	}
	public void setEmployerAddress(String employerAddress) {
		this.employerAddress = employerAddress;
	}
	public String getManagerAddress() {
		return managerAddress;
	}
	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}
	public String getEstablishmentName() {
		return EstablishmentName;
	}
	public void setEstablishmentName(String establishmentName) {
		EstablishmentName = establishmentName;
	}
	public String getEstablishmentAddress() {
		return EstablishmentAddress;
	}
	public void setEstablishmentAddress(String establishmentAddress) {
		EstablishmentAddress = establishmentAddress;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getTitleOfAct() {
		return TitleOfAct;
	}
	public void setTitleOfAct(String titleOfAct) {
		TitleOfAct = titleOfAct;
	}
	public String getRegistrationExpiryDate() {
		return RegistrationExpiryDate;
	}
	public void setRegistrationExpiryDate(String registrationExpiryDate) {
		RegistrationExpiryDate = registrationExpiryDate;
	}
	public String getName_Occupier() {
		return Name_Occupier;
	}
	public void setName_Occupier(String name_Occupier) {
		Name_Occupier = name_Occupier;
	}
	public String getDesignation_Occupier() {
		return Designation_Occupier;
	}
	public void setDesignation_Occupier(String designation_Occupier) {
		Designation_Occupier = designation_Occupier;
	}
	public String getPhone_Occupier() {
		return Phone_Occupier;
	}
	public void setPhone_Occupier(String phone_Occupier) {
		Phone_Occupier = phone_Occupier;
	}
	public String getMobile_Occupier() {
		return Mobile_Occupier;
	}
	public void setMobile_Occupier(String mobile_Occupier) {
		Mobile_Occupier = mobile_Occupier;
	}
	public String getFax_Occupier() {
		return Fax_Occupier;
	}
	public void setFax_Occupier(String fax_Occupier) {
		Fax_Occupier = fax_Occupier;
	}
	public String getEmail_Occupier() {
		return Email_Occupier;
	}
	public void setEmail_Occupier(String email_Occupier) {
		Email_Occupier = email_Occupier;
	}
	public String getName_Manager() {
		return Name_Manager;
	}
	public void setName_Manager(String name_Manager) {
		Name_Manager = name_Manager;
	}
	public String getDesignation_Manager() {
		return Designation_Manager;
	}
	public void setDesignation_Manager(String designation_Manager) {
		Designation_Manager = designation_Manager;
	}
	public String getPhone_Manager() {
		return Phone_Manager;
	}
	public void setPhone_Manager(String phone_Manager) {
		Phone_Manager = phone_Manager;
	}
	public String getMobile_Manager() {
		return Mobile_Manager;
	}
	public void setMobile_Manager(String mobile_Manager) {
		Mobile_Manager = mobile_Manager;
	}
	public String getFax_Manager() {
		return Fax_Manager;
	}
	public void setFax_Manager(String fax_Manager) {
		Fax_Manager = fax_Manager;
	}
	public String getEmail_Manager() {
		return Email_Manager;
	}
	public void setEmail_Manager(String email_Manager) {
		Email_Manager = email_Manager;
	}
	/**
	 * @return the establishmentCode
	 */
	public String getEstablishmentCode() {
		return establishmentCode;
	}
	/**
	 * @param establishmentCode the establishmentCode to set
	 */
	public void setEstablishmentCode(String establishmentCode) {
		this.establishmentCode = establishmentCode;
	}
	/**
	 * @return the dateofCommencement
	 */
	public String getDateofCommencement() {
		return dateofCommencement;
	}
	/**
	 * @param dateofCommencement the dateofCommencement to set
	 */
	public void setDateofCommencement(String dateofCommencement) {
		this.dateofCommencement = dateofCommencement;
	}
	/**
	 * @return the legalStatusOfEstablishment
	 */
	public String getLegalStatusOfEstablishment() {
		return legalStatusOfEstablishment;
	}
	/**
	 * @param legalStatusOfEstablishment the legalStatusOfEstablishment to set
	 */
	public void setLegalStatusOfEstablishment(String legalStatusOfEstablishment) {
		this.legalStatusOfEstablishment = legalStatusOfEstablishment;
	}
	/**
	 * @return the ownership
	 */
	public String getOwnership() {
		return ownership;
	}
	/**
	 * @param ownership the ownership to set
	 */
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	/**
	 * @return the typeOfEmployment
	 */
	public String getTypeOfEmployment() {
		return typeOfEmployment;
	}
	/**
	 * @param typeOfEmployment the typeOfEmployment to set
	 */
	public void setTypeOfEmployment(String typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}
	/**
	 * @return the weeklyOffId
	 */
	public String getWeeklyOffId() {
		return weeklyOffId;
	}
	/**
	 * @param weeklyOffId the weeklyOffId to set
	 */
	public void setWeeklyOffId(String weeklyOffId) {
		this.weeklyOffId = weeklyOffId;
	}
	/**
	 * @return the natureOfBusiness
	 */
	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}
	/**
	 * @param natureOfBusiness the natureOfBusiness to set
	 */
	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}
	/**
	 * @return the generalInfoId
	 */
	/**
	 * @return the licenseExpiryDate
	 */
	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}
	/**
	 * @param licenseExpiryDate the licenseExpiryDate to set
	 */
	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	public String getJointNationalForeign() {
		return jointNationalForeign;
	}
	public void setJointNationalForeign(String jointNationalForeign) {
		this.jointNationalForeign = jointNationalForeign;
	}
	public String getDailywageWorkersMale() {
		return dailywageWorkersMale;
	}
	public void setDailywageWorkersMale(String dailywageWorkersMale) {
		this.dailywageWorkersMale = dailywageWorkersMale;
	}
	public String getDailywageWorkersFemale() {
		return dailywageWorkersFemale;
	}
	public void setDailywageWorkersFemale(String dailywageWorkersFemale) {
		this.dailywageWorkersFemale = dailywageWorkersFemale;
	}
	public String getDailywageWorkersTotal() {
		return dailywageWorkersTotal;
	}
	public void setDailywageWorkersTotal(String dailywageWorkersTotal) {
		this.dailywageWorkersTotal = dailywageWorkersTotal;
	}
	public String getPermWorkersMaleLessThanOne() {
		return permWorkersMaleLessThanOne;
	}
	public void setPermWorkersMaleLessThanOne(String permWorkersMaleLessThanOne) {
		this.permWorkersMaleLessThanOne = permWorkersMaleLessThanOne;
	}
	public String getPermWorkersFemaleLessThanOne() {
		return permWorkersFemaleLessThanOne;
	}
	public void setPermWorkersFemaleLessThanOne(String permWorkersFemaleLessThanOne) {
		this.permWorkersFemaleLessThanOne = permWorkersFemaleLessThanOne;
	}
	public String getPermWorkersTotalLessThanOne() {
		return permWorkersTotalLessThanOne;
	}
	public void setPermWorkersTotalLessThanOne(String permWorkersTotalLessThanOne) {
		this.permWorkersTotalLessThanOne = permWorkersTotalLessThanOne;
	}
	public String getPermWorkersMaleFiveToTen() {
		return permWorkersMaleFiveToTen;
	}
	public void setPermWorkersMaleFiveToTen(String permWorkersMaleFiveToTen) {
		this.permWorkersMaleFiveToTen = permWorkersMaleFiveToTen;
	}
	public String getPermWorkersFemaleFiveToTen() {
		return permWorkersFemaleFiveToTen;
	}
	public void setPermWorkersFemaleFiveToTen(String permWorkersFemaleFiveToTen) {
		this.permWorkersFemaleFiveToTen = permWorkersFemaleFiveToTen;
	}
	public String getPermWorkersTotalFiveToTen() {
		return permWorkersTotalFiveToTen;
	}
	public void setPermWorkersTotalFiveToTen(String permWorkersTotalFiveToTen) {
		this.permWorkersTotalFiveToTen = permWorkersTotalFiveToTen;
	}
	public String getPermWorkersMaleMoreThanTen() {
		return permWorkersMaleMoreThanTen;
	}
	public void setPermWorkersMaleMoreThanTen(String permWorkersMaleMoreThanTen) {
		this.permWorkersMaleMoreThanTen = permWorkersMaleMoreThanTen;
	}
	public String getPermWorkersFemaleMoreThanTen() {
		return permWorkersFemaleMoreThanTen;
	}
	public void setPermWorkersFemaleMoreThanTen(String permWorkersFemaleMoreThanTen) {
		this.permWorkersFemaleMoreThanTen = permWorkersFemaleMoreThanTen;
	}
	public String getPermWorkersTotalMoreThanTen() {
		return permWorkersTotalMoreThanTen;
	}
	public void setPermWorkersTotalMoreThanTen(String permWorkersTotalMoreThanTen) {
		this.permWorkersTotalMoreThanTen = permWorkersTotalMoreThanTen;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGeneralInfoID() {
		return GeneralInfoID;
	}
	public void setGeneralInfoID(String generalInfoID) {
		GeneralInfoID = generalInfoID;
	}
	public String getReturnID() {
		return ReturnID;
	}
	public void setReturnID(String returnID) {
		ReturnID = returnID;
	}
	
	
	
	
}
