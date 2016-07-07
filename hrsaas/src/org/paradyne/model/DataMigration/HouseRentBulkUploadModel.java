package org.paradyne.model.DataMigration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class HouseRentBulkUploadModel extends ModelBase{
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HouseRentBulkUploadModel.class);

	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkAmt(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		} else {
			return result;
		}
	}
	
	public void uploadHouseRentDtlTemplate(HttpServletResponse response,
			HttpServletRequest request, EmpDetailsUpload empDetails) {
		
		try {
			String filePath = empDetails.getDataPath() + ""
					+ empDetails.getUploadFileName();
			empDetails.setUploadName("HouseRent");
			//to create  object of the file 
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
			
			Object[][] emIdObj = null;
			Object[][] empIdMaster = getSqlModel().getSingleResult(
					" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC ");
			if (empIdMaster != null && empIdMaster.length > 0) {
				emIdObj = MigrateExcelData.uploadExcelData(1, empIdMaster,
						MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			Object[][] emNameObj = MigrateExcelData.uploadExcelData(2, null,
					MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			Object[][] monthMaster = new Object[12][2];
			monthMaster[0][0] = "1";
			monthMaster[0][1] = "January";
			monthMaster[1][0] = "2";
			monthMaster[1][1] = "February";
			monthMaster[2][0] = "3";
			monthMaster[2][1] = "March";
			monthMaster[3][0] = "4";
			monthMaster[3][1] = "April";
			monthMaster[4][0] = "5";
			monthMaster[4][1] = "May";
			monthMaster[5][0] = "6";
			monthMaster[5][1] = "June";
			monthMaster[6][0] = "7";
			monthMaster[6][1] = "July";
			monthMaster[7][0] = "8";
			monthMaster[7][1] = "August";
			monthMaster[8][0] = "9";
			monthMaster[8][1] = "September";
			monthMaster[9][0] = "10";
			monthMaster[9][1] = "October";
			monthMaster[10][0] = "11";
			monthMaster[10][1] = "November";
			monthMaster[11][0] = "12";
			monthMaster[11][1] = "December";
			Object[][] monObj = MigrateExcelData.uploadExcelData(3,
					monthMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
			Object[][] yeObj = MigrateExcelData.uploadExcelData(4, null,
					MigrateExcelData.YEAR_TYPE, columnInformation.get(4));
			Object[][] amObj = MigrateExcelData.uploadExcelData(5, null,
					MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(5));
			
			Object[][] empIdObj = new Object[emIdObj.length][1];
			Object[][] empNameObj = new Object[emNameObj.length][1];
			Object[][] monthObj = new Object[monObj.length][1];
			Object[][] yearObj = new Object[yeObj.length][1];
			Object[][] amtObj = new Object[amObj.length][1];
			for(int k=0;k<emIdObj.length;k++) {
				try {
					empIdObj[k][0] = String.valueOf(emIdObj[k][0]);
				}catch(Exception e) {
					empIdObj[k][0] = "";
				}
				try {
					empNameObj[k][0] = String.valueOf(emNameObj[k][0]);
				}catch(Exception e) {
					empNameObj[k][0] = "";
				}
				try {
					monthObj[k][0] = String.valueOf(monObj[k][0]);
				}catch(Exception e) {
					monthObj[k][0] = "";
				}
				try {
					yearObj[k][0] = String.valueOf(yeObj[k][0]);
				}catch(Exception e) {
					yearObj[k][0] = "";
				}
				try {
					amtObj[k][0] = String.valueOf(amObj[k][0]);
				}catch(Exception e) {
					amtObj[k][0] = "";
				}
			}
			boolean res = MigrateExcelData.isFileToBeUploaded();
			if (res) {
				Set uniqueRecords = new LinkedHashSet();
				for (int i = 0; i < empIdObj.length; i++) {
					if(!(empIdObj[i][0].equals("")) && !(monthObj[i][0].equals("")) && !(yearObj[i][0].equals(""))){
						String str = String.valueOf(empIdObj[i][0]).trim() + "#"
								+ String.valueOf(yearObj[i][0]).trim();
						logger.info(str);
						uniqueRecords.add(str);
					}else {
						empDetails.setStatus("Fail");
						empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
						return;
					}
				}//End of for
				//Display unique no. of sets
				logger.info("no. of unique sets -- " + uniqueRecords.size());
				if (uniqueRecords.size() > 0) {
					Iterator it = uniqueRecords.iterator();
					String maxQuery = "SELECT NVL(MAX(HRMS_HOUSERENT_HDR.RENT_CODE),0)+1 FROM HRMS_HOUSERENT_HDR";
					Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);
					int rentId = Integer.parseInt(String.valueOf(maxObj[0][0]));
					Object uniqObj[][] = new Object[uniqueRecords.size()][4];
					String eID = "";
					int i = 0;
					while (it.hasNext()) {
						String split[] = it.next().toString().split("#");
						uniqObj[i][0] = "";	//RENT_CODE
						uniqObj[i][1] = split[0];	//empid
						uniqObj[i][2] = split[1];	//FROM year
						uniqObj[i][3] = Integer.parseInt(split[1])+1;	//To year
						eID += split[0]+",";
						i++;
					}
					eID = eID.substring(0, eID.length()-1);
					String selQuery = "SELECT RENT_EMPID,RENT_FROMYEAR,RENT_CODE FROM HRMS_HOUSERENT_HDR " +
						"WHERE RENT_EMPID IN ("+eID+")";
					Object[][] selObj = getSqlModel().getSingleResult(selQuery);
					int existedRecords = 0;
					if(selObj != null & selObj.length > 0) {
						for(int k=0;k<uniqObj.length;k++) {
							for(int t=0;t<selObj.length;t++){
								if(uniqObj[k][1].equals(String.valueOf(selObj[t][0])) && 
										uniqObj[k][2].equals(String.valueOf(selObj[t][1]))) {
									uniqObj[k][0] = selObj[t][2];	//RENT_CODE
									++existedRecords;
									//Update 
								}
							}
						}
					}
					logger.info("no. of employees already existed  == "+existedRecords);
					boolean insHdrRes = false;
					boolean insDtlRes = false;
					boolean updateRes = true;
					//Update dtl table of already existed records
					ArrayList amtList = new ArrayList();
					ArrayList rCodeList =  new ArrayList();
					ArrayList monList = new ArrayList();
					for(int k=0; k<uniqObj.length; k++) {
						if(!(uniqObj[k][0].equals(""))) {
							for(int t=0;t<empIdObj.length;t++){
								if(uniqObj[k][1].equals(empIdObj[t][0]) && uniqObj[k][2].equals(yearObj[t][0])) {
										amtList.add(checkAmt(String.valueOf(amtObj[t][0])));
										rCodeList.add(uniqObj[k][0]);
										monList.add(monthObj[t][0]);
								}
							}
						}
					}
					Object[][] upDtlObj = new Object[amtList.size()][3];
					for(int k=0;k<amtList.size();k++) {
						upDtlObj[k][0] = amtList.get(k);
						upDtlObj[k][1] = rCodeList.get(k);
						upDtlObj[k][2] = monList.get(k);
					}
					String upDtlQuery = " UPDATE HRMS_HOUSERENT_DTL SET RENT_AMOUNT = ? WHERE RENT_CODE = ? AND  RENT_MONTH = ? ";
					updateRes = getSqlModel().singleExecute(upDtlQuery,upDtlObj);
					//insRecords no of records to be inserted in hdr table(non-existing in hdr table)
					int insRecords = uniqueRecords.size() - existedRecords;
					//if we have new records(i.e, not existing in hdr previously), go to insert
					logger.info("insRecords -- "+insRecords);
					if(insRecords > 0){
						//insHdrObj is used to insert data into hdr table
						Object[][] insHdrObj = new Object[insRecords][4];
						//Inserting data into DTL table
						Object[][] insDtlObj = new Object[insRecords*12][3];
						int dtlIndex = 0;
						int hdrIndex = 0;
						for(int k=0;k<uniqObj.length;k++){
							if(uniqObj[k][0].equals("")) {
								insHdrObj[hdrIndex][0] = rentId++;				//RENT_CODE
							//	logger.info("Rent code is -- "+insHdrObj[hdrIndex][0]);
								insHdrObj[hdrIndex][1] = uniqObj[k][1];			//empid
								insHdrObj[hdrIndex][2] = uniqObj[k][2];			//FROM year
								insHdrObj[hdrIndex][3] = uniqObj[k][3];		//To year
								
								String[][] temp = new String[12][2];
								for(int t=0; t<empIdObj.length; t++){
									if(uniqObj[k][1].equals(String.valueOf(empIdObj[t][0])) &&
											uniqObj[k][2].equals(String.valueOf(yearObj[t][0]))) {
										int tt = Integer.parseInt(String.valueOf(monthObj[t][0]))-1;
										temp[tt][0] = String.valueOf(monthObj[t][0]);
										temp[tt][1] = String.valueOf(amtObj[t][0]);
									}
								}//End of for - t
								
								//Placing data into insDtlObj
								for(int m=3; m < temp.length; m++) {
										insDtlObj[dtlIndex][0] = insHdrObj[hdrIndex][0];	//Code
										insDtlObj[dtlIndex][1] = m+1;	//Month
										insDtlObj[dtlIndex++][2] = checkAmt(temp[m][1]);	//Amount
								}
								for(int m=0; m < 3; m++) {
									insDtlObj[dtlIndex][0] = insHdrObj[hdrIndex][0];	//Code
									insDtlObj[dtlIndex][1] = m+1;	//Month
									insDtlObj[dtlIndex++][2] = checkAmt(temp[m][1]);	//Amount
								}
								hdrIndex++;
							}
						}//End of for - k 
						//Inserting data into HDR table
						String insHdrQuery = "INSERT INTO HRMS_HOUSERENT_HDR (RENT_CODE,RENT_EMPID, "
											+"RENT_FROMYEAR,RENT_TOYEAR) VALUES(?,?,?,?)";
						insHdrRes =  getSqlModel().singleExecute(insHdrQuery, insHdrObj);
						logger.info("insHdrRes -- "+insHdrRes);
						if(insHdrRes) {
							String insDtlQuery = "INSERT INTO HRMS_HOUSERENT_DTL (RENT_CODE,RENT_MONTH,RENT_AMOUNT) VALUES(?,?,?)";
							insDtlRes = getSqlModel().singleExecute(insDtlQuery, insDtlObj);
						}
					}
					else {
						logger.info("---------- I did only updation no insertion -------------");
						insDtlRes = true;
					}

					if(updateRes && insDtlRes)
						empDetails.setStatus("Success");
					else{
						empDetails.setStatus("Fail");
						empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
					}	
				}//End of unique -- if
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
			}
		} catch (Exception e) {
			empDetails.setStatus("Fail");
			empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			logger.error("Exception in uploadHouseRentDtlTemplate  model -- "+e);
			e.printStackTrace();
		}
	}//End of uploadHouseRentDtlTemplate method
}
