/**
 * @author Manish sakpal
 * created on 11th Feb 2011
 */


package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.LMS.AccidentDetails;
import org.paradyne.lib.ModelBase;

public class AccidentDetailsModel extends ModelBase {
	
	public void getInitialList(AccidentDetails accBean) {
		try {
			String accidentListQuery = "SELECT ACCIDENT_CODE,DECODE(ACC_CASUALITY_TYPE,'A','Accident','N','Nearmiss'),"
										+" DECODE(ACCIDENT_TYPE,'F','Fatal','N','Non-Fatal'),ACCIDENT_PLACE,"
										+" TO_CHAR(ACCIDENT_DATE,'DD-MM-YYYY'),TO_CHAR(ACCIDENT_TIME,'HH:MI') FROM HRMS_ACCIDENT_HDR ORDER BY ACCIDENT_CODE";
			Object[][] accidenListObj = getSqlModel().getSingleResult(
					accidentListQuery);
			if (accidenListObj != null && accidenListObj.length > 0) {
				accBean.setAccidentListLength(true);
				ArrayList<Object> innerList = new ArrayList<Object>();
				for (int i = 0; i < accidenListObj.length; i++) {
					AccidentDetails innerbean = new AccidentDetails(); 
					
					innerbean.setHiddenAccidentListID(checkNull(String.valueOf(accidenListObj[i][0])));
					innerbean.setCasualType(checkNull(String.valueOf((accidenListObj[i][1]))));
					innerbean.setAccidentType(checkNull(String.valueOf((accidenListObj[i][2]))));
					innerbean.setAccidentPlace(checkNull(String.valueOf((accidenListObj[i][3]))));
					innerbean.setAccidentDate(checkNull(String.valueOf((accidenListObj[i][4]))));
					innerbean.setAccidentTime(checkNull(String.valueOf((accidenListObj[i][5]))));
					innerList.add(innerbean);
				}
				accBean.setAccidentIteratorList(innerList);
			} else {
				accBean.setAccidentListLength(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addVictimDetails(AccidentDetails accBean, HttpServletRequest request, String[] serialNum, 
			String[] victimIDItr,String[] victimNameItr, 
			String[] victimTokenItr, String[] injuryDetailsItr, String[] bodyPartsAffectedItr, 
			String[] dateOfDeathItr, String[] timeOfDeathItr, String[] statusItr, 
			String[] legalHiersEmployedItr, String[] nameOfHiersItr, String[] relationShipItr, 
			String[] insuranceNumberItr, String[] meansOfHospitalizationItr, String[] workedPerformedItr, 
			String[] protectiveMeasuresItr, String[] amtOfCompensationItr, String[] witnessID, String[] witnessName, String[] witnessAddress, String[] witnessOccupation) {
		try {
			accBean.setVictimListLength(true);
			ArrayList<Object> victimList = new ArrayList<Object>();
			ArrayList<AccidentDetails> witnessList = new ArrayList<AccidentDetails>();
			// For already existing Records
			if(victimNameItr!=null)
			{
				for (int i = 0; i < victimNameItr.length; i++) {
					AccidentDetails previousBean = new AccidentDetails();
					previousBean.setSerialNum(serialNum[i]);
					previousBean.setVictimIDItr(victimIDItr[i]);
					previousBean.setVictimTokenItr(victimTokenItr[i]);
					previousBean.setVictimNameItr(victimNameItr[i]);						
					previousBean.setStatusItr(statusItr[i]);
					previousBean.setInjuryDetailsItr(injuryDetailsItr[i]);
					previousBean.setBodyPartsAffectedItr(bodyPartsAffectedItr[i]);
					previousBean.setDateOfDeathItr(dateOfDeathItr[i]);
					previousBean.setTimeOfDeathItr(timeOfDeathItr[i]);
					previousBean.setLegalHiersEmployedItr(legalHiersEmployedItr[i]);
					previousBean.setNameOfHiersItr(nameOfHiersItr[i]);
					previousBean.setRelationShipItr(relationShipItr[i]);
					previousBean.setInsuranceNumberItr(insuranceNumberItr[i]);
					previousBean.setMeansOfHospitalizationItr(meansOfHospitalizationItr[i]);
					previousBean.setWorkedPerformedItr(workedPerformedItr[i]);
					previousBean.setProtectiveMeasuresItr(protectiveMeasuresItr[i]);
					previousBean.setAmtOfCompensationItr(amtOfCompensationItr[i]);
					victimList.add(previousBean);
				}
			}
			
			//For new record
			AccidentDetails currentBean = new AccidentDetails();
			currentBean.setSerialNum(String.valueOf(victimList.size() + 1));
			currentBean.setVictimIDItr(checkNull(String.valueOf(accBean.getVictimID())));
			currentBean.setVictimTokenItr(checkNull(String.valueOf(accBean.getVictimToken())));
			currentBean.setVictimNameItr(checkNull(String.valueOf(accBean.getVictimName())));
			if(accBean.getStatusRadio().equals("I"))
			{	
				currentBean.setStatusItr("Injured");
			}
			else
			if(accBean.getStatusRadio().equals("D"))
			{
				currentBean.setStatusItr("Death");
			}
			else
			{
				currentBean.setStatusItr("");
			}
			currentBean.setInjuryDetailsItr(checkNull(String.valueOf(accBean.getInjuryDetails())));
			currentBean.setBodyPartsAffectedItr(checkNull(String.valueOf(accBean.getBodyPartsAffected())));
			currentBean.setDateOfDeathItr(checkNull(String.valueOf(accBean.getDateOfDeath())));
			currentBean.setTimeOfDeathItr(checkNull(String.valueOf(accBean.getTimeOfDeath())));
			if(accBean.getLegalHeirsRadio().equals("Y"))
			{
				currentBean.setLegalHiersEmployedItr("Legal");
			}
			else
			if(accBean.getLegalHeirsRadio().equals("N"))
			{
				currentBean.setLegalHiersEmployedItr("Illegal");
			}
			else
			{
				currentBean.setLegalHiersEmployedItr("");
			}
			currentBean.setNameOfHiersItr(checkNull(String.valueOf(accBean.getNameOfHiers())));
			currentBean.setRelationShipItr(checkNull(String.valueOf(accBean.getRelationShip())));
			currentBean.setInsuranceNumberItr(checkNull(String.valueOf(accBean.getInsuranceNumber())));
			currentBean.setMeansOfHospitalizationItr(checkNull(String.valueOf(accBean.getMeansOfHospitalization())));
			currentBean.setWorkedPerformedItr(checkNull(String.valueOf(accBean.getWorkedPerformed())));
			currentBean.setProtectiveMeasuresItr(checkNull(String.valueOf(accBean.getProtectiveMeasures())));
			currentBean.setAmtOfCompensationItr(checkNull(String.valueOf(accBean.getAmtOfCompensation())));
			victimList.add(currentBean);
			accBean.setVictimDetailsList(victimList);
		
		
			// For previous Witness List
			try {
				if (witnessName != null) {
					for (int i = 0; i < witnessName.length; i++) {
						AccidentDetails previousBean = new AccidentDetails();
						//previousBean.setWitnessID(witnessID[i]);
						previousBean.setWitnessName(witnessName[i]);
						previousBean.setWitnessAddress(witnessAddress[i]);
						previousBean.setWitnessOccupation(witnessOccupation[i]);
						witnessList.add(previousBean);
					}
					accBean.setWitnessDetailsList(witnessList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
			// For previous Witness List	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public String checkNullForNumber(String result)
	{
		if(result==null || result.equals("") || result.equals("null"))
		{
			return "0";
		}
		else
		{
			return result;
		}
	}

	public boolean removeVictimRecord(AccidentDetails accBean,
			HttpServletRequest request, String[] serialNum,
			String[] victimIDItr,String[] victimNameItr,
			String[] victimTokenItr, String[] injuryDetailsItr,
			String[] bodyPartsAffectedItr, String[] dateOfDeathItr,
			String[] timeOfDeathItr, String[] statusItr,
			String[] legalHiersEmployedItr, String[] nameOfHiersItr,
			String[] relationShipItr, String[] insuranceNumberItr,
			String[] meansOfHospitalizationItr, String[] workedPerformedItr,
			String[] protectiveMeasuresItr, String[] amtOfCompensationItr) {
		boolean result = false;
		try {
			accBean.setVictimListLength(true);
			ArrayList<Object> delList = new ArrayList<Object>();
			if (serialNum != null && serialNum.length > 0) {
				for (int i = 0; i < serialNum.length; i++) {
					AccidentDetails localbean = new AccidentDetails();
					localbean.setSerialNum(serialNum[i]);
					localbean.setVictimIDItr(victimIDItr[i]);
					localbean.setVictimTokenItr(victimTokenItr[i]);
					localbean.setVictimNameItr(victimNameItr[i]);						
					localbean.setStatusItr(statusItr[i]);
					localbean.setInjuryDetailsItr(injuryDetailsItr[i]);
					localbean.setBodyPartsAffectedItr(bodyPartsAffectedItr[i]);
					localbean.setDateOfDeathItr(dateOfDeathItr[i]);
					localbean.setTimeOfDeathItr(timeOfDeathItr[i]);
					localbean.setLegalHiersEmployedItr(legalHiersEmployedItr[i]);
					localbean.setNameOfHiersItr(nameOfHiersItr[i]);
					localbean.setRelationShipItr(relationShipItr[i]);
					localbean.setInsuranceNumberItr(insuranceNumberItr[i]);
					localbean.setMeansOfHospitalizationItr(meansOfHospitalizationItr[i]);
					localbean.setWorkedPerformedItr(workedPerformedItr[i]);
					localbean.setProtectiveMeasuresItr(protectiveMeasuresItr[i]);
					localbean.setAmtOfCompensationItr(amtOfCompensationItr[i]);
					
					
					delList.add(localbean);
					result = true;
				}
				delList.remove(Integer.parseInt(accBean.getCheckDelete()) - 1);
			}
			
			accBean.setVictimDetailsList(delList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean insertRecords(AccidentDetails accBean,
			HttpServletRequest request, String[] victimIDItr,
			String[] victimNameItr,
			String[] victimTokenItr, String[] injuryDetailsItr,
			String[] bodyPartsAffectedItr, String[] dateOfDeathItr,
			String[] timeOfDeathItr, String[] statusItr,
			String[] legalHiersEmployedItr, String[] nameOfHiersItr,
			String[] relationShipItr, String[] insuranceNumberItr,
			String[] meansOfHospitalizationItr, String[] workedPerformedItr,
			String[] protectiveMeasuresItr, String[] amtOfCompensationItr, String[] witnessID, 
			String[] witnessName, String[] witnessAddress, String[] witnessOccupation) {
		boolean saveAccidentHdrResult = false;
		boolean saveVictimResult = false;
		boolean saveWitnessResult = false;
		boolean finalResult = false;
		
		
			try {
				Object[][] addAccidentHeader = null;
				Object[][] addVictim = null;
				Object[][] addWitness = null;
				//Incremented ID for accid H
				Object maxCodeAccidentObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(ACCIDENT_CODE),0) FROM HRMS_ACCIDENT_HDR");
				int maxCodeAccident = Integer.parseInt(""
						+ maxCodeAccidentObj[0][0]);
				Object maxCodeVictimObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(VICTIM_CODE),0) FROM HRMS_ACCIDENT_VICTMS");
				int maxCodeVictim = Integer.parseInt(""
						+ maxCodeVictimObj[0][0]);
				Object maxCodeWitnessObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(WITNESS_ID),0) FROM HRMS_ACCIDENT_WITNESS");
				int maxCodeWitness = Integer.parseInt(""
						+ maxCodeWitnessObj[0][0]);
				
				// Inserting records into HRMS_ACCIDENT_HDR Begins
				addAccidentHeader = new Object[1][12];
				addAccidentHeader[0][0] = ++maxCodeAccident;
				if (accBean.getCasualTypeRadio().equals("Ac")) {
					addAccidentHeader[0][1] = "A";
				} else {
					addAccidentHeader[0][1] = "N";
				}
				addAccidentHeader[0][2] = checkNull(accBean.getAccidentType());
				addAccidentHeader[0][3] = checkNull(accBean.getAccidentPlace());
				addAccidentHeader[0][4] = checkNull(accBean.getAccidentDate());
				addAccidentHeader[0][5] = checkNull(accBean.getAccidentTime());
				addAccidentHeader[0][6] = checkNull(accBean
						.getCauseOfAccident());
				addAccidentHeader[0][7] = checkNull(accBean.getInvestigatedBy());
				addAccidentHeader[0][8] = checkNull(accBean
						.getGeneralLocation());
				addAccidentHeader[0][9] = checkNull(accBean
						.getSpecificLocation());
				addAccidentHeader[0][10] = checkNull(accBean
						.getInvestigationMeans());
				addAccidentHeader[0][11] = checkNull(accBean
						.getPreventiveMeasure());
				saveAccidentHdrResult = getSqlModel().singleExecute(
						getQuery(1), addAccidentHeader);
				// Inserting records into HRMS_ACCIDENT_HDR Ends
				
				if (saveAccidentHdrResult) {
					String maxQuery = "SELECT MAX(ACCIDENT_CODE) FROM HRMS_ACCIDENT_HDR";
					Object[][] data = getSqlModel().getSingleResult(maxQuery);
					accBean.setAccidentID(String.valueOf(data[0][0]));
					
				// Inserting Records into HRMS_ACCIDENT_VICTMS Begins
					int k = 0;
					
					if(victimNameItr !=null && victimNameItr.length>0)
					{
						addVictim = new Object[victimNameItr.length][16];
						for (int i = 0; i < victimNameItr.length; i++) {
							addVictim[k][0] = ++maxCodeVictim;
							addVictim[k][1] = data[0][0];
							addVictim[k][2] = checkNull(String.valueOf(victimIDItr[i]));
							if(checkNull(String.valueOf(statusItr[i])).equals("Injured"))
							{
								addVictim[k][3] = "I";
							}
							else
							if(checkNull(String.valueOf(statusItr[i])).equals("Death"))
							{
								addVictim[k][3] = "D";
							}
							else
							{
								addVictim[k][3] = "";
							}
							addVictim[k][4] = checkNull(String.valueOf(injuryDetailsItr[i]));
							addVictim[k][5] = checkNull(String.valueOf(bodyPartsAffectedItr[i]));
							addVictim[k][6] = checkNull(String.valueOf(dateOfDeathItr[i]));
							addVictim[k][7] = checkNull(String.valueOf(timeOfDeathItr[i]));
							addVictim[k][8] = checkNull(String.valueOf(insuranceNumberItr[i]));
							addVictim[k][9] = checkNullForNumber(String.valueOf(amtOfCompensationItr[i]));
							if(checkNull(String.valueOf(legalHiersEmployedItr[i])).equals("Legal"))
							{
								addVictim[k][10] = "L";
							}
							else
							if(checkNull(String.valueOf(legalHiersEmployedItr[i])).equals("Illegal"))
							{
								addVictim[k][10] = "I";
							}
							else
							{
								addVictim[k][10] = "";
							}
							addVictim[k][11] = checkNull(String.valueOf(nameOfHiersItr[i]));
							addVictim[k][12] = checkNull(String.valueOf(relationShipItr[i]));
							addVictim[k][13] = checkNull(String.valueOf(meansOfHospitalizationItr[i]));
							addVictim[k][14] = checkNull(String.valueOf(workedPerformedItr[i]));
							addVictim[k][15] = checkNull(String.valueOf(protectiveMeasuresItr[i]));
							k++;
						}
						saveVictimResult = getSqlModel().singleExecute(getQuery(2),	addVictim);
					}
				// Inserting Records into HRMS_ACCIDENT_VICTMS Ends
					
				// Inserting Records into HRMS_ACCIDENT_WITNESS Begins
					int m = 0;
					
					if(witnessName !=null && witnessName.length>0)
					{
						addWitness = new Object[witnessName.length][5];
						for (int i = 0; i < witnessName.length; i++) {
							addWitness[m][0] = ++maxCodeWitness;
							addWitness[m][1] = data[0][0];
							addWitness[m][2] = checkNull(String.valueOf(witnessName[i]));
							addWitness[m][3] = checkNull(String.valueOf(witnessAddress[i]));
							addWitness[m][4] = checkNull(String.valueOf(witnessOccupation[i]));
							m++;
						}
						saveWitnessResult = getSqlModel().singleExecute(getQuery(3),	addWitness);
					}
					
				// Inserting Records into HRMS_ACCIDENT_WITNESS Ends	
					
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (saveAccidentHdrResult & saveVictimResult
					& saveWitnessResult) {
				finalResult = true;
			}
		return finalResult;
		
	}

	public boolean updateRecords(AccidentDetails accBean,
			HttpServletRequest request, String[] victimIDItr,
			String[] victimNameItr,
			String[] victimTokenItr, String[] injuryDetailsItr,
			String[] bodyPartsAffectedItr, String[] dateOfDeathItr,
			String[] timeOfDeathItr, String[] statusItr,
			String[] legalHiersEmployedItr, String[] nameOfHiersItr,
			String[] relationShipItr, String[] insuranceNumberItr,
			String[] meansOfHospitalizationItr, String[] workedPerformedItr,
			String[] protectiveMeasuresItr, String[] amtOfCompensationItr, String[] witnessID,
			String[] witnessName, String[] witnessAddress, String[] witnessOccupation) {
		
		boolean saveAccidentHdrResult = false;
		boolean saveVictimResult = false;
		boolean saveWitnessResult = false;
		boolean finalResult = false;
		
		boolean delWitnessResult = false;
		boolean delVictimResult = false;
		boolean delAccidentHeaderResult = false;
			try
			{
				
				String deleteWitnessQuery = "DELETE FROM HRMS_ACCIDENT_WITNESS WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
				delWitnessResult = getSqlModel().singleExecute(deleteWitnessQuery);
				if(delWitnessResult)
				{
					String delVictimQuery = "DELETE FROM HRMS_ACCIDENT_VICTMS WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
					delVictimResult = getSqlModel().singleExecute(delVictimQuery);
					if(delVictimResult)	
					{
						String delAccidentHeaderQuery = "DELETE FROM HRMS_ACCIDENT_HDR WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
						delAccidentHeaderResult = getSqlModel().singleExecute(delAccidentHeaderQuery);
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(delWitnessResult & delVictimResult & delAccidentHeaderResult)
			{
			try {
				Object[][] addAccidentHeader = null;
				Object[][] addVictim = null;
				Object[][] addWitness = null;
				//Incremented ID for accid H
				Object maxCodeAccidentObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(ACCIDENT_CODE),0) FROM HRMS_ACCIDENT_HDR");
				int maxCodeAccident = Integer.parseInt(""
						+ maxCodeAccidentObj[0][0]);
				Object maxCodeVictimObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(VICTIM_CODE),0) FROM HRMS_ACCIDENT_VICTMS");
				int maxCodeVictim = Integer.parseInt(""
						+ maxCodeVictimObj[0][0]);
				Object maxCodeWitnessObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(WITNESS_ID),0) FROM HRMS_ACCIDENT_WITNESS");
				int maxCodeWitness = Integer.parseInt(""
						+ maxCodeWitnessObj[0][0]);
				
				// Inserting records into HRMS_ACCIDENT_HDR Begins
				addAccidentHeader = new Object[1][12];
				addAccidentHeader[0][0] = ++maxCodeAccident;
				if (accBean.getCasualTypeRadio().equals("Ac")) {
					addAccidentHeader[0][1] = "A";
				} else {
					addAccidentHeader[0][1] = "N";
				}
				addAccidentHeader[0][2] = checkNull(accBean.getAccidentType());
				addAccidentHeader[0][3] = checkNull(accBean.getAccidentPlace());
				addAccidentHeader[0][4] = checkNull(accBean.getAccidentDate());
				addAccidentHeader[0][5] = checkNull(accBean.getAccidentTime());
				addAccidentHeader[0][6] = checkNull(accBean
						.getCauseOfAccident());
				addAccidentHeader[0][7] = checkNull(accBean.getInvestigatedBy());
				addAccidentHeader[0][8] = checkNull(accBean
						.getGeneralLocation());
				addAccidentHeader[0][9] = checkNull(accBean
						.getSpecificLocation());
				addAccidentHeader[0][10] = checkNull(accBean
						.getInvestigationMeans());
				addAccidentHeader[0][11] = checkNull(accBean
						.getPreventiveMeasure());
				saveAccidentHdrResult = getSqlModel().singleExecute(
						getQuery(1), addAccidentHeader);
				// Inserting records into HRMS_ACCIDENT_HDR Ends
				
				if (saveAccidentHdrResult) {
					String maxQuery = "SELECT MAX(ACCIDENT_CODE) FROM HRMS_ACCIDENT_HDR";
					Object[][] data = getSqlModel().getSingleResult(maxQuery);
					accBean.setAccidentID(String.valueOf(data[0][0]));
					
				// Inserting Records into HRMS_ACCIDENT_VICTMS Begins
					int k = 0;
					
					if(victimNameItr !=null && victimNameItr.length>0)
					{
						addVictim = new Object[victimNameItr.length][16];
						for (int i = 0; i < victimNameItr.length; i++) {
							addVictim[k][0] = ++maxCodeVictim;
							addVictim[k][1] = data[0][0];
							addVictim[k][2] = checkNull(String.valueOf(victimIDItr[i]));
							if(checkNull(String.valueOf(statusItr[i])).equals("Injured"))
							{
								addVictim[k][3] = "I";
							}
							else
							if(checkNull(String.valueOf(statusItr[i])).equals("Death"))
							{
								addVictim[k][3] = "D";
							}
							else
							{
								addVictim[k][3] = "";
							}
							addVictim[k][4] = checkNull(String.valueOf(injuryDetailsItr[i]));
							addVictim[k][5] = checkNull(String.valueOf(bodyPartsAffectedItr[i]));
							addVictim[k][6] = checkNull(String.valueOf(dateOfDeathItr[i]));
							addVictim[k][7] = checkNull(String.valueOf(timeOfDeathItr[i]));
							addVictim[k][8] = checkNull(String.valueOf(insuranceNumberItr[i]));
							addVictim[k][9] = checkNullForNumber(String.valueOf(amtOfCompensationItr[i]));
							if(checkNull(String.valueOf(legalHiersEmployedItr[i])).equals("Legal"))
							{
								addVictim[k][10] = "L";
							}
							else
							if(checkNull(String.valueOf(legalHiersEmployedItr[i])).equals("Illegal"))
							{
								addVictim[k][10] = "I";
							}
							else
							{
								addVictim[k][10] = "";
							}
							addVictim[k][11] = checkNull(String.valueOf(nameOfHiersItr[i]));
							addVictim[k][12] = checkNull(String.valueOf(relationShipItr[i]));
							addVictim[k][13] = checkNull(String.valueOf(meansOfHospitalizationItr[i]));
							addVictim[k][14] = checkNull(String.valueOf(workedPerformedItr[i]));
							addVictim[k][15] = checkNull(String.valueOf(protectiveMeasuresItr[i]));
							k++;
						
						}
						saveVictimResult = getSqlModel().singleExecute(getQuery(2),	addVictim);
					}
				// Inserting Records into HRMS_ACCIDENT_VICTMS Ends
					
				
				// Inserting Records into HRMS_ACCIDENT_WITNESS Begins
					int m = 0;
					if(witnessName !=null && witnessName.length>0)
					{
						addWitness = new Object[witnessName.length][5];
						for (int i = 0; i < witnessName.length; i++) {
							addWitness[m][0] = ++maxCodeWitness;
							addWitness[m][1] = data[0][0];
							addWitness[m][2] = checkNull(String.valueOf(witnessName[i]));
							addWitness[m][3] = checkNull(String.valueOf(witnessAddress[i]));
							addWitness[m][4] = checkNull(String.valueOf(witnessOccupation[i]));
							m++;
						}
						saveWitnessResult = getSqlModel().singleExecute(getQuery(3),	addWitness);
					}
				// Inserting Records into HRMS_ACCIDENT_WITNESS Ends	
					
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			if (saveAccidentHdrResult & saveVictimResult
					& saveWitnessResult) {
				finalResult = true;
			}
		return finalResult;
	}


	public void calforedit(AccidentDetails accBean, String accidentID) {
		try {
			//For setting Accident Header Begins
			String accidentHeaderQuery = "SELECT ACCIDENT_CODE, NVL(ACC_CASUALITY_TYPE,''), NVL(ACCIDENT_TYPE,''), NVL(ACCIDENT_PLACE,''),"
										+" NVL(TO_CHAR(ACCIDENT_DATE,'DD-MM-YYYY'),''), NVL(TO_CHAR(ACCIDENT_TIME,'HH:MI'),''), NVL(ACCIDENT_CAUSE,'')," 
										+" NVL(IS_ACCIDENT_INVESTIGATED,''),NVL(ACC_GENERAL_LOCATION,''), NVL(ACC_SPECIFIC_LOCATION,''), " 
										+" NVL(ACC_INVESTIGATION_MEANS,''), NVL(ACC_PREVENTIVE_MEASURES,'') " 
										+" FROM HRMS_ACCIDENT_HDR WHERE ACCIDENT_CODE="+accidentID;
			Object[][] accidentHeaderObj = getSqlModel().getSingleResult(accidentHeaderQuery);
			if(accidentHeaderObj !=null && accidentHeaderObj.length>0)
			{
				accBean.setAccidentID(checkNull(String.valueOf(accidentHeaderObj[0][0])));
				accBean.setCasualTypeRadio(checkNull(String.valueOf(accidentHeaderObj[0][1])));
				if(checkNull(String.valueOf(accidentHeaderObj[0][1])).equals("N"))
				{
					accBean.setCasualType("Ne");
				}
				if(checkNull(String.valueOf(accidentHeaderObj[0][1])).equals("A"))
				{
					accBean.setCasualType("Ac");
				}
				
				accBean.setAccidentType(checkNull(String.valueOf(accidentHeaderObj[0][2])));				
				accBean.setAccidentPlace(checkNull(String.valueOf(accidentHeaderObj[0][3])));
				accBean.setAccidentDate(checkNull(String.valueOf(accidentHeaderObj[0][4])));
				accBean.setAccidentTime(checkNull(String.valueOf(accidentHeaderObj[0][5])));
				accBean.setCauseOfAccident(checkNull(String.valueOf(accidentHeaderObj[0][6])));
				accBean.setInvestigatedBy(checkNull(String.valueOf(accidentHeaderObj[0][7])));
				accBean.setGeneralLocation(checkNull(String.valueOf(accidentHeaderObj[0][8])));
				accBean.setSpecificLocation(checkNull(String.valueOf(accidentHeaderObj[0][9])));
				accBean.setInvestigationMeans(checkNull(String.valueOf(accidentHeaderObj[0][10])));
				accBean.setPreventiveMeasure(checkNull(String.valueOf(accidentHeaderObj[0][11])));
			}
			//For setting Accident Header Ends
			
			// For setting Victim Details Begins
			String accidentVictimsQuery = "SELECT  NVL(VICTIM_CODE,0),NVL(VICTIM_EMP_ID,0),NVL(EMP_TOKEN,''),NVL(EMP_FNAME ||'  '|| EMP_LNAME,''), "
										  +" NVL(DECODE(VICTIM_STATUS,'I','Injured','D','Death'),''), "
										  +" NVL(VICTIM_INJURY_DTL,''),NVL(VICTIM_BODY_PARTS_AFFCTD,''),NVL(TO_CHAR(DEATH_DATE,'DD-MM-YYYY'),'')," 
										  +" NVL(TO_CHAR(DEATH_TIME,'HH:MI'),''),NVL(VICTIM_INSURANCE_NO,''),NVL(VICTIM_COMPENSATION_AMT,0), " 
										  +" NVL(DECODE(IS_LEGAL_HEIR_EMPLOYED,'L','Legal','I','Illegal'),''), NVL(HEIR_NAME,''),"
										  +" NVL(HEIR_RELATIONSHIP,''),NVL(VICTIM_HOSPITIZATION,''),NVL(ACC_WORK_PERFORMED,''), " 
										  +" NVL(ACC_PROTECTIVE_MEASURES,'') FROM HRMS_ACCIDENT_VICTMS " 
										  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ACCIDENT_VICTMS.VICTIM_EMP_ID) "
										  +" WHERE ACCIDENT_CODE="+accidentID;
			Object[][] accidentVictimObj = getSqlModel().getSingleResult(accidentVictimsQuery);
			ArrayList<Object> victimList = new ArrayList<Object>();
			if(accidentVictimObj !=null && accidentVictimObj.length>0)
			{
				accBean.setVictimListLength(true);
				for (int i = 0; i < accidentVictimObj.length; i++) {
					AccidentDetails innerBean = new AccidentDetails();					
					innerBean.setVictimHiddenCodeItr(checkNull(String.valueOf(accidentVictimObj[i][0])));
					innerBean.setVictimIDItr(checkNull(String.valueOf(accidentVictimObj[i][1])));
					innerBean.setVictimTokenItr(checkNull(String.valueOf(accidentVictimObj[i][2])));
					innerBean.setVictimNameItr(checkNull(String.valueOf(accidentVictimObj[i][3])));
					innerBean.setStatusItr(checkNull(String.valueOf(accidentVictimObj[i][4])));
					innerBean.setInjuryDetailsItr(checkNull(String.valueOf(accidentVictimObj[i][5])));
					innerBean.setBodyPartsAffectedItr(checkNull(String.valueOf(accidentVictimObj[i][6])));
					innerBean.setDateOfDeathItr(checkNull(String.valueOf(accidentVictimObj[i][7])));
					innerBean.setTimeOfDeathItr(checkNull(String.valueOf(accidentVictimObj[i][8])));
					innerBean.setInsuranceNumberItr(checkNull(String.valueOf(accidentVictimObj[i][9])));
					innerBean.setAmtOfCompensationItr(checkNull(String.valueOf(accidentVictimObj[i][10])));
					innerBean.setLegalHiersEmployedItr(checkNull(String.valueOf(accidentVictimObj[i][11])));
					innerBean.setNameOfHiersItr(checkNull(String.valueOf(accidentVictimObj[i][12])));
					innerBean.setRelationShipItr(checkNull(String.valueOf(accidentVictimObj[i][13])));
					innerBean.setMeansOfHospitalizationItr(checkNull(String.valueOf(accidentVictimObj[i][14])));
					innerBean.setWorkedPerformedItr(checkNull(String.valueOf(accidentVictimObj[i][15])));
					innerBean.setProtectiveMeasuresItr(checkNull(String.valueOf(accidentVictimObj[i][16])));
					victimList.add(innerBean);
					
				}
				accBean.setVictimDetailsList(victimList);
			}
			else
			{
				accBean.setVictimListLength(false);
			}
			// For setting Victim Details Ends
			
			
			//For setting Witness Details Begins
			String accidentWitnessQuery = "SELECT  WITNESS_ID,NVL(WITNESS_NAME,''),NVL(WITNESS_ADDRESS,''),NVL(WITNESS_OCCUPATION,'') "
										  +" FROM HRMS_ACCIDENT_WITNESS WHERE ACCIDENT_CODE="+accidentID;
			Object[][] accidentWitnessObj = getSqlModel().getSingleResult(accidentWitnessQuery);
			ArrayList<AccidentDetails> witnessList = new ArrayList<AccidentDetails>();
			if(accidentVictimObj !=null && accidentVictimObj.length>0)
			{
				for (int i = 0; i < accidentWitnessObj.length; i++) {
					AccidentDetails innerBean = new AccidentDetails();
					innerBean.setWitnessID(checkNull(String.valueOf(accidentWitnessObj[i][0])));
					innerBean.setWitnessName(checkNull(String.valueOf(accidentWitnessObj[i][1])));
					innerBean.setWitnessAddress(checkNull(String.valueOf(accidentWitnessObj[i][2])));
					innerBean.setWitnessOccupation(checkNull(String.valueOf(accidentWitnessObj[i][3])));
					witnessList.add(innerBean);
				}
				accBean.setWitnessDetailsList(witnessList);
			}
			
			//For setting Witness Details Ends
	} catch (Exception e) {
		e.printStackTrace();
	}
 }


	public void setAccidentRecord(AccidentDetails accBean) {
		try {
				//For setting Accident Header Begins
				String accidentHeaderQuery = "SELECT ACCIDENT_CODE, NVL(ACC_CASUALITY_TYPE,''), NVL(ACCIDENT_TYPE,''), NVL(ACCIDENT_PLACE,''),"
											+" NVL(TO_CHAR(ACCIDENT_DATE,'DD-MM-YYYY'),''), NVL(TO_CHAR(ACCIDENT_TIME,'HH:MI'),''), NVL(ACCIDENT_CAUSE,'')," 
											+" NVL(IS_ACCIDENT_INVESTIGATED,''),NVL(ACC_GENERAL_LOCATION,''), NVL(ACC_SPECIFIC_LOCATION,''), " 
											+" NVL(ACC_INVESTIGATION_MEANS,''), NVL(ACC_PREVENTIVE_MEASURES,'') " 
											+" FROM HRMS_ACCIDENT_HDR WHERE ACCIDENT_CODE="+accBean.getAccidentID();
				Object[][] accidentHeaderObj = getSqlModel().getSingleResult(accidentHeaderQuery);
				if(accidentHeaderObj !=null && accidentHeaderObj.length>0)
				{
					accBean.setAccidentID(checkNull(String.valueOf(accidentHeaderObj[0][0])));
					accBean.setCasualTypeRadio(checkNull(String.valueOf(accidentHeaderObj[0][1])));
					accBean.setAccidentType(checkNull(String.valueOf(accidentHeaderObj[0][2])));
					accBean.setAccidentPlace(checkNull(String.valueOf(accidentHeaderObj[0][3])));
					accBean.setAccidentDate(checkNull(String.valueOf(accidentHeaderObj[0][4])));
					accBean.setAccidentTime(checkNull(String.valueOf(accidentHeaderObj[0][5])));
					accBean.setCauseOfAccident(checkNull(String.valueOf(accidentHeaderObj[0][6])));
					accBean.setInvestigatedBy(checkNull(String.valueOf(accidentHeaderObj[0][7])));
					accBean.setGeneralLocation(checkNull(String.valueOf(accidentHeaderObj[0][8])));
					accBean.setSpecificLocation(checkNull(String.valueOf(accidentHeaderObj[0][9])));
					accBean.setInvestigationMeans(checkNull(String.valueOf(accidentHeaderObj[0][10])));
					accBean.setPreventiveMeasure(checkNull(String.valueOf(accidentHeaderObj[0][11])));
				}
				//For setting Accident Header Ends
				
				// For setting Victim Details Begins
				String accidentVictimsQuery = "SELECT  NVL(VICTIM_CODE,0),NVL(VICTIM_EMP_ID,0),NVL(EMP_TOKEN,''),NVL(EMP_FNAME ||'  '|| EMP_LNAME,''),"
											  +" NVL(DECODE(VICTIM_STATUS,'I','Injured','D','Death'),''), " 
											  +" NVL(VICTIM_INJURY_DTL,''),NVL(VICTIM_BODY_PARTS_AFFCTD,''),NVL(TO_CHAR(DEATH_DATE,'DD-MM-YYYY'),'')," 
											  +" NVL(TO_CHAR(DEATH_TIME,'HH:MI'),''),NVL(VICTIM_INSURANCE_NO,''),NVL(VICTIM_COMPENSATION_AMT,0), " 
											  +" NVL(DECODE(IS_LEGAL_HEIR_EMPLOYED,'L','Legal','I','Illegal'),''), NVL(HEIR_NAME,''),"
											  +" NVL(HEIR_RELATIONSHIP,''),NVL(VICTIM_HOSPITIZATION,''),NVL(ACC_WORK_PERFORMED,''), " 
											  +" NVL(ACC_PROTECTIVE_MEASURES,'') FROM HRMS_ACCIDENT_VICTMS " 
											  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ACCIDENT_VICTMS.VICTIM_EMP_ID) "
											  +" WHERE ACCIDENT_CODE="+accBean.getAccidentID();
				Object[][] accidentVictimObj = getSqlModel().getSingleResult(accidentVictimsQuery);
				ArrayList<Object> victimList = new ArrayList<Object>();
				int count = 0;
				if(accidentVictimObj !=null && accidentVictimObj.length>0)
				{
					accBean.setVictimListLength(true);
					for (int i = 0; i < accidentVictimObj.length; i++) {
						AccidentDetails innerBean = new AccidentDetails();						
						innerBean.setVictimHiddenCodeItr(checkNull(String.valueOf(accidentVictimObj[i][0])));
						innerBean.setVictimIDItr(checkNull(String.valueOf(accidentVictimObj[i][1])));
						innerBean.setVictimTokenItr(checkNull(String.valueOf(accidentVictimObj[i][2])));
						innerBean.setVictimNameItr(checkNull(String.valueOf(accidentVictimObj[i][3])));
						innerBean.setStatusItr(checkNull(String.valueOf(accidentVictimObj[i][4])));
						innerBean.setInjuryDetailsItr(checkNull(String.valueOf(accidentVictimObj[i][5])));
						innerBean.setBodyPartsAffectedItr(checkNull(String.valueOf(accidentVictimObj[i][6])));
						innerBean.setDateOfDeathItr(checkNull(String.valueOf(accidentVictimObj[i][7])));
						innerBean.setTimeOfDeathItr(checkNull(String.valueOf(accidentVictimObj[i][8])));
						innerBean.setInsuranceNumberItr(checkNull(String.valueOf(accidentVictimObj[i][9])));
						innerBean.setAmtOfCompensationItr(checkNull(String.valueOf(accidentVictimObj[i][10])));
						innerBean.setLegalHiersEmployedItr(checkNull(String.valueOf(accidentVictimObj[i][11])));
						innerBean.setNameOfHiersItr(checkNull(String.valueOf(accidentVictimObj[i][12])));
						innerBean.setRelationShipItr(checkNull(String.valueOf(accidentVictimObj[i][13])));
						innerBean.setMeansOfHospitalizationItr(checkNull(String.valueOf(accidentVictimObj[i][14])));
						innerBean.setWorkedPerformedItr(checkNull(String.valueOf(accidentVictimObj[i][15])));
						innerBean.setProtectiveMeasuresItr(checkNull(String.valueOf(accidentVictimObj[i][16])));
						victimList.add(innerBean);
						
					}
					accBean.setVictimDetailsList(victimList);
				}
				else
				{
					accBean.setVictimListLength(false);
				}
				// For setting Victim Details Ends
				
				
				//For setting Witness Details Begins
				String accidentWitnessQuery = "SELECT  NVL(WITNESS_ID,0),NVL(WITNESS_NAME,''),NVL(WITNESS_ADDRESS,''),NVL(WITNESS_OCCUPATION,'') "
											  +" FROM HRMS_ACCIDENT_WITNESS WHERE ACCIDENT_CODE="+accBean.getAccidentID();
				Object[][] accidentWitnessObj = getSqlModel().getSingleResult(accidentWitnessQuery);
				ArrayList<AccidentDetails> witnessList = new ArrayList<AccidentDetails>();
				if(accidentVictimObj !=null && accidentVictimObj.length>0)
				{
					for (int i = 0; i < accidentWitnessObj.length; i++) {
						AccidentDetails innerBean = new AccidentDetails();
						innerBean.setWitnessID(checkNull(String.valueOf(accidentWitnessObj[i][0])));
						innerBean.setWitnessName(checkNull(String.valueOf(accidentWitnessObj[i][1])));
						innerBean.setWitnessAddress(checkNull(String.valueOf(accidentWitnessObj[i][2])));
						innerBean.setWitnessOccupation(checkNull(String.valueOf(accidentWitnessObj[i][3])));
						witnessList.add(innerBean);
					}
					accBean.setWitnessDetailsList(witnessList);
				}
				
				//For setting Witness Details Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean deleteVictimRecordFromDatabase(AccidentDetails accBean,
			String victimDeleteCode) {
		boolean result = false;
		try {
				String deleteQuery = "DELETE FROM HRMS_ACCIDENT_VICTMS WHERE VICTIM_CODE = "+victimDeleteCode;
				result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public boolean deleteWitnessRecordFromDatabase(AccidentDetails accBean,
			String witnessCode) {
		boolean result = false;
		try {
				String deleteQuery = "DELETE FROM HRMS_ACCIDENT_WITNESS WHERE WITNESS_ID = "+witnessCode;
				result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public boolean deleteAccidentRecord(AccidentDetails accBean) {
		boolean delWitnessResult = false;
		boolean delVictimResult = false;
		boolean delAccidentHeaderResult = false;
		boolean finalResult = false;
			try
			{
				String deleteWitnessQuery = "DELETE FROM HRMS_ACCIDENT_WITNESS WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
				delWitnessResult = getSqlModel().singleExecute(deleteWitnessQuery);
				if(delWitnessResult)
				{
					String delVictimQuery = "DELETE FROM HRMS_ACCIDENT_VICTMS WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
					delVictimResult = getSqlModel().singleExecute(delVictimQuery);
					if(delVictimResult)	
					{
						String delAccidentHeaderQuery = "DELETE FROM HRMS_ACCIDENT_HDR WHERE ACCIDENT_CODE ="+accBean.getAccidentID();
						delAccidentHeaderResult = getSqlModel().singleExecute(delAccidentHeaderQuery);
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(delWitnessResult & delVictimResult & delAccidentHeaderResult)
			{
				finalResult =  true;
			}
		return 	finalResult ;
	}


	public boolean deleteAccidentRecordFromList(AccidentDetails accBean,
			String deleteAccidentID) {
		boolean delWitnessResult = false;
		boolean delVictimResult = false;
		boolean delAccidentHeaderResult = false;
		boolean finalResult = false;
			try
			{
				String deleteWitnessQuery = "DELETE FROM HRMS_ACCIDENT_WITNESS WHERE ACCIDENT_CODE ="+deleteAccidentID;
				delWitnessResult = getSqlModel().singleExecute(deleteWitnessQuery);
				if(delWitnessResult)
				{
					String delVictimQuery = "DELETE FROM HRMS_ACCIDENT_VICTMS WHERE ACCIDENT_CODE ="+deleteAccidentID;
					delVictimResult = getSqlModel().singleExecute(delVictimQuery);
					if(delVictimResult)	
					{
						String delAccidentHeaderQuery = "DELETE FROM HRMS_ACCIDENT_HDR WHERE ACCIDENT_CODE ="+deleteAccidentID;
						delAccidentHeaderResult = getSqlModel().singleExecute(delAccidentHeaderQuery);
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(delWitnessResult & delVictimResult & delAccidentHeaderResult)
			{
				finalResult =  true;
			}
		return 	finalResult ;
	}

}
