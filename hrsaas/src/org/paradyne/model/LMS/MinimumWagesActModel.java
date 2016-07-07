package org.paradyne.model.LMS;

/**
 * @author REEBA JOSEPH
 */

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.LMS.GeneralInfo;
import org.paradyne.bean.LMS.MinimumWages;
import org.paradyne.bean.LMS.WorkForceInfo;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class MinimumWagesActModel extends ModelBase {
	public boolean checkIfEmpTypeConfigured(){
		boolean result = true;
		String empTypeQuery = " SELECT TYPE_ID, NVL(LMS_EMP_TYPE,' ') FROM HRMS_EMP_TYPE ORDER BY TYPE_ID";
		Object[][] typeObj = getSqlModel().getSingleResult(empTypeQuery);
		for (int i = 0; i < typeObj.length; i++) {
			if(String.valueOf(typeObj[i][1]).trim().equals("")){
				result = false;
				break;
			}
			
		}
		return result;
	}
	
	public boolean checkIfRolesConfigured(){
		boolean result = true;
		String roleQuery = " SELECT RANK_ID, NVL(LMS_DESIGNATION,' ') FROM HRMS_RANK ORDER BY RANK_ID";
		Object[][] roleObj = getSqlModel().getSingleResult(roleQuery);
		for (int i = 0; i < roleObj.length; i++) {
			if(String.valueOf(roleObj[i][1]).trim().equals("")){
				result = false;
				break;
			}
			
		}
		return result;
	}
	
	public boolean checkIfDebitsConfigured(){
		boolean result = true;
		String debitQuery = " SELECT DEBIT_CODE, NVL(LMS_DEBIT_TYPE,' ') FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
		Object[][] debitObj = getSqlModel().getSingleResult(debitQuery);
		for (int i = 0; i < debitObj.length; i++) {
			if(String.valueOf(debitObj[i][1]).trim().equals("")){
				result = false;
				break;
			}
			
		}
		return result;
	}
	
	
	/**
	 * Refer to : General information Section In Returns
	 * @param orgId
	 * @return Bean containing all general information
	 */
	public GeneralInfo getLabourGeneralInfo(String orgId) {
		String generalInfoQuery = " SELECT NVL(DIV_NAME,' '), NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,' '), NVL(LOCATION_NAME, ' '), DIV_PINCODE, " 
			+ " NVL(DIV_EMPLOYER_NAME,' '), NVL(DIV_EMPLOYER_DESG,' '), NVL(DIV_MANAGER_NAME,' '), NVL(DIV_MANAGER_DESG,' '), "
			+ " DIV_TELEPHONE, DIV_EMPLOYER_FAX, DIV_EMAIL, DIV_EMPLOYER_MOBILE, DIV_MANAGER_TELEPHONE, DIV_MANAGER_FAX, DIV_MANAGER_EMAIL, DIV_MANAGER_MOBILE, "
			+ " DIV_REG_NO, TO_CHAR(DIV_EXPIRY_DATE,'DD-MM-YYYY'), DIV_TITLE_OF_ACT, DIV_LEGAL_STATUS, DIV_OWNERSHIP, "  
			+ " TO_CHAR(DIV_COMMENCEMENT_DATE,'DD-MM-YYYY'), ESTABLISHMENT_CODE, LOCATION_CODE "
			+ " FROM HRMS_DIVISION "
			+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID) "
			//+ " LEFT JOIN LMS_TITLE_OF_ACTS ON (LMS_TITLE_OF_ACTS.TITLE_OF_ACT_ID = HRMS_DIVISION.DIV_TITLE_OF_ACT) "
			//+ " LEFT JOIN LMS_LEGALSTAT_OF_ESTBLSHMNT ON (LMS_LEGALSTAT_OF_ESTBLSHMNT.LEGAL_STATUS_ID = HRMS_DIVISION.DIV_LEGAL_STATUS) "
			//+ " LEFT JOIN LMS_OWNERSHIP ON (LMS_OWNERSHIP.OWNERSHIP_ID = HRMS_DIVISION.DIV_OWNERSHIP) "
			+ " WHERE HRMS_DIVISION.RECORD_ORG_ID = "+orgId;
		Object[][] generalInfoObj = getSqlModel().getSingleResult(generalInfoQuery);
		
		GeneralInfo generalInfo = new GeneralInfo();
		generalInfo.setEstablishmentName(String.valueOf(generalInfoObj[0][0]));
		generalInfo.setEstablishmentAddress(String.valueOf(generalInfoObj[0][1]));
		generalInfo.setCity(String.valueOf(generalInfoObj[0][2]));
		
		
		String stateQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
			+ " WHERE LOCATION_CODE = (SELECT LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE ="
			+ String.valueOf(generalInfoObj[0][23]) + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(stateQuery);
		generalInfo.setState(String.valueOf(stateCode[0][1]));
		
		generalInfo.setPincode(String.valueOf(generalInfoObj[0][3]));
		generalInfo.setRegistrationNumber(String.valueOf(generalInfoObj[0][16]));
		generalInfo.setTitleOfAct(String.valueOf(generalInfoObj[0][18]));
		generalInfo.setRegistrationExpiryDate(String.valueOf(generalInfoObj[0][17]));
		generalInfo.setName_Occupier(String.valueOf(generalInfoObj[0][4]));
		generalInfo.setDesignation_Occupier(String.valueOf(generalInfoObj[0][5]));
		generalInfo.setPhone_Occupier(String.valueOf(generalInfoObj[0][8]));
		generalInfo.setMobile_Occupier(String.valueOf(generalInfoObj[0][11]));
		generalInfo.setFax_Occupier(String.valueOf(generalInfoObj[0][9]));
		generalInfo.setEmail_Occupier(String.valueOf(generalInfoObj[0][10]));
		generalInfo.setName_Manager(String.valueOf(generalInfoObj[0][6]));
		generalInfo.setDesignation_Manager(String.valueOf(generalInfoObj[0][7]));
		generalInfo.setPhone_Manager(String.valueOf(generalInfoObj[0][12]));
		generalInfo.setMobile_Manager(String.valueOf(generalInfoObj[0][15]));
		generalInfo.setFax_Manager(String.valueOf(generalInfoObj[0][13]));
		generalInfo.setEmail_Manager(String.valueOf(generalInfoObj[0][14]));
		generalInfo.setLegalStatusOfEstablishment(String.valueOf(generalInfoObj[0][19]));
		generalInfo.setOwnership(String.valueOf(generalInfoObj[0][20]));
		generalInfo.setDateofCommencement(String.valueOf(generalInfoObj[0][21]));
		generalInfo.setEstablishmentCode(String.valueOf(generalInfoObj[0][22]));
		//generalInfo.setTypeOfEmployment(typeOfEmployment);
		//generalInfo.setWeeklyOffId(weeklyOffId);
		//generalInfo.setNatureOfBusiness(natureOfBusiness);
		//generalInfo.setReturnId(returnId);
		//generalInfo.setGeneralInfoId(generalInfoId);
		
		return generalInfo;
	}
	
	/**
	 * Refer to: Workforce information in Returns
	 * @param orgId
	 * @return Bean containing all workforce Info
	 */
	public WorkForceInfo getLabourWorkforceInfo(String orgId) {
		WorkForceInfo workForceInfo = new WorkForceInfo();
		String permMgrSupQuery = " SELECT SUM(MALE),SUM(FEMALE) FROM  "
			+ "(   "
			+ "SELECT MGR ,SUM(MNGRSUP) AS MALE,0 AS FEMALE FROM(   "
			+ "SELECT  LMS_DESIGNATION,COUNT(*) AS MNGRSUP,'MGR' AS MGR  FROM HRMS_RANK  "
			+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)   "
			+ "where LMS_DESIGNATION IN('MNG','SUP') and EMP_GENDER='M'  GROUP BY LMS_DESIGNATION)  " 
			+ "GROUP BY MGR   "
			+ "UNION ALL   "
			+ "SELECT MGR ,SUM(MNGRSUP) AS MALE,0 AS FEMALE FROM(  " 
			+ "SELECT  LMS_DESIGNATION,COUNT(*) AS MNGRSUP,'MGR' AS MGR  FROM HRMS_RANK  "
			+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  " 
			+ "where LMS_DESIGNATION IN('MNG','SUP') and EMP_GENDER='F'  GROUP BY LMS_DESIGNATION)  " 
			+ "GROUP BY MGR  )   "
			+ "GROUP BY MGR ";
		Object[][] permMgrSupObj = getSqlModel().getSingleResult(permMgrSupQuery);
		workForceInfo.setPermanentManagersMale(Integer.parseInt(String.valueOf(permMgrSupObj[0][0])));
		workForceInfo.setPermanentManagersFemale(Integer.parseInt(String.valueOf(permMgrSupObj[0][1])));
		
		String workersQuery = " SELECT SUM(WORK_GRT_18) ,SUM(WORK_BET_15_18), SUM(WORK_LESS_15),EMP_GENDER FROM "
			+ " ( "
			+ " SELECT  LMS_DESIGNATION AS ROLE_ID,COUNT(*) AS WORK_GRT_18,0 AS WORK_BET_15_18, "
			+ " 0 AS WORK_LESS_15,EMP_GENDER  FROM HRMS_RANK "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
			+ " WHERE  "
			+ " LMS_DESIGNATION IN('WRK') AND SYSDATE-TO_DATE(EMP_DOB)>=(365*18) "
			+ " GROUP BY LMS_DESIGNATION,EMP_GENDER "
			+ " UNION ALL "
			+ " SELECT  LMS_DESIGNATION AS ROLE_ID,0,COUNT(*) AS WORK_BET_15_18,0,EMP_GENDER  FROM HRMS_RANK "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
			+ " WHERE  "
			+ " LMS_DESIGNATION IN('WRK') AND SYSDATE-TO_DATE(EMP_DOB) BETWEEN (365*15) AND (365*18) " 
			+ " GROUP BY LMS_DESIGNATION,EMP_GENDER "
			+ " UNION ALL "
			+ " SELECT  LMS_DESIGNATION AS ROLE_ID,0,0,COUNT(*) AS WORK_LESS_15,EMP_GENDER  FROM HRMS_RANK "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
			+ " WHERE LMS_DESIGNATION IN('WRK')  AND SYSDATE-TO_DATE(EMP_DOB)< (365*15) "
			+ " GROUP BY LMS_DESIGNATION,EMP_GENDER "
			+ " ) "
			+ " GROUP BY ROLE_ID,EMP_GENDER ";
		Object[][] workersObj = getSqlModel().getSingleResult(workersQuery);
		for (int i = 0; i < workersObj.length; i++) {
			if(String.valueOf(workersObj[i][3]).equals("M")){
				workForceInfo.setWorkersMale(Integer.parseInt(String.valueOf(workersObj[i][0])));
				workForceInfo.setAdolscentWorkersMale(Integer.parseInt(String.valueOf(workersObj[i][1])));
				workForceInfo.setChildWorkersMale(Integer.parseInt(String.valueOf(workersObj[i][2])));
			}
			if(String.valueOf(workersObj[i][3]).equals("F")){
				workForceInfo.setWorkersFemale(Integer.parseInt(String.valueOf(workersObj[i][0])));
				workForceInfo.setAdolscentWorkersFemale(Integer.parseInt(String.valueOf(workersObj[i][1])));
				workForceInfo.setChildWorkersFemale(Integer.parseInt(String.valueOf(workersObj[i][2])));
			}
		}
		
		String typeQuery = " SELECT TYPEID,SUM(MALE),SUM(FEMALE) FROM( "
			+ " SELECT LMS_EMP_TYPE AS TYPEID,COUNT(*) AS MALE,0 AS FEMALE FROM HRMS_EMP_TYPE "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "
			+ " WHERE EMP_GENDER='M' AND HRMS_EMP_TYPE.RECORD_ORG_ID = 1   "
			+ " GROUP BY LMS_EMP_TYPE "
			+ " UNION ALL "
			+ " SELECT LMS_EMP_TYPE AS TYPEID,0 AS MALE ,COUNT(*) AS FEMALE FROM HRMS_EMP_TYPE "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "
			+ " WHERE EMP_GENDER='F' AND HRMS_EMP_TYPE.RECORD_ORG_ID = 1 "
			+ " GROUP BY LMS_EMP_TYPE) "
			+ " GROUP BY TYPEID ";
		Object[][] typeObj = getSqlModel().getSingleResult(typeQuery);
		for (int i = 0; i < typeObj.length; i++) {
			if(String.valueOf(typeObj[i][0]).equals("CONT")){
				workForceInfo.setContractWorkersMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setContractWorkersFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
			if(String.valueOf(typeObj[i][0]).equals("WAGE")){
				workForceInfo.setDailywageWorkersMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setDailywageWorkersFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
			//workForceInfo.setDailywageWorkersMaleDrivers(dailywageWorkersMaleDrivers);
			//workForceInfo.setDailywageWorkersFemaleDrivers(dailywageWorkersFemaleDrivers);
			//workForceInfo.setDailywageWorkersMaleNonDerivers(dailywageWorkersMaleNonDerivers);
			//workForceInfo.setDailywageWorkersFemaleNonDrivers(dailywageWorkersFemaleNonDrivers);
			if(String.valueOf(typeObj[i][0]).equals("TEMP")){
				workForceInfo.setTempWorkersMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setTempWorkersFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
			//workForceInfo.setTempWorkersMaleDrivers(tempWorkersMaleDrivers);
			//workForceInfo.setTempWorkersFemaleDrivers(tempWorkersFemaleDrivers);
			//workForceInfo.setTempWorkersMaleNonDerivers(tempWorkersMaleNonDerivers);
			//workForceInfo.setTempWorkersFemaleNonDrivers(tempWorkersFemaleNonDrivers);
			if(String.valueOf(typeObj[i][0]).equals("CASL")){
				workForceInfo.setCasualWorkersMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setCasualWorkersFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
			//workForceInfo.setCasualWorkersMaleDrivers(casualWorkersMaleDrivers);
			//workForceInfo.setCasualWorkersFemaleDrivers(casualWorkersFemaleDrivers);
			//workForceInfo.setCasualWorkersMaleNonDerivers(casualWorkersMaleNonDerivers);
			//workForceInfo.setCasualWorkersFemaleNonDrivers(casualWorkersFemaleNonDrivers);
			if(String.valueOf(typeObj[i][0]).equals("APPR")){
				workForceInfo.setApprenticeMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setApprenticeFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
			if(String.valueOf(typeObj[i][0]).equals("TRNE")){
				workForceInfo.setTraineeMale(Integer.parseInt(String.valueOf(typeObj[i][1])));
				workForceInfo.setTraineeFemale(Integer.parseInt(String.valueOf(typeObj[i][2])));
			}
		}
		
		String paidUnpaidFml = " SELECT FML_EMPLOYMENT_STATUS,COUNT(*) AS COUNT_A ,FML_GENDER AS GENDER "
			+ " FROM HRMS_EMP_FML "
			+ " WHERE FML_GENDER = 'M'  "
			+ " AND RECORD_ORG_ID = 1 "
			+ " GROUP BY FML_EMPLOYMENT_STATUS,FML_GENDER "
			+ " UNION ALL "
			+ " SELECT FML_EMPLOYMENT_STATUS,COUNT(*) AS COUNT_A, FML_GENDER AS GENDER "
			+ " FROM HRMS_EMP_FML  "
			+ " WHERE FML_GENDER = 'F' "
			+ " AND RECORD_ORG_ID = 1 "
			+ " GROUP BY FML_EMPLOYMENT_STATUS,FML_GENDER ";
		Object[][] paidUnpaidFmlObj = getSqlModel().getSingleResult(paidUnpaidFml);
		for (int j = 0; j < paidUnpaidFmlObj.length; j++) {
			if(String.valueOf(paidUnpaidFmlObj[j][0]).equals("E") && String.valueOf(paidUnpaidFmlObj[j][2]).equals("M")){
				workForceInfo.setFamilyMembersMalePaid(Integer.parseInt(String.valueOf(paidUnpaidFmlObj[j][1])));
			}
			if(String.valueOf(paidUnpaidFmlObj[j][0]).equals("U") && String.valueOf(paidUnpaidFmlObj[j][2]).equals("M")){
				workForceInfo.setFamilyMembersMaleUnpaid(Integer.parseInt(String.valueOf(paidUnpaidFmlObj[j][1])));
			}
			if(String.valueOf(paidUnpaidFmlObj[j][0]).equals("E") && String.valueOf(paidUnpaidFmlObj[j][2]).equals("F")){
				workForceInfo.setFamilyMembersFemalePaid(Integer.parseInt(String.valueOf(paidUnpaidFmlObj[j][1])));
			}
			if(String.valueOf(paidUnpaidFmlObj[j][0]).equals("U") && String.valueOf(paidUnpaidFmlObj[j][2]).equals("F")){
				workForceInfo.setFamilyMembersFemaleUnpaid(Integer.parseInt(String.valueOf(paidUnpaidFmlObj[j][1])));
			}
		}
		
		String servYrsQuery = " SELECT SUM(LESS_THAN_1) ,SUM(BET_1_5), SUM(BET_5_10), SUM(GRT_THAN_10), EMP_GENDER FROM(  "
			+ " SELECT COUNT(*) AS LESS_THAN_1,0 AS BET_1_5,0 AS BET_5_10 ,0 AS GRT_THAN_10, EMP_GENDER "
			+ " FROM HRMS_EMP_OFFC "
			+ " WHERE (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12))< 12 "
			+ " GROUP BY EMP_GENDER "
			+ " UNION ALL "
			+ " SELECT 0,COUNT(*),0,0, EMP_GENDER " 
			+ " FROM HRMS_EMP_OFFC "
			+ " WHERE (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12))>= 12 "
			+ " AND (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12)) < 60 "
			+ " GROUP BY EMP_GENDER "
			+ " UNION ALL "
			+ " SELECT 0,0,COUNT(*),0, EMP_GENDER "
			+ " FROM HRMS_EMP_OFFC "
			+ " WHERE (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12))>= 60 "
			+ " AND (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12)) < 120 "
			+ " GROUP BY EMP_GENDER "
			+ " UNION ALL "
			+ " SELECT 0,0,0,COUNT(*), EMP_GENDER "
			+ " FROM HRMS_EMP_OFFC "
			+ " WHERE (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)*12 ) + "
			+ " (FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
			+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12))>= 120 "
			+ " GROUP BY EMP_GENDER "
			+ " ) "
			+ " GROUP BY EMP_GENDER" ;
		Object[][] servYrsObj = getSqlModel().getSingleResult(servYrsQuery);
		
		for (int i = 0; i < servYrsObj.length; i++) {
			if(String.valueOf(servYrsObj[i][4]).equals("M")){
				workForceInfo.setPermWorkersMaleLessThanOne(Integer.parseInt(String.valueOf(servYrsObj[i][0])));
				workForceInfo.setPermWorkersMaleOneToFive(Integer.parseInt(String.valueOf(servYrsObj[i][1])));
				workForceInfo.setPermWorkersMaleFiveToTen(Integer.parseInt(String.valueOf(servYrsObj[i][2])));
				workForceInfo.setPermWorkersMaleMoreThanTen(Integer.parseInt(String.valueOf(servYrsObj[i][3])));
			}
			
			if(String.valueOf(servYrsObj[i][4]).equals("F")){
				workForceInfo.setPermWorkersFemaleLessThanOne(Integer.parseInt(String.valueOf(servYrsObj[i][0])));
				workForceInfo.setPermWorkersFemaleOneToFive(Integer.parseInt(String.valueOf(servYrsObj[i][1])));
				workForceInfo.setPermWorkersFemaleFiveToTen(Integer.parseInt(String.valueOf(servYrsObj[i][2])));
				workForceInfo.setPermWorkersFemaleMoreThanTen(Integer.parseInt(String.valueOf(servYrsObj[i][3])));
			}
		}
		
		/*workForceInfo.setOwnSecurityGuards(ownSecurityGuards);
		workForceInfo.setOwnCleaningStaff(ownCleaningStaff);
		workForceInfo.setHasIsmWorkers(hasIsmWorkers);
		workForceInfo.setNoOfIsmWorkers(noOfIsmWorkers);
		workForceInfo.setNoOfIsmWorkersAdolscent(noOfIsmWorkersAdolscent);
		workForceInfo.setNoOfContractors(noOfContractors);
		workForceInfo.setNoOfContractWorkersEngaged(noOfContractWorkersEngaged);
		workForceInfo.setNoOfDifferentContractors(noOfDifferentContractors);
		workForceInfo.setContractWorkersFromContractors(contractWorkersFromContractors);
		workForceInfo.setWorkForceId(workForceId);
		workForceInfo.setReturnId(returnId);*/
		
		return workForceInfo;
	}
	
	/**
	 * Refer to: Wages information section of Minimum wages act
	 * @param orgId
	 * @param month
	 * @param year
	 * @return Bean containing wages information for the given month, year and organization code
	 */
	public MinimumWages getLabourWageRecords(String orgId, String month, String year, HttpServletRequest request) {
		String ledgerQuery = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
			+ " WHERE LEDGER_MONTH = "+month+" AND LEDGER_YEAR ="+year+" AND LEDGER_DIVISION ="+orgId;
		Object[][] ledgerCode = getSqlModel().getSingleResult(ledgerQuery);
		MinimumWages minimumWages = new MinimumWages();
		ArrayList<Object> minWagesRecord = new ArrayList<Object>();
		if(ledgerCode != null && ledgerCode.length > 0){
			String minWageCommon = " SELECT HRMS_SALARY_"+year+".EMP_ID, "
				+ " EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||EMP_LNAME NAME, TO_CHAR(EMP_DOB,'DD-MM-YYYY') DOB, "
				+ " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') DOJ, DECODE(EMP_GENDER,'M','MALE','F','FEMALE') GENDER, " 
				+ " NVL(RANK_NAME,' ') DESG, ATTN_DAYS, SAL_TOTAL_DEBIT, SAL_TOTAL_CREDIT, SAL_NET_SALARY, "
				+ " NVL(BANK_NAME,' ') "  
				+ " FROM HRMS_SALARY_"+year+" " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_"+year+".EMP_ID) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_MONTH_ATTENDANCE_"+year+" ON (HRMS_MONTH_ATTENDANCE_"+year+".ATTN_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE "
				+ " AND HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_SALARY_"+year+".EMP_ID) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
				+ " WHERE  SAL_LEDGER_CODE = "+String.valueOf(ledgerCode[0][0])
				+ " ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			Object[][] minWageComObj = getSqlModel().getSingleResult(minWageCommon);
			
			String minWagesDebits = " SELECT A0.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+ " SUM(A0.SAL_AMOUNT),SUM(A1.SAL_AMOUNT),SUM(A2.SAL_AMOUNT),SUM(A3.SAL_AMOUNT),SUM(A4.SAL_AMOUNT),SUM(A5.SAL_AMOUNT) " 
				+ " FROM (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE " 
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='ADV') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A0  "
				+ " INNER JOIN (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE " 
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='PF') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A1  "
				+ " ON A0.EMP_ID = A1.EMP_ID  "
				+ " INNER JOIN (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE  "
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='ESI') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A2 ON A0.EMP_ID = A2.EMP_ID  "
				+ " INNER JOIN (SELECT EMP_ID,SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE " 
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='PT') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A3 ON A0.EMP_ID = A3.EMP_ID  "
				+ " INNER JOIN (SELECT EMP_ID, SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE " 
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='IT') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A4 ON A0.EMP_ID = A4.EMP_ID " 
				+ " INNER JOIN (SELECT EMP_ID, SAL_AMOUNT FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_DEBIT_CODE IN(SELECT DISTINCT SAL_DEBIT_CODE " 
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE WHERE LMS_DEBIT_TYPE='LWB') " 
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) A5 ON A0.EMP_ID = A5.EMP_ID  "
				+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = A0.EMP_ID  "
				+ " INNER JOIN HRMS_SALARY_"+year+" ON  (HRMS_SALARY_"+year+".EMP_ID = A0.EMP_ID  "
				+ " AND SAL_LEDGER_CODE IN ("+String.valueOf(ledgerCode[0][0])+")) " 
				+ " GROUP BY A0.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " ORDER BY  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";
			Object[][] minWagesDebObj = getSqlModel().getSingleResult(minWagesDebits);
			
			String creditQuery="";
			String creditQuery1=" ";
			String creditQuery2="";String creditQuery3="",creditQuery4="";
			String count="0";
			
			Object[][] creditHead = getCreditHeader(String.valueOf(ledgerCode[0][0]),year);
			
			Object[] creditObj = new Object[creditHead.length];
			
			for (int i = 0; i < creditHead.length; i++) {
				creditObj[i] =  creditHead[i][1];
			}
			request.setAttribute("creditHead", creditObj);
			for (int i = 0; i < creditHead.length; i++) {
				if(i==0){
					creditQuery1 += "select a"+i+".EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,a"+i+".sal_amount,";
					creditQuery2 += " from (select emp_id,SAL_AMOUNT from HRMS_SAL_CREDITS_"+year+" " +
									" where SAL_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("+String.valueOf(ledgerCode[0][0])+")) a"+i+" ";
					
					
					creditQuery4 = "inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".emp_id " +
									" inner join hrms_salary_"+year+" on  (hrms_salary_"+year+".emp_id = a"+i+".emp_id  " +
									" and SAL_LEDGER_CODE in ("+String.valueOf(ledgerCode[0][0])+"))" +
									" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
			
					count=String.valueOf(i);
				}
				else{
					creditQuery1 += "a"+i+".sal_amount,";
					creditQuery3 += " inner join (select emp_id,SAL_AMOUNT from HRMS_SAL_CREDITS_"+year
					+" where SAL_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and SAL_LEDGER_CODE in ("
					+String.valueOf(ledgerCode[0][0])+")) a"+i+" on a"+count+".emp_id = a"+i+".emp_id ";
				}
											
			}
			creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4;
			Object[][] minWagesCreditObj = getSqlModel().getSingleResult(creditQuery);
			request.setAttribute("creditValues", minWagesCreditObj);
			Object[][] finalObj=null;
			if(minWagesDebObj !=null && minWagesDebObj.length>0){
				 finalObj = Utility.joinArrayWithUniqueCol(minWageComObj, minWagesDebObj, 0, 0, new int[]{0, 1});
			}
			else{
			finalObj=minWageComObj;
			}
			
			for (int i = 0; i < finalObj.length; i++) {
				for (int j = 0; j < finalObj[0].length; j++) {
					System.out.println("finalObj["+i+"]["+j+"]....."+finalObj[i][j]);
				}
			}
			
			for (int i = 0; i < finalObj.length; i++) {
				MinimumWages minimumWages1 = new MinimumWages();
				minimumWages1.setEmployeeName(String.valueOf(finalObj[i][1]));
				minimumWages1.setDateOfBirth(String.valueOf(finalObj[i][2]));
				minimumWages1.setDateOfJoining(String.valueOf(finalObj[i][3]));
				minimumWages1.setGender(String.valueOf(finalObj[i][4]));
				minimumWages1.setDesignation(String.valueOf(finalObj[i][5]));
				minimumWages1.setTotalWorkingDays(String.valueOf(finalObj[i][6]));
				minimumWages1.setTotalAllowances(String.valueOf(finalObj[i][8]));
				minimumWages1.setGrossEarnings("0");
				minimumWages1.setTotalDeductions(String.valueOf(finalObj[i][7]));
				minimumWages1.setNetEarnings(String.valueOf(finalObj[i][9]));
				minimumWages1.setAmountDepositedInBank(String.valueOf(finalObj[i][9]));
				minimumWages1.setEmployersBankName(String.valueOf(finalObj[i][10]));
				try {
					minimumWages1.setPf(String.valueOf(finalObj[i][11]));
					minimumWages1.setEsi(String.valueOf(finalObj[i][12]));
					minimumWages1.setPt(String.valueOf(finalObj[i][13]));
					minimumWages1.setIt(String.valueOf(finalObj[i][14]));
					minimumWages1.setLwb(String.valueOf(finalObj[i][15]));
				} catch (Exception e) {
					minimumWages1.setPf("0");
					minimumWages1.setEsi("0");
					minimumWages1.setPt("0");
					minimumWages1.setIt("0");
					minimumWages1.setLwb("0");
				}
				minWagesRecord.add(minimumWages1);
			}
			minimumWages.setMinWagesRecord(minWagesRecord);
		}
		
		return minimumWages;
	}
	
	public Object[][] getCreditHeader(String ledgerCode,String year) {
		Object credit_header[][] = null;
		try{			
		
			String query ="SELECT DISTINCT SAL_CREDIT_CODE,TRIM(CREDIT_ABBR) FROM HRMS_SAL_CREDITS_"+year+" " +
							" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE" +
							" WHERE SAL_LEDGER_CODE IN("+ledgerCode+") ORDER BY SAL_CREDIT_CODE";
		
		credit_header = getSqlModel().getSingleResult(query);
		
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return credit_header;
	} // end of method getCreditHeader()
	
	
	public int totalSum(String obj1, String obj2){
		int totalObj = 0;
		totalObj = Integer.parseInt(obj1) + Integer.parseInt(obj1);
		return totalObj;
	}
	
	
}
