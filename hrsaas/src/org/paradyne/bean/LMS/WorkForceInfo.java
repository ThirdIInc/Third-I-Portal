package org.paradyne.bean.LMS;

/**
 * @author REEBA JOSEPH
 */

public class WorkForceInfo {
	private int permanentManagersMale;				//No Of Permanent Managers/supervisors Male
	private int permanentManagersFemale;			//No Of Permanent Managers/supervisors Female
	private int workersMale;						//number of male workers
	private int workersFemale;						//number of female workers
	private int adolscentWorkersMale;				//number of male workers between age 14 to 18
	private int adolscentWorkersFemale;				//number of female workers between age 14 to 18
	private int childWorkersMale;					//number of male workers below 14
	private int childWorkersFemale;					//number of female workers below 14
	private int contractWorkersMale;				//No Of Contract Workers Male. Contracted by the establishment
	private int contractWorkersFemale;				//No Of Contract Workers Female. Contracted by the establishment
	private int dailywageWorkersMale;				//No Of Daily Wage Workers Male
	private int dailywageWorkersFemale;				//No Of Daily Wage Workers female
	private int dailywageWorkersMaleDrivers;
	private int dailywageWorkersFemaleDrivers;
	private int dailywageWorkersMaleNonDerivers;
	private int dailywageWorkersFemaleNonDrivers;
	private int tempWorkersMale;					//No Of Temporary Workers Male
	private int tempWorkersFemale;					//No Of Temporary Workers Female
	private int tempWorkersMaleDrivers;
	private int tempWorkersFemaleDrivers;
	private int tempWorkersMaleNonDerivers;
	private int tempWorkersFemaleNonDrivers;
	private int casualWorkersMale;					//No Of Casual Workers Male
	private int casualWorkersFemale;				//No Of Casual Workers Female
	private int casualWorkersMaleDrivers;
	private int casualWorkersFemaleDrivers;
	private int casualWorkersMaleNonDerivers;
	private int casualWorkersFemaleNonDrivers;
	private int apprenticeMale;						//No Of Apprentices Male
	private int apprenticeFemale;					//No Of Apprentices Female
	private int traineeMale;						//No of Trainees Male
	private int traineeFemale;						//No of Trainees Female
	private int familyMembersMalePaid;				//FamilyMembersMale Paid
	private int familyMembersFemalePaid;			//FamilyMembersFemale Paid
	private int familyMembersMaleUnpaid;			//FamilyMembersMale Unpaid
	private int familyMembersFemaleUnpaid;			//FamilyMembersFemale Unpaid
	private int permWorkersMaleLessThanOne;			//permanent male workers with less than 1 yr. service
	private int permWorkersFemaleLessThanOne;		//permanent female workers with less than 1 yr. service
	private int permWorkersMaleOneToFive;			//permanent male workers with service between 1 to 5 yrs.
	private int permWorkersFemaleOneToFive;			//permanent female workers with service between 1 to 5 yrs.
	private int permWorkersMaleFiveToTen;			//permanent male workers with service between 5 to 10 yrs.
	private int permWorkersFemaleFiveToTen;			//permanent female workers with service between 5 to 10 yrs.
	private int permWorkersMaleMoreThanTen;			//permanent male workers with more than 10 yr. service
	private int permWorkersFemaleMoreThanTen;		//permanent female workers with more than 10 yr. service
	private char ownSecurityGuards;					//does the establishment employ its own security guards as direct employees
	private char ownCleaningStaff;					//does the establishment employ its own cleaning staff as direct employees
	private char hasIsmWorkers;						//are any contract workers ISM workers?
	private int noOfIsmWorkers;						//number of ISM workers employed by the contractor during the reporting period
	private int noOfIsmWorkersAdolscent;			//number of ISM workers employed by the contractor during the reporting period under 18 years
	private int noOfContractors;					//number of contractors engaged at work site
	private int noOfContractWorkersEngaged;			//total number of contract workers engaged at work site. Contract workers from the contractors.
	private int noOfDifferentContractors;			//number of different contractors will be used over the entire construction period?
	private int contractWorkersFromContractors;		//approximately how many workers will those contractors employ at this site over the entire construction period?
	private long workForceId;
	private long returnId;
	/**
	 * @return the permanentManagersMale
	 */
	public int getPermanentManagersMale() {
		return permanentManagersMale;
	}
	/**
	 * @param permanentManagersMale the permanentManagersMale to set
	 */
	public void setPermanentManagersMale(int permanentManagersMale) {
		this.permanentManagersMale = permanentManagersMale;
	}
	/**
	 * @return the permanentManagersFemale
	 */
	public int getPermanentManagersFemale() {
		return permanentManagersFemale;
	}
	/**
	 * @param permanentManagersFemale the permanentManagersFemale to set
	 */
	public void setPermanentManagersFemale(int permanentManagersFemale) {
		this.permanentManagersFemale = permanentManagersFemale;
	}
	/**
	 * @return the workersMale
	 */
	public int getWorkersMale() {
		return workersMale;
	}
	/**
	 * @param workersMale the workersMale to set
	 */
	public void setWorkersMale(int workersMale) {
		this.workersMale = workersMale;
	}
	/**
	 * @return the workersFemale
	 */
	public int getWorkersFemale() {
		return workersFemale;
	}
	/**
	 * @param workersFemale the workersFemale to set
	 */
	public void setWorkersFemale(int workersFemale) {
		this.workersFemale = workersFemale;
	}
	/**
	 * @return the adolscentWorkersMale
	 */
	public int getAdolscentWorkersMale() {
		return adolscentWorkersMale;
	}
	/**
	 * @param adolscentWorkersMale the adolscentWorkersMale to set
	 */
	public void setAdolscentWorkersMale(int adolscentWorkersMale) {
		this.adolscentWorkersMale = adolscentWorkersMale;
	}
	/**
	 * @return the adolscentWorkersFemale
	 */
	public int getAdolscentWorkersFemale() {
		return adolscentWorkersFemale;
	}
	/**
	 * @param adolscentWorkersFemale the adolscentWorkersFemale to set
	 */
	public void setAdolscentWorkersFemale(int adolscentWorkersFemale) {
		this.adolscentWorkersFemale = adolscentWorkersFemale;
	}
	/**
	 * @return the childWorkersMale
	 */
	public int getChildWorkersMale() {
		return childWorkersMale;
	}
	/**
	 * @param childWorkersMale the childWorkersMale to set
	 */
	public void setChildWorkersMale(int childWorkersMale) {
		this.childWorkersMale = childWorkersMale;
	}
	/**
	 * @return the childWorkersFemale
	 */
	public int getChildWorkersFemale() {
		return childWorkersFemale;
	}
	/**
	 * @param childWorkersFemale the childWorkersFemale to set
	 */
	public void setChildWorkersFemale(int childWorkersFemale) {
		this.childWorkersFemale = childWorkersFemale;
	}
	/**
	 * @return the contractWorkersMale
	 */
	public int getContractWorkersMale() {
		return contractWorkersMale;
	}
	/**
	 * @param contractWorkersMale the contractWorkersMale to set
	 */
	public void setContractWorkersMale(int contractWorkersMale) {
		this.contractWorkersMale = contractWorkersMale;
	}
	/**
	 * @return the contractWorkersFemale
	 */
	public int getContractWorkersFemale() {
		return contractWorkersFemale;
	}
	/**
	 * @param contractWorkersFemale the contractWorkersFemale to set
	 */
	public void setContractWorkersFemale(int contractWorkersFemale) {
		this.contractWorkersFemale = contractWorkersFemale;
	}
	/**
	 * @return the dailywageWorkersMale
	 */
	public int getDailywageWorkersMale() {
		return dailywageWorkersMale;
	}
	/**
	 * @param dailywageWorkersMale the dailywageWorkersMale to set
	 */
	public void setDailywageWorkersMale(int dailywageWorkersMale) {
		this.dailywageWorkersMale = dailywageWorkersMale;
	}
	/**
	 * @return the dailywageWorkersFemale
	 */
	public int getDailywageWorkersFemale() {
		return dailywageWorkersFemale;
	}
	/**
	 * @param dailywageWorkersFemale the dailywageWorkersFemale to set
	 */
	public void setDailywageWorkersFemale(int dailywageWorkersFemale) {
		this.dailywageWorkersFemale = dailywageWorkersFemale;
	}
	/**
	 * @return the dailywageWorkersMaleDrivers
	 */
	public int getDailywageWorkersMaleDrivers() {
		return dailywageWorkersMaleDrivers;
	}
	/**
	 * @param dailywageWorkersMaleDrivers the dailywageWorkersMaleDrivers to set
	 */
	public void setDailywageWorkersMaleDrivers(int dailywageWorkersMaleDrivers) {
		this.dailywageWorkersMaleDrivers = dailywageWorkersMaleDrivers;
	}
	/**
	 * @return the dailywageWorkersFemaleDrivers
	 */
	public int getDailywageWorkersFemaleDrivers() {
		return dailywageWorkersFemaleDrivers;
	}
	/**
	 * @param dailywageWorkersFemaleDrivers the dailywageWorkersFemaleDrivers to set
	 */
	public void setDailywageWorkersFemaleDrivers(int dailywageWorkersFemaleDrivers) {
		this.dailywageWorkersFemaleDrivers = dailywageWorkersFemaleDrivers;
	}
	/**
	 * @return the dailywageWorkersMaleNonDerivers
	 */
	public int getDailywageWorkersMaleNonDerivers() {
		return dailywageWorkersMaleNonDerivers;
	}
	/**
	 * @param dailywageWorkersMaleNonDerivers the dailywageWorkersMaleNonDerivers to set
	 */
	public void setDailywageWorkersMaleNonDerivers(
			int dailywageWorkersMaleNonDerivers) {
		this.dailywageWorkersMaleNonDerivers = dailywageWorkersMaleNonDerivers;
	}
	/**
	 * @return the dailywageWorkersFemaleNonDrivers
	 */
	public int getDailywageWorkersFemaleNonDrivers() {
		return dailywageWorkersFemaleNonDrivers;
	}
	/**
	 * @param dailywageWorkersFemaleNonDrivers the dailywageWorkersFemaleNonDrivers to set
	 */
	public void setDailywageWorkersFemaleNonDrivers(
			int dailywageWorkersFemaleNonDrivers) {
		this.dailywageWorkersFemaleNonDrivers = dailywageWorkersFemaleNonDrivers;
	}
	/**
	 * @return the tempWorkersMale
	 */
	public int getTempWorkersMale() {
		return tempWorkersMale;
	}
	/**
	 * @param tempWorkersMale the tempWorkersMale to set
	 */
	public void setTempWorkersMale(int tempWorkersMale) {
		this.tempWorkersMale = tempWorkersMale;
	}
	/**
	 * @return the tempWorkersFemale
	 */
	public int getTempWorkersFemale() {
		return tempWorkersFemale;
	}
	/**
	 * @param tempWorkersFemale the tempWorkersFemale to set
	 */
	public void setTempWorkersFemale(int tempWorkersFemale) {
		this.tempWorkersFemale = tempWorkersFemale;
	}
	/**
	 * @return the tempWorkersMaleDrivers
	 */
	public int getTempWorkersMaleDrivers() {
		return tempWorkersMaleDrivers;
	}
	/**
	 * @param tempWorkersMaleDrivers the tempWorkersMaleDrivers to set
	 */
	public void setTempWorkersMaleDrivers(int tempWorkersMaleDrivers) {
		this.tempWorkersMaleDrivers = tempWorkersMaleDrivers;
	}
	/**
	 * @return the tempWorkersFemaleDrivers
	 */
	public int getTempWorkersFemaleDrivers() {
		return tempWorkersFemaleDrivers;
	}
	/**
	 * @param tempWorkersFemaleDrivers the tempWorkersFemaleDrivers to set
	 */
	public void setTempWorkersFemaleDrivers(int tempWorkersFemaleDrivers) {
		this.tempWorkersFemaleDrivers = tempWorkersFemaleDrivers;
	}
	/**
	 * @return the tempWorkersMaleNonDerivers
	 */
	public int getTempWorkersMaleNonDerivers() {
		return tempWorkersMaleNonDerivers;
	}
	/**
	 * @param tempWorkersMaleNonDerivers the tempWorkersMaleNonDerivers to set
	 */
	public void setTempWorkersMaleNonDerivers(int tempWorkersMaleNonDerivers) {
		this.tempWorkersMaleNonDerivers = tempWorkersMaleNonDerivers;
	}
	/**
	 * @return the tempWorkersFemaleNonDrivers
	 */
	public int getTempWorkersFemaleNonDrivers() {
		return tempWorkersFemaleNonDrivers;
	}
	/**
	 * @param tempWorkersFemaleNonDrivers the tempWorkersFemaleNonDrivers to set
	 */
	public void setTempWorkersFemaleNonDrivers(int tempWorkersFemaleNonDrivers) {
		this.tempWorkersFemaleNonDrivers = tempWorkersFemaleNonDrivers;
	}
	/**
	 * @return the casualWorkersMale
	 */
	public int getCasualWorkersMale() {
		return casualWorkersMale;
	}
	/**
	 * @param casualWorkersMale the casualWorkersMale to set
	 */
	public void setCasualWorkersMale(int casualWorkersMale) {
		this.casualWorkersMale = casualWorkersMale;
	}
	/**
	 * @return the casualWorkersFemale
	 */
	public int getCasualWorkersFemale() {
		return casualWorkersFemale;
	}
	/**
	 * @param casualWorkersFemale the casualWorkersFemale to set
	 */
	public void setCasualWorkersFemale(int casualWorkersFemale) {
		this.casualWorkersFemale = casualWorkersFemale;
	}
	/**
	 * @return the casualWorkersMaleDrivers
	 */
	public int getCasualWorkersMaleDrivers() {
		return casualWorkersMaleDrivers;
	}
	/**
	 * @param casualWorkersMaleDrivers the casualWorkersMaleDrivers to set
	 */
	public void setCasualWorkersMaleDrivers(int casualWorkersMaleDrivers) {
		this.casualWorkersMaleDrivers = casualWorkersMaleDrivers;
	}
	/**
	 * @return the casualWorkersFemaleDrivers
	 */
	public int getCasualWorkersFemaleDrivers() {
		return casualWorkersFemaleDrivers;
	}
	/**
	 * @param casualWorkersFemaleDrivers the casualWorkersFemaleDrivers to set
	 */
	public void setCasualWorkersFemaleDrivers(int casualWorkersFemaleDrivers) {
		this.casualWorkersFemaleDrivers = casualWorkersFemaleDrivers;
	}
	/**
	 * @return the casualWorkersMaleNonDerivers
	 */
	public int getCasualWorkersMaleNonDerivers() {
		return casualWorkersMaleNonDerivers;
	}
	/**
	 * @param casualWorkersMaleNonDerivers the casualWorkersMaleNonDerivers to set
	 */
	public void setCasualWorkersMaleNonDerivers(int casualWorkersMaleNonDerivers) {
		this.casualWorkersMaleNonDerivers = casualWorkersMaleNonDerivers;
	}
	/**
	 * @return the casualWorkersFemaleNonDrivers
	 */
	public int getCasualWorkersFemaleNonDrivers() {
		return casualWorkersFemaleNonDrivers;
	}
	/**
	 * @param casualWorkersFemaleNonDrivers the casualWorkersFemaleNonDrivers to set
	 */
	public void setCasualWorkersFemaleNonDrivers(int casualWorkersFemaleNonDrivers) {
		this.casualWorkersFemaleNonDrivers = casualWorkersFemaleNonDrivers;
	}
	/**
	 * @return the apprenticeMale
	 */
	public int getApprenticeMale() {
		return apprenticeMale;
	}
	/**
	 * @param apprenticeMale the apprenticeMale to set
	 */
	public void setApprenticeMale(int apprenticeMale) {
		this.apprenticeMale = apprenticeMale;
	}
	/**
	 * @return the apprenticeFemale
	 */
	public int getApprenticeFemale() {
		return apprenticeFemale;
	}
	/**
	 * @param apprenticeFemale the apprenticeFemale to set
	 */
	public void setApprenticeFemale(int apprenticeFemale) {
		this.apprenticeFemale = apprenticeFemale;
	}
	/**
	 * @return the traineeMale
	 */
	public int getTraineeMale() {
		return traineeMale;
	}
	/**
	 * @param traineeMale the traineeMale to set
	 */
	public void setTraineeMale(int traineeMale) {
		this.traineeMale = traineeMale;
	}
	/**
	 * @return the traineeFemale
	 */
	public int getTraineeFemale() {
		return traineeFemale;
	}
	/**
	 * @param traineeFemale the traineeFemale to set
	 */
	public void setTraineeFemale(int traineeFemale) {
		this.traineeFemale = traineeFemale;
	}
	/**
	 * @return the familyMembersMalePaid
	 */
	public int getFamilyMembersMalePaid() {
		return familyMembersMalePaid;
	}
	/**
	 * @param familyMembersMalePaid the familyMembersMalePaid to set
	 */
	public void setFamilyMembersMalePaid(int familyMembersMalePaid) {
		this.familyMembersMalePaid = familyMembersMalePaid;
	}
	/**
	 * @return the familyMembersFemalePaid
	 */
	public int getFamilyMembersFemalePaid() {
		return familyMembersFemalePaid;
	}
	/**
	 * @param familyMembersFemalePaid the familyMembersFemalePaid to set
	 */
	public void setFamilyMembersFemalePaid(int familyMembersFemalePaid) {
		this.familyMembersFemalePaid = familyMembersFemalePaid;
	}
	/**
	 * @return the familyMembersMaleUnpaid
	 */
	public int getFamilyMembersMaleUnpaid() {
		return familyMembersMaleUnpaid;
	}
	/**
	 * @param familyMembersMaleUnpaid the familyMembersMaleUnpaid to set
	 */
	public void setFamilyMembersMaleUnpaid(int familyMembersMaleUnpaid) {
		this.familyMembersMaleUnpaid = familyMembersMaleUnpaid;
	}
	/**
	 * @return the familyMembersFemaleUnpaid
	 */
	public int getFamilyMembersFemaleUnpaid() {
		return familyMembersFemaleUnpaid;
	}
	/**
	 * @param familyMembersFemaleUnpaid the familyMembersFemaleUnpaid to set
	 */
	public void setFamilyMembersFemaleUnpaid(int familyMembersFemaleUnpaid) {
		this.familyMembersFemaleUnpaid = familyMembersFemaleUnpaid;
	}
	/**
	 * @return the permWorkersMaleLessThanOne
	 */
	public int getPermWorkersMaleLessThanOne() {
		return permWorkersMaleLessThanOne;
	}
	/**
	 * @param permWorkersMaleLessThanOne the permWorkersMaleLessThanOne to set
	 */
	public void setPermWorkersMaleLessThanOne(int permWorkersMaleLessThanOne) {
		this.permWorkersMaleLessThanOne = permWorkersMaleLessThanOne;
	}
	/**
	 * @return the permWorkersFemaleLessThanOne
	 */
	public int getPermWorkersFemaleLessThanOne() {
		return permWorkersFemaleLessThanOne;
	}
	/**
	 * @param permWorkersFemaleLessThanOne the permWorkersFemaleLessThanOne to set
	 */
	public void setPermWorkersFemaleLessThanOne(int permWorkersFemaleLessThanOne) {
		this.permWorkersFemaleLessThanOne = permWorkersFemaleLessThanOne;
	}
	/**
	 * @return the permWorkersMaleOneToFive
	 */
	public int getPermWorkersMaleOneToFive() {
		return permWorkersMaleOneToFive;
	}
	/**
	 * @param permWorkersMaleOneToFive the permWorkersMaleOneToFive to set
	 */
	public void setPermWorkersMaleOneToFive(int permWorkersMaleOneToFive) {
		this.permWorkersMaleOneToFive = permWorkersMaleOneToFive;
	}
	/**
	 * @return the permWorkersFemaleOneToFive
	 */
	public int getPermWorkersFemaleOneToFive() {
		return permWorkersFemaleOneToFive;
	}
	/**
	 * @param permWorkersFemaleOneToFive the permWorkersFemaleOneToFive to set
	 */
	public void setPermWorkersFemaleOneToFive(int permWorkersFemaleOneToFive) {
		this.permWorkersFemaleOneToFive = permWorkersFemaleOneToFive;
	}
	/**
	 * @return the permWorkersMaleFiveToTen
	 */
	public int getPermWorkersMaleFiveToTen() {
		return permWorkersMaleFiveToTen;
	}
	/**
	 * @param permWorkersMaleFiveToTen the permWorkersMaleFiveToTen to set
	 */
	public void setPermWorkersMaleFiveToTen(int permWorkersMaleFiveToTen) {
		this.permWorkersMaleFiveToTen = permWorkersMaleFiveToTen;
	}
	/**
	 * @return the permWorkersFemaleFiveToTen
	 */
	public int getPermWorkersFemaleFiveToTen() {
		return permWorkersFemaleFiveToTen;
	}
	/**
	 * @param permWorkersFemaleFiveToTen the permWorkersFemaleFiveToTen to set
	 */
	public void setPermWorkersFemaleFiveToTen(int permWorkersFemaleFiveToTen) {
		this.permWorkersFemaleFiveToTen = permWorkersFemaleFiveToTen;
	}
	/**
	 * @return the permWorkersMaleMoreThanTen
	 */
	public int getPermWorkersMaleMoreThanTen() {
		return permWorkersMaleMoreThanTen;
	}
	/**
	 * @param permWorkersMaleMoreThanTen the permWorkersMaleMoreThanTen to set
	 */
	public void setPermWorkersMaleMoreThanTen(int permWorkersMaleMoreThanTen) {
		this.permWorkersMaleMoreThanTen = permWorkersMaleMoreThanTen;
	}
	/**
	 * @return the permWorkersFemaleMoreThanTen
	 */
	public int getPermWorkersFemaleMoreThanTen() {
		return permWorkersFemaleMoreThanTen;
	}
	/**
	 * @param permWorkersFemaleMoreThanTen the permWorkersFemaleMoreThanTen to set
	 */
	public void setPermWorkersFemaleMoreThanTen(int permWorkersFemaleMoreThanTen) {
		this.permWorkersFemaleMoreThanTen = permWorkersFemaleMoreThanTen;
	}
	/**
	 * @return the ownSecurityGuards
	 */
	public char getOwnSecurityGuards() {
		return ownSecurityGuards;
	}
	/**
	 * @param ownSecurityGuards the ownSecurityGuards to set
	 */
	public void setOwnSecurityGuards(char ownSecurityGuards) {
		this.ownSecurityGuards = ownSecurityGuards;
	}
	/**
	 * @return the ownCleaningStaff
	 */
	public char getOwnCleaningStaff() {
		return ownCleaningStaff;
	}
	/**
	 * @param ownCleaningStaff the ownCleaningStaff to set
	 */
	public void setOwnCleaningStaff(char ownCleaningStaff) {
		this.ownCleaningStaff = ownCleaningStaff;
	}
	/**
	 * @return the hasIsmWorkers
	 */
	public char getHasIsmWorkers() {
		return hasIsmWorkers;
	}
	/**
	 * @param hasIsmWorkers the hasIsmWorkers to set
	 */
	public void setHasIsmWorkers(char hasIsmWorkers) {
		this.hasIsmWorkers = hasIsmWorkers;
	}
	/**
	 * @return the noOfIsmWorkers
	 */
	public int getNoOfIsmWorkers() {
		return noOfIsmWorkers;
	}
	/**
	 * @param noOfIsmWorkers the noOfIsmWorkers to set
	 */
	public void setNoOfIsmWorkers(int noOfIsmWorkers) {
		this.noOfIsmWorkers = noOfIsmWorkers;
	}
	/**
	 * @return the noOfIsmWorkersAdolscent
	 */
	public int getNoOfIsmWorkersAdolscent() {
		return noOfIsmWorkersAdolscent;
	}
	/**
	 * @param noOfIsmWorkersAdolscent the noOfIsmWorkersAdolscent to set
	 */
	public void setNoOfIsmWorkersAdolscent(int noOfIsmWorkersAdolscent) {
		this.noOfIsmWorkersAdolscent = noOfIsmWorkersAdolscent;
	}
	/**
	 * @return the noOfContractors
	 */
	public int getNoOfContractors() {
		return noOfContractors;
	}
	/**
	 * @param noOfContractors the noOfContractors to set
	 */
	public void setNoOfContractors(int noOfContractors) {
		this.noOfContractors = noOfContractors;
	}
	/**
	 * @return the noOfContractWorkersEngaged
	 */
	public int getNoOfContractWorkersEngaged() {
		return noOfContractWorkersEngaged;
	}
	/**
	 * @param noOfContractWorkersEngaged the noOfContractWorkersEngaged to set
	 */
	public void setNoOfContractWorkersEngaged(int noOfContractWorkersEngaged) {
		this.noOfContractWorkersEngaged = noOfContractWorkersEngaged;
	}
	/**
	 * @return the noOfDifferentContractors
	 */
	public int getNoOfDifferentContractors() {
		return noOfDifferentContractors;
	}
	/**
	 * @param noOfDifferentContractors the noOfDifferentContractors to set
	 */
	public void setNoOfDifferentContractors(int noOfDifferentContractors) {
		this.noOfDifferentContractors = noOfDifferentContractors;
	}
	/**
	 * @return the contractWorkersFromContractors
	 */
	public int getContractWorkersFromContractors() {
		return contractWorkersFromContractors;
	}
	/**
	 * @param contractWorkersFromContractors the contractWorkersFromContractors to set
	 */
	public void setContractWorkersFromContractors(int contractWorkersFromContractors) {
		this.contractWorkersFromContractors = contractWorkersFromContractors;
	}
	/**
	 * @return the workForceId
	 */
	public long getWorkForceId() {
		return workForceId;
	}
	/**
	 * @param workForceId the workForceId to set
	 */
	public void setWorkForceId(long workForceId) {
		this.workForceId = workForceId;
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
	
	
	
	
}
