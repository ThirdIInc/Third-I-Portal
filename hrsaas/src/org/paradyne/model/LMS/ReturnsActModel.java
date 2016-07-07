package org.paradyne.model.LMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.paradyne.bean.LMS.ActsChildLabour;
import org.paradyne.bean.LMS.ActsMaternityBenefits;
import org.paradyne.bean.LMS.ActsPaymentOfBonus;
import org.paradyne.bean.LMS.EqualRemuneration;
import org.paradyne.bean.LMS.FactorySafetyHealth;
import org.paradyne.bean.LMS.FactoryWelfareFacilities;
import org.paradyne.bean.LMS.GeneralInfo;
import org.paradyne.bean.LMS.GratuityRules;
import org.paradyne.bean.LMS.RentAllowance;
import org.paradyne.bean.LMS.ReturnActBean;
import org.paradyne.bean.LMS.WorkForceInfo;
import org.paradyne.lib.ModelBase;

public class ReturnsActModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReturnsActModel.class);
	
	//===============METHODS ADDED BY REEBA BEGINS=============//
	public void setReturns(ReturnActBean retactBean, String status){
		
		
		String query="SELECT RETURN_ID,DECODE(RETURN_FREQUENCY,'M','Monthly','A','Annual'), "
			+ " COMPANY_ID,RETURN_FREQUENCY,DECODE(RETURN_FREQUENCY,'A',"
			+ " TO_CHAR(RETURN_FROM_PERIOD,'Mon YYYY')||'-'||TO_CHAR(RETURN_TO_PERIOD,'Mon YYYY'),"
			+ " TO_CHAR(RETURN_FROM_PERIOD,'Mon YYYY')),DECODE(RETURN_STATUS,'P','Pending') "
			+ " FROM LMS_RETURNS WHERE RETURN_STATUS  IN ('"+status+"') ";
		Object[][]obj=getSqlModel().getSingleResult(query);
		String subActQuery="SELECT TITLE_OF_ACT_FREQ,TITLE_OF_ACT FROM LMS_TITLE_OF_ACTS ORDER BY ACT_SEQUENCE_NUMBER";
		Object[][]subActObj=getSqlModel().getSingleResult(subActQuery);
		
		if(obj !=null && obj.length>0){
			ArrayList<Object> pendingReturnsList = new ArrayList<Object>();
			for (int i = 0; i < obj.length; i++) {
				ReturnActBean bean = new ReturnActBean();
				bean.setReturnID(String.valueOf(obj[i][0]));
				bean.setReturnFrequency(String.valueOf(obj[i][1]));
				bean.setReturnCompany(String.valueOf(obj[i][2]));
				ArrayList<Object> sublistNA = new ArrayList<Object>();
				for (int j = 0; j < subActObj.length; j++) {
					ReturnActBean bean1 = new ReturnActBean();
					if(String.valueOf(obj[i][3]).equals(String.valueOf(subActObj[j][0]))){
						bean1.setReturnName(String.valueOf(subActObj[j][1]));		
						sublistNA.add(bean1);
					}
					
				}
				bean.setReturnActNames(sublistNA);
				bean.setFinancialYear(String.valueOf(obj[i][4]));
				bean.setReturnStatus(String.valueOf(obj[i][5]));
				pendingReturnsList.add(bean);				
			}
			retactBean.setPendingReturnsList(pendingReturnsList);			
		}		
	}
	
	
	
	public LinkedHashMap<String, String> setReturnActs(String frequency) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String query = " SELECT TITLE_OF_ACT, TITLE_OF_ACT_ABBR FROM LMS_TITLE_OF_ACTS "
			+ " WHERE TITLE_OF_ACT_FREQ = '"+frequency+"' ORDER BY ACT_SEQUENCE_NUMBER";
		Object[][] mapObj = getSqlModel().getSingleResult(query);
		if (mapObj != null && mapObj.length > 0) {
			for (int i = 0; i < mapObj.length; i++) {
				map.put(String.valueOf(mapObj[i][1]), String.valueOf(mapObj[i][0]));
			}
		}
		return map;
	}
	
	public boolean generateReturnID(ReturnActBean retactBean, String returnID) {
		boolean result = false;
		if(returnID.equals("")){
			//INSERT
			String insertQuery = " INSERT INTO TBL_LMS_RETURNS (RETURNID) "
				+ " VALUES (SELECT NVL(MAX(RETURNID)+1,0) FROM TBL_LMS_RETURNS)";
			result = getLMSSqlModel().singleExecute(insertQuery);
			
		}else{
			//UPDATE
			String updateQuery = " UPDATE TBL_LMS_RETURNS SET RETURNID = "+returnID
				+ " WHERE RETURNID = "+returnID;
			result = getLMSSqlModel().singleExecute(updateQuery);
		}
		
		return result;
	}
	//===============METHODS ADDED BY REEBA ENDS=============//
	
	//===============METHODS ADDED BY MANISH BEGINS=============//
	//Start of Gratuity insert records
	public boolean gratuityInsertData(GratuityRules gratuityBean) {
		boolean result = false;
		try {			
			Object insertGratuityObj[][] = new Object[1][14];
			insertGratuityObj[0][0] = gratuityBean.getNominations_Received();
			insertGratuityObj[0][1] = gratuityBean.getNominations_Accepted();
			insertGratuityObj[0][2] = gratuityBean.getApplications_Received();
			insertGratuityObj[0][3] = gratuityBean.getApplications_Approved();
			insertGratuityObj[0][4] = gratuityBean.getApplications_Rejected();
			insertGratuityObj[0][5] = gratuityBean.getAmountOfGratuityPaid();
			insertGratuityObj[0][6] = gratuityBean.getMaxGratuityAmount();
			insertGratuityObj[0][7] = gratuityBean.getMinGratuityAmount();
			insertGratuityObj[0][8] = gratuityBean.getGratuityPaid_Cash();
			insertGratuityObj[0][9] = gratuityBean.getGratuityPaid_BankCheque();
			insertGratuityObj[0][10] = gratuityBean.getGratuityPaid_NormalCheque();			
			insertGratuityObj[0][11] = gratuityBean.getGratuityPaid_CreditToAccount();
			if(gratuityBean.getAnyCasesPending().equals("true"))
			{
				insertGratuityObj[0][12] = "Y";
			}
			else
			{
				insertGratuityObj[0][12] = "N";
			}
			insertGratuityObj[0][13] = gratuityBean.getPendingCasesDetails();
			
			String insertQuery = "INSERT INTO TBL_LMS_RTN_PAYMENTOFGRATUITY (PAYMENTOFGRATUITYID, NOMINATIONS_RECEIVED, NOMINATIONS_ACCEPTED, APPLICATIONS_RECEIVED,"
								+" APPLICATIONS_APPROVED, APPLICATIONS_REJECTED, AMOUNTOFGRATUITYPAID, MAXGRATUITYAMOUNT, MINGRATUITYAMOUNT, "
								+" GRATUITYPAID_CASH, GRATUITYPAID_BANKCHEQUE, GRATUITYPAID_NORMALCHEQUE, GRATUITYPAID_CREDITTOACCOUNT, ANYCASESPENDING, PENDINGCASESDETAILS)" 
								+" VALUES((SELECT NVL(MAX(PAYMENTOFGRATUITYID),0)+1 FROM TBL_LMS_RTN_PAYMENTOFGRATUITY),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	     
			
			result = getLMSSqlModel().singleExecute(insertQuery, insertGratuityObj);
			
			
			Object insertRejectObj[][] = new Object[1][2];
			insertRejectObj[0][0] = gratuityBean.getOtherReasonsSpecify();
			insertRejectObj[0][1] = gratuityBean.getApplications_Rejected();
			String insertRejectQuery = "INSERT INTO LC_PAYMNTOFGRATUITY_REJ(PAYMENTOFGRATUITYID, REASON, COUNT)"
									   +" VALUES((SELECT NVL(MAX(PAYMENTOFGRATUITYID),0)+1 FROM LC_PAYMNTOFGRATUITY_REJ),?,?) ";
			result = getLMSSqlModel().singleExecute(insertRejectQuery, insertRejectObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//End of Gratuity insert records
	
	//Start of Gratuity update records
	public boolean gratuityUpdateData(GratuityRules gratuityBean) {
		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][14];
			updateObj[0][0] = gratuityBean.getNominations_Received();
			updateObj[0][1] = gratuityBean.getNominations_Accepted();
			updateObj[0][2] = gratuityBean.getApplications_Received();
			updateObj[0][3] = gratuityBean.getApplications_Approved();
			updateObj[0][4] = gratuityBean.getApplications_Rejected();
			updateObj[0][5] = gratuityBean.getAmountOfGratuityPaid();
			updateObj[0][6] = gratuityBean.getMaxGratuityAmount();
			updateObj[0][7] = gratuityBean.getMinGratuityAmount();
			updateObj[0][8] = gratuityBean.getGratuityPaid_Cash();
			updateObj[0][9] = gratuityBean.getGratuityPaid_BankCheque();
			updateObj[0][10] = gratuityBean.getGratuityPaid_NormalCheque();			
			updateObj[0][11] = gratuityBean.getGratuityPaid_CreditToAccount();
			if(gratuityBean.getAnyCasesPending().equals("true"))
			{
				updateObj[0][12] = "Y";
			}
			else
			{
				updateObj[0][12] = "N";
			}
			updateObj[0][13] = gratuityBean.getPendingCasesDetails();
			
			String updateQuery = "UPDATE TBL_LMS_RTN_PAYMENTOFGRATUITY SET NOMINATIONS_RECEIVED=?, NOMINATIONS_ACCEPTED=?, APPLICATIONS_RECEIVED=?, APPLICATIONS_APPROVED=?," 
								 +" APPLICATIONS_REJECTED=?, AMOUNTOFGRATUITYPAID=?, MAXGRATUITYAMOUNT=?, MINGRATUITYAMOUNT=?, GRATUITYPAID_CASH=?, GRATUITYPAID_BANKCHEQUE=?, " 
								 +" GRATUITYPAID_NORMALCHEQUE=?, GRATUITYPAID_CREDITTOACCOUNT=?, ANYCASESPENDING=?, PENDINGCASESDETAILS=? WHERE PAYMENTOFGRATUITYID= "+gratuityBean.getGratuityID();	     
			result = getLMSSqlModel().singleExecute(updateQuery, updateObj);
			
			Object updateRejectObj[][] = new Object[1][2];
			updateRejectObj[0][0] = gratuityBean.getOtherReasonsSpecify();
			updateRejectObj[0][1] = gratuityBean.getApplications_Rejected();
			String updateRejectQuery = "UPDATE LC_PAYMNTOFGRATUITY_REJ SET REASON=?, COUNT=? WHERE PAYMENTOFGRATUITYID ="+gratuityBean.getGratuityID();
			result = getLMSSqlModel().singleExecute(updateRejectQuery, updateRejectObj);
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return result;
	}
	//End of Gratuity update records
	
	
	// Start of HRA Insert data function
	public boolean hraInsertData(RentAllowance rentAllowanceBean) {
		boolean result= false;
		try {
			Object insertHRAObj[][] = new Object[1][14];
			if(rentAllowanceBean.getAccommodationProvided().equals("true"))
			{
				insertHRAObj[0][0] = "Y";
			}
			else
			{
				insertHRAObj[0][0] = "N";
			}
			
			if(rentAllowanceBean.getFreeAccommodationProvided().equals("true"))
			{
				insertHRAObj[0][1] = "Y";
			}
			else
			{
				insertHRAObj[0][1] = "N";
			}
			insertHRAObj[0][2] = rentAllowanceBean.getNoOfEmpHRAPaid();
			if(rentAllowanceBean.getHRACalculatedAs5Percent().equals("true"))
			{
				insertHRAObj[0][3] = "Y";
			}
			else
			{
				insertHRAObj[0][3]= "N";
			}
			insertHRAObj[0][4] = rentAllowanceBean.getHRACalculationFormula();
			insertHRAObj[0][5] = rentAllowanceBean.getMaxHRAPaid();
			insertHRAObj[0][6] = rentAllowanceBean.getTotalHRAPaid();
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("B"))
			{
				insertHRAObj[0][7] = "B";
			}
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("D"))
			{
				insertHRAObj[0][7] = "D";
			}
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("A"))
			{
				insertHRAObj[0][7] = "A";
			}			
			insertHRAObj[0][8] = rentAllowanceBean.getAfterDays();
			if(rentAllowanceBean.getHRADisputesReceived().equals("true"))
			{
				insertHRAObj[0][9] = "Y";
			}
			else
			{
				insertHRAObj[0][9] = "N";
			}
			insertHRAObj[0][10] = rentAllowanceBean.getNoOfHRADisputes();
			if(rentAllowanceBean.getWorkersRepresentedByTU().equals("true"))
			{
				insertHRAObj[0][11] = "Y";
			}
			else
			{
				insertHRAObj[0][11] = "N";
			}
			insertHRAObj[0][12] = rentAllowanceBean.getTUName();
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("N"))
			{
				insertHRAObj[0][13] = "N";
			}
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("C"))
			{
				insertHRAObj[0][13] = "C";
			}
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("O"))
			{
				insertHRAObj[0][13] = "O";
			}
			
			
			String insertQuery = "INSERT INTO TBL_LMS_RTN_MINIMUMHRA (MINIMUMHRAID, ACCOMMODATIONPROVIDED, FREEACCOMMODATIONPROVIDED, NOOFEMPHRAPAID,"
				+" HRACALCULATEDAS5PERCENT, HRACALCULATIONFORMULA, MAXHRAPAID, MINHRAPAID, HRAPAYMENTMODE, "
				+" AFTERDAYS, HRADISPUTESRECEIVED, NOOFHRADISPUTES, WORKERSREPRESENTEDBYTU, TUNAME, DISPUTESRESOLUTIONMETHOD)" 
				+" VALUES((SELECT NVL(MAX(MINIMUMHRAID),0)+1 FROM TBL_LMS_RTN_MINIMUMHRA),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	     

			result = getLMSSqlModel().singleExecute(insertQuery, insertHRAObj);


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// End of HRA Insert data function

	
	// Start of HRA Update data function	
	public boolean hraUpdateData(RentAllowance rentAllowanceBean) {
		boolean result= false;
		try {
			Object updateHRAObj[][] = new Object[1][14];
			if(rentAllowanceBean.getAccommodationProvided().equals("true"))
			{
				updateHRAObj[0][0] = "Y";
			}
			else
			{
				updateHRAObj[0][0] = "N";
			}
			
			if(rentAllowanceBean.getFreeAccommodationProvided().equals("true"))
			{
				updateHRAObj[0][1] = "Y";
			}
			else
			{
				updateHRAObj[0][1] = "N";
			}
			updateHRAObj[0][2] = rentAllowanceBean.getNoOfEmpHRAPaid();
			if(rentAllowanceBean.getHRACalculatedAs5Percent().equals("true"))
			{
				updateHRAObj[0][3] = "Y";
			}
			else
			{
				updateHRAObj[0][3]= "N";
			}
			updateHRAObj[0][4] = rentAllowanceBean.getHRACalculationFormula();
			updateHRAObj[0][5] = rentAllowanceBean.getMaxHRAPaid();
			updateHRAObj[0][6] = rentAllowanceBean.getTotalHRAPaid();
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("B"))
			{
				updateHRAObj[0][7] = "B";
			}
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("D"))
			{
				updateHRAObj[0][7] = "D";
			}
			if(rentAllowanceBean.getRentAllowanceRadioOptionValue().equals("A"))
			{
				updateHRAObj[0][7] = "A";
			}			
			updateHRAObj[0][8] = rentAllowanceBean.getAfterDays();
			if(rentAllowanceBean.getHRADisputesReceived().equals("true"))
			{
				updateHRAObj[0][9] = "Y";
			}
			else
			{
				updateHRAObj[0][9] = "N";
			}
			updateHRAObj[0][10] = rentAllowanceBean.getNoOfHRADisputes();
			if(rentAllowanceBean.getWorkersRepresentedByTU().equals("true"))
			{
				updateHRAObj[0][11] = "Y";
			}
			else
			{
				updateHRAObj[0][11] = "N";
			}
			updateHRAObj[0][12] = rentAllowanceBean.getTUName();
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("N"))
			{
				updateHRAObj[0][13] = "N";
			}
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("C"))
			{
				updateHRAObj[0][13] = "C";
			}
			if(rentAllowanceBean.getDisputeRadioOptionValue().equals("O"))
			{
				updateHRAObj[0][13] = "O";
			}
			
			
			String updateQuery = "UPDATE TBL_LMS_RTN_MINIMUMHRA SET ACCOMMODATIONPROVIDED=?, FREEACCOMMODATIONPROVIDED=?, NOOFEMPHRAPAID=?,"
								 +" HRACALCULATEDAS5PERCENT=?, HRACALCULATIONFORMULA=?, MAXHRAPAID=?, MINHRAPAID=?, HRAPAYMENTMODE=?, "
								 +" AFTERDAYS=?, HRADISPUTESRECEIVED=?, NOOFHRADISPUTES=?, WORKERSREPRESENTEDBYTU=?, TUNAME=?, DISPUTESRESOLUTIONMETHOD=? WHERE MINIMUMHRAID="+rentAllowanceBean.getMinimumHRAID();
			result = getLMSSqlModel().singleExecute(updateQuery, updateHRAObj);


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// End of HRA Update data function	

	
	// Start of Equal Emuneration Insert records
	public boolean equalRemunerationInsertRecords(
			EqualRemuneration equalRemunerationBean) {
		boolean result = false;
		try {
			Object equalRemunerationInsertObj[][] = new Object[1][16];
			equalRemunerationInsertObj[0][0] = equalRemunerationBean.getEmp_Male();
			equalRemunerationInsertObj[0][1] = equalRemunerationBean.getTotalNumberOfEmployeesFemale();
			equalRemunerationInsertObj[0][2] = equalRemunerationBean.getTotalNumberOfEmployeesTotal();
			equalRemunerationInsertObj[0][3] = equalRemunerationBean.getEmployeeWithSameJobMale();
			equalRemunerationInsertObj[0][4] = equalRemunerationBean.getEmployeeWithSameJobFeMale();
			equalRemunerationInsertObj[0][5] = equalRemunerationBean.getHowMuchPayForFemaleWorker();
			equalRemunerationInsertObj[0][6] = equalRemunerationBean.getHowMuchPayForMaleWorker();
			equalRemunerationInsertObj[0][7] = equalRemunerationBean.getMaleEmpWithSameJobReceiveHRA();
			equalRemunerationInsertObj[0][8] = equalRemunerationBean.getFemaleEmpWithSameJobReceiveHRA();
			equalRemunerationInsertObj[0][9] = equalRemunerationBean.getMaleEmpWithSameJobReceiveAllowance();
			equalRemunerationInsertObj[0][10] = equalRemunerationBean.getFemaleEmpWithSameJobReceiveAllowance();
			equalRemunerationInsertObj[0][11] = equalRemunerationBean.getTypeOfAllowance();
			if(equalRemunerationBean.getDoEmpWithSameJobReceiveSameHRA().equals("true"))
			{
				equalRemunerationInsertObj[0][12] = "Y";
			}
			else
			{
				equalRemunerationInsertObj[0][12] = "N";
			}
			if(equalRemunerationBean.getDoEmpWithSameJobReceiveOtherAllowance().equals("true"))
			{
				equalRemunerationInsertObj[0][13] = "Y";
			}
			else
			{
				equalRemunerationInsertObj[0][13] = "N";
			}
			equalRemunerationInsertObj[0][14] = equalRemunerationBean.getEmpWithSameJobNotSamePay();
			equalRemunerationInsertObj[0][15] = equalRemunerationBean.getEmpWithSameJobNotSameAllowance();
			
			String equalRemunerationInsertQuery = "INSERT INTO LMS_EQUALREMUNERATION(EQUALREMUNERATIONID, EMP_MALE, EMP_FEMALE, EMP_TOTAL, EMP_SAMEJOB_MALE, EMP_SAMEJOB_FEMALE, "
												  +" PAYPERPERIOD_FEMALE, PAYPERPERIOD_MALE, EMP_SMJOB_RECEIVEHRA_MALE, EMP_SMJOB_RECEIVEHRA_FEMALE, EMP_SMJOB_RECEIVEOA_MALE," 
												  +" EMP_SMJOB_RECEIVEOA_FEMALE, OANAMES, EMP_SAMEJOB_RECEIVESAMEHRA, EMP_SAMEJOB_RECEIVESAMEOA, REASONS_NOSAMEHRA, REASONS_NOSAMEOA) " 
												  +" VALUES((SELECT NVL(MAX(EQUALREMUNERATIONID),0)+1 FROM LMS_EQUALREMUNERATION),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			result = getLMSSqlModel().singleExecute(equalRemunerationInsertQuery, equalRemunerationInsertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// End of Equal Emuneration Insert records

	
	// Start of Equal Emuneration Update records
	public boolean equalRemunerationUpdateRecords(
			EqualRemuneration equalRemunerationBean) {
		boolean result = false;
		try {
			Object equalRemunerationUpdateObj[][] = new Object[1][16];
			equalRemunerationUpdateObj[0][0] = equalRemunerationBean.getEmp_Male();
			equalRemunerationUpdateObj[0][1] = equalRemunerationBean.getTotalNumberOfEmployeesFemale();
			equalRemunerationUpdateObj[0][2] = equalRemunerationBean.getTotalNumberOfEmployeesTotal();
			equalRemunerationUpdateObj[0][3] = equalRemunerationBean.getEmployeeWithSameJobMale();
			equalRemunerationUpdateObj[0][4] = equalRemunerationBean.getEmployeeWithSameJobFeMale();
			equalRemunerationUpdateObj[0][5] = equalRemunerationBean.getHowMuchPayForFemaleWorker();
			equalRemunerationUpdateObj[0][6] = equalRemunerationBean.getHowMuchPayForMaleWorker();
			equalRemunerationUpdateObj[0][7] = equalRemunerationBean.getMaleEmpWithSameJobReceiveHRA();
			equalRemunerationUpdateObj[0][8] = equalRemunerationBean.getFemaleEmpWithSameJobReceiveHRA();
			equalRemunerationUpdateObj[0][9] = equalRemunerationBean.getMaleEmpWithSameJobReceiveAllowance();
			equalRemunerationUpdateObj[0][10] = equalRemunerationBean.getFemaleEmpWithSameJobReceiveAllowance();
			equalRemunerationUpdateObj[0][11] = equalRemunerationBean.getTypeOfAllowance();
			if(equalRemunerationBean.getDoEmpWithSameJobReceiveSameHRA().equals("true"))
			{
				equalRemunerationUpdateObj[0][12] = "Y";
			}
			else
			{
				equalRemunerationUpdateObj[0][12] = "N";
			}
			if(equalRemunerationBean.getDoEmpWithSameJobReceiveOtherAllowance().equals("true"))
			{
				equalRemunerationUpdateObj[0][13] = "Y";
			}
			else
			{
				equalRemunerationUpdateObj[0][13] = "N";
			}
			equalRemunerationUpdateObj[0][14] = equalRemunerationBean.getEmpWithSameJobNotSamePay();
			equalRemunerationUpdateObj[0][15] = equalRemunerationBean.getEmpWithSameJobNotSameAllowance();
			
			String equalRemunerationUpdateQuery = "UPDATE LMS_EQUALREMUNERATION SET EMP_MALE=?, EMP_FEMALE=?, EMP_TOTAL=?, EMP_SAMEJOB_MALE=?, EMP_SAMEJOB_FEMALE=?, "
												  +" PAYPERPERIOD_FEMALE=?, PAYPERPERIOD_MALE=?, EMP_SMJOB_RECEIVEHRA_MALE=?, EMP_SMJOB_RECEIVEHRA_FEMALE=?, EMP_SMJOB_RECEIVEOA_MALE=?," 
												  +" EMP_SMJOB_RECEIVEOA_FEMALE=?, OANAMES=?, EMP_SAMEJOB_RECEIVESAMEHRA=?, EMP_SAMEJOB_RECEIVESAMEOA=?, REASONS_NOSAMEHRA=?, REASONS_NOSAMEOA=? WHERE EQUALREMUNERATIONID="+equalRemunerationBean.getEqualRemunerationID(); 
			
			result = getLMSSqlModel().singleExecute(equalRemunerationUpdateQuery, equalRemunerationUpdateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// End of Equal Remuneration Update records	
	
	//===============METHODS ADDED BY MANISH ENDS=============//
	
	//===============METHODS ADDED BY GANESH BEGINS=============//
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean getInsertBonusRecords(ActsPaymentOfBonus actsPaymentOfBonus) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){

			Object insertObj[][] = new Object[1][22];

			insertObj[0][0] = actsPaymentOfBonus.getNoOfEmployees_Male();
			insertObj[0][1] = actsPaymentOfBonus.getNoOfEmployees_Female();
			insertObj[0][2] = actsPaymentOfBonus.getEmpReceivingLT10K_Male();
			insertObj[0][3] = actsPaymentOfBonus
					.getEmpReceivingLT10K_Female();
			insertObj[0][4] = actsPaymentOfBonus
					.getEmpWorkingLT30Days_Male();
			insertObj[0][5] = actsPaymentOfBonus
					.getEmpWorkingLT30Days_Female();
			insertObj[0][6] = actsPaymentOfBonus
					.getDisqualified_Male();
			insertObj[0][7] = actsPaymentOfBonus
					.getDisqualified_Female();
			insertObj[0][8] = actsPaymentOfBonus
					.getEligible_Male();
			insertObj[0][9] = actsPaymentOfBonus
					.getEligible_Female();
			insertObj[0][10] = actsPaymentOfBonus.getTotalEligible();
			insertObj[0][11] = actsPaymentOfBonus.getRateOfBonusPayment();
			// insertObj[0][12]=actsPaymentOfBonus.getRateOfBonusPaymentOfTheBonusPeriod();
			// insertObj[0][13]=actsPaymentOfBonus.getFlatRateBonusPeriod();
			insertObj[0][12] = actsPaymentOfBonus.getTotalBonusPaid();
			insertObj[0][13] = actsPaymentOfBonus.getAdvanceBonusPaid();
			insertObj[0][14] = actsPaymentOfBonus.getNoOfEmpReceivingAdvBonus();
			insertObj[0][15] = actsPaymentOfBonus.getBonusPaymentDate();
			insertObj[0][16] = actsPaymentOfBonus.getReasonsForNonPayment();

			if (actsPaymentOfBonus.getExGratiaPaid().equals("true")) {
				insertObj[0][17] = "Y";
			} else {
				insertObj[0][17] = "N";
			}

			insertObj[0][18] = actsPaymentOfBonus.getExGratiaPercent();
			insertObj[0][19] = actsPaymentOfBonus.getNoOfEmp();
			insertObj[0][20] = actsPaymentOfBonus.getExGratiaAmount();
			insertObj[0][21] = actsPaymentOfBonus.getExGratiaPaymentDate();

			String insertQuery = "INSERT INTO TBL_LMS_RTN_PAYMENTOFBONUS(PAYMENTOFBONUSID, NOOFEMPLOYEES_MALE, NOOFEMPLOYEES_FEMALE, EMPRECEIVINGLT10K_MALE,"
					+ " EMPRECEIVINGLT10K_FEMALE,EMPWORKINGLT30DAYS_MALE,EMPWORKINGLT30DAYS_FEMALE, DISQUALIFIED_MALE,DISQUALIFIED_FEMALE, ELIGIBLE_MALE,  "
					+ "  ELIGIBLE_FEMALE, TOTALELIGIBLE, RATEOFBONUSPAYMENT, TOTALBONUSPAID, "
					+ "ADVANCEBONUSPAID, NOOFEMPRECEIVINGADVBONUS, BONUSPAYMENTDATE, REASONSFORNONPAYMENT, EXGRATIAPAID, EXGRATIAPERCENT, NOOFEMP, "
					+ "EXGRATIAAMOUNT, EXGRATIAPAYMENTDATE)"
					+ " VALUES((SELECT MAX(NVL(PAYMENTOFBONUSID,0))+1 FROM TBL_LMS_RTN_PAYMENTOFBONUS),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'))";

			result = getLMSSqlModel().singleExecute(insertQuery, insertObj);
			/*
			 * if(result){ Object
			 * bonusId[][]=getSqlModel().getSingleResult("SELECT
			 * MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
			 * 
			 * actsPaymentOfBonus.setBonusId(checkNull(String.valueOf(bonusId[0][0]))); }
			 */

			// }else{
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean getUpdateBonusRecords(ActsPaymentOfBonus actsPaymentOfBonus) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){
			Object updateObj[][] = new Object[1][22];

			updateObj[0][0] = actsPaymentOfBonus.getNoOfEmployees_Male();
			updateObj[0][1] = actsPaymentOfBonus.getNoOfEmployees_Female();
			updateObj[0][2] = actsPaymentOfBonus.getEmpReceivingLT10K_Male();
			updateObj[0][3] = actsPaymentOfBonus
					.getEmpReceivingLT10K_Female();
			updateObj[0][4] = actsPaymentOfBonus
					.getEmpWorkingLT30Days_Male();
			updateObj[0][5] = actsPaymentOfBonus
					.getEmpWorkingLT30Days_Female();
			updateObj[0][6] = actsPaymentOfBonus
					.getDisqualified_Male();
			updateObj[0][7] = actsPaymentOfBonus
					.getDisqualified_Female();
			updateObj[0][8] = actsPaymentOfBonus
					.getEligible_Male();
			updateObj[0][9] = actsPaymentOfBonus
					.getEligible_Female();
			updateObj[0][10] = actsPaymentOfBonus.getTotalEligible();
			updateObj[0][11] = actsPaymentOfBonus.getRateOfBonusPayment();
			// updateObj[0][12]=actsPaymentOfBonus.getPercentageRateOfTheBonusPeriod();
			// updateObj[0][13]=actsPaymentOfBonus.getFlatRateBonusPeriod();
			updateObj[0][12] = actsPaymentOfBonus.getTotalBonusPaid();
			updateObj[0][13] = actsPaymentOfBonus.getAdvanceBonusPaid();
			updateObj[0][14] = actsPaymentOfBonus.getNoOfEmpReceivingAdvBonus();
			updateObj[0][15] = actsPaymentOfBonus.getBonusPaymentDate();
			updateObj[0][16] = actsPaymentOfBonus.getReasonsForNonPayment();

			if (actsPaymentOfBonus.getExGratiaPaid().equals("true")) {
				updateObj[0][17] = "Y";
			} else {
				updateObj[0][17] = "N";
			}

			updateObj[0][18] = actsPaymentOfBonus.getExGratiaPercent();
			updateObj[0][19] = actsPaymentOfBonus.getNoOfEmp();
			updateObj[0][20] = actsPaymentOfBonus.getExGratiaAmount();
			updateObj[0][21] = actsPaymentOfBonus.getExGratiaPaymentDate();

			String updateQuery = "UPDATE TBL_LMS_RTN_PAYMENTOFBONUS SET  NOOFEMPLOYEES_MALE= ?, NOOFEMPLOYEES_FEMALE= ?, EMPRECEIVINGLT10K_MALE= ?,"
					+ " EMPRECEIVINGLT10K_FEMALE= ?,EMPWORKINGLT30DAYS_MALE= ?,EMPWORKINGLT30DAYS_FEMALE= ?, DISQUALIFIED_MALE= ?,DISQUALIFIED_FEMALE= ?, ELIGIBLE_MALE= ?,  "
					+ "  ELIGIBLE_FEMALE= ?, TOTALELIGIBLE= ?, RATEOFBONUSPAYMENT= ?, TOTALBONUSPAID= ?, "
					+ "ADVANCEBONUSPAID= ?, NOOFEMPRECEIVINGADVBONUS= ?, BONUSPAYMENTDATE= ?, REASONSFORNONPAYMENT= ?, EXGRATIAPAID= ?, EXGRATIAPERCENT= ?, NOOFEMP= ?, "
					+ "EXGRATIAAMOUNT= ?, ExGratiaPaidDATE= ? WHERE PAYMENTOFBONUSID = "
					+ actsPaymentOfBonus.getPaymentOfBonusID();

			result = getLMSSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getInsertChildLabourRecords(ActsChildLabour actsChildLabour) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){

			Object insertChildlabourObj[][] = new Object[1][23];

			if (actsChildLabour.getAreHazardousProcessesCarriedOut().equals("true")) {
				insertChildlabourObj[0][0] = "Y";
			} else {
				insertChildlabourObj[0][0] = "N";
			}

			insertChildlabourObj[0][1] = actsChildLabour
					.getHazardousOccupationDetails();
			insertChildlabourObj[0][2] = actsChildLabour
					.getHazardousProcessDetails();

			if (actsChildLabour.getChildLaboursEmployed().equals("true")) {
				insertChildlabourObj[0][3] = "Y";
			} else {
				insertChildlabourObj[0][3] = "N";
			}

			insertChildlabourObj[0][4] = actsChildLabour.getNumberOfChildLaboursEmployed();

			insertChildlabourObj[0][5] = actsChildLabour
					.getWorkDetails();

			insertChildlabourObj[0][6] = actsChildLabour.getWorkHrsPerDay();
			insertChildlabourObj[0][7] = actsChildLabour
					.getWorkHrsWithoutBreak();
			insertChildlabourObj[0][8] = actsChildLabour
					.getBreakDuration();

			insertChildlabourObj[0][9] = actsChildLabour
					.getMaxSpreadOverHrs();
			insertChildlabourObj[0][10] = actsChildLabour
					.getNoOfChildLaboursWorkingEarlynLate();
			insertChildlabourObj[0][11] = actsChildLabour
					.getNumberOfHolidays();
			insertChildlabourObj[0][12] = actsChildLabour.getWagesPerHr();
			insertChildlabourObj[0][13] = actsChildLabour.getWagesPerDay();
			insertChildlabourObj[0][14] = actsChildLabour.getWagesPerWeek();
			insertChildlabourObj[0][15] = actsChildLabour.getWagesPerMonth();

			if (actsChildLabour.getSafetyNHealthInitiativesTaken().equals("true")) {
				insertChildlabourObj[0][16] = "Y";
			} else {
				insertChildlabourObj[0][16] = "N";
			}
			if (actsChildLabour.getChildLaboursAttendSchool().equals("true")) {
				insertChildlabourObj[0][17] = "Y";
			} else {
				insertChildlabourObj[0][17] = "N";
			}
			// insertChildlabourObj[0][17]=actsChildLabour.getChildlaboureducation();
			insertChildlabourObj[0][18] = actsChildLabour
					.getSchoolHrsPerDay();
			insertChildlabourObj[0][19] = actsChildLabour
					.getSchoolHrsPerWeek();

			/*
			 * if(actsChildLabour.getChildLabourFinancialOtherAssistance().equals("true")) {
			 * insertChildlabourObj[0][21] = "Y"; } else {
			 * insertChildlabourObj[0][21] = "N"; }
			 */

			if (actsChildLabour.getNotifiedToLabourDept().equals("true")) {
				insertChildlabourObj[0][20] = "Y";
			} else {
				insertChildlabourObj[0][20] = "N";
			}

			insertChildlabourObj[0][21] = actsChildLabour.getNoticeDate();

			if (actsChildLabour.getChildLabourRegisterMaintained().equals("true")) {
				insertChildlabourObj[0][22] = "Y";
			} else {
				insertChildlabourObj[0][22] = "N";
			}

			String insertQuery = "INSERT INTO TBL_LMS_RTN_CHILDLABOUR(CHILDLABOUTACTID,AREHAZARDOUSPROCCARRIEDOUT,HAZARDOUSOCCUPATIONDETAILS, HAZARDOUSPROCESSDETAILS, CHILDLABOURSEMPLOYED,"
					+ " NUMBEROFCHILDLABOURSEMPLOYED, WORKDETAILS,WORKHRSPERDAY,WORKHRSWITHOUTBREAK,"
					+ " BREAKDURATION,MAXSPREADOVERHRS,NOOFCHILDLABORSWRKNGEARLYNLATE, NUMBEROFHOLIDAYS,"
					+ " WAGESPERHR, WAGESPERDAY, WAGESPERWEEK, WAGESPERMONTH,SAFETYNHEALTHINITIATIVESTAKEN,"
					+ " CHILDLABOURSATTENDSCHOOL, SCHOOLHRSPERDAY, SCHOOLHRSPERWEEK,NOTIFIEDTOLABOURDEPT, NOTICEDATE,"
					+ " CHILDLABOURREGISTERMAINTAINED)"
					+ " VALUES((SELECT MAX(NVL(CHILDLABOUTACTID,0))+1 FROM TBL_LMS_RTN_CHILDLABOUR),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

			result = getLMSSqlModel().singleExecute(insertQuery,
					insertChildlabourObj);
			/*
			 * if(result){ Object
			 * bonusId[][]=getSqlModel().getSingleResult("SELECT
			 * MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
			 * 
			 * actsChildLabour.setBonusId(checkNull(String.valueOf(bonusId[0][0]))); }
			 */

			// }else{
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getUpdateChildLabourRecords(ActsChildLabour actsChildLabour) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){

			Object updateChildlabourObj[][] = new Object[1][23];

			if (actsChildLabour.getAreHazardousProcessesCarriedOut().equals("true")) {
				updateChildlabourObj[0][0] = "Y";
			} else {
				updateChildlabourObj[0][0] = "N";
			}

			updateChildlabourObj[0][1] = actsChildLabour
					.getHazardousOccupationDetails();
			updateChildlabourObj[0][2] = actsChildLabour
					.getHazardousProcessDetails();

			if (actsChildLabour.getChildLaboursEmployed().equals("true")) {
				updateChildlabourObj[0][3] = "Y";
			} else {
				updateChildlabourObj[0][3] = "N";
			}

			updateChildlabourObj[0][4] = actsChildLabour.getNumberOfChildLaboursEmployed();

			updateChildlabourObj[0][5] = actsChildLabour
					.getWorkDetails();

			updateChildlabourObj[0][6] = actsChildLabour.getWorkHrsPerDay();
			updateChildlabourObj[0][7] = actsChildLabour
					.getWorkHrsWithoutBreak();
			updateChildlabourObj[0][8] = actsChildLabour
					.getBreakDuration();

			updateChildlabourObj[0][9] = actsChildLabour
					.getMaxSpreadOverHrs();
			updateChildlabourObj[0][10] = actsChildLabour
					.getNoOfChildLaboursWorkingEarlynLate();
			updateChildlabourObj[0][11] = actsChildLabour
					.getNumberOfHolidays();
			updateChildlabourObj[0][12] = actsChildLabour.getWagesPerHr();
			updateChildlabourObj[0][13] = actsChildLabour.getWagesPerDay();
			updateChildlabourObj[0][14] = actsChildLabour.getWagesPerWeek();
			updateChildlabourObj[0][15] = actsChildLabour.getWagesPerMonth();

			if (actsChildLabour.getSafetyNHealthInitiativesTaken().equals("true")) {
				updateChildlabourObj[0][16] = "Y";
			} else {
				updateChildlabourObj[0][16] = "N";
			}
			if (actsChildLabour.getChildLaboursAttendSchool().equals("true")) {
				updateChildlabourObj[0][17] = "Y";
			} else {
				updateChildlabourObj[0][17] = "N";
			}
			// updateChildlabourObj[0][17]=actsChildLabour.getChildlaboureducation();
			updateChildlabourObj[0][18] = actsChildLabour
					.getSchoolHrsPerDay();
			updateChildlabourObj[0][19] = actsChildLabour
					.getSchoolHrsPerWeek();

			/*
			 * if(actsChildLabour.getChildLabourFinancialOtherAssistance().equals("true")) {
			 * updateChildlabourObj[0][21] = "Y"; } else {
			 * updateChildlabourObj[0][21] = "N"; }
			 */

			if (actsChildLabour.getNotifiedToLabourDept().equals("true")) {
				updateChildlabourObj[0][20] = "Y";
			} else {
				updateChildlabourObj[0][20] = "N";
			}

			updateChildlabourObj[0][21] = actsChildLabour.getNoticeDate();

			if (actsChildLabour.getChildLabourRegisterMaintained().equals("true")) {
				updateChildlabourObj[0][22] = "Y";
			} else {
				updateChildlabourObj[0][22] = "N";
			}

			String updateQuery = "UPDATE TBL_LMS_RTN_CHILDLABOUR SET AREHAZARDOUSPROCCARRIEDOUT = ?,HAZARDOUSOCCUPATIONDETAILS = ?, HAZARDOUSPROCESSDETAILS = ?, CHILDLABOURSEMPLOYED = ?,"
					+ " NUMBEROFCHILDLABOURSEMPLOYED = ?, WORKDETAILS = ?,WORKHRSPERDAY = ?,WORKHRSWITHOUTBREAK = ?,"
					+ " BREAKDURATION = ?,MAXSPREADOVERHRS = ?,NOOFCHILDLABORSWRKNGEARLYNLATE = ?, NUMBEROFHOLIDAYS = ?,"
					+ " WAGESPERHR = ?, WAGESPERDAY = ?, WAGESPERWEEK = ?, WAGESPERMONTH = ?,SAFETYNHEALTHINITIATIVESTAKEN = ?,"
					+ " CHILDLABOURSATTENDSCHOOL = ?, SCHOOLHRSPERDAY = ?, SCHOOLHRSPERWEEK = ?,NOTIFIEDTOLABOURDEPT = ?, NOTICEDATE = ?,"
					+ " CHILDLABOURREGISTERMAINTAINED = ? WHERE PAYMENTOFBONUSID = "
					+ actsChildLabour.getChildLaboutActID();
					

			result = getLMSSqlModel().singleExecute(updateQuery,
					updateChildlabourObj);
			/*
			 * if(result){ Object
			 * bonusId[][]=getSqlModel().getSingleResult("SELECT
			 * MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
			 * 
			 * actsChildLabour.setBonusId(checkNull(String.valueOf(bonusId[0][0]))); }
			 */

			// }else{
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getInsertMaternityBenefitsRecords(
			ActsMaternityBenefits actsMaternityBenefits) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){

			Object insertMaternityBenefitsObj[][] = new Object[1][13];
			
			if (actsMaternityBenefits.getMaternityLeavesApplied().equals("true")) {
				insertMaternityBenefitsObj[0][0] = "Y";
			} else {
				insertMaternityBenefitsObj[0][0] = "N";
			}
			
			if (actsMaternityBenefits.getAllApplicationsApproved().equals("true")) {
				insertMaternityBenefitsObj[0][1] = "Y";
			} else {
				insertMaternityBenefitsObj[0][1] = "N";
			}
		
		
			insertMaternityBenefitsObj[0][2] = actsMaternityBenefits.getNoOfRejectedApplications();
			insertMaternityBenefitsObj[0][3] = actsMaternityBenefits.getReasonsForRejection();
			
			if (actsMaternityBenefits.getMedicalBonusPaid().equals("true")) {
				insertMaternityBenefitsObj[0][4] = "Y";
			} else {
				insertMaternityBenefitsObj[0][4] = "N";
			}
			
			
			insertMaternityBenefitsObj[0][5] = actsMaternityBenefits
					.getMedicalBonusAmountPerPerson();
			
			if (actsMaternityBenefits.getMaternityBenefitPaid().equals("true")) {
				insertMaternityBenefitsObj[0][6] = "Y";
			} else {
				insertMaternityBenefitsObj[0][6] = "N";
			}
			
			
			insertMaternityBenefitsObj[0][7] = actsMaternityBenefits
					.getTotalAmountOfMaternityBenefitPaid();
			insertMaternityBenefitsObj[0][8] = actsMaternityBenefits
					.getNumofDays();
			insertMaternityBenefitsObj[0][9] = actsMaternityBenefits
					.getHighestMaternityBenefitPaid();
			insertMaternityBenefitsObj[0][10] = actsMaternityBenefits.getLowestMaternityBenefitPaid();
			
			if (actsMaternityBenefits.getNoOfDismissedEmployees().equals("true")) {
				insertMaternityBenefitsObj[0][11] = "Y";
			} else {
				insertMaternityBenefitsObj[0][11] = "N";
			}
			
		
			insertMaternityBenefitsObj[0][12] = actsMaternityBenefits.getDismissalReasons();
			

			String insertQuery = "INSERT INTO LMS_MATERNITYBENEFITS(MATERNITYBENEFITSID, MATERNITYLEAVESAPPLIED, "
								+"	ALLAPPLICATIONSAPPROVED, NOOFREJECTEDAPPLICATIONS, REASONSFORREJECTION, "
								+"	MEDICALBONUSPAID, MEDICALBONUSAMOUNTPERPERSON, MATERNITYBENEFITPAID, "
								+"	TOTAMTOFMATERNITYBENEFITPAID, BASISDAYS, HIGHESTMATERNITYBENEFITPAID, "
								+"	LOWESTMATERNITYBENEFITPAID, NOOFDISMISSEDEMPLOYEES, DISMISSALREASONS)"
					+ " VALUES((SELECT MAX(NVL(MATERNITYBENEFITSID,0))+1 FROM LMS_MATERNITYBENEFITS),?,?,?,?,?,?,?,?,?,?,?,?,?)";

			
			/*for (int i = 0; i < 13; i++) {
				System.out.println("" + insertMaternityBenefitsObj[0][i]);
			}*/
			result = getLMSSqlModel().singleExecute(insertQuery, insertMaternityBenefitsObj);
			/*
			 * if(result){ Object
			 * bonusId[][]=getSqlModel().getSingleResult("SELECT
			 * MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
			 * 
			 * actsMaternityBenefits.setBonusId(checkNull(String.valueOf(bonusId[0][0]))); }
			 */

			// }else{
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getUpdateMaternityBenefitsRecords(
			ActsMaternityBenefits actsMaternityBenefits) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){

			Object updateMaternityBenefitsObj[][] = new Object[1][13];
			
			if (actsMaternityBenefits.getMaternityLeavesApplied().equals("true")) {
				updateMaternityBenefitsObj[0][0] = "Y";
			} else {
				updateMaternityBenefitsObj[0][0] = "N";
			}
			
			if (actsMaternityBenefits.getAllApplicationsApproved().equals("true")) {
				updateMaternityBenefitsObj[0][1] = "Y";
			} else {
				updateMaternityBenefitsObj[0][1] = "N";
			}
		
		
			updateMaternityBenefitsObj[0][2] = actsMaternityBenefits.getNoOfRejectedApplications();
			updateMaternityBenefitsObj[0][3] = actsMaternityBenefits.getReasonsForRejection();
			
			if (actsMaternityBenefits.getMedicalBonusPaid().equals("true")) {
				updateMaternityBenefitsObj[0][4] = "Y";
			} else {
				updateMaternityBenefitsObj[0][4] = "N";
			}
			
			
			updateMaternityBenefitsObj[0][5] = actsMaternityBenefits
					.getMedicalBonusAmountPerPerson();
			
			if (actsMaternityBenefits.getMaternityBenefitPaid().equals("true")) {
				updateMaternityBenefitsObj[0][6] = "Y";
			} else {
				updateMaternityBenefitsObj[0][6] = "N";
			}
			
			
			updateMaternityBenefitsObj[0][7] = actsMaternityBenefits
					.getTotalAmountOfMaternityBenefitPaid();
			updateMaternityBenefitsObj[0][8] = actsMaternityBenefits
					.getNumofDays();
			updateMaternityBenefitsObj[0][9] = actsMaternityBenefits
					.getHighestMaternityBenefitPaid();
			updateMaternityBenefitsObj[0][10] = actsMaternityBenefits.getLowestMaternityBenefitPaid();
			
			if (actsMaternityBenefits.getNoOfDismissedEmployees().equals("true")) {
				updateMaternityBenefitsObj[0][11] = "Y";
			} else {
				updateMaternityBenefitsObj[0][11] = "N";
			}
			
		
			updateMaternityBenefitsObj[0][12] = actsMaternityBenefits.getDismissalReasons();
			

			String updateQuery = "UPDATE LMS_MATERNITYBENEFITS SET MATERNITYLEAVESAPPLIED =?, "
								+"	ALLAPPLICATIONSAPPROVED =?, NOOFREJECTEDAPPLICATIONS =?, REASONSFORREJECTION =?, "
								+"	MEDICALBONUSPAID =?, MEDICALBONUSAMOUNTPERPERSON =?, MATERNITYBENEFITPAID =?, "
								+"	TOTAMTOFMATERNITYBENEFITPAID =?, BASISDAYS =?, HIGHESTMATERNITYBENEFITPAID =?, "
								+"	LOWESTMATERNITYBENEFITPAID =?, NOOFDISMISSEDEMPLOYEES =?, DISMISSALREASONS =? WHERE PAYMENTOFBONUSID = "
					+ actsMaternityBenefits.getReturnID();

			
			/*for (int i = 0; i < 13; i++) {
				System.out.println("" + updateMaternityBenefitsObj[0][i]);
			}*/
			result = getLMSSqlModel().singleExecute(updateQuery, updateMaternityBenefitsObj);
			/*
			 * if(result){ Object
			 * bonusId[][]=getSqlModel().getSingleResult("SELECT
			 * MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
			 * 
			 * actsMaternityBenefits.setBonusId(checkNull(String.valueOf(bonusId[0][0]))); }
			 */

			// }else{
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getInsertGeneralInfoRecords(GeneralInfo generalInfo, WorkForceInfo workForceInfo) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){
			
			//Gereral Information
			Object insertObj[][] = new Object[1][24];
			
			insertObj[0][0] = generalInfo.getEstablishmentName();
			insertObj[0][1] = generalInfo.getEstablishmentAddress();
		//	insertObj[0][2] = "";//Establishment State
		//	insertObj[0][3] = "";//Establishment District
		//	insertObj[0][4] = generalInfo.getCity();//Establishment City
		//	insertObj[0][5] = generalInfo
		//			.getPincode();
			insertObj[0][2] = generalInfo
					.getName_Occupier();
			insertObj[0][3] = generalInfo
					.getName_Manager();
			insertObj[0][4] = generalInfo
					.getDesignation_Occupier();
			insertObj[0][5] = generalInfo
					.getDesignation_Manager();
		//	insertObj[0][10] = generalInfo.getEmployerAddress();
		//	insertObj[0][11] = generalInfo.getManagerAddress();
			insertObj[0][6] = generalInfo.getPhone_Occupier();
			insertObj[0][7] = generalInfo.getPhone_Manager();
			insertObj[0][8] = generalInfo.getEmail_Occupier();
			insertObj[0][9] = generalInfo.getEmail_Manager();
			insertObj[0][10] = generalInfo.getFax_Occupier();
			insertObj[0][11] = generalInfo.getFax_Manager();
			insertObj[0][12] = generalInfo.getMobile_Occupier();
			insertObj[0][13] = generalInfo.getMobile_Manager();
			//Registration number, expiry, and title of Act
			
			insertObj[0][14] = generalInfo.getRegistrationNumber();
			insertObj[0][15] = generalInfo.getRegistrationExpiryDate();
			insertObj[0][16] = generalInfo.getTitleOfAct();
			
			// end Registration number, expiry, and title of Act
			
			
		//	insertObj[0][23] = generalInfo.getLegalStatusOfEstablishment();
			//radio button
			
			/*if(generalInfo.getOwnershipOptionValue().equals("N"))
			{
				insertObj[0][17] = "N";
			}
			if(generalInfo.getOwnershipOptionValue().equals("F"))
			{
				insertObj[0][17] = "F";
			}
			if(generalInfo.getOwnershipOptionValue().equals("J"))
			{
				insertObj[0][17] = "J";
			}	*/	
			
			insertObj[0][17] = generalInfo.getOwnership();
			System.out.println("generalInfo.getOwnership() = " + generalInfo.getOwnership());
			
		//	insertObj[0][25] = generalInfo.getTypeOfEstablishment();
			
			insertObj[0][18] = generalInfo.getDigitCodeManufacturingProcess();
			insertObj[0][19] = generalInfo.getPlanApprovalNumber();
			insertObj[0][20] = generalInfo.getPlanApprovalDate();
			
			if (generalInfo.getFactoryCertificateFlag().equals("true")) {
				insertObj[0][21] =  "Y";
			} else {
				insertObj[0][21] =  "N";
			}
			
			insertObj[0][22] = generalInfo.getFactoryCertificateDate();
		//	insertObj[0][23] = generalInfo.getEmploymentTypeAsPerShcedule();
		//	insertObj[0][24] = generalInfo.getNumberOfBranchOrBusiness();
		//	insertObj[0][25] = generalInfo.getNameOfBranchOrBusiness();
		//	insertObj[0][26] = generalInfo.getAddressofBranchOrBusiness();
		//	insertObj[0][27] = generalInfo.getContactPersonOfBranchOrBusiness();
		//	insertObj[0][28] = generalInfo.getTelephoneNumberOfBranchOrBusiness();
			insertObj[0][23] = generalInfo.getDateofCommencement();
			for (int i = 0; i < insertObj.length; i++) {
				for (int j = 0; j < insertObj[0].length; j++) {
					logger.info("insertObj["+i+"]["+j+"]........."+insertObj[i][j]);
				}
			}
			

			String insertQuery = "INSERT INTO TBL_LMS_RTN_GENERALINFO(GENERALINFOID, ESTABLISHMENTNAME, ESTABLISHMENTADDRESS, NAME_OCCUPIER,"
					+ " NAME_MANAGER,DESIGNATION_OCCUPIER,DESIGNATION_MANAGER, PHONE_OCCUPIER,PHONE_MANAGER, EMAIL_OCCUPIER,  "
					+ " EMAIL_MANAGER, FAX_OCCUPIER, FAX_MANAGER, MOBILE_OCCUPIER, "
					+ "MOBILE_MANAGER, REGISTRATIONNUMBER, REGISTRATIONEXPIRYDATE, TITLEOFACT, OWNERSHIPID, NICCODEID, PLANAPPROVALNUMBER, "
					+ "PLANAPPROVALDATE, HASSTABILITYCERTIFICATE, DATEOFSTABILITYCERTIFICATE , COMMENCEMENTDATE)"
					+ " VALUES((SELECT MAX(NVL(GENERALINFOID,0))+1 FROM TBL_LMS_RTN_GENERALINFO),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'))";
			
		
			
			result = getLMSSqlModel().singleExecute(insertQuery, insertObj);
			
			//General Information end
			
			//workForce start
			Object insertworkForceObj[][] = new Object[1][30];
			
			
			insertworkForceObj[0][0] = workForceInfo.getPermanentManagersMale();
			insertworkForceObj[0][1] = workForceInfo.getPermanentManagersFemale();
		//	insertworkForceObj[0][2] = workForceInfo.getPermanenetManagersTotal(); // Total Manager and supervisors
			insertworkForceObj[0][2] = workForceInfo.getWorkersMale();
			insertworkForceObj[0][3] = workForceInfo.getWorkersFemale();
		//	insertworkForceObj[0][5] = workForceInfo.getWorkersTotal();//total workers
			insertworkForceObj[0][4] = workForceInfo.getAdolscentWorkersMale();
			insertworkForceObj[0][5] = workForceInfo.getAdolscentWorkersFemale();
		//	insertworkForceObj[0][8] = workForceInfo.getWorkersOver15ButLess18Total();
			insertworkForceObj[0][6] = workForceInfo.getContractWorkersMale();
			insertworkForceObj[0][7] = workForceInfo.getContractWorkersFemale();
		//	insertworkForceObj[0][11] = workForceInfo.getContractWorkersTotal();
			insertworkForceObj[0][8] = workForceInfo.getDailywageWorkersMale();
			insertworkForceObj[0][9] = workForceInfo.getDailywageWorkersFemale();
		//	insertworkForceObj[0][10] = workForceInfo.getDailywageWorkersTotal();
			insertworkForceObj[0][10] = workForceInfo.getTempWorkersMale();
			insertworkForceObj[0][11] = workForceInfo.getTempWorkersFemale();
		//	insertworkForceObj[0][13] = workForceInfo.getTempWorkersTotal();
			insertworkForceObj[0][12] = workForceInfo.getCasualWorkersMale();
			insertworkForceObj[0][13] = workForceInfo.getCasualWorkersFemale();
			//insertworkForceObj[0][13] = workForceInfo.getCasualWorkersTotal();
			insertworkForceObj[0][14] = workForceInfo.getApprenticeFemale();
			insertworkForceObj[0][15] = workForceInfo.getApprenticeFemale();
		//	insertworkForceObj[0][13] = workForceInfo.getApprenticeTotal();
			insertworkForceObj[0][16] = workForceInfo.getTraineeMale();
			insertworkForceObj[0][17] = workForceInfo.getTraineeFemale();
		//	insertworkForceObj[0][13] = workForceInfo.getTraineeTotal();
			insertworkForceObj[0][18] = workForceInfo.getFamilyMembersMalePaid();
			insertworkForceObj[0][19] = workForceInfo.getFamilyMembersFemalePaid();
		//	insertworkForceObj[0][13] = workForceInfo.getFamilyMembersTotalPaid();
			insertworkForceObj[0][20] = workForceInfo.getFamilyMembersMaleUnpaid();
			insertworkForceObj[0][21] = workForceInfo.getFamilyMembersFemaleUnpaid();
		//	insertworkForceObj[0][13] = workForceInfo.getFamilyMembersTotalUnpaid();
			insertworkForceObj[0][22] = workForceInfo.getPermWorkersMaleLessThanOne();
			insertworkForceObj[0][23] = workForceInfo.getPermWorkersFemaleLessThanOne();
		//	insertworkForceObj[0][13] = workForceInfo.getPermWorkersTotalLessThanOne();
			insertworkForceObj[0][24] = workForceInfo.getPermWorkersMaleOneToFive();
			insertworkForceObj[0][25] = workForceInfo.getPermWorkersFemaleOneToFive();
		//	insertworkForceObj[0][16] = workForceInfo.getPermWorkersTotalOneToFive();
			insertworkForceObj[0][26] = workForceInfo.getPermWorkersMaleFiveToTen();
			insertworkForceObj[0][27] = workForceInfo.getPermWorkersFemaleFiveToTen();
		//	insertworkForceObj[0][13] = workForceInfo.getPermWorkersTotalFiveToTen();
			insertworkForceObj[0][28] = workForceInfo.getPermWorkersMaleMoreThanTen();
			insertworkForceObj[0][29] = workForceInfo.getPermWorkersFemaleMoreThanTen();
		//	insertworkForceObj[0][13] = workForceInfo.getPermWorkersTotalMoreThanTen();
			

			String insertWorkForceQuery = "INSERT INTO TBL_LMS_RTN_WORKFORCE(WORKFORCEID, PERMANANTMANAGERS_MALE, PERMANANTMANAGERS_FEMALE, WORKERS_MALE,"
					+ " WORKERS_FEMALE,WORKERS_ADOLSCENT_MALE,WORKERS_ADOLSCENT_FEMALE, CONTRACTWORKERS_MALE,CONTRACTWORKERS_FEMALE, DAILYWGWRKRS_MALE,  "
					+ " DAILYWGWRKRS_FEMALE, TEMPWORKERS_MALE, TEMPWORKERS_FEMALE, CASUALWRKRS_MALE, "
					+ "CASUALWRKRS_FEMALE, APPRENTICES_MALE, APPRENTICES_FEMALE, TRAINEES_MALE, TRAINEES_FEMALE, FAMILYMEMBERS_PAID_MALE, FAMILYMEMBERS_PAID_FEMALE, "
					+ "FAMILYMEMBERS_UNPAID_MALE, FAMILYMEMBERS_UNPAID_FEMALE, PW_M_LESSTHAN1YR , PW_F_LESSTHAN1YR,PW_M_1TO5,PW_F_1TO5,PW_M_5TO10,PW_F_5TO10,PW_M_MORETHAN10,PW_F_MORETHAN10)"
					+ " VALUES((SELECT MAX(NVL(WORKFORCEID,0))+1 FROM TBL_LMS_RTN_WORKFORCE),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			for (int i = 0; i < 30; i++) {
				System.out.println("" + insertworkForceObj[0][i]);
			}
			
			result = getLMSSqlModel().singleExecute(insertWorkForceQuery, insertworkForceObj);
			
			//workForce end
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getUpdateGeneralInfoRecords(GeneralInfo generalInfo, WorkForceInfo workForceInfo2) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			// if(actsPaymentOfBonus.getReturnId().equals("")){
			
			//Gereral Information
			Object updateObj[][] = new Object[1][24];
			
			updateObj[0][0] = generalInfo.getEstablishmentName();
			updateObj[0][1] = generalInfo.getEstablishmentAddress();
		//	updateObj[0][2] = "";//Establishment State
		//	updateObj[0][3] = "";//Establishment District
		//	updateObj[0][4] = generalInfo.getCity();//Establishment City
		//	updateObj[0][5] = generalInfo
		//			.getPincode();
			updateObj[0][2] = generalInfo
					.getName_Occupier();
			updateObj[0][3] = generalInfo
					.getName_Manager();
			updateObj[0][4] = generalInfo
					.getDesignation_Occupier();
			updateObj[0][5] = generalInfo
					.getDesignation_Manager();
		//	updateObj[0][10] = generalInfo.getEmployerAddress();
		//	updateObj[0][11] = generalInfo.getManagerAddress();
			updateObj[0][6] = generalInfo.getPhone_Occupier();
			updateObj[0][7] = generalInfo.getPhone_Manager();
			updateObj[0][8] = generalInfo.getEmail_Occupier();
			updateObj[0][9] = generalInfo.getEmail_Manager();
			updateObj[0][10] = generalInfo.getFax_Occupier();
			updateObj[0][11] = generalInfo.getFax_Manager();
			updateObj[0][12] = generalInfo.getMobile_Occupier();
			updateObj[0][13] = generalInfo.getMobile_Manager();
			//Registration number, expiry, and title of Act
			
			updateObj[0][14] = generalInfo.getRegistrationNumber();
			updateObj[0][15] = generalInfo.getRegistrationExpiryDate();
			updateObj[0][16] = generalInfo.getTitleOfAct();
			
			// end Registration number, expiry, and title of Act
			
			
		//	updateObj[0][23] = generalInfo.getLegalStatusOfEstablishment();
			//radio button
			
			if(generalInfo.getOwnershipOptionValue().equals("N"))
			{
				updateObj[0][17] = "N";
			}
			if(generalInfo.getOwnershipOptionValue().equals("F"))
			{
				updateObj[0][17] = "F";
			}
			if(generalInfo.getOwnershipOptionValue().equals("J"))
			{
				updateObj[0][17] = "J";
			}		
			
		//	updateObj[0][17] = generalInfo.getOwnership();
			
			
		//	updateObj[0][25] = generalInfo.getTypeOfEstablishment();
			
			updateObj[0][18] = generalInfo.getDigitCodeManufacturingProcess();
			updateObj[0][19] = generalInfo.getPlanApprovalNumber();
			updateObj[0][20] = generalInfo.getPlanApprovalDate();
			
			if (generalInfo.getFactoryCertificateFlag().equals("true")) {
				updateObj[0][21] =  "Y";
			} else {
				updateObj[0][21] =  "N";
			}
			
			updateObj[0][22] = generalInfo.getFactoryCertificateDate();
		//	updateObj[0][23] = generalInfo.getEmploymentTypeAsPerShcedule();
		//	updateObj[0][24] = generalInfo.getNumberOfBranchOrBusiness();
		//	updateObj[0][25] = generalInfo.getNameOfBranchOrBusiness();
		//	updateObj[0][26] = generalInfo.getAddressofBranchOrBusiness();
		//	updateObj[0][27] = generalInfo.getContactPersonOfBranchOrBusiness();
		//	updateObj[0][28] = generalInfo.getTelephoneNumberOfBranchOrBusiness();
			updateObj[0][23] = generalInfo.getDateofCommencement();
			

			String updateQuery = "UPDATE TBL_LMS_RTN_GENERALINFO SET ESTABLISHMENTNAME =?, ESTABLISHMENTADDRESS =?, NAME_OCCUPIER =?,"
					+ " NAME_MANAGER =?,DESIGNATION_OCCUPIER =?,DESIGNATION_MANAGER =?, PHONE_OCCUPIER =?,PHONE_MANAGER =?, EMAIL_OCCUPIER =?,  "
					+ " EMAIL_MANAGER =?, FAX_OCCUPIER =?, FAX_MANAGER =?, MOBILE_OCCUPIER =?, "
					+ "MOBILE_MANAGER =?, REGISTRATIONNUMBER =?, REGISTRATIONEXPIRYDATE =?, TITLEOFACT =?, OWNERSHIPID =?, NICCODEID =?, PLANAPPROVALNUMBER =?, "
					+ "PLANAPPROVALDATE =?, HASSTABILITYCERTIFICATE =?, DATEOFSTABILITYCERTIFICATE =? , COMMENCEMENTDATE =? WHERE GENERALINFOID = "
					+ generalInfo.getGeneralInfoID();
			
		
			
			result = getLMSSqlModel().singleExecute(updateQuery, updateObj);
			
			//General Information end
			
			//workForce start
			Object updateworkForceObj[][] = new Object[1][30];
			WorkForceInfo workForceInfo = new WorkForceInfo();
			
			updateworkForceObj[0][0] = workForceInfo.getPermanentManagersMale();
			updateworkForceObj[0][1] = workForceInfo.getPermanentManagersFemale();
		//	updateworkForceObj[0][2] = workForceInfo.getPermanenetManagersTotal(); // Total Manager and supervisors
			updateworkForceObj[0][2] = workForceInfo.getWorkersMale();
			updateworkForceObj[0][3] = workForceInfo.getWorkersFemale();
		//	updateworkForceObj[0][5] = workForceInfo.getWorkersTotal();//total workers
			updateworkForceObj[0][4] = workForceInfo.getAdolscentWorkersMale();
			updateworkForceObj[0][5] = workForceInfo.getAdolscentWorkersFemale();
		//	updateworkForceObj[0][8] = workForceInfo.getWorkersOver15ButLess18Total();
			updateworkForceObj[0][6] = workForceInfo.getContractWorkersMale();
			updateworkForceObj[0][7] = workForceInfo.getContractWorkersFemale();
		//	updateworkForceObj[0][11] = workForceInfo.getContractWorkersTotal();
			updateworkForceObj[0][8] = workForceInfo.getDailywageWorkersMale();
			updateworkForceObj[0][9] = workForceInfo.getDailywageWorkersFemale();
		//	updateworkForceObj[0][10] = workForceInfo.getDailywageWorkersTotal();
			updateworkForceObj[0][10] = workForceInfo.getTempWorkersMale();
			updateworkForceObj[0][11] = workForceInfo.getTempWorkersFemale();
		//	updateworkForceObj[0][13] = workForceInfo.getTempWorkersTotal();
			updateworkForceObj[0][12] = workForceInfo.getCasualWorkersMale();
			updateworkForceObj[0][13] = workForceInfo.getCasualWorkersFemale();
			//updateworkForceObj[0][13] = workForceInfo.getCasualWorkersTotal();
			updateworkForceObj[0][14] = workForceInfo.getApprenticeFemale();
			updateworkForceObj[0][15] = workForceInfo.getApprenticeFemale();
		//	updateworkForceObj[0][13] = workForceInfo.getApprenticeTotal();
			updateworkForceObj[0][16] = workForceInfo.getTraineeMale();
			updateworkForceObj[0][17] = workForceInfo.getTraineeFemale();
		//	updateworkForceObj[0][13] = workForceInfo.getTraineeTotal();
			updateworkForceObj[0][18] = workForceInfo.getFamilyMembersMalePaid();
			updateworkForceObj[0][19] = workForceInfo.getFamilyMembersFemalePaid();
		//	updateworkForceObj[0][13] = workForceInfo.getFamilyMembersTotalPaid();
			updateworkForceObj[0][20] = workForceInfo.getFamilyMembersMaleUnpaid();
			updateworkForceObj[0][21] = workForceInfo.getFamilyMembersFemaleUnpaid();
		//	updateworkForceObj[0][13] = workForceInfo.getFamilyMembersTotalUnpaid();
			updateworkForceObj[0][22] = workForceInfo.getPermWorkersMaleLessThanOne();
			updateworkForceObj[0][23] = workForceInfo.getPermWorkersFemaleLessThanOne();
		//	updateworkForceObj[0][13] = workForceInfo.getPermWorkersTotalLessThanOne();
			updateworkForceObj[0][24] = workForceInfo.getPermWorkersMaleOneToFive();
			updateworkForceObj[0][25] = workForceInfo.getPermWorkersFemaleOneToFive();
		//	updateworkForceObj[0][16] = workForceInfo.getPermWorkersTotalOneToFive();
			updateworkForceObj[0][26] = workForceInfo.getPermWorkersMaleFiveToTen();
			updateworkForceObj[0][27] = workForceInfo.getPermWorkersFemaleFiveToTen();
		//	updateworkForceObj[0][13] = workForceInfo.getPermWorkersTotalFiveToTen();
			updateworkForceObj[0][28] = workForceInfo.getPermWorkersMaleMoreThanTen();
			updateworkForceObj[0][29] = workForceInfo.getPermWorkersFemaleMoreThanTen();
		//	updateworkForceObj[0][13] = workForceInfo.getPermWorkersTotalMoreThanTen();
			

			String updateWorkForceQuery = "UPDATE TBL_LMS_RTN_WORKFORCE SET PERMANANTMANAGERS_MALE=?, PERMANANTMANAGERS_FEMALE=?, WORKERS_MALE=?,"
					+ " WORKERS_FEMALE=?,WORKERS_ADOLSCENT_MALE=?,WORKERS_ADOLSCENT_FEMALE=?, CONTRACTWORKERS_MALE=?,CONTRACTWORKERS_FEMALE=?, DAILYWGWRKRS_MALE=?,  "
					+ " DAILYWGWRKRS_FEMALE=?, TEMPWORKERS_MALE=?, TEMPWORKERS_FEMALE=?, CASUALWRKRS_MALE=?, "
					+ "CASUALWRKRS_FEMALE=?, APPRENTICES_MALE=?, APPRENTICES_FEMALE=?, TRAINEES_MALE=?, TRAINEES_FEMALE=?, FAMILYMEMBERS_PAID_MALE=?, FAMILYMEMBERS_PAID_FEMALE=?, "
					+ "FAMILYMEMBERS_UNPAID_MALE=?, FAMILYMEMBERS_UNPAID_FEMALE=?, PW_M_LESSTHAN1YR=? , PW_F_LESSTHAN1YR=?,PW_M_1TO5=?,PW_F_1TO5=?,PW_M_5TO10=?,PW_F_5TO10=?,PW_M_MORETHAN10=?,PW_F_MORETHAN10=? WHERE WORKFORCEID = "
					+ workForceInfo.getWorkForceId();
			
			result = getLMSSqlModel().singleExecute(updateWorkForceQuery, updateworkForceObj);
			
			//workForce end
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//===============METHODS ADDED BY GANESH ENDS=============//
	
	
	/*
	 * METHODS ADDED BY SHASHIKANT :START
	 */
	
	/**
	 * METHOD TO SET GRATUITY ACT
	 */
	public GratuityRules setGratuityAct(String orgId,String fromDate,String toDate) {
		GratuityRules gratuityBean=new GratuityRules();
		/*
		 * GET GRATUITY ACT INFORMATION
		 */
		String selGratuityAct="SELECT (SELECT COUNT(*) FROM HRMS_EMP_NOMINEE  )AS NOMINEE_APPLICATION "
								+"	, (SELECT COUNT(*) FROM HRMS_EMP_NOMINEE WHERE IS_NOM_ACCEPTED='Y' )AS NOMINEE_ACCEPTED "  
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY)AS GRATUITY_APPLICATION "
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='A')AS GRATUITY_APPROVED "
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='R')AS GRATUITY_REJECT	 "
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='R' AND GRAT_REJ_OTHER_REASONS='Workers disorderly conduct')AS GRATUITY_REJECT1 " 
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='R' AND GRAT_REJ_OTHER_REASONS='Workers not in continuous service')AS GRATUITY_REJECT2 "	
								+"	,(SELECT COUNT(*) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='R' AND GRAT_REJ_REASONS IS NOT NULL)AS GRATUITY_OTHER_REJECT "
								+"	,(SELECT NVL(SUM(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY WHERE GRAT_CLAIM_STATUS='A')AS TOTAL_GRATUITY"
								+"	,(SELECT NVL(MAX(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY)AS MAX_GRATUITY "
								+"	,(SELECT NVL(MIN(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY)AS MIN_GRATUITY	"
								+"	,(SELECT NVL(SUM(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY WHERE GRAT_PAY_MODE='CH')AS CASH_GRATUITY"
								+"	,(SELECT NVL(SUM(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY WHERE GRAT_PAY_MODE='CK')AS CHEQUE_GRATUITY"
								+"	,(SELECT NVL(SUM(GRAT_CLAIM_AMT),0) FROM HRMS_EMP_GRATUITY WHERE GRAT_PAY_MODE='TR')AS TRANSFER_GRATUITY "
								+" ,(SELECT GRAT_REJ_OTHER_REASONS||''||RTRIM (xmlagg (xmlelement (e, GRAT_REJ_REASONS || ',')).extract ('//text()'), ',') GRAT_REJ_REASONS "
								+"	FROM  HRMS_EMP_GRATUITY	WHERE GRAT_REJ_OTHER_REASONS IS NULL GROUP BY  GRAT_REJ_OTHER_REASONS)	FROM  HRMS_EMP_NOMINEE ";
		
		
		
		Object[][]gratuityActObj=getSqlModel().getSingleResult(selGratuityAct);
		if(gratuityActObj !=null && gratuityActObj.length>0){
			gratuityBean.setNominations_Received(Integer.parseInt(String.valueOf(gratuityActObj[0][0])));
			gratuityBean.setNominations_Accepted(Integer.parseInt(String.valueOf(gratuityActObj[0][1])));
			gratuityBean.setApplications_Received(Integer.parseInt(String.valueOf(gratuityActObj[0][2])));
			gratuityBean.setApplications_Approved(Integer.parseInt(String.valueOf(gratuityActObj[0][3])));
			gratuityBean.setApplications_Rejected(Integer.parseInt(String.valueOf(gratuityActObj[0][4])));
			gratuityBean.setWorkerDisorderlyConduct(Integer.parseInt(String.valueOf(gratuityActObj[0][5])));
			gratuityBean.setWorkersContinuedService(Integer.parseInt(String.valueOf(gratuityActObj[0][6])));
			gratuityBean.setOtherReasons(Integer.parseInt(String.valueOf(gratuityActObj[0][7])));
			gratuityBean.setAmountOfGratuityPaid(Integer.parseInt(String.valueOf(gratuityActObj[0][8])));
			gratuityBean.setMaxGratuityAmount(Integer.parseInt(String.valueOf(gratuityActObj[0][9])));
			gratuityBean.setMinGratuityAmount(Integer.parseInt(String.valueOf(gratuityActObj[0][10])));
			gratuityBean.setGratuityPaid_Cash(Integer.parseInt(String.valueOf(gratuityActObj[0][11])));
			gratuityBean.setGratuityPaid_BankCheque((String.valueOf(gratuityActObj[0][12])));
			gratuityBean.setGratuityPaid_NormalCheque((String.valueOf(gratuityActObj[0][12])));
			gratuityBean.setGratuityPaid_CreditToAccount((String.valueOf(gratuityActObj[0][13])));
			gratuityBean.setOtherReasonsSpecify((String.valueOf(gratuityActObj[0][14])));			
		}
		/**
		 * CASE PENDING
		 */
		String casePendingQuery="SELECT NVL(DISPUTE_STATEMENT||' '||DISPUTE_DESCRIPTION,' ') FROM HRMS_DISPUTES WHERE DISPUTE_RESOLN_STMNT IS NULL AND DISPUTE_RESOLN_METHOD IS NULL";
		Object[][]caseObj=getSqlModel().getSingleResult(casePendingQuery);
		if(caseObj !=null && caseObj.length>0){
			gratuityBean.setAnyCasesPending("true");
			gratuityBean.setPendingCasesDetails(String.valueOf(caseObj[0][0]));
		}
		
		
		
		return gratuityBean;
	}

	public RentAllowance setHRA(String orgId, String fromDate, String toDate) {
		RentAllowance rentBean=new RentAllowance();
		String date[]=toDate.split("-");
		String month=date[1];
		String year=date[2];
		
		/**
		 * ?EMP PROVIDE ACC,
		 * ACC PROVIDE TYPE
		 */
		String accQuery="SELECT (SELECT DECODE(COUNT(*),0,'false','true')	FROM LMS_ACCOMMODATION WHERE ACCOM_TYPE IS NOT NULL) AS EMP_ACCM "
						+"	, (SELECT DECODE(COUNT(*),0,'false','true') FROM LMS_ACCOMMODATION WHERE ACCOM_TYPE IS NOT NULL AND (ACCOM_TYPE='F' OR  ACCOM_TYPE='H') )AS ACCFREE "
						+"	FROM LMS_ACCOMMODATION ";
		Object[][]AccObj=getSqlModel().getSingleResult(accQuery);
		if(AccObj !=null && AccObj.length>0){
			rentBean.setAccommodationProvided(String.valueOf(AccObj[0][0]));
			System.out.println(":::::::::_"+AccObj[0][0]);
			rentBean.setFreeAccommodationProvided(String.valueOf(AccObj[0][1]));
		}
		
		
		/**
		 * SET TOTAL NO EMP HRA PAID,
		 * MAX HRA PAID,
		 * TOTAL HRA PAID
		 */
		String hraQuery="SELECT COUNT(*)AS HRA_PAID_EMP,NVL(MAX(SAL_AMOUNT),0)AS MAX_HRA,NVL(SUM(SAL_AMOUNT),0)AS TOTAL_HRA FROM HRMS_SAL_CREDITS_"+year+" " 
						+"	INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE) "
						+"	WHERE SAL_CREDIT_CODE=4	AND  LEDGER_MONTH="+month+"  AND SAL_AMOUNT>0  ";
		
		//  hraQuery+=" AND RECORD_ORG_ID=1 ";
		Object[][]hraObj=getSqlModel().getSingleResult(hraQuery);
		if(hraObj !=null && hraObj.length>0){
			rentBean.setNoOfEmpHRAPaid(Integer.parseInt(String.valueOf(hraObj[0][0])));
			rentBean.setMaxHRAPaid(Integer.parseInt(String.valueOf(hraObj[0][1])));
			rentBean.setTotalHRAPaid(Integer.parseInt(String.valueOf(hraObj[0][2])));
		}
		
		setHRADisputes( orgId,  fromDate,toDate,rentBean);
		
		return rentBean;
	}

	public RentAllowance setHRADisputes(String orgId, String fromDate,
			String toDate, RentAllowance rentBean) {
		//RentAllowance rentBean=new RentAllowance();
		String date[]=toDate.split("-");
		String month=date[1];
		String year=date[2];
		String HRADisputeQuery="SELECT DISTINCT "
							+"	(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_DISPUTES	WHERE DISPUTE_UNDER_ACT='MHRA' AND DISPUTE_LOGGED_ON BETWEEN TO_DATE('"+fromDate+"','DD-MM-YYYY') AND TO_DATE('"+toDate+"','DD-MM-YYYY'))AS HRA "
							+"	,(SELECT COUNT(*) FROM HRMS_DISPUTES	WHERE DISPUTE_UNDER_ACT='MHRA' AND DISPUTE_LOGGED_ON BETWEEN TO_DATE('"+fromDate+"','DD-MM-YYYY') AND TO_DATE('"+toDate+"','DD-MM-YYYY'))AS HRA_COUNT "
							+" ,(SELECT (DECODE(COUNT(*),0,'false','true')) FROM	HRMS_DISPUTES  INNER JOIN HRMS_COMMITEE_HDR ON(HRMS_COMMITEE_HDR.COMMITTEE_ID=HRMS_DISPUTES.DISPUTE_COMMITTE_ID) WHERE  COMMITTEE_TYPE='T') AS TRADE_UNION	FROM  HRMS_DISPUTES";
		Object hraDisputeObj[][]=getSqlModel().getSingleResult(HRADisputeQuery);
		if(hraDisputeObj !=null && hraDisputeObj.length>0){
			//	Were there any disputes over house rent allowance during the period?
			rentBean.setHRADisputesReceived(String.valueOf(hraDisputeObj[0][0]));
			System.out.println("hraDisputeObj[0][0] : "+hraDisputeObj[0][0]);
			//	If yes, how many disputes? (number) 
			rentBean.setNoOfHRADisputes(Integer.parseInt(String.valueOf(hraDisputeObj[0][1])));
			rentBean.setWorkersRepresentedByTU(String.valueOf(hraDisputeObj[0][2]));
		}
		
		
		String disp="SELECT NVL(COMMITTEE_NAME,' '),DISPUTE_RESOLN_METHOD FROM HRMS_DISPUTES "
					+"	INNER JOIN HRMS_COMMITEE_HDR ON(HRMS_COMMITEE_HDR.COMMITTEE_ID=HRMS_DISPUTES.DISPUTE_COMMITTE_ID)"
					+"	WHERE DISPUTE_UNDER_ACT='MHRA'";
		Object[][]dispObj=getSqlModel().getSingleResult(disp);
		if(dispObj !=null && dispObj.length>0){
			rentBean.setTUName(String.valueOf(dispObj[0][0]));
			//rentBean.setRentAllowanceHidden(String.valueOf(dispObj[0][1]));
		}
		return rentBean;
	}

	public FactorySafetyHealth setAnualFactorySafety_Health(String orgId,
			String string, String string2, RentAllowance rentAllowanceBean) {
		FactorySafetyHealth factorySafetyHealthBean=new FactorySafetyHealth();

		String sel_32_33QUery="SELECT DISTINCT " 
							+" (SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H'	)AS POLICY_SAFETY_POLICY "
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%N%')AS POLICY_NOTICE_BOARD	"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%C%')AS POLICY_CIRCULAR_BOARD"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%O%')AS POLICY_OTHER_BOARD "
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%M%')AS POLICY_MARATHI_LANG "
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%H%')AS POLICY_HINDI_LANG"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='P' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%E%')AS POLICY_ENGLISH_LAG"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H'	)AS RULE_SAFETY_POLICY"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%N%')AS POLICY_NOTICE_BOARD	"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%C%')AS POLICY_CIRCULAR_BOARD"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_MEDIUM  LIKE '%O%')AS POLICY_OTHER_BOARD "
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%M%')AS POLICY_MARATHI_LANG "
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%H%')AS POLICY_HINDI_LANG"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H' AND RULE_POLICY_COMM_LANG  LIKE '%E%')AS POLICY_ENGLISH_LAG"
							+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CATEGORY='R' AND RULE_POLICY_TYPE='H'	)AS RULE_SAFETY_POLICY"
							
							+"	FROM HRMS_RULES_POLICY";
		
		Object[][]data=getSqlModel().getSingleResult(sel_32_33QUery);
		if(data !=null && data.length>0){
			factorySafetyHealthBean.setHasWrittenPolicy(String.valueOf(data[0][0]));
			factorySafetyHealthBean.setPolicyCommunicationMediaIDNotice(String.valueOf(data[0][1]));
			factorySafetyHealthBean.setPolicyCommunicationMediaIDCircular(String.valueOf(data[0][2]));
			factorySafetyHealthBean.setPolicyCommunicationMediaIDOther(String.valueOf(data[0][3]));
			factorySafetyHealthBean.setPolicyCommunicationLanguageIDMarathi(String.valueOf(data[0][4]));
			factorySafetyHealthBean.setPolicyCommunicationLanguageIDHindi(String.valueOf(data[0][5]));
			factorySafetyHealthBean.setPolicyCommunicationLanguageIDEnglish(String.valueOf(data[0][6]));
			factorySafetyHealthBean.setHasWrittenRules(String.valueOf(data[0][7]));
			factorySafetyHealthBean.setRuleCommunicationMediaIDNotice(String.valueOf(data[0][8]));
			factorySafetyHealthBean.setRuleCommunicationMediaIDCircular(String.valueOf(data[0][9]));
			factorySafetyHealthBean.setRuleCommunicationMediaIDOther(String.valueOf(data[0][10]));
			factorySafetyHealthBean.setRuleCommunicationLanguageIDMarathi(String.valueOf(data[0][11]));
			factorySafetyHealthBean.setRuleCommunicationLanguageIDHindi(String.valueOf(data[0][12]));
			factorySafetyHealthBean.setRuleCommunicationLanguageIDEnglish(String.valueOf(data[0][13]));
		}
		String set34_35_36="SELECT "
						+"		(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE IS_EMERG_EVAC_PLAN_AVBL='Y')AS EMGERGENCY "  
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE IS_SAFETY_POSTERS_DISPLAYED='Y')AS EMGERGENCY1 "
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_EMERGENCY_EVACUATION WHERE EMERG_EVACN_DRILL='Y')AS EVALUTION"
						+"		,(SELECT NVL(MAX(EMERG_EVACN_DRILL_DATE),' ') FROM HRMS_EMERGENCY_EVACUATION )AS MAXDATE	"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H' AND SAFETY_MEMBER_ROLE='O')AS SAFETY"
						+"		 ,(SELECT COUNT(*) FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H' AND SAFETY_MEMBER_ROLE='O')AS SAFETYCOUNT"
						+"			,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H' AND SAFETY_MEMBER_ROLE='O' AND SAFETY_TRAINING_STATUS='Y')AS SAFETY_OFFICERCOUNT"
						+"			,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H')AS FACT_SAFETY"
						+"		 ,(SELECT COUNT(*) FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H' AND SAFETY_MEMBER_ROLE='W')AS EXITCOUNT "
						+"		  ,(SELECT COUNT(*) FROM HRMS_SAFETY_COMMITTEE WHERE SAFETY_COMM_TYPE='H' AND SAFETY_MEMBER_ROLE='M')AS MEMBERCOUNT"
						+"				FROM   HRMS_RULES_POLICY ";
		Object[][] Obj34_35_36=getSqlModel().getSingleResult(set34_35_36);
		if(Obj34_35_36 !=null &&Obj34_35_36.length>0){
			factorySafetyHealthBean.setHasEnEplan(checkNull(String.valueOf(Obj34_35_36[0][0])));
			factorySafetyHealthBean.setEnEPlanDisplayed(checkNull(String.valueOf(Obj34_35_36[0][1])));
			factorySafetyHealthBean.setRegularEvacuationDrills(checkNull(String.valueOf(Obj34_35_36[0][2])));
			factorySafetyHealthBean.setLastDateOfEvacuationDrill(checkNull(String.valueOf(Obj34_35_36[0][3])));
			factorySafetyHealthBean.setHasSafetyOfficers(checkNull(String.valueOf(Obj34_35_36[0][4])));
			factorySafetyHealthBean.setNoOfSO(checkNull(String.valueOf(Obj34_35_36[0][5])));
			factorySafetyHealthBean.setNoOfQualifiedSO(checkNull(String.valueOf(Obj34_35_36[0][6])));
			factorySafetyHealthBean.setHasSafetyCommittee(checkNull(String.valueOf(Obj34_35_36[0][7])));
			factorySafetyHealthBean.setSCWorkerMembers(checkNull(String.valueOf(Obj34_35_36[0][8])));
			factorySafetyHealthBean.setSCManagementMembers(checkNull(String.valueOf(Obj34_35_36[0][9])));
		}
		/**
		 * 38 39
		 * 
		 */
		String sel39_40="SELECT  DISTINCT "
						+"	(SELECT 'true' FROM HRMS_SAFETY_HEALTH WHERE NO_BUILDING_EXITS>=2)AS EXITDATA "	
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE NO_FIRE_EXTINGUISHERS IS NOT NULL)AS TOTAL_EXITDATA "
						+"	,(SELECT COUNT(*) FROM HRMS_SAFETY_HEALTH WHERE NO_FIRE_EXTINGUISHERS IS NOT NULL)AS TOTAL_EXITDATA1 "
						+"	,(SELECT COUNT(*) FROM HRMS_SAFETY_HEALTH WHERE NO_EXTING_WRKR_TRAINED IS NOT NULL)AS TOTAL_EXITDATA2 "
							
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE NO_FIRST_AID_BOX IS NOT NULL)AS TOTAL_EXITDATA3"
						+"	,(SELECT COUNT(*) FROM HRMS_SAFETY_HEALTH WHERE NO_FIRST_AID_BOX IS NOT NULL)AS TOTAL_EXITDATA4 "
						+"	,(SELECT NVL(CONTENT_CHECKING_DURATION,'0') FROM HRMS_SAFETY_HEALTH WHERE CONTENT_CHECKING_DURATION IS NOT NULL)AS TOTAL_EXITDATA5 "	 
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE NO_WRKR_FIRSTAID_CERTFD IS NOT NULL)AS TOTAL_EXITDATA6 "
						+"	,(SELECT NVL(NO_WRKR_FIRSTAID_CERTFD,'0') FROM HRMS_SAFETY_HEALTH WHERE NO_WRKR_FIRSTAID_CERTFD IS NOT NULL)AS TOTAL_EXITDATA7 "
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE IS_HIV_AIDS_POLICY_AVBL='Y')AS TOTAL_EXITDATA8 "
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE IS_UNIFORM_PROVISION='Y')AS TOTAL_EXITDATA9	"
						+"	,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE UNIFORM_PROVISION_TYPE='P')AS TOTAL_EXITDATA10	"	
						+"  ,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_SAFETY_HEALTH WHERE IS_SAFETY_POSTERS_DISPLAYED='Y')AS TOTAL_EXITDATA11	"
						+"  ,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Foot protection')  AS FOOT"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Foot protection' AND PROTECTION_TYPE='P')  AS FOOT_PAY"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Eye protection')    AS EYE"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Eye protection' AND PROTECTION_TYPE='P')	  AS EYE_PAY"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Ear protection') AS EAR "
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Ear protection' AND PROTECTION_TYPE='P')AS EAR_PAY"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES" 
						+"		WHERE PROTECTION_NAME='Hand protection')AS HAND "
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Hand protection' AND PROTECTION_TYPE='P')AS HAND_PAY"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES" 
						+"		WHERE PROTECTION_NAME='Body protection')  AS BODYPRO"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Body protection' AND PROTECTION_TYPE='P') AS BODY_PAY"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES" 
						+"		WHERE PROTECTION_NAME='Respiratory protection')  AS RESP"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES "
						+"		WHERE PROTECTION_NAME='Respiratory protection' AND PROTECTION_TYPE='P')	AS RESP_PAY"
						+"		,(SELECT NVL(PROTECTION_NAME,' ')  FROM HRMS_PROTECTION_MEASURES" 
						+"		WHERE PROTECTION_NAME='Other') 	AS OTHER"
						+"		,(SELECT DECODE(COUNT(*),0,'false','true') FROM HRMS_PROTECTION_MEASURES " 
						+"		WHERE PROTECTION_NAME='Other' AND PROTECTION_TYPE='P')AS OTHER_PAY "	
						
						+"	FROM  HRMS_RULES_POLICY";
		Object[][] Obj39_40=getSqlModel().getSingleResult(sel39_40);
		if(Obj39_40 !=null && Obj39_40.length>0){
			factorySafetyHealthBean.setHasTwoExitsOnEachFloor(checkNull(String.valueOf(Obj39_40[0][0])));
			factorySafetyHealthBean.setHasFireExtinguishers(checkNull(String.valueOf(Obj39_40[0][1])));
			factorySafetyHealthBean.setNoOfFE(checkNull(String.valueOf(Obj39_40[0][2])));
			factorySafetyHealthBean.setNoOfWorkersTrainedFE(checkNull(String.valueOf(Obj39_40[0][3])));
			factorySafetyHealthBean.setHasFirstAidBoxes(checkNull(String.valueOf(Obj39_40[0][4])));
			factorySafetyHealthBean.setNoOfFirstAidboxes(checkNull(String.valueOf(Obj39_40[0][5])));
			factorySafetyHealthBean.setFABCheckingFrequencyID(checkNull(String.valueOf(Obj39_40[0][6])));
			factorySafetyHealthBean.setHasFACertifiedWorkers(checkNull(String.valueOf(Obj39_40[0][7])));
			factorySafetyHealthBean.setNoOfFACertifiedWorkers(checkNull(String.valueOf(Obj39_40[0][8])));
			factorySafetyHealthBean.setHasHIVPolicy(checkNull(String.valueOf(Obj39_40[0][9])));
			factorySafetyHealthBean.setProvidesProtectiveClothNEquip(checkNull(String.valueOf(Obj39_40[0][10])));
			factorySafetyHealthBean.setPaidProtectiveClothingNEquip(checkNull(String.valueOf(Obj39_40[0][11])));
			//SET 46 NO
			factorySafetyHealthBean.setSafetyPostersDisplayed(String.valueOf(Obj39_40[0][12]));
			/**
			 * 41 TO 43
			 */
			factorySafetyHealthBean.setHasFootProtection(String.valueOf(Obj39_40[0][13]));
			factorySafetyHealthBean.setIsFootProtectionPaid(String.valueOf(Obj39_40[0][14]));
			factorySafetyHealthBean.setHasEyeProtection(String.valueOf(Obj39_40[0][15]));
			factorySafetyHealthBean.setIsEyeProtectionPaid(String.valueOf(Obj39_40[0][16]));
			factorySafetyHealthBean.setHasEarProtection(String.valueOf(Obj39_40[0][17]));
			factorySafetyHealthBean.setIsEarProtectionPaid(String.valueOf(Obj39_40[0][18]));
			factorySafetyHealthBean.setHasHandProtection(String.valueOf(Obj39_40[0][19]));
			
			factorySafetyHealthBean.setIsHandProtectionPaid(String.valueOf(Obj39_40[0][20]));;
			factorySafetyHealthBean.setHasBodyProtection(String.valueOf(Obj39_40[0][21]));
			factorySafetyHealthBean.setIsBodyProtectionPaid(String.valueOf(Obj39_40[0][22]));
			factorySafetyHealthBean.setHasRespiratoryProtection(String.valueOf(Obj39_40[0][23]));
			factorySafetyHealthBean.setIsRespiratoryProtectionPaid(String.valueOf(Obj39_40[0][24]));
			factorySafetyHealthBean.setOtherProtection(checkNull(String.valueOf(Obj39_40[0][25])));
			factorySafetyHealthBean.setIsOtherProtectionPaid(checkNull(String.valueOf(Obj39_40[0][26])));
						
		}
		
		return factorySafetyHealthBean;
	}
	
	public FactoryWelfareFacilities setWelfFareFacility(FactoryWelfareFacilities factorySafetyHealthBean11){
		FactoryWelfareFacilities factorySafetyHealthBean =new FactoryWelfareFacilities();
		
		String query="SELECT DISTINCT (SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_DRINKING_WATER_FACILITY='Y')AS COL47 "
					+"	,'true'AS COL_48 ,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_MEAL_FACILITY='Y') AS col_49 "
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_LOCKER_FACILITY='Y')	 AS COL_50"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE NO_CHANGING_ROOMS>0)	AS COL_51"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE NO_REST_ROOMS>0)	AS COL_52"
					+"	,'TRUE'	 AS COL_53 ,'TRUE'	AS COL_54"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE NO_EMERGENCY_ROOMS>0)	AS COL_55"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_DOCTOR_FULLTIME='Y')	AS COL_56"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_DOCTOR_PARTTIME='Y')	AS COL_57"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_NURSE_FULLTIME='Y')	AS COL_58"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE IS_NURSE_PARTTIME='Y')	AS COL_59"
					+"	 ,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE NO_TOILET_MALE>0 AND NO_TOILET_FEMALE>0)	AS COL_60"
					+"	,(SELECT NO_TOILET_MALE FROM HRMS_WELFARE_DTL WHERE NO_TOILET_MALE>0)	AS COL_61"
					+"	,(SELECT NO_URINAL_MALE FROM HRMS_WELFARE_DTL WHERE NO_URINAL_MALE>0)	AS COL_62"
					+"	,(SELECT NO_TOILET_FEMALE FROM HRMS_WELFARE_DTL WHERE NO_TOILET_FEMALE>0)	AS COL_63"
					+"	,(SELECT 'true' FROM HRMS_WELFARE_DTL WHERE (IS_OFFICER_FULLTIME='Y' OR IS_OFFICER_PARTTIME='Y'))	AS COL_64"
					+"	,(SELECT NO_FULL_TIME_OFFICERS+NO_PARTTIME_OFFICERS FROM HRMS_WELFARE_DTL WHERE IS_NURSE_PARTTIME='Y')	AS COL_65"
					+"	FROM HRMS_WELFARE_DTL";
		Object[][]data=getSqlModel().getSingleResult(query);
		if(data !=null && data.length>0){
			try {
				factorySafetyHealthBean.setProvideDrinkingWater("");
			} catch (Exception e) {
				// TODO: handle exception
			}
			factorySafetyHealthBean.setHasCreche(String.valueOf(data[0][1]));
			factorySafetyHealthBean.setHasCanteen(String.valueOf(data[0][2]));
			factorySafetyHealthBean.setProvidesLocker(String.valueOf(data[0][3]));
			factorySafetyHealthBean.setHasChangingRoom(String.valueOf(data[0][4]));
			factorySafetyHealthBean.setHasRestRoom(String.valueOf(data[0][5]));
			factorySafetyHealthBean.setHasClinic(String.valueOf(data[0][6]));
			factorySafetyHealthBean.setHasClinicForWorkersFamily(String.valueOf(data[0][7]));
			factorySafetyHealthBean.setHasAmbulanceRoom(String.valueOf(data[0][8]));
			factorySafetyHealthBean.setHasFullTimeDoctor(String.valueOf(data[0][9]));
			factorySafetyHealthBean.setHasPartTimeDoctor(String.valueOf(data[0][10]));
			factorySafetyHealthBean.setHasFullTimeNurse(String.valueOf(data[0][11]));
			factorySafetyHealthBean.setHasPartTimeNurse(String.valueOf(data[0][12]));
			factorySafetyHealthBean.setHasSeperateToiletForMnF(String.valueOf(data[0][13]));
			factorySafetyHealthBean.setNoOfLatrinesForMen(String.valueOf(data[0][14]));
			factorySafetyHealthBean.setNoOfUrinalsForMale(String.valueOf(data[0][15]));
			factorySafetyHealthBean.setNoOfLatrinesForWomen(String.valueOf(data[0][16]));
			factorySafetyHealthBean.setHasWelfareOfficer(String.valueOf(data[0][17]));
			factorySafetyHealthBean.setNoOfWelfareOfficers(String.valueOf(data[0][18]));
		}
		return factorySafetyHealthBean;
		
	}

	public ActsMaternityBenefits setMaternityBenefits(String orgId,
			String string, String string2) {
		ActsMaternityBenefits bean = new ActsMaternityBenefits(); 
		String query="SELECT  DISTINCT (SELECT DECODE(COUNT(*),0,'false','true')  FROM   HRMS_LEAVE_DTL WHERE LEAVE_CODE=( "
					+"		SELECT HRMS_MATERNITY_LEAVE_TYPE FROM HRMS_MATERNITY_LEAVE_POLICY))AS IS_MATERNITY ,( SELECT  COUNT(*)   FROM   HRMS_LEAVE_DTL WHERE LEAVE_CODE=( "
					+"		SELECT HRMS_MATERNITY_LEAVE_TYPE FROM HRMS_MATERNITY_LEAVE_POLICY))	AS COUNT_MAT ,( SELECT  DECODE(COUNT(*),0,'true','false')   FROM   HRMS_LEAVE_DTL WHERE LEAVE_CODE=( "
					+"		SELECT HRMS_MATERNITY_LEAVE_TYPE FROM HRMS_MATERNITY_LEAVE_POLICY WHERE LEAVE_DTL_STATUS IN('N','R')))	"
					+"		AS LEAVE_REJECT	,( SELECT  COUNT(*)   FROM   HRMS_LEAVE_DTL WHERE LEAVE_CODE=( "
					+"		SELECT HRMS_MATERNITY_LEAVE_TYPE FROM HRMS_MATERNITY_LEAVE_POLICY WHERE LEAVE_DTL_STATUS IN('N','R')))	"
					+"		AS LEAVE_REJECT_count FROM HRMS_LEAVE_DTL	";;
		Object[][]data=getSqlModel().getSingleResult(query);
		if(data !=null && data.length>0){
			bean.setMaternityLeavesApplied(String.valueOf(data[0][0]));
			bean.setMaternityLeavePeriodReason(String.valueOf(data[0][1]));
			bean.setAllApplicationsApproved(String.valueOf(data[0][2]));
			bean.setNoOfRejectedApplications(String.valueOf(data[0][3]));
			
		}
		return bean;
	}
	
	/*
	 * METHODS ADDED BY SHASHIKANT :END
	 */
	
	
	
	
	
	//===============METHODS ADDED BY PRASHANT BEGINS=========//
	
	/*public boolean saveFactoryReturns(
			FactoryAnnualReturn factoryAnnualReturnBean,
			FactoryInspections factoryInspectionsBean,
			FactorySafetyHealth factorySafetyHealthBean,
			FactoryWelfareFacilities factoryWelfareFacilitiesBean,
			FactoryWagesBenefits factoryWagesBenefitsBean,
			FactoryIndustrialRelations factoryIndustrialRelationsBean,
			FactoryOtherDetails factoryOtherDetailsBean, String factoryReturnId) {
		
		boolean result = false;
		try {
			//result = saveFactoryInspections(factoryInspectionsBean, factoryReturnId);
			//result = saveFactoryDangerousAndHazardousProcesses(factoryAnnualReturnBean, factoryReturnId);
			result = saveFactorySafetyAndHealth(factorySafetyHealthBean, factoryReturnId);
			//result = saveFactoryWelfareFacilities(factoryWelfareFacilitiesBean, factoryReturnId);
			//result = saveFactoryWagesAndBenefits(factoryWagesBenefitsBean, factoryReturnId);
			//result = saveFactoryIndustrialRelations(factoryIndustrialRelationsBean, factoryReturnId);
			//result = saveFactoryEmployeeInformation(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryLeaveWithWages(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryAccidentDetails(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryCompensation(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryClosureInformation(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryReopeningInformation(factoryAnnualReturnBean, factoryReturnId);
			//result = saveFactoryOtherDetails(factoryOtherDetailsBean, factoryReturnId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean saveFactoryInspections(FactoryInspections factoryInspectionsBean, String factoryReturnId) {
		boolean result = false;
		try {
			
			String inspectionQuery = " INSERT INTO TBL_LMS_RTN_INSPECTIONS(INSPECTIONID, RETURNID, LASTINSPECTIONDATE, LASTSPOTSAFETYAUDITDATE, LASTSAFETYAUDITDATE_INTERNAL, LASTSAFETYAUDITDATE_EXTERNAL, LASTEXAMDATE_COMPETENTPERSON, LASTEXAMDTLS_COMPETENTPRSN, ISFACTORYCERTIFIED, LASTRENEWALDATE, HASCODEOFCONDUCT, LASTDATEOFCOCINSPECTION, ISCERTIFIED) "
				+ " VALUES ((SELECT NVL(MAX(INSPECTIONID),0)+1 FROM TBL_LMS_RTN_INSPECTIONS),?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?)";
			Object inspectionsObj[][] = new Object[1][12];
			inspectionsObj[0][0] = factoryReturnId;
			inspectionsObj[0][1] = factoryInspectionsBean.getLastInspectionDate();
			inspectionsObj[0][2] = factoryInspectionsBean.getLastSpotSafetyAuditDate();
			inspectionsObj[0][3] = factoryInspectionsBean.getLastSafetyAuditDateInternal();
			inspectionsObj[0][4] = factoryInspectionsBean.getLastSafetyAuditDateExternal();
			inspectionsObj[0][5] = factoryInspectionsBean.getLastExamDateCompetentPerson();
			inspectionsObj[0][6] = factoryInspectionsBean.getLastExamDetailsCompetentPerson();
			//inspectionsObj[0][7] = factoryInspectionsBean.getExaminedMachineryEquipment(); missing in database
			
			if( factoryInspectionsBean.getIsFactoryCertified().equals("true")){
				inspectionsObj[0][7] = "Y";
			}else{
				inspectionsObj[0][7] = "N";
			}
			inspectionsObj[0][8] = factoryInspectionsBean.getLastRenewalDate();
			if(factoryInspectionsBean.getHasCodeOfConduct().equals("true")){
				inspectionsObj[0][9] = "Y";
			}else{
				inspectionsObj[0][9] = "N";
			}
			inspectionsObj[0][10] = factoryInspectionsBean.getLastDateOfCOCInspection();
			inspectionsObj[0][11] = "N";//Is establishment certified

			result = getLMSSqlModel().singleExecute(inspectionQuery, inspectionsObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryDangerousAndHazardousProcesses(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			String dangerousAndHazardousQuery = " INSERT INTO TBL_LMS_RTN_DONHP(DONHPID, RETURNID, DANGEROUSOPERATIONIDS, HAZARDOUSPROCESSIDS) "
					+ " VALUES ((SELECT NVL(MAX(DONHPID),0)+1 FROM TBL_LMS_RTN_DONHP),?,?,?,?)";
			Object dangerousAndHazardousObj[][] = new Object[1][3];
			
			dangerousAndHazardousObj[0][0] = factoryReturnId;
			dangerousAndHazardousObj[0][1] = factoryAnnualReturnBean.getOperation(); 
			dangerousAndHazardousObj[0][2] = factoryAnnualReturnBean.getProcess();
				
			result = getLMSSqlModel().singleExecute(dangerousAndHazardousQuery, dangerousAndHazardousObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactorySafetyAndHealth(
			FactorySafetyHealth factorySafetyHealthBean, String factoryReturnId) {
		boolean result = false;
		try {
			String safetyAndHealthQuery = " INSERT INTO TBL_LMS_RTN_SAFETYNHEALTH(SAFETYNHEALTHID, RETURNID, HASWRITTENPOLICY, POLICYCOMMMEDIAID, POLICYCOMMLANGUAGEID, HASWRITTENRULES, RULECOMMMEDIAID, RULECOMMLANGUAGEID, HASENEPLAN, ENEPLANDISPLAYED, REGULAREVACUATIONDRILLS, LASTDATEOFEVACUATIONDRILL, HASSAFETYOFFICERS, NOOFSO, NOOFQUALIFIEDSO, HASSAFETYCOMMITTEE, SCWORKERMEMBERS, SCMANAGEMENTMEMBERS, MEETINGFREQUENCYID, HASTWOEXITSONEACHFLOOR, HASFIREEXTINGUISHERS, NOOFFE, NOOFWORKERSTRAINEDFE, HASFIRSTAIDBOXES, NOOFFIRSTAIDBOXES, FABCHECKINGFREQUENCYID, HASFACERTIFIEDWORKERS, NOOFFACERTIFIEDWORKERS, HASHIVPOLICY, PROVIDESPROTECTIVECLOTHNEQUIP, CLOTHINGNEQUIPID, PAIDPROTECTIVECLOTHINGNEQUIP, PAIDCLOTHINGNEQUIPID, PROVIDEUNIFORMTOWORKERS, PAIDUNIFORM, HASNATURALVENTILATION, USEFANSFORAIRCIRCULATION, HASAIRCONDITIONING, HASACFORENTIREBUILDING, RELYSOLELYONNATURALLIGHTING, SAFETYPOSTERSDISPLAYED, NOOFACCIDENTSREPORTED, NOOFACCIDENTSREPORTED_F, NOOFACCIDENTSREPORTED_NF, CONTRACTORSEMPLOYOWNSO, CONTRPROVPROTCTVECLOTHNEQUIP, WARNINGSIGNSDISPLAYED, WARNINGSIGNSLANGUAGEID, OCCUPATIONALDISEASESREPORTED, NONFATALDISEASES, FATALDISEASES) "
				+ " VALUES ((SELECT NVL(MAX(SAFETYNHEALTHID),0)+1 FROM TBL_LMS_RTN_SAFETYNHEALTH),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object safetyAndHealthObj[][] = new Object[1][50];
			String mediaId = "";
			String languageId = "";
			String ruleCommunicationMediaId = "";
			String ruleCommunicationLanguageId = "";
			
			
			safetyAndHealthObj[0][0] = factoryReturnId; 	
			safetyAndHealthObj[0][1] = factorySafetyHealthBean.getHasWrittenPolicy();
			
			if(factorySafetyHealthBean.getPolicyCommunicationMediaIDNotice().equals("true")){
				mediaId = "1,";
			}if(factorySafetyHealthBean.getPolicyCommunicationMediaIDCircular().equals("true")){
				mediaId+= "2,";
			}if(factorySafetyHealthBean.getPolicyCommunicationMediaIDOther().equals("true")){
				mediaId+= "3";
			}
			
			safetyAndHealthObj[0][2] = mediaId;	
			
			if(factorySafetyHealthBean.getPolicyCommunicationLanguageIDMarathi().equals("true")){
				languageId = "1,";
			}if(factorySafetyHealthBean.getPolicyCommunicationLanguageIDHindi().equals("true")){
				languageId+= "2,";
			}if(factorySafetyHealthBean.getPolicyCommunicationLanguageIDEnglish().equals("true")){
				languageId+= "3";
			}
			
			safetyAndHealthObj[0][3] = languageId;
			
			if( factorySafetyHealthBean.getHasWrittenRules().equals("true")){
				safetyAndHealthObj[0][4] = "Y";
			}else{
				safetyAndHealthObj[0][4] = "N";
			}
			
			if(factorySafetyHealthBean.getRuleCommunicationMediaIDNotice().equals("true")){
				ruleCommunicationMediaId = "1,";
			}if(factorySafetyHealthBean.getRuleCommunicationMediaIDCircular().equals("true")){
				ruleCommunicationMediaId+= "2,";
			}if(factorySafetyHealthBean.getRuleCommunicationMediaIDOther().equals("true")){
				ruleCommunicationMediaId+= "3";
			}
			safetyAndHealthObj[0][5] = ruleCommunicationMediaId;
			
			if(factorySafetyHealthBean.getRuleCommunicationLanguageIDMarathi().equals("true")){
				ruleCommunicationLanguageId = "1,";
			}if(factorySafetyHealthBean.getRuleCommunicationLanguageIDHindi().equals("true")){
				ruleCommunicationLanguageId+= "2,";
			}if(factorySafetyHealthBean.getRuleCommunicationLanguageIDEnglish().equals("true")){
				ruleCommunicationLanguageId+= "3";
			}
			safetyAndHealthObj[0][6] = ruleCommunicationLanguageId;
			
			if( factorySafetyHealthBean.getHasEnEplan().equals("true")){
				safetyAndHealthObj[0][7] = "Y";
			}else{
				safetyAndHealthObj[0][7] = "N";
			}
			
			if( factorySafetyHealthBean.getEnEPlanDisplayed().equals("true")){
				safetyAndHealthObj[0][8] = "Y";
			}else{
				safetyAndHealthObj[0][8] = "N";
			}
			
			if( factorySafetyHealthBean.getRegularEvacuationDrills().equals("true")){
				safetyAndHealthObj[0][9] = "Y";
			}else{
				safetyAndHealthObj[0][9] = "N";
			}
			
			safetyAndHealthObj[0][10] = factorySafetyHealthBean.getLastDateOfEvacuationDrill();
			
			if( factorySafetyHealthBean.getHasSafetyOfficers().equals("true")){
				safetyAndHealthObj[0][11] = "Y";
			}else{
				safetyAndHealthObj[0][11] = "N";
			}
			
			safetyAndHealthObj[0][12] = factorySafetyHealthBean.getNoOfSO();
			safetyAndHealthObj[0][13] = factorySafetyHealthBean.getNoOfQualifiedSO();	
			
			if( factorySafetyHealthBean.getHasSafetyCommittee().equals("true")){
				safetyAndHealthObj[0][14] = "Y";
			}else{
				safetyAndHealthObj[0][14] = "N";
			}
			
			safetyAndHealthObj[0][15] = factorySafetyHealthBean.getSCWorkerMembers();	
			safetyAndHealthObj[0][16] = factorySafetyHealthBean.getSCManagementMembers();
			safetyAndHealthObj[0][17] = factorySafetyHealthBean.getMeetingFrequencyID();
			
			
			if( factorySafetyHealthBean.getHasTwoExitsOnEachFloor().equals("true")){
				safetyAndHealthObj[0][18] = "Y";
			}else{
				safetyAndHealthObj[0][18] = "N";
			}
			
			if( factorySafetyHealthBean.getHasFireExtinguishers().equals("true")){
				safetyAndHealthObj[0][19] = "Y";
			}else{
				safetyAndHealthObj[0][19] = "N";
			}
			
			safetyAndHealthObj[0][20] = factorySafetyHealthBean.getNoOfFE();	
			safetyAndHealthObj[0][21] = factorySafetyHealthBean.getNoOfWorkersTrainedFE();
			
			if( factorySafetyHealthBean.getHasFirstAidBoxes().equals("true")){
				safetyAndHealthObj[0][22] = "Y";
			}else{
				safetyAndHealthObj[0][22] = "N";
			}
			
			safetyAndHealthObj[0][23] = factorySafetyHealthBean.getNoOfFirstAidboxes();	
			safetyAndHealthObj[0][24] = factorySafetyHealthBean.getFABCheckingFrequencyID();
			
			if( factorySafetyHealthBean.getHasFACertifiedWorkers().equals("true")){
				safetyAndHealthObj[0][25] = "Y";
			}else{
				safetyAndHealthObj[0][25] = "N";
			}
			
			safetyAndHealthObj[0][26] = factorySafetyHealthBean.getNoOfFACertifiedWorkers();
			
			if( factorySafetyHealthBean.getHasHIVPolicy().equals("true")){
				safetyAndHealthObj[0][27] = "Y";
			}else{
				safetyAndHealthObj[0][27] = "N";
			}
				
			safetyAndHealthObj[0][28] = PROVIDESPROTECTIVECLOTHNEQUIP;	
			safetyAndHealthObj[0][29] = "";// CLOTHINGNEQUIPID;	
			safetyAndHealthObj[0][30] = PAIDPROTECTIVECLOTHINGNEQUIP;	
			safetyAndHealthObj[0][31] = PAIDCLOTHINGNEQUIPID;	
			safetyAndHealthObj[0][32] = PROVIDEUNIFORMTOWORKERS;	
			safetyAndHealthObj[0][33] = PAIDUNIFORM;	
			safetyAndHealthObj[0][34] = HASNATURALVENTILATION;	
			safetyAndHealthObj[0][35] = USEFANSFORAIRCIRCULATION;	
			safetyAndHealthObj[0][36] = HASAIRCONDITIONING;	
			safetyAndHealthObj[0][37] = HASACFORENTIREBUILDING;	
			safetyAndHealthObj[0][38] = RELYSOLELYONNATURALLIGHTING;	
			safetyAndHealthObj[0][39] = SAFETYPOSTERSDISPLAYED;	
			safetyAndHealthObj[0][40] = NOOFACCIDENTSREPORTED;	
			safetyAndHealthObj[0][41] = NOOFACCIDENTSREPORTED_F;	
			safetyAndHealthObj[0][42] = NOOFACCIDENTSREPORTED_NF;	
			safetyAndHealthObj[0][43] = CONTRACTORSEMPLOYOWNSO;	
			safetyAndHealthObj[0][44] = CONTRPROVPROTCTVECLOTHNEQUIP;	
			safetyAndHealthObj[0][45] = WARNINGSIGNSDISPLAYED;	
			safetyAndHealthObj[0][46] = WARNINGSIGNSLANGUAGEID;	
			safetyAndHealthObj[0][47] = OCCUPATIONALDISEASESREPORTED;	
			safetyAndHealthObj[0][48] = NONFATALDISEASES;	
			safetyAndHealthObj[0][49] = FATALDISEASES;	
			
			result = getLMSSqlModel().singleExecute(safetyAndHealthQuery, safetyAndHealthObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryWelfareFacilities(
			FactoryWelfareFacilities factoryWelfareFacilitiesBean,
			String factoryReturnId) {
		boolean result = false;
		try {
			String welfareFacilitiesQuery = " INSERT INTO TBL_LMS_RTN_WELFAREFACILITIES(WELFAREFACILITYID, RETURNID, PROVIDEDRINKINGWATER, HASCRECHE, HASCANTEEN, PROVIDEFREEMEALS, PROVIDESLOCKER, HASCHANGINGROOM, HASRESTROOM, HASCLINIC, HASCLINICFORWORKERSFAMILY, HASAMBULANCEROOM, HASFULLTIMEDOCTOR, HASPARTTIMEDOCTOR, HASFULLTIMENURSE, HASPARTTIMENURSE, PROVIDEMEDICALSERVICES, FREEMEDICALSERVICES, HASSEPERATETOILETFORMNF, NOOFLATRINESFORMEN, NOOFURINALSFORMALE, NOOFLATRINESFORWOMEN, HASWELFAREOFFICER, NOOFWELFAREOFFICERS, FULLTIMEWO, PROVIDEONSITEACCOMMODATION, PROVIDEOFFSITEACCOMMODATION, FREEACCOMMODATION, HASHEALTHCENTRE) "
				+ " VALUES ((SELECT NVL(MAX(WELFAREFACILITYID),0)+1 FROM TBL_LMS_RTN_WELFAREFACILITIES),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object welfareFacilitiesObj[][] = new Object[1][28];
			
			welfareFacilitiesObj[0][0] = ; 	
			welfareFacilitiesObj[0][1] = ;	
			welfareFacilitiesObj[0][2] = ;	
			welfareFacilitiesObj[0][3] = ;	
			welfareFacilitiesObj[0][4] = ;	
			welfareFacilitiesObj[0][5] = ;	
			welfareFacilitiesObj[0][6] = ;	
			welfareFacilitiesObj[0][7] = ;	
			welfareFacilitiesObj[0][8] = ;	
			welfareFacilitiesObj[0][9] = ;	
			welfareFacilitiesObj[0][10] = ;	
			welfareFacilitiesObj[0][11] = ;	
			welfareFacilitiesObj[0][12] = ;	
			welfareFacilitiesObj[0][13] = ;	
			welfareFacilitiesObj[0][14] = ;	
			welfareFacilitiesObj[0][15] = ;	
			welfareFacilitiesObj[0][16] = ;	
			welfareFacilitiesObj[0][17] = ;	
			welfareFacilitiesObj[0][18] = ;	
			welfareFacilitiesObj[0][19] = ;	
			welfareFacilitiesObj[0][20] = ;	
			welfareFacilitiesObj[0][21] = ;	
			welfareFacilitiesObj[0][22] = ;	
			welfareFacilitiesObj[0][23] = ;	
			welfareFacilitiesObj[0][24] = ;	
			welfareFacilitiesObj[0][25] = ;	
			welfareFacilitiesObj[0][26] = ;	
			welfareFacilitiesObj[0][27] = ;	
			
			result = getLMSSqlModel().singleExecute(welfareFacilitiesQuery, welfareFacilitiesObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryWagesAndBenefits(
			FactoryWagesBenefits factoryWagesBenefitsBean,
			String factoryReturnId) {
		boolean result = false;
		try {
			String wagesBenefitsQuery = " INSERT INTO TBL_LMS_RTN_WAGESNBENEFITS(WAGESNBENEFITSID, RETURNID, LOWESTWAGEPAIDPERMONTH, NOOFPERMANENTWORKERS, WORKERSRECEIVEWAGESLIPS, WORKERRECEIVEPAYSLIPS, OVERTIMEREQUIRED, OVERTIMERATE, MAXOVERTIMEHRS, WORKINGHRSPERDAY, WORKINGDAYSPERWEEK, WORKINGWEEKSPERYR, MAXSPREADOVER, BONUSPAIDINLASTFY, PERCENTAGEBONUSRATE, NOOFWORKERSPAIDBONUS, BONUSAMOUNTPAID, MATERNITYLEAVESGRANTED, NOOFWORKERS_MLG, MEDICALBONUSAMTPERPERSON, AMOUNTOFMATERNITYBENEFITPAID, GRATUITYPAID, NOOFGRATUITYAPPLICATIONS, NOOFGRATUITYAPPLNS_APPROVED, TOTALGRATUITYAMOUNTPAID, GRATUITYPAIDATMAX, MNFDOSAMEJOB, MNFDOSAMEJOB_SAMEPAY, MNFDOSAMEJOB_SAMEALLOWANCE)"
				+ " VALUES ((SELECT NVL(MAX(WAGESNBENEFITSID),0)+1 FROM TBL_LMS_RTN_WAGESNBENEFITS),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object wagesBenefitsObj[][] = new Object[1][28];
			
			wagesBenefitsObj[0][0] = ; 	
			wagesBenefitsObj[0][1] = ;	
			wagesBenefitsObj[0][2] = ;	
			wagesBenefitsObj[0][3] = ;	
			wagesBenefitsObj[0][4] = ;	
			wagesBenefitsObj[0][5] = ;	
			wagesBenefitsObj[0][6] = ;	
			wagesBenefitsObj[0][7] = ;	
			wagesBenefitsObj[0][8] = ;	
			wagesBenefitsObj[0][9] = ;	
			wagesBenefitsObj[0][10] = ;	
			wagesBenefitsObj[0][11] = ;	
			wagesBenefitsObj[0][12] = ;	
			wagesBenefitsObj[0][13] = ;	
			wagesBenefitsObj[0][14] = ;	
			wagesBenefitsObj[0][15] = ;	
			wagesBenefitsObj[0][16] = ;	
			wagesBenefitsObj[0][17] = ;	
			wagesBenefitsObj[0][18] = ;	
			wagesBenefitsObj[0][19] = ;	
			wagesBenefitsObj[0][20] = ;	
			wagesBenefitsObj[0][21] = ;	
			wagesBenefitsObj[0][22] = ;	
			wagesBenefitsObj[0][23] = ;	
			wagesBenefitsObj[0][24] = ;	
			wagesBenefitsObj[0][25] = ;	
			wagesBenefitsObj[0][26] = ;	
			wagesBenefitsObj[0][27] = ;	
			
			result = getLMSSqlModel().singleExecute(wagesBenefitsQuery, wagesBenefitsObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryIndustrialRelations(
			FactoryIndustrialRelations factoryIndustrialRelationsBean,
			String factoryReturnId) {
		boolean result = false;
		try {
			String industrilaRelationsQuery = " INSERT INTO TBL_LMS_RTN_SH(SHID, RETURNID, HASWRITTENSHPOLICY, HASSHRULES, HASSHCOMMITTEE, SHCOMPLAINTSLODGED)"
				+ " VALUES ((SELECT NVL(MAX(SHID),0)+1 FROM TBL_LMS_RTN_SH),?,?,?,?,?)";
			Object industrilaRelationsObj[][] = new Object[1][5];
			industrilaRelationsObj[0][0] = ; 	
			industrilaRelationsObj[0][1] = ;	
			industrilaRelationsObj[0][2] = ;	
			industrilaRelationsObj[0][3] = ;	
			industrilaRelationsObj[0][4] = ;	
			
			result = getLMSSqlModel().singleExecute(industrilaRelationsQuery, industrilaRelationsObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryEmployeeInformation(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			
			String employeeInformationQuery = " INSERT INTO TBL_LMS_RTN_EMPLOYMENTINFO(EMPLOYMENTINFOID, RETURNID, WORKERS_ADULTS_MALE, WORKERS_ADULTS_FEMALE, MANDAYS_ADULTS_MALE, MANDAYS_ADULTS_FEMALE, OT_MANDAYS_ADULTS_MALE, OT_MANDAYS_ADULTS_FEMALE, MANHRSPERMNTH_ADULTS_MALE, MANHRSPERMNTH_ADULTS_FEMALE, WORKERS_ADOLESCENTS_MALE, WORKERS_ADOLESCENTS_FEMALE, MANDAYS_ADOLESCENTS_MALE, MANDAYS_ADOLESCENTS_FEMALE, OT_MANDAYS_ADLSCNTS_MALE, OT_MANDAYS_ADLSCNTS_FEMALE, MANHRSPERMNTH_ADLSCNTS_MALE, MANHRSPERMNTH_ADLSCNTS_FEMALE, WORKERS_CHILDREN_MALE, WORKERS_CHILDREN_FEMALE, MANDAYS_CHILDREN_MALE, MANDAYS_CHILDREN_FEMALE, OT_MANDAYS_CHILDREN_MALE, OT_MANDAYS_CHILDREN_FEMALE, MANHRSPERMNTH_CHILDREN_MALE, MANHRSPERMNTH_CHILDREN_FEMALE)"
				+ " VALUES ((SELECT NVL(MAX(SHID),0)+1 FROM TBL_LMS_RTN_EMPLOYMENTINFO),?,?,?,?,?)";
			Object employeeInformationObj[][] = new Object[1][25];
			
			employeeInformationObj[0][0] = ; 	
			employeeInformationObj[0][1] = ;	
			employeeInformationObj[0][2] = ;	
			employeeInformationObj[0][3] = ;	
			employeeInformationObj[0][4] = ;	
			employeeInformationObj[0][5] = ;	
			employeeInformationObj[0][6] = ;	
			employeeInformationObj[0][7] = ;	
			employeeInformationObj[0][8] = ;	
			employeeInformationObj[0][9] = ;	
			employeeInformationObj[0][10] = ;	
			employeeInformationObj[0][11] = ;	
			employeeInformationObj[0][12] = ;	
			employeeInformationObj[0][13] = ;	
			employeeInformationObj[0][14] = ;	
			employeeInformationObj[0][15] = ;	
			employeeInformationObj[0][16] = ;	
			employeeInformationObj[0][17] = ;	
			employeeInformationObj[0][18] = ;	
			employeeInformationObj[0][19] = ;	
			employeeInformationObj[0][20] = ;	
			employeeInformationObj[0][21] = ;	
			employeeInformationObj[0][22] = ;	
			employeeInformationObj[0][23] = ;	
			employeeInformationObj[0][24] = ;	
			
			result = getLMSSqlModel().singleExecute(employeeInformationQuery, employeeInformationObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryLeaveWithWages(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			String leaveWithWagesQuery = " INSERT INTO TBL_LMS_RTN_LEAVE_WAGEREC(LEAVEWITHWAGESRECORDID, RETURNID, WORKERS_ADULTS_MALE, WORKERS_ADULTS_FEMALE, ENTITLDTOANNLEAVE_ADLTS_MALE, ENTITLDTOANNLEAVE_ADLTS_FEMALE, LEAVEGRANTED_ADULTS_MALE, LEAVEGRANTED_ADULTS_FEMALE, DISCHARGED_ADULTS_MALE, DISCHARGED_ADULTS_FEMALE, DISMISSED_ADULTS_MALE, DISMISSED_ADULTS_FEMALE, LEAVEENCASHED_ADULTS_MALE, LEAVEENCASHED_ADULTS_FEMALE, WORKERS_ADOLESCENTS_MALE, WORKERS_ADOLESCENTS_FEMALE, ENTITLDTOANNLV_ADLSCNTS_MALE, ENTITLDTOANNLV_ADLSCNTS_FEMALE, LEAVEGRANTED_ADLSENTS_MALE, LEAVEGRANTED_ADLSCNTS_FEMALE, DISCHARGED_ADOLESCENTS_MALE, DISCHARGED_ADOLESCENTS_FEMALE, DISMISSED_ADOLESCENTS_MALE, DISMISSED_ADOLESCENTS_FEMALE, LEAVEENCSHED_ADLSCNTS_MALE, LEAVEENCSHED_ADLSCNTS_FEMALE, WORKERS_CHILDREN_MALE, WORKERS_CHILDREN_FEMALE, ENTITLEDTOANNLVE_CHILD_MALE, ENTITLEDTOANNLVE_CHILD_FEMALE, LEAVEGRANTED_CHILDREN_MALE, LEAVEGRANTED_CHILDREN_FEMALE, DISCHARGED_CHILDREN_MALE, DISCHARGED_CHILDREN_FEMALE, DISMISSED_CHILDREN_MALE, DISMISSED_CHILDREN_FEMALE, LEAVEENCASHED_CHILDREN_MALE, LEAVEENCASHED_CHILDREN_FEMALE)"
				+ " VALUES ((SELECT NVL(MAX(LEAVEWITHWAGESRECORDID),0)+1 FROM TBL_LMS_RTN_LEAVE_WAGEREC),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object leaveWithWagesObj[][] = new Object[1][37];
			
			leaveWithWagesObj[0][0] = ; 	
			leaveWithWagesObj[0][1] = ;	
			leaveWithWagesObj[0][2] = ;	
			leaveWithWagesObj[0][3] = ;	
			leaveWithWagesObj[0][4] = ;	
			leaveWithWagesObj[0][5] = ;	
			leaveWithWagesObj[0][6] = ;	
			leaveWithWagesObj[0][7] = ;	
			leaveWithWagesObj[0][8] = ;	
			leaveWithWagesObj[0][9] = ;	
			leaveWithWagesObj[0][10] = ;	
			leaveWithWagesObj[0][11] = ;	
			leaveWithWagesObj[0][12] = ;	
			leaveWithWagesObj[0][13] = ;	
			leaveWithWagesObj[0][14] = ;	
			leaveWithWagesObj[0][15] = ;	
			leaveWithWagesObj[0][16] = ;	
			leaveWithWagesObj[0][17] = ;	
			leaveWithWagesObj[0][18] = ;	
			leaveWithWagesObj[0][19] = ;	
			leaveWithWagesObj[0][20] = ;	
			leaveWithWagesObj[0][21] = ;	
			leaveWithWagesObj[0][22] = ;	
			leaveWithWagesObj[0][23] = ;	
			leaveWithWagesObj[0][24] = ;	
			leaveWithWagesObj[0][25] = ;	
			leaveWithWagesObj[0][26] = ;	
			leaveWithWagesObj[0][27] = ;	
			leaveWithWagesObj[0][28] = ;	
			leaveWithWagesObj[0][29] = ;	
			leaveWithWagesObj[0][30] = ;	
			leaveWithWagesObj[0][31] = ;	
			leaveWithWagesObj[0][32] = ;	
			leaveWithWagesObj[0][33] = ;	
			leaveWithWagesObj[0][34] = ;	
			leaveWithWagesObj[0][35] = ;	
			leaveWithWagesObj[0][36] = ;
			
			result = getLMSSqlModel().singleExecute(leaveWithWagesQuery, leaveWithWagesObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryAccidentDetails(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			String accidentDetailsQuery = " INSERT INTO TBL_LMS_RTN_ACCIDENTDETAILS(ACCIDENTDETAILID, RETURNID, NUMBER_FA, NUMBER_NFA, NUMBER_DO, WORKERSINJURED_FA, WORKERSINJURED_NFA, WORKERSINJURED_DO, INJWORKERS_RETTOWORK_FA, INJWORKERS_RETTOWORK_NFA, INJWORKERS_RETTOWORK_DO, WRKRS_INJPREVYR_JOINTHISYR_FA, WRKRS_INJPREVYR_JOINTHISYR_NFA, WRKRS_INJPREVYR_JOINTHISYR_DO, MANDAYSLOST_FA, MANDAYSLOST_NFA, MANDAYSLOST_DO, WRKRS_INJTHISYR_NOTJOIN_FA, WRKRS_INJTHISYR_NOTJOIN_NFA, WRKRS_INJTHISYR_NOTJOIN_DO)"
				+ " VALUES ((SELECT NVL(MAX(ACCIDENTDETAILID),0)+1 FROM TBL_LMS_RTN_ACCIDENTDETAILS),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object accidentDetailsObj[][] = new Object[1][19];
			
			accidentDetailsObj[0][0] = ; 	
			accidentDetailsObj[0][1] = ;	
			accidentDetailsObj[0][2] = ;	
			accidentDetailsObj[0][3] = ;	
			accidentDetailsObj[0][4] = ;	
			accidentDetailsObj[0][5] = ;	
			accidentDetailsObj[0][6] = ;	
			accidentDetailsObj[0][7] = ;	
			accidentDetailsObj[0][8] = ;	
			accidentDetailsObj[0][9] = ;	
			accidentDetailsObj[0][10] = ;	
			accidentDetailsObj[0][11] = ;	
			accidentDetailsObj[0][12] = ;	
			accidentDetailsObj[0][13] = ;	
			accidentDetailsObj[0][14] = ;	
			accidentDetailsObj[0][15] = ;	
			accidentDetailsObj[0][16] = ;	
			accidentDetailsObj[0][17] = ;	
			accidentDetailsObj[0][18] = ;
			
			result = getLMSSqlModel().singleExecute(accidentDetailsQuery, accidentDetailsObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryCompensation(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			String compensationQuery = " INSERT INTO TBL_LMS_RTN_COMPENSATION(ACCIDENTDETAILID, RETURNID, NUMBER_FA, NUMBER_NFA, NUMBER_DO, WORKERSINJURED_FA, WORKERSINJURED_NFA, WORKERSINJURED_DO, INJWORKERS_RETTOWORK_FA, INJWORKERS_RETTOWORK_NFA, INJWORKERS_RETTOWORK_DO, WRKRS_INJPREVYR_JOINTHISYR_FA, WRKRS_INJPREVYR_JOINTHISYR_NFA, WRKRS_INJPREVYR_JOINTHISYR_DO, MANDAYSLOST_FA, MANDAYSLOST_NFA, MANDAYSLOST_DO, WRKRS_INJTHISYR_NOTJOIN_FA, WRKRS_INJTHISYR_NOTJOIN_NFA, WRKRS_INJTHISYR_NOTJOIN_DO)"
				+ " VALUES ((SELECT NVL(MAX(COMPENSATIONID),0)+1 FROM TBL_LMS_RTN_COMPENSATION),?,?,?,?,?,?,?)";
			Object compensationObj[][] = new Object[1][7];
			
			compensationObj[0][0] = ; 	
			compensationObj[0][1] = ;	
			compensationObj[0][2] = ;	
			compensationObj[0][3] = ;	
			compensationObj[0][4] = ;	
			compensationObj[0][5] = ;	
			compensationObj[0][6] = ;	
			
			result = getLMSSqlModel().singleExecute(compensationQuery, compensationObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryClosureInformation(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			String closureInformationQuery = " INSERT INTO TBL_LMS_RTN_CLOSURE(CLOSUREID, ANNUALRETURNID, CLOSUREDATE, REASON, PARTIALCLOSURE, PARTIALCLOSUREDETAILS, MUSTERCOUNT, WORKERSAFFECTED)"
				+ " VALUES ((SELECT NVL(MAX(CLOSUREID),0)+1 FROM TBL_LMS_RTN_CLOSURE),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			Object closureInformationObj[][] = new Object[1][7];
			
			closureInformationObj[0][0] = ; 	
			closureInformationObj[0][1] = ;	
			closureInformationObj[0][2] = ;	
			closureInformationObj[0][3] = ;	
			closureInformationObj[0][4] = ;	
			closureInformationObj[0][5] = ;	
			closureInformationObj[0][6] = ;	
			
			result = getLMSSqlModel().singleExecute(closureInformationQuery, closureInformationObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFactoryReopeningInformation(
			FactoryAnnualReturn factoryAnnualReturnBean, String factoryReturnId) {
		boolean result = false;
		try {
			
			String reopeningQuery = " INSERT INTO TBL_LMS_RTN_REOPEN(REOPENID, RETURNID, CLOSUREDATE, WORKERSAFFECTEDBYCLOSURE, PARTIALREOPEN, PARTIALREOPENINGDETAILS, WORKERCOUNTATREOPENING, WORKERSREEMPLOYED, WORKERSNEWLYEMPLOYED)"
				+ " VALUES ((SELECT NVL(MAX(REOPENID),0)+1 FROM TBL_LMS_RTN_REOPEN),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?)";
			Object reopeningObj[][] = new Object[1][8];
			
			reopeningObj[0][0] = ; 	
			reopeningObj[0][1] = ;	
			reopeningObj[0][2] = ;	
			reopeningObj[0][3] = ;	
			reopeningObj[0][4] = ;	
			reopeningObj[0][5] = ;	
			reopeningObj[0][6] = ;	
			reopeningObj[0][7] = ;	
			
			result = getLMSSqlModel().singleExecute(reopeningQuery, reopeningObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean saveFactoryOtherDetails(
			FactoryOtherDetails factoryOtherDetailsBean, String factoryReturnId) {
		boolean result = false;
		try {
			String otherDetailsQuery = " INSERT INTO TBL_LMS_RTN_OTHER(OTHERID, RETURNID, IDENTITYCARDSPROVIDED, ENGAGEDINVA, VADETAILS, CSRABENEFIETARYDETAILS, DISABLEDEMPLOYED, DISABILITYDETAILS, NOOFDISABLEDMEN, NOOFDISABLEDWOMEN, SPECIALASSISTANCEDETAILS, ISMARGMEMBER, ENGAGEDINCSRA, CSRADETAILS)"
				+ " VALUES ((SELECT NVL(MAX(OTHERID),0)+1 FROM TBL_LMS_RTN_OTHER),?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object otherDetailsObj[][] = new Object[1][13];
			
			otherDetailsObj[0][0] = ; 	
			otherDetailsObj[0][1] = ;	
			otherDetailsObj[0][2] = ;	
			otherDetailsObj[0][3] = ;	
			otherDetailsObj[0][4] = ;	
			otherDetailsObj[0][5] = ;	
			otherDetailsObj[0][6] = ;	
			otherDetailsObj[0][7] = ;	
			otherDetailsObj[0][8] = ;	
			otherDetailsObj[0][9] = ;	
			otherDetailsObj[0][10] = ;	
			otherDetailsObj[0][11] = ;	
			otherDetailsObj[0][12] = ;	
			
			result = getLMSSqlModel().singleExecute(otherDetailsQuery, otherDetailsObj);
			if(result){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	
	
}
