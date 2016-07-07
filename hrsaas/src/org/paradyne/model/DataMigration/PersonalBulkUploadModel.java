package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.struts.action.DataMigration.MigrateExcelData;


public class PersonalBulkUploadModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalBulkUploadModel.class);

	public void downloadPersonalTemplate(HttpServletRequest request,HttpServletResponse response){
		
		try {
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType("Xls");
			rds.setFileName("Upload Emp Details");
			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			TableDataSet tds = new TableDataSet();
			tds.setHeader(new String []{"Employee Code","Nationality","Blood Group","Religion","Caste Category","Caste",
										"SubCaste","Weight(in k.g.)","Height(in c.m.)","Is Handicap","Handicap Desc","Marital Status",
										"Marriage Date(mm-dd-yyyy)","Hobbies","ID Mark","Passport No","Passport Expiry Date(mm-dd-yyyy)","HomeTown(City)",
										"EPF Applicable","VPF Applicable","GPF Applicable","PF Trust Applicable","PF Number","PAN Number",
										"ESIC Number","Gratuity Id","Salary A/C No","Salary Bank","Reimbursement A/C No","Reimbursement Bank",
										"Pension A/C No","Pension Bank","Pensionable","Pay Mode","Accounting Category","Cost Center ",
										"Sub-Cost Center ", "Customer Reference No"});
			tds.setCellAlignment(new int []{0,0,0,0,0,0,0,
											0,0,0,0,0,0,
											0,0,0,0,0,0,
											0,0,0,0,0,0,
											0,0,0,0,0,0,
											0,0,0,0,0,
											0,0});
			tds.setCellWidth(new int []{15,15,15,15,15,15,
										15,15,15,15,15,15,
										15,15,15,15,15,15,
										15,15,15,15,15,15,
										15,15,15,15,15,15,
										15,15,15,15,15,15,
										15,15});
			Object tempObj [][] = new Object[100][38];
			tds.setData(tempObj);
			tds.setBorder(true);
			rg.addTableToDoc(tds);
			rg.process();
			rg.createReport(response);
			
		} catch (Exception e) {
			logger.info("Exception in downloadPersonalTemplate "+e);
		}
	}
	
	public void uploaddPersonalTemplate(HttpServletRequest request,HttpServletResponse response, EmpDetailsUpload empDetails) {

		try {
			String filePath=empDetails.getDataPath()+""+empDetails.getUploadFileName();
			empDetails.setUploadName("Personal");
			//to create  object of the file 
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
			
			Object[][] empIdObj = null;
			Object [][] empIdMaster = getSqlModel().getSingleResult(" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC ");
			if(empIdMaster != null && empIdMaster.length > 0)
				empIdObj = MigrateExcelData.uploadExcelData(1, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			
			Object[][] empName=MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			Object[][] nationalityObj=MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
			
			DataModificatonUtil dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap bloodMap = dmu.getGenderXml("Blood");
			dmu.terminate();
			Object [][] bloodMaster = null;
			Object[][] bloodGrpObj = null;
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
				bloodGrpObj=MigrateExcelData.uploadExcelData(4, bloodMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(4));
			
			Object[][] religionObj = null;
			Object [][] religionMaster = getSqlModel().getSingleResult(" SELECT RELIGION_ID,RELIGION_NAME FROM HRMS_RELIGION ");
			if(religionMaster != null && religionMaster.length > 0)
				religionObj=MigrateExcelData.uploadExcelData(5, religionMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(5));
			
			Object[][] categoryObj = null;
			Object [][] categoryMaster = getSqlModel().getSingleResult(" SELECT CATG_ID,CATG_NAME FROM HRMS_CATG ");
			if(categoryMaster != null && categoryMaster.length > 0)
				categoryObj=MigrateExcelData.uploadExcelData(6, categoryMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(6));
			
			Object[][] casteObj = null;
			Object [][] casteMaster = getSqlModel().getSingleResult(" SELECT CAST_ID,CAST_NAME FROM HRMS_CAST ");
			if(casteMaster != null && casteMaster.length > 0)
				casteObj=MigrateExcelData.uploadExcelData(7, casteMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(7));
			
			Object[][] subCasteObj=MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.STRING_TYPE, columnInformation.get(8));
			Object[][] weightObj=MigrateExcelData.uploadExcelData(9, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(9));
			Object[][] heightObj=MigrateExcelData.uploadExcelData(10, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(10));
			
			Object[][]yesNoObj = new Object[2][2];
				yesNoObj[0][0]="Y";
				yesNoObj[0][1]="Yes";
				yesNoObj[1][0]="N";
				yesNoObj[1][1]="No";
			Object[][] handicapObj=MigrateExcelData.uploadExcelData(11, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(11));
			Object[][] handicapDescObj=MigrateExcelData.uploadExcelData(12, null, MigrateExcelData.STRING_TYPE, columnInformation.get(12));
			
			dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap maritialMap = dmu.getGenderXml("marriage");
			dmu.terminate();
			Object [][] maritialMaster = null;
			Object[][] maritalStatusObj = null;
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
				maritalStatusObj=MigrateExcelData.uploadExcelData(13, maritialMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(13));
			
			Object[][] marriageDtObj=MigrateExcelData.uploadExcelData(14, null, MigrateExcelData.DATE_TYPE, columnInformation.get(14));
			
			Object[][] hobbiesObj=MigrateExcelData.uploadExcelData(15, null, MigrateExcelData.STRING_TYPE, columnInformation.get(15));
			Object[][] idMarkObj=MigrateExcelData.uploadExcelData(16, null, MigrateExcelData.STRING_TYPE, columnInformation.get(16));
			Object[][] passportObj=MigrateExcelData.uploadExcelData(17, null, MigrateExcelData.STRING_TYPE, columnInformation.get(17));
			Object[][] passExpDtObj=MigrateExcelData.uploadExcelData(18, null, MigrateExcelData.DATE_TYPE, columnInformation.get(18));
			
			Object [][] homeTownObj = null;
			Object [][] homeTownMaster = getSqlModel().getSingleResult("SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ");
			if(homeTownMaster != null && homeTownMaster.length > 0)
				homeTownObj=MigrateExcelData.uploadExcelData(19, homeTownMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(19));
			
			
			Object[][] EPFapplicableObj=MigrateExcelData.uploadExcelData(20, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(20));
			Object[][] VPFapplicableObj=MigrateExcelData.uploadExcelData(21, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(21));
			Object[][] GPFapplicableObj=MigrateExcelData.uploadExcelData(22, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(22));
			Object[][] PFTrustapplicable=MigrateExcelData.uploadExcelData(23, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(23));
			Object[][] pfNumberObj=MigrateExcelData.uploadExcelData(24, null, MigrateExcelData.STRING_TYPE, columnInformation.get(24));
			Object[][] panNumberObj=MigrateExcelData.uploadExcelData(25, null, MigrateExcelData.STRING_TYPE, columnInformation.get(25));
			
			Object[][] esicNumberObj=MigrateExcelData.uploadExcelData(26, null, MigrateExcelData.STRING_TYPE, columnInformation.get(26));
			Object[][] gratuityNumberObj=MigrateExcelData.uploadExcelData(27, null, MigrateExcelData.STRING_TYPE, columnInformation.get(27));
			Object[][] salaryAcObj=MigrateExcelData.uploadExcelData(28, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(28));
			
			Object[][] salaryBankObj = null;
			Object [][] bankMaster = getSqlModel().getSingleResult("SELECT BANK_MICR_CODE,BANK_MICR_CODE FROM HRMS_BANK ");
			if(bankMaster != null && bankMaster.length > 0)
				salaryBankObj=MigrateExcelData.uploadExcelData(29, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(29));
			
			Object[][] reimburseAcObj=MigrateExcelData.uploadExcelData(30, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(30));
			
			Object[][] reimburseBankObj = null;
			if(bankMaster != null && bankMaster.length > 0)
				reimburseBankObj=MigrateExcelData.uploadExcelData(31, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(31));
			
			Object [][] pensionAcObj=MigrateExcelData.uploadExcelData(32, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(32));
			
			Object[][] pensionBankObj = null;
			if(bankMaster != null && bankMaster.length > 0)
				pensionBankObj=MigrateExcelData.uploadExcelData(33, bankMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(33));
			
			Object[][] pensionableObj=MigrateExcelData.uploadExcelData(34, yesNoObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(34));
			
			dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap payModeMap = dmu.getGenderXml("Paymode");
			dmu.terminate();
			Object [][] payModeMaster = null;
			Object[][] payModeObj = null;
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
				payModeObj=MigrateExcelData.uploadExcelData(35, payModeMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(35));
			
			Object[][] accountingCatgObj = null;
			Object [][] accountingMaster = getSqlModel().getSingleResult(" SELECT ACCOUNT_CATEGORY_ID,ACCOUNT_CATEGORY_NAME FROM HRMS_ACCOUNTING_CATEGORY ");
			if(accountingMaster != null && accountingMaster.length > 0)
				accountingCatgObj=MigrateExcelData.uploadExcelData(36, accountingMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(36));
			
			Object[][] costCenterObj = null;
			Object [][] costCenterMaster = getSqlModel().getSingleResult(" SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ");
			if(costCenterMaster != null && costCenterMaster.length > 0)
				costCenterObj=MigrateExcelData.uploadExcelData(37, costCenterMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(37));
			
			Object[][] subCostCenterObj= null;
			Object [][] subCostCenterMaster = getSqlModel().getSingleResult("SELECT SUB_COST_CENTER_ID,SUB_COST_CENTER_NAME FROM HRMS_SUB_COST_CENTER " );
			if(subCostCenterMaster != null && subCostCenterMaster.length > 0)
				subCostCenterObj=MigrateExcelData.uploadExcelData(38, subCostCenterMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(38));
			
			Object[][] customerRefNoObj=MigrateExcelData.uploadExcelData(39, null, MigrateExcelData.STRING_TYPE, columnInformation.get(39));
			
			boolean res = MigrateExcelData.isFileToBeUploaded();
			if(res){
				
				if(empIdObj != null && empIdObj.length > 0){
					String empIds="";
					
					Object [][] insertPersonalObj = new Object[empIdObj.length][18];
					Object [][] insertSalObj = new Object[empIdObj.length][22];
					
					for (int i = 0; i < empIdObj.length; i++) {
						empIds += String.valueOf(empIdObj[i][0]) + ",";
						try {
							insertPersonalObj[i][0] =String.valueOf(empIdObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][0] ="";
						}
						try {
							insertPersonalObj[i][1] =String.valueOf(categoryObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][1] ="";
						}
						try {
							insertPersonalObj[i][2] =String.valueOf(casteObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][2] ="";
						}
						try {
							insertPersonalObj[i][3] =String.valueOf(subCasteObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][3] ="";
						}
						try {
							insertPersonalObj[i][4] =String.valueOf(heightObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][4] ="";
						}
						try {
							insertPersonalObj[i][5] =String.valueOf(weightObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][5] ="";
						}
						try {
							insertPersonalObj[i][6] =String.valueOf(idMarkObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][6] ="";
						}
						try {
							insertPersonalObj[i][7] =String.valueOf(bloodGrpObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][7] ="";
						}
						try {
							insertPersonalObj[i][8] =String.valueOf(handicapDescObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][8] ="";
						}
						try {
							insertPersonalObj[i][9] =String.valueOf(hobbiesObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][9] ="";
						}
						try {
							insertPersonalObj[i][10] =String.valueOf(maritalStatusObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][10] ="";
						}
						try {
							insertPersonalObj[i][11] =String.valueOf(marriageDtObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][11] ="";
						}
						try {
							insertPersonalObj[i][12] =String.valueOf(homeTownObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][12] ="";
						}
						try {
							insertPersonalObj[i][13] =String.valueOf(nationalityObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][13] ="";
						}
						try {
							insertPersonalObj[i][14] =String.valueOf(religionObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][14] ="";
						}
						try {
							insertPersonalObj[i][15] =String.valueOf(passportObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][15] ="";
						}
						try {
							insertPersonalObj[i][16] =String.valueOf(handicapObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][16] ="";
						}
						try {
							insertPersonalObj[i][17] =String.valueOf(passExpDtObj[i][0]);
						}catch (Exception e) {
							insertPersonalObj[i][17] ="";
						}
						
						try {
							insertSalObj[i][0] =String.valueOf(empIdObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][0] ="";
						}
						try {
							insertSalObj[i][1] =String.valueOf(pfNumberObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][1] ="";
						}
						try {
							insertSalObj[i][2] =String.valueOf(panNumberObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][2] ="";
						}	
						try {
							insertSalObj[i][3] =String.valueOf(salaryBankObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][3] ="";
						}
						try {
							insertSalObj[i][4] =String.valueOf(salaryAcObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][4] ="";
						}	
						try {
							insertSalObj[i][5] =String.valueOf(pensionAcObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][5] ="";
						}
						try {
							insertSalObj[i][6] =String.valueOf(pensionableObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][6] ="";
						}
						try {
							insertSalObj[i][7] =String.valueOf(payModeObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][7] ="";
						}	
						try {
							insertSalObj[i][8] =String.valueOf(esicNumberObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][8] ="";
						}
						try {
							insertSalObj[i][9] =String.valueOf(reimburseAcObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][9] ="";
						}	
						try {
							insertSalObj[i][10] =String.valueOf(reimburseBankObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][10] ="";
						}
						try {
							insertSalObj[i][11] =String.valueOf(EPFapplicableObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][11] ="";
						}
						try {
							insertSalObj[i][12] =String.valueOf(VPFapplicableObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][12] ="";
						}	
						try {
							insertSalObj[i][13] =String.valueOf(GPFapplicableObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][13] ="";
						}
						try {
							insertSalObj[i][14] =String.valueOf(PFTrustapplicable[i][0]);
						}catch (Exception e) {
							insertSalObj[i][14] ="";
						}
						try {
							insertSalObj[i][15] =String.valueOf(gratuityNumberObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][15] ="";
						}
						try {
							insertSalObj[i][16] =String.valueOf(pensionBankObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][16] ="";
						}
						try {
							insertSalObj[i][17] =String.valueOf(accountingCatgObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][17] ="";
						}
						try {
							insertSalObj[i][18] =String.valueOf(costCenterObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][18] ="";
						}	
						try {
							insertSalObj[i][19] =String.valueOf(pensionAcObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][19] ="";
						}
						try {
							insertSalObj[i][20] =String.valueOf(subCostCenterObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][20] ="";
						}
						try {
							insertSalObj[i][21] =String.valueOf(customerRefNoObj[i][0]);
						}catch (Exception e) {
							insertSalObj[i][21] ="";
						}
							
					}
					empIds = empIds.substring(0, empIds.length() - 1);
					
					String delPersQuery =" DELETE FROM HRMS_EMP_PERS WHERE EMP_ID IN ("+empIds+") ";
					boolean delPersResult = getSqlModel().singleExecute(delPersQuery);
					boolean insertPersResult = false;
					if(delPersResult){
						
						String insertPersQuery = " INSERT INTO HRMS_EMP_PERS (EMP_ID,EMP_CASTE_CATG,EMP_CASTE,EMP_SUBCAST,EMP_HEIGHT,EMP_WEIGHT," +
						 	" EMP_IDMARK,EMP_BLDGP,EMP_HANDI_DESC,EMP_HOBBY,EMP_MARITAL_STATUS,EMP_MARRG_DATE, " +
						 	" EMP_HOMETOWN,EMP_NATIONALITY,EMP_RELIGION,EMP_PASSPORT,EMP_ISHANDICAP,EMP_PASSPORT_EXPDATE)" +
						 	" VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'))";

						insertPersResult = getSqlModel().singleExecute(insertPersQuery,insertPersonalObj);
						
					}
					
					String delSalQuery =" DELETE FROM HRMS_SALARY_MISC WHERE EMP_ID IN ("+empIds+") ";
					boolean delSalResult = getSqlModel().singleExecute(delSalQuery);
					boolean insertSalResult = false;
					if(delSalResult){
						//SAL_MICR_PENSION_SAVINGS,SAL_ACCNO_PENSION_SAVINGS
						String insertSalQuery = " INSERT INTO HRMS_SALARY_MISC (EMP_ID,SAL_GPFNO,SAL_PANNO,SAL_MICR_REGULAR,SAL_ACCNO_REGULAR,SAL_ACCNO_PENSION," +
							" SAL_PENSIONABLE,SAL_PAY_MODE,SAL_ESCINUMBER,SAL_REIMBMENT,SAL_REIMBANK,SAL_EPF_FLAG, " +
							" SAL_VPF_FLAG,SAL_GPF_FLAG,SAL_PFTRUST_FLAG,SAL_GRATUITY_ACCNO,SAL_PENSION_BANK,ACCOUNT_CATEGORY_ID," +
							" COST_CENTER_ID,SAL_PENSION_ACCNO,SUB_COST_CENTER_ID,CUSTOMER_REF_NO)"+
							" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";

						insertSalResult = getSqlModel().singleExecute(insertSalQuery,insertSalObj);
					}
					if(insertPersResult && insertSalResult)
						empDetails.setStatus("Success");
					else{
						empDetails.setStatus("Fail");
						empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
					}
				}
			}else{
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
		} catch (Exception e) {
			logger.info("Exceptione in upload personal "+e);
			empDetails.setStatus("Fail");
			empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
	}
}
