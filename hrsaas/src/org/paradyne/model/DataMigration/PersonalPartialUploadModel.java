
package org.paradyne.model.DataMigration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.PersonalPartialUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

/**
 * @author Prakash Shetkar
 * Date 29 April 2010
 *
 */
public class PersonalPartialUploadModel extends ModelBase  {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PersonalPartialUploadModel.class);
	private final int EMPLOYEE_COUNT = 100;
	
	public boolean downloadTemplate(PersonalPartialUpload bean,HttpServletRequest request,HttpServletResponse response,
			String client,String dataPath,String applicationName,String rangeValue){
			
		try{
			Object [][] empObj = getEmployee(bean);
			
			if(empObj != null && empObj.length > 0){
				
				int count = 2;
				
				if(bean.getNationalityChk().equals("true"))
					count++;
				if(bean.getBloodGroupChk().equals("true"))
					count++;
				if(bean.getReligionChk().equals("true"))
					count++;
				if(bean.getCasteCatChk().equals("true"))
					count++;
				if(bean.getCasteChk().equals("true"))
					count++;
				if(bean.getSubCasteChk().equals("true"))
					count++;
				if(bean.getHeightChk().equals("true"))
					count++;
				if(bean.getWeightChk().equals("true"))
					count++;
				if(bean.getIsHandicapChk().equals("true"))
					count++;
				if(bean.getHandicapDescChk().equals("true"))
					count++;
				if(bean.getMaritalStatusChk().equals("true"))
					count++;
				if(bean.getMarriageDateChk().equals("true"))
					count++;
				if(bean.getHobbiesChk().equals("true"))
					count++;
				if(bean.getIdMarkChk().equals("true"))
					count++;
				if(bean.getPassportNoChk().equals("true"))
					count++;
				if(bean.getPassExpDateChk().equals("true"))
					count++;
				if(bean.getHomeTownChk().equals("true"))
					count++;
				if(bean.getEpfApplChk().equals("true"))
					count++;
				if(bean.getVpfApplChk().equals("true"))
					count++;
				if(bean.getGpfApplChk().equals("true"))
					count++;
				if(bean.getPfTrustApplChk().equals("true"))
					count++;
				if(bean.getPfNoChk().equals("true"))
					count++;
				if(bean.getPanNoChk().equals("true"))
					count++;
				if(bean.getEsicNoChk().equals("true"))
					count++;
				if(bean.getGratuityNoChk().equals("true"))
					count++;
				if(bean.getSalACNoChk().equals("true"))
					count++;
				if(bean.getSalBankChk().equals("true"))
					count++;
				if(bean.getReimbAcNoChk().equals("true"))
					count++;
				if(bean.getReimbBankChk().equals("true"))
					count++;
				if(bean.getPensionACNoChk().equals("true"))
					count++;
				if(bean.getPensionBankChk().equals("true"))
					count++;
				if(bean.getPensionableChk().equals("true"))
					count++;
				if(bean.getPayModeChk().equals("true"))
					count++;
				if(bean.getAccountCatgChk().equals("true"))
					count++;
				if(bean.getCostCenterChk().equals("true"))
					count++;
				if(bean.getSubCostCenterChk().equals("true"))
					count++;
				if(bean.getCustReferenceNo().equals("true"))
					count++;
				
				int recordNo = 0;
				int startRecord = 0, recordsInCurrentPage = 0;
				int totalRecords = empObj.length;

				if(!rangeValue.equals("")) {
					startRecord = Integer.parseInt(rangeValue) * EMPLOYEE_COUNT;
					recordsInCurrentPage = startRecord + EMPLOYEE_COUNT;
				}
				
				if (recordsInCurrentPage >= totalRecords) {
					recordsInCurrentPage = totalRecords;
				}
				recordNo = startRecord;
				int rowsCount = 0;
				int cnt = 0;
				rowsCount = (recordsInCurrentPage - recordNo);
				Object[][] downloadData = new Object[rowsCount][count];
				for (; recordNo < recordsInCurrentPage; recordNo++) {
					
					downloadData[cnt][0] = String.valueOf(empObj[recordNo][0]); // EMP_TOKEN
					downloadData[cnt][1] = String.valueOf(empObj[recordNo][1]); // EMP_NAME
					cnt++;
				}
				applicationName += "_" + (startRecord + 1) + " to " + recordsInCurrentPage;
				
				String [] headerNames = new String [count];
				headerNames[0] = "empId";
				headerNames[1] = "empName";
				int headerCount = 2;
				if(bean.getNationalityChk().equals("true")){
					headerNames[headerCount] = "nationality";
					headerCount++;
				}
				if(bean.getBloodGroupChk().equals("true")){
					headerNames[headerCount] = "bloodGroup";
					headerCount++;
				}
				if(bean.getReligionChk().equals("true")){
					headerNames[headerCount] = "religion";
					headerCount++;
				}
				if(bean.getCasteCatChk().equals("true")){
					headerNames[headerCount] = "casteCatg";
					headerCount++;
				}
				if(bean.getCasteChk().equals("true")){
					headerNames[headerCount] = "caste";
					headerCount++;
				}
				if(bean.getSubCasteChk().equals("true")){
					headerNames[headerCount] = "subCaste";
					headerCount++;
				}
				if(bean.getHeightChk().equals("true")){
					headerNames[headerCount] = "height";
					headerCount++;
				}
				if(bean.getWeightChk().equals("true")){
					headerNames[headerCount] = "weight";
					headerCount++;
				}
				if(bean.getIsHandicapChk().equals("true")){
					headerNames[headerCount] = "isHandicap";
					headerCount++;
				}
				if(bean.getHandicapDescChk().equals("true")){
					headerNames[headerCount] = "handicapDesc";
					headerCount++;
				}
				if(bean.getMaritalStatusChk().equals("true")){
					headerNames[headerCount] = "maritalStatus";
					headerCount++;
				}
				if(bean.getMarriageDateChk().equals("true")){
					headerNames[headerCount] = "marriageDate";
					headerCount++;
				}
				if(bean.getHobbiesChk().equals("true")){
					headerNames[headerCount] = "hobbies";
					headerCount++;
				}
				if(bean.getIdMarkChk().equals("true")){
					headerNames[headerCount] = "idMark";
					headerCount++;
				}
				if(bean.getPassportNoChk().equals("true")){
					headerNames[headerCount] = "passportNo";
					headerCount++;
				}
				if(bean.getPassExpDateChk().equals("true")){
					headerNames[headerCount] = "passportExp";
					headerCount++;
				}
				if(bean.getHomeTownChk().equals("true")){
					headerNames[headerCount] = "homeTown";
					headerCount++;
				}
				if(bean.getEpfApplChk().equals("true")){
					headerNames[headerCount] = "EPFApplicability";
					headerCount++;
				}
				if(bean.getVpfApplChk().equals("true")){
					headerNames[headerCount] = "VPFApplicability";
					headerCount++;
				}
				if(bean.getGpfApplChk().equals("true")){
					headerNames[headerCount] = "GPFApplicability";
					headerCount++;
				}
				if(bean.getPfTrustApplChk().equals("true")){
					headerNames[headerCount] = "PFTrustApplicability";
					headerCount++;
				}
				if(bean.getPfNoChk().equals("true")){
					headerNames[headerCount] = "PfNumber";
					headerCount++;
				}
				if(bean.getPanNoChk().equals("true")){
					headerNames[headerCount] = "PanNumber";
					headerCount++;
				}
				if(bean.getEsicNoChk().equals("true")){
					headerNames[headerCount] = "EsiNumber";
					headerCount++;
				}
				if(bean.getGratuityNoChk().equals("true")){
					headerNames[headerCount] = "gratuityNumber";
					headerCount++;
				}
				if(bean.getSalACNoChk().equals("true")){
					headerNames[headerCount] = "salACNumber";
					headerCount++;
				}
				if(bean.getSalBankChk().equals("true")){
					headerNames[headerCount] = "salBankNumber";
					headerCount++;
				}
				if(bean.getReimbAcNoChk().equals("true")){
					headerNames[headerCount] = "ReimbACNumber";
					headerCount++;
				}
				if(bean.getReimbBankChk().equals("true")){
					headerNames[headerCount] = "ReimbBankNumber";
					headerCount++;
				}
				if(bean.getPensionACNoChk().equals("true")){
					headerNames[headerCount] = "pensionACNumber";
					headerCount++;
				}
				if(bean.getPensionBankChk().equals("true")){
					headerNames[headerCount] = "pensionBankNumber";
					headerCount++;
				}
				if(bean.getPensionableChk().equals("true")){
					headerNames[headerCount] = "pensionable";
					headerCount++;
				}
				if(bean.getPayModeChk().equals("true")){
					headerNames[headerCount] = "paymode";
					headerCount++;
				}
				if(bean.getAccountCatgChk().equals("true")){
					headerNames[headerCount] = "accountCategory";
					headerCount++;
				}
				if(bean.getCostCenterChk().equals("true")){
					headerNames[headerCount] = "costCenter";
					headerCount++;
				}
				if(bean.getSubCostCenterChk().equals("true")){
					headerNames[headerCount] = "subCostCenter";
					headerCount++;
				}
				
				if(bean.getCustReferenceNo().equals("true")){
					headerNames[headerCount] = "custReferenceNo";
					headerCount++;
				}
				
				logger.info("count ----------"+count);
				//applicationName += "_" + (startRecord + 1) + " to " + recordsInCurrentPage;
				logger.info("applicationName ----------"+applicationName);
				MigrateExcelData.downloadTemplateWithData(request, response, dataPath, headerNames, downloadData, applicationName, client);
			}
		}catch(Exception e){
			logger.error("Error in PersonalPartialUploadModel -- downloadTemplate "+e);
			//e.printStackTrace();
		}
		return true;
	}
	public void uploaddPersonalTemplate(String path,PersonalPartialUpload bean) {
		try {
			MigrateExcelData.getFile(path);
			Object[][] columnNames = MigrateExcelData.getColumnInfo();
			bean.setFileUploaded(true);
			if(columnNames != null && columnNames.length > 0){
				
				Object[][] nationalityObj = null;
				Object[][] bloodGrpObj = null;
				Object[][] religionObj = null;
				Object[][] categoryObj = null;
				Object[][] casteObj = null;
				Object[][] subCasteObj= null;
				Object[][] heightObj= null;
				Object[][] weightObj= null;
				Object[][] handicapObj=null;
				Object[][]yesNoObj = new Object[2][2];
					yesNoObj[0][0]="Y";
					yesNoObj[0][1]="Yes";
					yesNoObj[1][0]="N";
					yesNoObj[1][1]="No";
				Object[][] handicapDescObj=null;
				Object[][] maritalStatusObj = null;
				Object[][] marriageDtObj=null;
				Object[][] hobbiesObj=null;
				Object[][] idMarkObj=null;
				Object[][] passportObj=null;
				Object[][] passExpDtObj=null;
				Object [][] homeTownObj = null;
				Object[][] EPFapplicableObj=null;
				Object[][] VPFapplicableObj=null;
				Object[][] GPFapplicableObj=null;
				Object[][] PFTrustapplicable=null;
				Object[][] pfNumberObj=null;
				Object[][] panNumberObj=null;
				Object[][] esicNumberObj=null;
				Object[][] gratuityNumberObj=null;
				Object[][] salaryAcObj=null;
				Object[][] salaryBankObj = null;
				Object[][] reimburseAcObj=null;
				Object [][] bankMaster = getSqlModel().getSingleResult("SELECT BANK_MICR_CODE,BANK_MICR_CODE FROM HRMS_BANK ");
				Object[][] reimburseBankObj = null;
				Object [][] pensionAcObj=null;
				Object[][] pensionBankObj = null;
				Object[][] pensionableObj=null;
				Object[][] payModeObj = null;
				Object[][] accountingCatgObj = null;
				Object[][] costCenterObj = null;
				Object[][] subCostCenterObj= null;
				Object[][] empIdObj = null;
				Object[][] custRefNoObj = null;
				
				int personalCount=0;
				int salCount=0;
					
				String insertPersonalQuery="INSERT INTO HRMS_EMP_PERS ( ";
				String insertSalaryQuery="INSERT INTO HRMS_SALARY_MISC ( ";
				String persValues=",EMP_ID ) VALUES(";
				String salValues=",EMP_ID ) VALUES(";
				
				String updatePersonalQuery =" UPDATE HRMS_EMP_PERS SET ";
				String updateSalaryQuery =" UPDATE HRMS_SALARY_MISC SET ";
				String whereQuery =" WHERE EMP_ID = ? ";
				
				/**
				 * Get column numbers with mandatory information
				 */
				HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
				
				for (int i = 0; i < columnNames.length; i++) {
					int colNo = Integer.parseInt(String.valueOf(columnNames[i][0]));
					String colName = String.valueOf(columnNames[i][1]);
					
					if(colName.equals("Employee ID")) {
						Object [][] empIdMaster = getSqlModel().getSingleResult(" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC WHERE EMP_STATUS ='S' ");
						if(empIdMaster != null && empIdMaster.length > 0)
							empIdObj = MigrateExcelData.uploadExcelData(colNo, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						continue;
					}
					if(colName.equals("Employee Name")) {
						Object[][] empName=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
					}
					if(colName.equals("Nationality")) {
						nationalityObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_NATIONALITY = ? ,";
						insertPersonalQuery+=" EMP_NATIONALITY,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					if(colName.equals("Blood Group")) {
						DataModificatonUtil dmu = new DataModificatonUtil();
						dmu.initiate(context, session);
						TreeMap bloodMap = dmu.getGenderXml("Blood");
						dmu.terminate();
						Object [][] bloodMaster = null;
						if(bloodMap != null && bloodMap.size() > 0){
							int l=0;
							bloodMaster = new Object[bloodMap.size()][2];
							for (Iterator k = bloodMap.keySet().iterator() ; k.hasNext();) {
								bloodMaster[l][0] = k.next();
								bloodMaster[l][1] = bloodMap.get(String.valueOf(bloodMaster[l][0]));
								l++;
							}
						}
						if(bloodMaster != null && bloodMaster.length > 0)
							bloodGrpObj=MigrateExcelData.uploadExcelData(colNo, bloodMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_BLDGP = ? ,";
						insertPersonalQuery+=" EMP_BLDGP,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					if(colName.equals("Religion")) {
						Object [][] religionMaster = getSqlModel().getSingleResult(" SELECT RELIGION_ID,RELIGION_NAME FROM HRMS_RELIGION ");
						//if(religionMaster != null && religionMaster.length > 0)
							religionObj=MigrateExcelData.uploadExcelData(colNo, religionMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_RELIGION = ? ,";
						insertPersonalQuery+=" EMP_RELIGION,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Caste Category")) {
						Object [][] categoryMaster = getSqlModel().getSingleResult(" SELECT CATG_ID,CATG_NAME FROM HRMS_CATG ");
						//if(categoryMaster != null && categoryMaster.length > 0)
							categoryObj=MigrateExcelData.uploadExcelData(colNo, categoryMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_CASTE_CATG = ? ,";
						insertPersonalQuery+=" EMP_CASTE_CATG,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Caste")) {
						Object [][] casteMaster = getSqlModel().getSingleResult(" SELECT CAST_ID,CAST_NAME FROM HRMS_CAST ");
						//if(casteMaster != null && casteMaster.length > 0)
							casteObj=MigrateExcelData.uploadExcelData(colNo, casteMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_CASTE = ? ,";
						insertPersonalQuery+=" EMP_CASTE,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Sub Caste")) { 
						subCasteObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_SUBCAST = ? ,";
						insertPersonalQuery+=" EMP_SUBCAST,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Height")) { 
						heightObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_HEIGHT = ? ,";
						insertPersonalQuery+=" EMP_HEIGHT,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Weight")) {
						weightObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_WEIGHT = ? ,";
						insertPersonalQuery+=" EMP_WEIGHT,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Is Handicap")) {
						handicapObj=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_ISHANDICAP = ? ,";
						insertPersonalQuery+=" EMP_ISHANDICAP,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Handicap Desc")) {
						handicapDescObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_HANDI_DESC = ? ,";
						insertPersonalQuery+=" EMP_HANDI_DESC,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Marital Status")) {
						DataModificatonUtil dmu = new DataModificatonUtil();
						dmu.initiate(context, session);
						TreeMap maritialMap = dmu.getGenderXml("marriage");
						dmu.terminate();
						Object [][] maritialMaster = null;
						if(maritialMap != null && maritialMap.size() > 0){
							int l=0;
							maritialMaster = new Object[maritialMap.size()][2];
							for (Iterator k = maritialMap.keySet().iterator() ; k.hasNext();) {
								maritialMaster[l][0] = k.next();
								maritialMaster[l][1] = maritialMap.get(String.valueOf(maritialMaster[l][0]));
								l++;
							}
						}
						if(maritialMaster != null && maritialMaster.length > 0)
							maritalStatusObj=MigrateExcelData.uploadExcelData(colNo, maritialMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_MARITAL_STATUS = ? ,";
						insertPersonalQuery+=" EMP_MARITAL_STATUS,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Marriage Date")) {
						marriageDtObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_MARRG_DATE = TO_DATE(?,'MM/DD/YYYY') ,";
						insertPersonalQuery+=" EMP_MARRG_DATE,";
						persValues+="TO_DATE(?,'MM/DD/YYYY'),";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Hobbies")) {
						hobbiesObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_HOBBY = ? ,";
						insertPersonalQuery+=" EMP_HOBBY,";
						persValues+="?,";
						personalCount++;
						continue;
					}
						
					if(colName.equals("ID Mark")) { 
						idMarkObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_IDMARK = ? ,";
						insertPersonalQuery+=" EMP_IDMARK,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Passport No")) { 
						passportObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_PASSPORT = ? ,";
						insertPersonalQuery+=" EMP_PASSPORT,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("Passport Expiry Date")) {
						passExpDtObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
						updatePersonalQuery+=" EMP_PASSPORT_EXPDATE = TO_DATE(?,'MM/DD/YYYY') ,";
						insertPersonalQuery+=" EMP_PASSPORT_EXPDATE,";
						persValues+="TO_DATE(?,'MM/DD/YYYY'),";
						personalCount++;
						continue;
					}
					
					if(colName.equals("HomeTown(City)")) {
						Object [][] homeTownMaster = getSqlModel().getSingleResult("SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ");
						//if(homeTownMaster != null && homeTownMaster.length > 0)
							homeTownObj=MigrateExcelData.uploadExcelData(colNo, homeTownMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updatePersonalQuery+=" EMP_HOMETOWN = ? ,";
						insertPersonalQuery+=" EMP_HOMETOWN,";
						persValues+="?,";
						personalCount++;
						continue;
					}
					
					if(colName.equals("EPF Applicabile")) { 
						EPFapplicableObj=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_EPF_FLAG = ? ,";
						insertSalaryQuery+=" SAL_EPF_FLAG,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("VPF Applicabile")) { 
						VPFapplicableObj=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_VPF_FLAG = ? ,";
						insertSalaryQuery+=" SAL_VPF_FLAG ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("GPF Applicabile")) { 
						GPFapplicableObj=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_GPF_FLAG = ? ,";
						insertSalaryQuery+=" SAL_GPF_FLAG ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("PF Trust Applicabile")) {
						PFTrustapplicable=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_PFTRUST_FLAG = ? ,";
						insertSalaryQuery+=" SAL_PFTRUST_FLAG ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("PF Number")) {
						pfNumberObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_GPFNO = ? ,";
						insertSalaryQuery+=" SAL_GPFNO ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("PAN Number")) {
						panNumberObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_PANNO = ? ,";
						insertSalaryQuery+=" SAL_PANNO ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("ESIC Number")) {
						esicNumberObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_ESCINUMBER = ? ,";
						insertSalaryQuery+=" SAL_ESCINUMBER ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Gratuity Id")) {
						gratuityNumberObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_GRATUITY_ACCNO = ? ,";
						insertSalaryQuery+=" SAL_GRATUITY_ACCNO ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Salary A/C No")) {
						salaryAcObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_ACCNO_REGULAR = ? ,";
						insertSalaryQuery+=" SAL_ACCNO_REGULAR ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Salary Bank")) {
						if(bankMaster != null && bankMaster.length > 0)
							salaryBankObj=MigrateExcelData.uploadExcelData(colNo, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" SAL_MICR_REGULAR = ? ,";
						insertSalaryQuery+=" SAL_MICR_REGULAR ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Reimbursement A/C No")) {
						reimburseAcObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_REIMBMENT = ? ,";
						insertSalaryQuery+=" SAL_REIMBMENT ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Reimbursement Bank")) {
						if(bankMaster != null && bankMaster.length > 0)
							reimburseBankObj=MigrateExcelData.uploadExcelData(colNo, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" SAL_REIMBANK = ? ,";
						insertSalaryQuery+=" SAL_REIMBANK ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Pension A/C No")) { 
						pensionAcObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_ACCNO_PENSION = ? ,";
						insertSalaryQuery+=" SAL_ACCNO_PENSION ,";
						salValues+="?,";
						salCount++;
						updateSalaryQuery+=" SAL_PENSION_ACCNO = ? ,";
						insertSalaryQuery+=" SAL_PENSION_ACCNO ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Pension Bank")) {
						if(bankMaster != null && bankMaster.length > 0)
							pensionBankObj=MigrateExcelData.uploadExcelData(colNo, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_PENSION_BANK = ? ,";
						insertSalaryQuery+=" SAL_PENSION_BANK ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Pensionable")) { 
						pensionableObj=MigrateExcelData.uploadExcelData(colNo, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						updateSalaryQuery+=" SAL_PENSIONABLE = ? ,";
						insertSalaryQuery+=" SAL_PENSIONABLE ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Pay Mode")) {
						DataModificatonUtil dmu = new DataModificatonUtil();
						dmu.initiate(context, session);
						TreeMap payModeMap = dmu.getGenderXml("Paymode");
						dmu.terminate();
						Object [][] payModeMaster = null;
						if(payModeMap != null && payModeMap.size() > 0){
							int l=0;
							payModeMaster = new Object[payModeMap.size()][2];
							for (Iterator k = payModeMap.keySet().iterator() ; k.hasNext();) {
								payModeMaster[l][0] = k.next();
								payModeMaster[l][1] = payModeMap.get(String.valueOf(payModeMaster[l][0]));
								l++;
							}
						}
						if(payModeMaster != null && payModeMaster.length > 0)
							payModeObj=MigrateExcelData.uploadExcelData(colNo, payModeMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" SAL_PAY_MODE = ? ,";
						insertSalaryQuery+=" SAL_PAY_MODE ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Accounting Category")) {
						Object [][] accountingMaster = getSqlModel().getSingleResult(" SELECT ACCOUNT_CATEGORY_ID,ACCOUNT_CATEGORY_NAME FROM HRMS_ACCOUNTING_CATEGORY ");
						//if(accountingMaster != null && accountingMaster.length > 0)
							accountingCatgObj=MigrateExcelData.uploadExcelData(colNo, accountingMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" ACCOUNT_CATEGORY_ID = ? ,";
						insertSalaryQuery+=" ACCOUNT_CATEGORY_ID ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Cost Center")) {
						Object [][] costCenterMaster = getSqlModel().getSingleResult(" SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ");
						//if(costCenterMaster != null && costCenterMaster.length > 0)
							costCenterObj=MigrateExcelData.uploadExcelData(colNo, costCenterMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" COST_CENTER_ID = ? ,";
						insertSalaryQuery+=" COST_CENTER_ID ,";
						salValues+="?,";
						salCount++;
						continue;
					}

					if(colName.equals("Sub-Cost Center")) {
						Object [][] subCostCenterMaster = getSqlModel().getSingleResult("SELECT SUB_COST_CENTER_ID,SUB_COST_CENTER_NAME FROM HRMS_SUB_COST_CENTER " );
						//if(subCostCenterMaster != null && subCostCenterMaster.length > 0)
							subCostCenterObj=MigrateExcelData.uploadExcelData(colNo, subCostCenterMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						
						updateSalaryQuery+=" SUB_COST_CENTER_ID = ? ,";
						insertSalaryQuery+=" SUB_COST_CENTER_ID ,";
						salValues+="?,";
						salCount++;
						continue;
					}
					
					if(colName.equals("Customer Reference No")) { 
						custRefNoObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(colNo));
						updateSalaryQuery+=" CUSTOMER_REF_NO = ? ,";
						insertSalaryQuery+=" CUSTOMER_REF_NO,";
						salValues+="?,";
						salCount++;
						continue;
					}
				}
				insertSalaryQuery = insertSalaryQuery.substring(0, insertSalaryQuery.length() - 1);
				insertSalaryQuery += salValues+" ? )";
				
				insertPersonalQuery = insertPersonalQuery.substring(0, insertPersonalQuery.length() - 1);
				insertPersonalQuery += persValues+" ? )";
				
				updateSalaryQuery = updateSalaryQuery.substring(0, updateSalaryQuery.length() - 1);
				updateSalaryQuery+=whereQuery;
				
				updatePersonalQuery = updatePersonalQuery.substring(0, updatePersonalQuery.length() - 1);
				updatePersonalQuery+=whereQuery;
				
				logger.info("insert personal -- "+insertPersonalQuery);
				logger.info("update personal -- "+updatePersonalQuery);
				logger.info("insert salary -- "+insertSalaryQuery);
				logger.info("update salary -- "+updateSalaryQuery);
				
				boolean res = MigrateExcelData.isFileToBeUploaded();
				if(res){
					if(empIdObj != null && empIdObj.length > 0){
						ArrayList dataList = new ArrayList();
						ArrayList list = divideEmployee(empIdObj);
						if(list != null && list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {
								if(i < 2) {
									Object [] tempEmpIdObj = (Object[])list.get(i);
									if(personalCount > 0) {
										Object [][] updatePersonalObj = new Object[tempEmpIdObj.length][personalCount+1];
										for (int k = 0; k < tempEmpIdObj.length; k++) {
											int temp = 0;
											int excelPos= 0 ;
											for (int j = 0; j < empIdObj.length; j++) {
												if(String.valueOf(tempEmpIdObj[k]).equals(String.valueOf(empIdObj[j][0]))) {
													excelPos = j;
													break;
												}
											}
											
											if(nationalityObj != null && nationalityObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(nationalityObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(bloodGrpObj != null && bloodGrpObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(bloodGrpObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(religionObj != null && religionObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(religionObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(categoryObj != null && categoryObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(categoryObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(casteObj != null && casteObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(casteObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(subCasteObj != null && subCasteObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(subCasteObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(heightObj != null && heightObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(heightObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(weightObj != null && weightObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(weightObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(handicapObj != null && handicapObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(handicapObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(handicapDescObj != null && handicapDescObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(handicapDescObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}
												temp++;
											}
											if(maritalStatusObj != null && maritalStatusObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(maritalStatusObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(marriageDtObj != null && marriageDtObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(marriageDtObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(hobbiesObj != null && hobbiesObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(hobbiesObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(idMarkObj != null && idMarkObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(idMarkObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}
												temp++;
											}
											if(passportObj != null && passportObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(passportObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}
												temp++;
											}
											if(passExpDtObj != null && passExpDtObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(passExpDtObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											if(homeTownObj != null && homeTownObj.length > 0){
												try {
													updatePersonalObj[k][temp] =String.valueOf(homeTownObj[excelPos][0]);
												}catch (Exception e) {
													updatePersonalObj[k][temp] ="";
												}	
												temp++;
											}
											updatePersonalObj[k][temp] =String.valueOf(tempEmpIdObj[k]);
										}
										dataList.add(i,updatePersonalObj);
									}else {
										dataList.add(i,null);
									}
									
								}else {
									Object [] tempEmpIdObj = (Object[])list.get(i);
									if(salCount > 0) {
										Object [][] updateSalObj = new Object[tempEmpIdObj.length][salCount+1];
										for (int k = 0; k < tempEmpIdObj.length; k++) {
											int temp = 0;
											int excelPos= 0 ;
											for (int j = 0; j < empIdObj.length; j++) {
												if(String.valueOf(tempEmpIdObj[k]).equals(String.valueOf(empIdObj[j][0]))) {
													excelPos = j;
													break;
												}
											}
											if(EPFapplicableObj != null && EPFapplicableObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(EPFapplicableObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(VPFapplicableObj != null && VPFapplicableObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(VPFapplicableObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(GPFapplicableObj != null && GPFapplicableObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(GPFapplicableObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(PFTrustapplicable != null && PFTrustapplicable.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(PFTrustapplicable[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(pfNumberObj != null && pfNumberObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(pfNumberObj[excelPos][0]);
												}catch (Exception e) {
													e.printStackTrace();
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(panNumberObj != null && panNumberObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(panNumberObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(esicNumberObj != null && esicNumberObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(esicNumberObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}	
												temp++;
											}
											if(gratuityNumberObj != null && gratuityNumberObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(gratuityNumberObj[excelPos][0]);
												}catch (Exception e) {
													e.printStackTrace();
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(salaryAcObj != null && salaryAcObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(salaryAcObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(salaryBankObj != null && salaryBankObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(salaryBankObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(reimburseAcObj != null && reimburseAcObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(reimburseAcObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(reimburseBankObj != null && reimburseBankObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(reimburseBankObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(pensionAcObj != null && pensionAcObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(pensionAcObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
												try {
													updateSalObj[k][temp] =String.valueOf(pensionAcObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(pensionBankObj != null && pensionBankObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(pensionBankObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(pensionableObj != null && pensionableObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(pensionableObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(payModeObj != null && payModeObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(payModeObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(accountingCatgObj != null && accountingCatgObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(accountingCatgObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(costCenterObj != null && costCenterObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(costCenterObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(subCostCenterObj != null && subCostCenterObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(subCostCenterObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											if(custRefNoObj != null && custRefNoObj.length > 0){
												try {
													updateSalObj[k][temp] =String.valueOf(custRefNoObj[excelPos][0]);
												}catch (Exception e) {
													updateSalObj[k][temp] ="";
												}		
												temp++;
											}
											updateSalObj[k][temp] =String.valueOf(tempEmpIdObj[k]);
										}
										dataList.add(i,updateSalObj);
									}else {
										dataList.add(i,null);
									}
								}
							}
							/*boolean updatePersResult= false;
							boolean updateSalResult= false;
							boolean insertPersResult= false;
							boolean insertSalResult= false;
							
							if(dataList.size() > 0) {
								Object [][] insertPers = (Object [][])dataList.get(0);
									if(insertPers != null && insertPers.length > 0) {
										insertPersResult=getSqlModel().singleExecute(insertPersonalQuery, insertPers);
										if(insertPersResult)
										logger.info("personal insert length -- "+insertPers.length);
									}
								Object [][] updatePers = (Object [][])dataList.get(1);
									if(updatePers != null && updatePers.length > 0) {
										updatePersResult=getSqlModel().singleExecute(updatePersonalQuery, updatePers);
										if(updatePersResult)
										logger.info("personal update length -- "+updatePers.length);
									}
								Object [][] insertSal = (Object [][])dataList.get(2);
									if(insertSal != null && insertSal.length > 0) {
										insertSalResult=getSqlModel().singleExecute(insertSalaryQuery, insertSal);
										if(insertSalResult)
										logger.info("salary insert length -- "+insertSal.length);
									}
								Object [][] updateSal = (Object [][])dataList.get(3);
									if(updateSal != null && updateSal.length > 0) {
										updateSalResult=getSqlModel().singleExecute(updateSalaryQuery, updateSal);
										if(updateSalResult)
										logger.info("salary update length-- "+updateSal.length);
									}
								}	
								if(updatePersResult && updateSalResult)
									bean.setStatus("Success");
								else{
									if(updatePersResult && salCount == 0) {
											bean.setStatus("Success");
									}else if(updateSalResult && personalCount == 0) {
											bean.setStatus("Success");
									}else {
											bean.setStatus("Fail");
											bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
									}
								}*/
							
							ArrayList queryList = new ArrayList();
							Vector objVector = new Vector();
							int count = 0;
							boolean result = false;
							if(dataList.size() > 0) {
								Object [][] insertPers = (Object [][])dataList.get(0);
									if(insertPers != null && insertPers.length > 0) {
										logger.info("personal insert length -- "+insertPers.length);
										queryList.add(insertPersonalQuery);
										objVector.add(count, insertPers);
										count++;
									}
								Object [][] updatePers = (Object [][])dataList.get(1);
									if(updatePers != null && updatePers.length > 0) {
										logger.info("personal update length -- "+updatePers.length);
										queryList.add(updatePersonalQuery);
										objVector.add(count, updatePers);
										count++;
									}
								Object [][] insertSal = (Object [][])dataList.get(2);
									if(insertSal != null && insertSal.length > 0) {
										logger.info("salary insert length -- "+insertSal.length);
										queryList.add(insertSalaryQuery);
										objVector.add(count, insertSal);
										count++;
									}
								Object [][] updateSal = (Object [][])dataList.get(3);
									if(updateSal != null && updateSal.length > 0) {
										logger.info("salary update length-- "+updateSal.length);
										queryList.add(updateSalaryQuery);
										objVector.add(count, updateSal);
										count++;
									}
									Object[] queries = queryList.toArray();
									result = getSqlModel().multiExecute(queries, objVector);
								}
								if(result)
									bean.setStatus("Success");
								else{
									bean.setStatus("Fail");
									bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
								}
						}else {
							bean.setStatus("Fail");
							bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
						}
					}else {
						bean.setStatus("Fail");
						bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
					}
				}else {
					bean.setStatus("Fail");
					bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
		}			
		}catch(Exception e){
			logger.error("Error in PersonalPartialUploadModel -- uploaddPersonalTemplate "+e);
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
	}
	public ArrayList divideEmployee(Object [][] empExcelObj) {
		ArrayList returnObjectList = new ArrayList();
		try {
			if(empExcelObj != null && empExcelObj.length > 0 ) {
				
				HashMap<String,Object[]> persEmpMap = new HashMap<String, Object[]>();
				HashMap<String,Object[]> salEmpMap = new HashMap<String, Object[]>();
				
				
				ArrayList persInsertList = new ArrayList();
				ArrayList persUpdateList = new ArrayList();
				ArrayList salInsertList = new ArrayList();
				ArrayList salUpdateList = new ArrayList();
				
				String persQuery =" SELECT EMPID,STATUS FROM(SELECT HRMS_EMP_OFFC.EMP_ID EMPID , "
                                 + " CASE WHEN HRMS_EMP_PERS.EMP_ID IS NULL THEN 'false' ELSE 'true'" 
                                 + " END STATUS FROM HRMS_EMP_OFFC "
                                 + " LEFT JOIN HRMS_EMP_PERS ON "
                                 + " HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
                                 + " ORDER BY EMPID ";
				
				persEmpMap = getSqlModel().getSingleResultMap(persQuery,0,0);
				
				String salQuery =" SELECT EMPID,STATUS FROM(SELECT HRMS_EMP_OFFC.EMP_ID EMPID , "
                                + " CASE WHEN HRMS_SALARY_MISC.EMP_ID IS NULL THEN 'false' ELSE 'true'" 
                                + " END STATUS FROM HRMS_EMP_OFFC "
                                + " LEFT JOIN HRMS_SALARY_MISC ON "
                                + " HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
                                + " ORDER BY EMPID ";
				
				salEmpMap = getSqlModel().getSingleResultMap(salQuery,0,0);
				
				for (int i = 0; i < empExcelObj.length; i++) {
					
						Object [] tempPersObj = persEmpMap.get(String.valueOf(empExcelObj[i][0]));
						if(tempPersObj != null && tempPersObj.length > 0 ) {
							if(String.valueOf(tempPersObj[1]).equals("true")){
								persUpdateList.add(String.valueOf(empExcelObj[i][0]));
							}else {
								persInsertList.add(String.valueOf(empExcelObj[i][0]));
							}
						}
						
						Object [] tempSalObj = salEmpMap.get(String.valueOf(empExcelObj[i][0]));
						if(tempSalObj != null && tempSalObj.length > 0 ) {
							if(String.valueOf(tempSalObj[1]).equals("true")){
								salUpdateList.add(String.valueOf(empExcelObj[i][0]));
							}else {
								salInsertList.add(String.valueOf(empExcelObj[i][0]));
							}
						}
				}
				
				Object [] persInsertObj = persInsertList.toArray();
				Object [] persUpdateObj = persUpdateList.toArray();
				Object [] salInsertObj = salInsertList.toArray();
				Object [] salUpdateObj = salUpdateList.toArray();
				
				returnObjectList.add(0, persInsertObj);
				returnObjectList.add(1, persUpdateObj);
				returnObjectList.add(2, salInsertObj);
				returnObjectList.add(3, salUpdateObj);
			}
		}catch(Exception e) {
			logger.error("Error in PersonalPartialUploadModel -- divideEmployee "+e);
		}
		return returnObjectList;
	}
	
	public Object [][] getEmployee(PersonalPartialUpload bean){
			
			Object[][] empObj = null;
			try {
				String empQuery =" SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
						+" WHERE EMP_STATUS ='S'AND EMP_TOKEN IS NOT NULL ";

				if(!bean.getDivCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_DIV="+bean.getDivCode();
				if(!bean.getBranchCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchCode();
				if(!bean.getDeptCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptCode();
				if(!bean.getDesgCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgCode();
				if(!bean.getEmpTypeCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_TYPE="+bean.getEmpTypeCode();
				if(!bean.getGradeCode().equals(""))
					empQuery+=" and HRMS_EMP_OFFC.EMP_CADRE="+bean.getGradeCode();
				
				empQuery+=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)";

				empObj = getSqlModel().getSingleResult(empQuery);
				
			} catch (Exception e) {
				logger.error("Error in getEmployee == "+e);
			}
			
			return empObj;
	}
	
	public boolean generateUrlList(HttpServletRequest request,
			HttpServletResponse response, PersonalPartialUpload bean) {
		try{
			Object [][] resultObj = getEmployee(bean);
				if (resultObj != null && resultObj.length >0) {
					request.setAttribute("totalRecords",resultObj.length);
					request.setAttribute("recPerPage",EMPLOYEE_COUNT);
					bean.setDataExists(true);
					return true;
				}else{
					request.setAttribute("totalRecords",0);
					return false;
				}
			}
			catch (Exception e) {
				logger.error("Error in generateUrlList == "+e);
				return false;
		}
	}
}
