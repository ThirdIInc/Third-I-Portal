package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class FamilyBulkUploadModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FamilyBulkUploadModel.class);

	
	public void uploaddFamilyTemplate(HttpServletRequest request,HttpServletResponse response, EmpDetailsUpload empDetails) {
		try{
			
			String filePath=empDetails.getDataPath()+""+empDetails.getUploadFileName();
			empDetails.setUploadName("Family");
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
			
			Object[][] empNameObj=MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			
			Object[][] fNameObj=MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
			Object[][] mNameObj=MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
			Object[][] lNameObj=MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
			
			Object[][] relationObj = null;
			Object [][] relationMaster = getSqlModel().getSingleResult(" SELECT RELATION_CODE,RELATION_NAME FROM HRMS_RELATION ");
			if(relationMaster != null && relationMaster.length > 0)
				relationObj=MigrateExcelData.uploadExcelData(6, relationMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(6));
			
			DataModificatonUtil dmu = new DataModificatonUtil();
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
				maritalStatusObj=MigrateExcelData.uploadExcelData(7, maritialMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(7));
			
			Object [][]genderMasterObj=null;
			Object[][] genderObj=null;
			
			dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap genderMap = dmu.getGenderXml("gender");
			dmu.terminate();
			if(genderMap != null && genderMap.size() > 0){
				int l=0;
				genderMasterObj = new Object[genderMap.size()][2];
				for (Iterator k = genderMap.keySet().iterator() ; k.hasNext();) {
					genderMasterObj[l][0] = k.next();
					genderMasterObj[l][1] = genderMap.get(String.valueOf(genderMasterObj[l][0]));
					l++;
				}
			}
			
			if(genderMasterObj != null && genderMasterObj.length > 0)
				genderObj=MigrateExcelData.uploadExcelData(8, genderMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(8));
			
			Object[][] emailIDObj = MigrateExcelData.uploadExcelData(9, null,MigrateExcelData.STRING_TYPE, columnInformation.get(9));
			
			Object[][] phoneObj = MigrateExcelData.uploadExcelData(10, null,MigrateExcelData.STRING_TYPE, columnInformation.get(10));
			
			Object[][] dobObj=MigrateExcelData.uploadExcelData(11, null, MigrateExcelData.DATE_TYPE, columnInformation.get(11));
			
			Object[][] professionObj = MigrateExcelData.uploadExcelData(12, null,MigrateExcelData.STRING_TYPE, columnInformation.get(12));
			
			Object[][] addressObj = MigrateExcelData.uploadExcelData(13, null,MigrateExcelData.STRING_TYPE, columnInformation.get(13));
			
			Object[][] identificationObj = MigrateExcelData.uploadExcelData(14, null,MigrateExcelData.STRING_TYPE, columnInformation.get(14));
			
			Object[][]yesNoObj = new Object[2][2];
				yesNoObj[0][0]="Y";
				yesNoObj[0][1]="Yes";
				yesNoObj[1][0]="N";
				yesNoObj[1][1]="No";
			
			Object[][] isLiveObj = MigrateExcelData.uploadExcelData(15,yesNoObj,MigrateExcelData.MASTER_TYPE, columnInformation.get(15));
			
			Object[][] isDependentObj = MigrateExcelData.uploadExcelData(16,yesNoObj,MigrateExcelData.MASTER_TYPE, columnInformation.get(16));
			
			Object[][] otherInfoObj = MigrateExcelData.uploadExcelData(17, null,MigrateExcelData.STRING_TYPE, columnInformation.get(17));
			
			boolean res = MigrateExcelData.isFileToBeUploaded();
			if(res){
					String maxQuery = "SELECT NVL(MAX(FML_ID),0)+1  FROM HRMS_EMP_FML ";
					Object [][] maxObj = getSqlModel().getSingleResult(maxQuery);
					
					int familyId = Integer.parseInt(String.valueOf(maxObj[0][0]));
					
				if(empIdObj != null && empIdObj.length > 0){
					String empIds="";
					
					Object [][] insertFamilyObj = new Object[empIdObj.length][17];
					
					for (int i = 0; i < empIdObj.length; i++) {
						empIds += String.valueOf(empIdObj[i][0]) + ",";
						try {
							insertFamilyObj[i][0] =String.valueOf(empIdObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][0] ="";
						}
						try {
							insertFamilyObj[i][1] =String.valueOf(familyId++);
						}catch(Exception e) {
							insertFamilyObj[i][1] ="";
						}
						try {
							insertFamilyObj[i][2] =String.valueOf(fNameObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][2] ="";
						}	
						try {
							insertFamilyObj[i][3] =String.valueOf(mNameObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][3] ="";
						}
						try {
							insertFamilyObj[i][4] =String.valueOf(lNameObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][4] ="";
						}
						try {
							insertFamilyObj[i][5] =String.valueOf(relationObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][5] ="";
						}
						try {
							insertFamilyObj[i][6] =String.valueOf(maritalStatusObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][6] ="";
						}
						try {
							insertFamilyObj[i][7] =String.valueOf(genderObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][7] ="";
						}
						try {
							insertFamilyObj[i][8] =String.valueOf(phoneObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][8] ="";
						}
						try {
							insertFamilyObj[i][9] =String.valueOf(professionObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][9] ="";
						}
						try {
							insertFamilyObj[i][10] =String.valueOf(identificationObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][10] ="";
						}
						try {
							insertFamilyObj[i][11] =String.valueOf(emailIDObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][11] ="";
						}
						try {
							insertFamilyObj[i][12] =String.valueOf(dobObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][12] ="";
						}	
						try {
							insertFamilyObj[i][13] =String.valueOf(addressObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][13] ="";
						}	
						try {
						if(String.valueOf(isLiveObj[i][0]).equals("")||
								String.valueOf(isLiveObj[i][0]).equals("null")||
								String.valueOf(isLiveObj[i][0]).equals(null))
							insertFamilyObj[i][14] ="Y";
						else
							insertFamilyObj[i][14] =String.valueOf(isLiveObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][14] ="Y";
						}
						try {
						if(String.valueOf(isDependentObj[i][0]).equals("")||
								String.valueOf(isDependentObj[i][0]).equals("null")||
								String.valueOf(isDependentObj[i][0]).equals(null))
							insertFamilyObj[i][15] ="Y";
						else
							insertFamilyObj[i][15] =String.valueOf(isDependentObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][15] ="Y";
						}
						try {
							insertFamilyObj[i][16] =String.valueOf(otherInfoObj[i][0]);
						}catch(Exception e) {
							insertFamilyObj[i][16] ="";
						}	
					}
					empIds = empIds.substring(0, empIds.length() - 1);
					
					String delNomineeQuery =" DELETE FROM HRMS_EMP_NOMINEE WHERE EMP_ID IN ("+empIds+") ";
					boolean delNomineeResult = getSqlModel().singleExecute(delNomineeQuery);
					
					String delFamilyQuery =" DELETE FROM HRMS_EMP_FML WHERE EMP_ID IN ("+empIds+") ";
					boolean delFamilyResult = getSqlModel().singleExecute(delFamilyQuery);
					
					boolean insertFamilyResult = false;
					if(delFamilyResult){
						
						String insertPersQuery = "INSERT INTO HRMS_EMP_FML (EMP_ID,FML_ID,FML_FNAME,FML_MNAME,FML_LNAME,FML_RELATION," +
										" FML_MARITAL_STATUS,FML_GENDER,FML_PH,FML_PROFESSION,FML_IDMARK,FML_EMAIL," +
										" FML_DOB,FML_ADDRESS,FML_ISALIVE,FML_ISDEPENDENT,FML_OTHERINFO) "+
										" VALUES(?,?,?,?,?,?," +
										" ?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?) " ;

						insertFamilyResult = getSqlModel().singleExecute(insertPersQuery,insertFamilyObj);
					}
					if(insertFamilyResult)
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
			
		}catch(Exception e){
			logger.info("Exceptione in upload Family "+e);
			empDetails.setStatus("Fail");
		}
	}
}
