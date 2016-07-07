/*Author:Anantha lakshmi*/
package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.CandidateDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class CandidateDetailsBulkUpload extends ModelBase {

	public String checkNull(String result) {
		if(result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void downLoadTemplate(HttpServletResponse response, HttpServletRequest request, String templateName) throws Exception {
		String reportType = "";
		reportType = "Xls";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, templateName, "");

		String[] str_colNames = new String[10];
		str_colNames[0] = "Title";
		str_colNames[1] = "First Name";
		str_colNames[2] = "Middle Name";
		str_colNames[3] = "Last Name";
		str_colNames[4] = "Date of Birth";
		str_colNames[5] = "Gender";
		str_colNames[6] = "Marital Status";
		str_colNames[7] = "Experience in Years";
		str_colNames[8] = "Experience in Months";
		str_colNames[9] = "Current Address";
		str_colNames[10] = "City";
		str_colNames[11]=  "State";
		str_colNames[12] = "Country";
		str_colNames[13] = "PinCode ";
		
		str_colNames[14] = "Residency Phone";
		str_colNames[15] = "Mobile No.";
		str_colNames[16] = "Office Phone";
		str_colNames[17] = "Emai Id";
		str_colNames[18]="Country";
		
		str_colNames[19] = "Current CTC In Lacs";
		str_colNames[20] = "Expected CTC In Lacs";
		str_colNames[21] = "Passport No";
		str_colNames[22] = "Pan No";
		str_colNames[23]="Current Company";
		
		str_colNames[24] = "Current Position";
		str_colNames[25] = "Current Location";
		str_colNames[26] = "Other Information";
		str_colNames[27] = "Upload Resume";
		str_colNames[28]= "Do you know anybody currently working with the group";
		str_colNames[29]= "Whether you employed earlier in the group";
		str_colNames[30]="Relocation";
		
		
		int cellWidth[] = {15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15, 15, 15, 15, 15, 15, 15, 15, 15, 15 ,15};
		int cellAlign[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Object[][] data = new Object[1][31];
		for(int i = 0; i < data[0].length; i++) {
			data[0][i] = "";
		}
		rg.tableBody(str_colNames, data, cellWidth, cellAlign);
		rg.createReport(response);

	}

	public void uploadTemplate(HttpServletResponse response, HttpServletRequest request,CandidateDetailsUpload candDetails) {
		String filePath = candDetails.getDataPath() + candDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
		
		Object[][] empId=MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE, columnInformation.get(1));
		Object[][] empFName=MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
		Object[][] empMName=MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
		Object[][] empLName=MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
		Object[][] dob=MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.DATE_TYPE, columnInformation.get(5));
		Object[][] gender=MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.STRING_TYPE, columnInformation.get(6));
		Object[][] maritalStatus=MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.STRING_TYPE, columnInformation.get(7));
		Object[][] expInYear=MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.STRING_TYPE, columnInformation.get(8));
		Object[][] expInMonth=MigrateExcelData.uploadExcelData(9, null, MigrateExcelData.STRING_TYPE, columnInformation.get(9));
		Object[][] curAdd=MigrateExcelData.uploadExcelData(10, null, MigrateExcelData.STRING_TYPE, columnInformation.get(10));
		Object[][] city=MigrateExcelData.uploadExcelData(11, null, MigrateExcelData.STRING_TYPE, columnInformation.get(11));
		Object[][] state=MigrateExcelData.uploadExcelData(12, null, MigrateExcelData.STRING_TYPE, columnInformation.get(12));
		Object[][] country=MigrateExcelData.uploadExcelData(13, null, MigrateExcelData.STRING_TYPE, columnInformation.get(13));
		Object[][] pinCode=MigrateExcelData.uploadExcelData(14, null, MigrateExcelData.STRING_TYPE, columnInformation.get(14));
		Object[][] resPhone=MigrateExcelData.uploadExcelData(15, null, MigrateExcelData.STRING_TYPE, columnInformation.get(15));
		Object[][] mobileNum=MigrateExcelData.uploadExcelData(16, null, MigrateExcelData.STRING_TYPE, columnInformation.get(16));
		Object[][] officePhone=MigrateExcelData.uploadExcelData(17, null, MigrateExcelData.STRING_TYPE, columnInformation.get(17));
		Object[][] emailId=MigrateExcelData.uploadExcelData(18, null, MigrateExcelData.STRING_TYPE, columnInformation.get(18));
		Object[][] currCTC=MigrateExcelData.uploadExcelData(19, null, MigrateExcelData.STRING_TYPE, columnInformation.get(19));
		Object[][] expCTC=MigrateExcelData.uploadExcelData(20, null, MigrateExcelData.STRING_TYPE, columnInformation.get(20));
		Object[][] passPortNum=MigrateExcelData.uploadExcelData(21, null, MigrateExcelData.STRING_TYPE, columnInformation.get(21));
		Object[][] panNum=MigrateExcelData.uploadExcelData(22, null, MigrateExcelData.STRING_TYPE, columnInformation.get(22));
		Object[][] currCompany=MigrateExcelData.uploadExcelData(23, null, MigrateExcelData.STRING_TYPE, columnInformation.get(23));
		Object[][] currPos=MigrateExcelData.uploadExcelData(24, null, MigrateExcelData.STRING_TYPE, columnInformation.get(24));
		Object[][] currLoc=MigrateExcelData.uploadExcelData(25, null, MigrateExcelData.STRING_TYPE, columnInformation.get(25));
		Object[][] otherInfo=MigrateExcelData.uploadExcelData(26, null, MigrateExcelData.STRING_TYPE, columnInformation.get(26));
		Object[][] doYouKnowCurrentlyWork=MigrateExcelData.uploadExcelData(27, null, MigrateExcelData.STRING_TYPE, columnInformation.get(27));
		Object[][] earilyEmployed=MigrateExcelData.uploadExcelData(28, null, MigrateExcelData.STRING_TYPE, columnInformation.get(28));
		Object[][] relocation=MigrateExcelData.uploadExcelData(29, null, MigrateExcelData.STRING_TYPE, columnInformation.get(29));
		
		
		boolean res = MigrateExcelData.isFileToBeUploaded();
		if(res){
			String query = " SELECT NVL(MAX(CAND_CODE),0)+1 FROM  HRMS_REC_CAND_DATABANK ";
			Object[][] autoIdObj =getSqlModel().getSingleResult(query);
			
			String quetyDtlCode="SELECT NVL(MAX(CAND_DTL_CODE),0)+1 FROM HRMS_REC_CD_ADDRESSDTL";
			Object[][] dtlCodeObj= getSqlModel().getSingleResult(quetyDtlCode);
			int dtlCode=Integer.parseInt(checkNull(String.valueOf(dtlCodeObj[0][0])));
			int autoCode =Integer.parseInt(String.valueOf(autoIdObj[0][0]));
			Object[][] data = new Object[empId.length][25];
			Object[][] contactDetails=new Object[empId.length][7];
			Object[][] titleCode=null;
			Object candCode[][]=null;
			for(int i = 0; i < empId.length; i++) {
				try {
					String qery="SELECT TITLE_CODE FROM HRMS_TITLE WHERE  upper(TITLE_NAME)=upper('"+empId[i][0]+"')";
					titleCode= getSqlModel().getSingleResult(qery);
					data[i][0]=checkNull(String.valueOf(titleCode[0][0])); 
				} catch(Exception e) {
					data[i][0] = "";
				}
				try {
					data[i][1] = String.valueOf(empFName[i][0]).trim();
				} catch(Exception e) {
					data[i][1] = "";
				}
				try {
					data[i][2] = String.valueOf(empMName[i][0]).trim();
				} catch(Exception e) {
					data[i][2] = "";
				}
				try {
					data[i][3] = String.valueOf(empLName[i][0]).trim();
				} catch(Exception e) {
					data[i][3] = "";
				}
				try {
					data[i][4] = checkNull(String.valueOf(dob[i][0])).trim();
				} catch(Exception e) {
					data[i][4] = "";
				}
				try {
					data[i][5] = checkNull(String.valueOf(gender[i][0])).trim().toUpperCase();
				} catch(Exception e) {
					data[i][5] = "";
				}
				try {
					data[i][6] = checkNull(String.valueOf(maritalStatus[i][0])).trim().toUpperCase();
				} catch(Exception e) {
					data[i][6] = "";
				}
				try {
					data[i][7] = checkNull(String.valueOf(expInYear[i][0])).trim();
				} catch(Exception e) {
					data[i][7] = "";
				}
				try {
					data[i][8] = checkNull(String.valueOf(expInMonth[i][0])).trim();
				} catch(Exception e) {
					data[i][8] = "";
				}
				
				try {
					data[i][9]= checkNull(String.valueOf(resPhone[i][0])).trim();
				} catch(Exception e) {
					data[i][9] = "";
				}
				try {
					data[i][10] = checkNull(String.valueOf(mobileNum[i][0])).trim();
				} catch(Exception e) {
					data[i][10] = "";
				}
				try {
					data[i][11] = checkNull(String.valueOf(officePhone[i][0])).trim();
				} catch(Exception e) {
					data[i][11] = "";
				}
				try {
					data[i][12] = checkNull(String.valueOf(emailId[i][0])).trim();
				} catch(Exception e) {
					data[i][12] = "";
				}
				try {
					data[i][13] = checkNull(String.valueOf(currCTC[i][0])).trim();
				} catch(Exception e) {
					data[i][13] = "";
				}
				try {
					data[i][14] = checkNull(String.valueOf(expCTC[i][0])).trim();
				} catch(Exception e) {
					data[i][14] = "";
				}
				try {
					data[i][15] = checkNull(String.valueOf(passPortNum[i][0])).trim();
				} catch(Exception e) {
					data[i][15] = "";
				}
				try {
					data[i][16] = checkNull(String.valueOf(panNum[i][0])).trim();
				} catch(Exception e) {
					data[i][16] = "";
				}
				try {
					data[i][17] = checkNull(String.valueOf(currCompany[i][0])).trim();
				} catch(Exception e) {
					data[i][17] = "";
				}
				try {
					data[i][18] = checkNull(String.valueOf(currPos[i][0])).trim();
				} catch(Exception e) {
					data[i][18] = "";
				}
				try {
					data[i][19] = checkNull(String.valueOf(currLoc[i][0])).trim();
				} catch(Exception e) {
					data[i][19] = "";
				}
				try {
					data[i][20] = checkNull(String.valueOf(otherInfo[i][0])).trim();
				} catch(Exception e) {
					data[i][20] = "";
				}
			
				try {
					data[i][21] = checkNull(String.valueOf(doYouKnowCurrentlyWork[i][0])).trim().toUpperCase();
				} catch(Exception e) {
					data[i][21] = "";
				}
				
				try {
					data[i][22] = checkNull(String.valueOf(relocation[i][0])).trim().toUpperCase();
				} catch(Exception e) {
					data[i][22] = "";	
			    }
				try {
					data[i][23] = checkNull(String.valueOf(earilyEmployed[i][0])).trim().toUpperCase();
				} catch(Exception e) {
					data[i][23] = "";	
			    }
				try {
					data[i][24]  =autoCode++;
				} catch(Exception e) {
					data[i][24] = "";	
			    }
				
				
				// Contact Details 
				
				try {
					contactDetails[i][0] = checkNull(String.valueOf(curAdd[i][0])).trim();
				} catch(Exception e) {
					contactDetails[i][0] = "";
				}
				try {
					String qery="SELECT LOCATION_CODE FROM  HRMS_LOCATION WHERE upper(LOCATION_NAME)=upper('"+city[i][0]+"')"; 
					Object[][] strCity=getSqlModel().getSingleResult(qery);
					contactDetails[i][1]=checkNull(String.valueOf(strCity[0][0]));
					System.out.println("1="+contactDetails[i][1]);
				} catch(Exception e) {
					contactDetails[i][1] = "";
				}
				try {
					String qery="SELECT LOCATION_CODE FROM  HRMS_LOCATION WHERE upper(LOCATION_NAME)=upper('"+state[i][0]+"')";
					Object[][] strState= getSqlModel().getSingleResult(qery);
					contactDetails[i][2]=checkNull(String.valueOf(strState[0][0]));
				} catch(Exception e) {
					contactDetails[i][2] = "";
				}
				try {
					String qery="SELECT LOCATION_CODE FROM  HRMS_LOCATION WHERE upper(LOCATION_NAME)=upper('"+country[i][0]+"')";
					Object[][] strCountry= getSqlModel().getSingleResult(qery);
					contactDetails[i][3]=checkNull(String.valueOf(strCountry[0][0]));
				} catch(Exception e) {
					contactDetails[i][3] = "";
				}
				try {
					contactDetails[i][4] = checkNull(String.valueOf(pinCode[i][0])).trim();
				} catch(Exception e) {
					contactDetails[i][4] = "";
				}
				try {
					contactDetails[i][5]=dtlCode++;
				} catch(Exception e) {
					contactDetails[i][5]="";
				}
				try {
					contactDetails[i][6] =checkNull(String.valueOf(data[i][24]));
				} catch(Exception e) {
					contactDetails[i][6] ="";
				}
				
				String insertQuery = "INSERT INTO  HRMS_REC_CAND_DATABANK(CAND_TITLE_CODE, CAND_FIRST_NAME, CAND_MID_NAME, " +
				"CAND_LAST_NAME, CAND_DOB, CAND_GENDER,CAND_MART_STATUS,CAND_EXP_YEAR,CAND_EXP_MONTH,CAND_RES_PHONE,CAND_MOB_PHONE,CAND_OFF_PHONE," +
				"CAND_EMAIL_ID, CAND_CURR_CTC,CAND_EXPC_CTC, CAND_PASSPORT_NO, CAND_PAN_NO,CAND_COMPANY, " +
				"CAND_POSITION, CAND_LOCATION,CAND_OTHER_INFO,CAND_DOYOUKNOW," +
				"CAND_RELOCATION,CAND_EMPLOYED,CAND_CODE,CAND_REF_TYPE,CAND_POSTING_DATE,CAND_SHORT_STATUS)    "+
				"values(?,?,?,?,TO_DATE(?,'MM-DD-YYYY'),DECODE(?,'FEMALE','F','MALE','M','OTHER','O'),DECODE(?,'SINGLE','S','MARRIED','M','DIVORCED','D')," +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
				"DECODE(?,'YES','Y','NO','N')," +
				"DECODE(?,'YES','Y','NO','N')," +
				"DECODE(?,'YES','Y','NO','N'),?,'D'," +
				"(select To_date(TO_DATE(Sysdate,'DD-MM-YYYY')) from dual),'N')";
				boolean result =false;
				try{
					result= getSqlModel().singleExecute(insertQuery, data);	
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				if(result){
					String queryAddDetails="INSERT INTO HRMS_REC_CD_ADDRESSDTL(CAND_ADD, CAND_ADD_CITY,CAND_ADD_STATE, " +
						"CAND_ADD_COUNTRY, CAND_ADD_PINCODE,CAND_DTL_CODE,CAND_CODE,CAND_ADD_TYPE) VALUES(?,?,?,?,?,?,?,'C')";
					result = getSqlModel().singleExecute(queryAddDetails, contactDetails);		
				}
				if(result) {
					candDetails.setStatus("Success");
				} else {
					candDetails.setStatus("Fail");
					candDetails.setNote(" Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data.");
				}
			}//for Closed
			
		} else {
			candDetails.setStatus("Fail");
			candDetails.setNote(" Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		candDetails.setUploadName("Candidate");
	}
}