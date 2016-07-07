/**
 * @author manish sakpal
 * created on 11th Feb. 2011
 */

package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AccidentDetails extends BeanBase {
	
	private String accidentID = "";
	private String hiddenAccidentListID = "";
	//Accident Details Begins
	private String casualType = "";
	private String casualTypeRadio = "";
	private String accidentType = "";
	private String accidentDate = "";
	private String accidentPlace = "";
	private String accidentTime = "";
	private String causeOfAccident = "";
	private String generalLocation = "";
	private String specificLocation = "";
	private String investigatedBy = "";
	private String investigationMeans = "";
	private String preventiveMeasure = "";
	//Accident Details Ends
	
	//Victim Details Begins
	private String victimID = "";
	private String victimToken = "";
	private String victimName = "";
	private String status = "";
	private String injuryDetails = "";
	private String bodyPartsAffected = "";
	private String dateOfDeath = "";
	private String timeOfDeath = "";
	private String insuranceNumber = "";
	private String amtOfCompensation = "";
	private String legalHiersEmployed = "";
	private String nameOfHiers = "";
	private String relationShip = "";
	private String meansOfHospitalization = "";
	private String workedPerformed = "";
	private String protectiveMeasures = "";
	//Victim Details Ends
	
	//Victim Iterator Begins
	ArrayList<Object> victimDetailsList = null;
	boolean victimListLength = false;
	boolean accidentListLength = false;
	ArrayList<Object> accidentIteratorList = null;
	private String serialNum = "";
	private String victimIDItr = "";
	private String victimNameItr = "";
	private String victimTokenItr = "";
	private String injuryDetailsItr = "";
	private String bodyPartsAffectedItr = "";
	private String dateOfDeathItr = "";
	private String timeOfDeathItr = "";
	private String statusItr = "";
	private String legalHiersEmployedItr = "";
	private String nameOfHiersItr = "";
	private String relationShipItr = "";
	private String insuranceNumberItr = "";
	private String meansOfHospitalizationItr = "";
	private String workedPerformedItr = "";
	private String protectiveMeasuresItr = "";
	private String amtOfCompensationItr = "";
	//Victim Iterator Ends
	
	
	//Witness Details Begins
	private String witnessID = "";
	private String witnessName = "";
	private String witnessAddress = "";
	private String witnessOccupation = "";
	//Witness Details Ends
	
	private String statusRadio = "";
	private String legalHeirsRadio = "";
	private String checkDelete = "";
	private String VictimHiddenCodeItr = "";
	ArrayList<AccidentDetails> witnessDetailsList= null;
	
	public ArrayList<AccidentDetails> getWitnessDetailsList() {
		return witnessDetailsList;
	}
	public void setWitnessDetailsList(ArrayList<AccidentDetails> witnessDetailsList) {
		this.witnessDetailsList = witnessDetailsList;
	}
	public String getAccidentID() {
		return accidentID;
	}
	public void setAccidentID(String accidentID) {
		this.accidentID = accidentID;
	}
	public String getCasualType() {
		return casualType;
	}
	public void setCasualType(String casualType) {
		this.casualType = casualType;
	}
	public String getCasualTypeRadio() {
		return casualTypeRadio;
	}
	public void setCasualTypeRadio(String casualTypeRadio) {
		this.casualTypeRadio = casualTypeRadio;
	}
	public String getAccidentType() {
		return accidentType;
	}
	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAccidentPlace() {
		return accidentPlace;
	}
	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}
	public String getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(String accidentTime) {
		this.accidentTime = accidentTime;
	}
	public String getCauseOfAccident() {
		return causeOfAccident;
	}
	public void setCauseOfAccident(String causeOfAccident) {
		this.causeOfAccident = causeOfAccident;
	}
	public String getGeneralLocation() {
		return generalLocation;
	}
	public void setGeneralLocation(String generalLocation) {
		this.generalLocation = generalLocation;
	}
	public String getSpecificLocation() {
		return specificLocation;
	}
	public void setSpecificLocation(String specificLocation) {
		this.specificLocation = specificLocation;
	}
	public String getInvestigatedBy() {
		return investigatedBy;
	}
	public void setInvestigatedBy(String investigatedBy) {
		this.investigatedBy = investigatedBy;
	}
	public String getInvestigationMeans() {
		return investigationMeans;
	}
	public void setInvestigationMeans(String investigationMeans) {
		this.investigationMeans = investigationMeans;
	}
	public String getPreventiveMeasure() {
		return preventiveMeasure;
	}
	public void setPreventiveMeasure(String preventiveMeasure) {
		this.preventiveMeasure = preventiveMeasure;
	}
	public String getVictimID() {
		return victimID;
	}
	public void setVictimID(String victimID) {
		this.victimID = victimID;
	}
	public String getVictimToken() {
		return victimToken;
	}
	public void setVictimToken(String victimToken) {
		this.victimToken = victimToken;
	}
	public String getVictimName() {
		return victimName;
	}
	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInjuryDetails() {
		return injuryDetails;
	}
	public void setInjuryDetails(String injuryDetails) {
		this.injuryDetails = injuryDetails;
	}
	public String getBodyPartsAffected() {
		return bodyPartsAffected;
	}
	public void setBodyPartsAffected(String bodyPartsAffected) {
		this.bodyPartsAffected = bodyPartsAffected;
	}
	public String getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getTimeOfDeath() {
		return timeOfDeath;
	}
	public void setTimeOfDeath(String timeOfDeath) {
		this.timeOfDeath = timeOfDeath;
	}
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getAmtOfCompensation() {
		return amtOfCompensation;
	}
	public void setAmtOfCompensation(String amtOfCompensation) {
		this.amtOfCompensation = amtOfCompensation;
	}
	public String getLegalHiersEmployed() {
		return legalHiersEmployed;
	}
	public void setLegalHiersEmployed(String legalHiersEmployed) {
		this.legalHiersEmployed = legalHiersEmployed;
	}
	public String getNameOfHiers() {
		return nameOfHiers;
	}
	public void setNameOfHiers(String nameOfHiers) {
		this.nameOfHiers = nameOfHiers;
	}
	public String getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}
	public String getMeansOfHospitalization() {
		return meansOfHospitalization;
	}
	public void setMeansOfHospitalization(String meansOfHospitalization) {
		this.meansOfHospitalization = meansOfHospitalization;
	}
	public String getWorkedPerformed() {
		return workedPerformed;
	}
	public void setWorkedPerformed(String workedPerformed) {
		this.workedPerformed = workedPerformed;
	}
	public String getProtectiveMeasures() {
		return protectiveMeasures;
	}
	public void setProtectiveMeasures(String protectiveMeasures) {
		this.protectiveMeasures = protectiveMeasures;
	}
	public ArrayList<Object> getVictimDetailsList() {
		return victimDetailsList;
	}
	public void setVictimDetailsList(ArrayList<Object> victimDetailsList) {
		this.victimDetailsList = victimDetailsList;
	}
	public boolean isVictimListLength() {
		return victimListLength;
	}
	public void setVictimListLength(boolean victimListLength) {
		this.victimListLength = victimListLength;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getVictimIDItr() {
		return victimIDItr;
	}
	public void setVictimIDItr(String victimIDItr) {
		this.victimIDItr = victimIDItr;
	}
	public String getVictimNameItr() {
		return victimNameItr;
	}
	public void setVictimNameItr(String victimNameItr) {
		this.victimNameItr = victimNameItr;
	}
	public String getVictimTokenItr() {
		return victimTokenItr;
	}
	public void setVictimTokenItr(String victimTokenItr) {
		this.victimTokenItr = victimTokenItr;
	}
	public String getInjuryDetailsItr() {
		return injuryDetailsItr;
	}
	public void setInjuryDetailsItr(String injuryDetailsItr) {
		this.injuryDetailsItr = injuryDetailsItr;
	}
	public String getBodyPartsAffectedItr() {
		return bodyPartsAffectedItr;
	}
	public void setBodyPartsAffectedItr(String bodyPartsAffectedItr) {
		this.bodyPartsAffectedItr = bodyPartsAffectedItr;
	}
	public String getDateOfDeathItr() {
		return dateOfDeathItr;
	}
	public void setDateOfDeathItr(String dateOfDeathItr) {
		this.dateOfDeathItr = dateOfDeathItr;
	}
	public String getTimeOfDeathItr() {
		return timeOfDeathItr;
	}
	public void setTimeOfDeathItr(String timeOfDeathItr) {
		this.timeOfDeathItr = timeOfDeathItr;
	}
	public String getStatusItr() {
		return statusItr;
	}
	public void setStatusItr(String statusItr) {
		this.statusItr = statusItr;
	}
	public String getLegalHiersEmployedItr() {
		return legalHiersEmployedItr;
	}
	public void setLegalHiersEmployedItr(String legalHiersEmployedItr) {
		this.legalHiersEmployedItr = legalHiersEmployedItr;
	}
	public String getNameOfHiersItr() {
		return nameOfHiersItr;
	}
	public void setNameOfHiersItr(String nameOfHiersItr) {
		this.nameOfHiersItr = nameOfHiersItr;
	}
	public String getRelationShipItr() {
		return relationShipItr;
	}
	public void setRelationShipItr(String relationShipItr) {
		this.relationShipItr = relationShipItr;
	}
	public String getInsuranceNumberItr() {
		return insuranceNumberItr;
	}
	public void setInsuranceNumberItr(String insuranceNumberItr) {
		this.insuranceNumberItr = insuranceNumberItr;
	}
	public String getMeansOfHospitalizationItr() {
		return meansOfHospitalizationItr;
	}
	public void setMeansOfHospitalizationItr(String meansOfHospitalizationItr) {
		this.meansOfHospitalizationItr = meansOfHospitalizationItr;
	}
	public String getWorkedPerformedItr() {
		return workedPerformedItr;
	}
	public void setWorkedPerformedItr(String workedPerformedItr) {
		this.workedPerformedItr = workedPerformedItr;
	}
	public String getProtectiveMeasuresItr() {
		return protectiveMeasuresItr;
	}
	public void setProtectiveMeasuresItr(String protectiveMeasuresItr) {
		this.protectiveMeasuresItr = protectiveMeasuresItr;
	}
	public String getAmtOfCompensationItr() {
		return amtOfCompensationItr;
	}
	public void setAmtOfCompensationItr(String amtOfCompensationItr) {
		this.amtOfCompensationItr = amtOfCompensationItr;
	}
	public String getWitnessID() {
		return witnessID;
	}
	public void setWitnessID(String witnessID) {
		this.witnessID = witnessID;
	}
	public String getWitnessName() {
		return witnessName;
	}
	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}
	public String getWitnessAddress() {
		return witnessAddress;
	}
	public void setWitnessAddress(String witnessAddress) {
		this.witnessAddress = witnessAddress;
	}
	public String getWitnessOccupation() {
		return witnessOccupation;
	}
	public void setWitnessOccupation(String witnessOccupation) {
		this.witnessOccupation = witnessOccupation;
	}
	public String getStatusRadio() {
		return statusRadio;
	}
	public void setStatusRadio(String statusRadio) {
		this.statusRadio = statusRadio;
	}
	public String getLegalHeirsRadio() {
		return legalHeirsRadio;
	}
	public void setLegalHeirsRadio(String legalHeirsRadio) {
		this.legalHeirsRadio = legalHeirsRadio;
	}
	public String getCheckDelete() {
		return checkDelete;
	}
	public void setCheckDelete(String checkDelete) {
		this.checkDelete = checkDelete;
	}
	public boolean isAccidentListLength() {
		return accidentListLength;
	}
	public void setAccidentListLength(boolean accidentListLength) {
		this.accidentListLength = accidentListLength;
	}
	public ArrayList<Object> getAccidentIteratorList() {
		return accidentIteratorList;
	}
	public void setAccidentIteratorList(ArrayList<Object> accidentIteratorList) {
		this.accidentIteratorList = accidentIteratorList;
	}
	public String getHiddenAccidentListID() {
		return hiddenAccidentListID;
	}
	public void setHiddenAccidentListID(String hiddenAccidentListID) {
		this.hiddenAccidentListID = hiddenAccidentListID;
	}
	public String getVictimHiddenCodeItr() {
		return VictimHiddenCodeItr;
	}
	public void setVictimHiddenCodeItr(String victimHiddenCodeItr) {
		VictimHiddenCodeItr = victimHiddenCodeItr;
	}
	
	
}