package org.paradyne.model.ApplicationStudio;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.ApplicationStudio.UploadDatasheet;
import org.paradyne.lib.ModelBase;

public class UploadDatasheetModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger	.getLogger(UploadDatasheetModel.class);

	public boolean generate(UploadDatasheet uploadDatasheet, HttpSession session) {
		boolean result = false;

		String fullFile = context.getRealPath("/") + "pages\\oo\\"+ session.getAttribute("session_pool") + "\\upload\\"+ uploadDatasheet.getUploadFileName();
		logger.info("entry to model---------------"+ session.getAttribute("session_pool"));
		logger.info("fullFile...." + fullFile);
		HSSFWorkbook wb = null;
				
		try {
			InputStream myxls = new FileInputStream(fullFile);
			wb = new HSSFWorkbook(myxls);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	/*	*//****************** Title Master*****************************//*
		
		try {
			Vector vect1 = new Vector();
			HSSFSheet sheet1 = wb.getSheet("Title Master");

			for (int j = 2; j <= sheet1.getLastRowNum(); j++) {

				HSSFRow row = sheet1.getRow(j);
				HSSFCell titleCode = row.getCell((short) 0);
				HSSFCell titleName = row.getCell((short) 1);
				if (!(String.valueOf(titleName.getStringCellValue()).equals(""))) {
					logger.info("..........."+ String.valueOf(titleCode.getNumericCellValue()));
					vect1.add(String.valueOf(titleCode.getNumericCellValue()));
					vect1.add(String.valueOf(titleName.getStringCellValue()));

				}
			}
			int count = 0;
			String[][] obj1 = new String[vect1.size() / 2][2];
			for (int i = 0; i < vect1.size() / 2; i++) {
				obj1[i][0] = (String.valueOf(vect1.get(count++)));
				obj1[i][1] = (String.valueOf(vect1.get(count++)));

			}
			String titleQuery1 = "DELETE FROM  HRMS_TITLE ";
			boolean flag = getSqlModel().singleExecute(titleQuery1);
			if (flag) {
				String hdrQuery = " INSERT INTO HRMS_TITLE(TITLE_CODE,TITLE_NAME )values(?,?)";
				result = getSqlModel().singleExecute(hdrQuery, obj1);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
					

		}
		*/
		/***********************City Master**************************/
		
		/*
		try {
			Vector vect28 = new Vector();
			HSSFSheet sheet28 = wb.getSheet("City Master");

			for (int j = 2; j <= sheet28.getLastRowNum(); j++) {

				HSSFRow row = sheet28.getRow(j);
				HSSFCell cityCode = row.getCell((short) 0);
				HSSFCell cityName = row.getCell((short) 1);
				if (!(String.valueOf(cityName.getStringCellValue()).equals(""))) {
					logger.info("..........."+ String.valueOf(cityCode.getNumericCellValue()));
					vect28.add(String.valueOf(cityCode.getNumericCellValue()));
					vect28.add(String.valueOf(cityName.getStringCellValue()));

				}
			}
			int count = 0;
			String[][] obj28 = new String[vect28.size() / 2][2];
			for (int i = 0; i < vect28.size() / 2; i++) {
				obj28[i][0] = (String.valueOf(vect28.get(count++)));
				obj28[i][1] = (String.valueOf(vect28.get(count++)));

			}
			String cityQuery1 = "DELETE FROM  HRMS_LOCATION ";
			boolean flag = getSqlModel().singleExecute(cityQuery1);
			if (flag) {
				String hdrQuery = " INSERT INTO HRMS_LOCATION(LOCATION_CODE,LOCATION_NAME )values(?,?)";
				result = getSqlModel().singleExecute(hdrQuery, obj28);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//**
		 * ***************************** For Branch Master * *****************************************
		 *//*
		try {
			Vector vect2 = new Vector();
			HSSFSheet sheet2 = wb.getSheet("Branch Master");
			for (int j = 2; j <= sheet2.getLastRowNum(); j++) {

				HSSFRow row = sheet2.getRow(j);
				HSSFCell branchCode = row.getCell((short) 0);
				HSSFCell branchName = row.getCell((short) 1);
				HSSFCell address = row.getCell((short) 2);
				HSSFCell city = row.getCell((short) 3);
				HSSFCell pinCode = row.getCell((short) 4);
				HSSFCell phoneNo = row.getCell((short) 5);
				if (!(String.valueOf(branchName.getStringCellValue()).equals(""))) {

					vect2.add(String.valueOf(branchCode.getNumericCellValue()));
					vect2.add(String.valueOf(branchName.getStringCellValue()));
					if (address != null) {
						vect2.add(String.valueOf(address.getStringCellValue()));
					} else {
						vect2.add("");
					}

					if (city != null) {
						vect2.add(String.valueOf(city.getStringCellValue()));
					} else {
						vect2.add("");
					}

					if (pinCode != null) {
						vect2.add(String.valueOf(pinCode.getNumericCellValue()));
					} 
					else {
						vect2.add("");
					}

					if (phoneNo != null) {
						vect2.add(String.valueOf(phoneNo.getNumericCellValue()));
					} 
					else {
						vect2.add("");
					}
				}

			}
			int count = 0;
			String[][] obj2 = new String[vect2.size() / 6][6];
			logger.info("Size------=-= " + vect2.size());
			for (int i = 0; i < vect2.size() / 6; i++) {
				obj2[i][0] = (String.valueOf(vect2.get(count++)));
				obj2[i][1] = (String.valueOf(vect2.get(count++)));
				obj2[i][2] = (String.valueOf(vect2.get(count++)));
				obj2[i][3] = (String.valueOf(vect2.get(count++)).toUpperCase());
				obj2[i][4] = (String.valueOf(vect2.get(count++)));
				obj2[i][5] = (String.valueOf(vect2.get(count++)));

			}

			String brncQuery = " DELETE  FROM  HRMS_CENTER ";
			boolean flag = getSqlModel().singleExecute(brncQuery);
			if (flag) {
				String query = "INSERT INTO HRMS_CENTER(CENTER_ID,CENTER_NAME,CENTER_ADDRESS1,CENTER_CITY,CENTER_PINCODE,CENTER_TELEPHONE)  VALUES((SELECT NVL(MAX(CENTER_ID),0)+1 FROM HRMS_CENTER),?,?,?,(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE upper(LOCATION_NAME)=?),?,?)";
				result = getSqlModel().singleExecute(query, obj2);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//**
		 * ************************ For Bank * *****************************************
		 *//*
		try {
			Vector vect3 = new Vector();
			HSSFSheet sheet3 = wb.getSheet("Bank Master");

			for (int k = 2; k <= sheet3.getLastRowNum(); k++) {

				HSSFRow row = sheet3.getRow(k);

				HSSFCell micrcode = row.getCell((short) 0);
				HSSFCell bsrcode = row.getCell((short) 1);
				HSSFCell name = row.getCell((short) 2);
				HSSFCell branchcode = row.getCell((short) 3);
				HSSFCell branchname = row.getCell((short) 4);
				HSSFCell city = row.getCell((short) 5);
				HSSFCell address = row.getCell((short) 6);

				if (!(String.valueOf(name.getStringCellValue()).equals(""))) {
					logger.info("..........."+ String.valueOf(micrcode.getNumericCellValue()));
					vect3.add(String.valueOf(micrcode.getNumericCellValue()));
					if (bsrcode != null) {
						vect3.add(String.valueOf(bsrcode.getNumericCellValue()));
					} else {
						vect3.add("");
					}
					vect3.add(String.valueOf(name.getStringCellValue()));
					vect3.add(String.valueOf(branchcode.getNumericCellValue()));
					if (branchname != null) {
						vect3.add(checkNull(String.valueOf(branchname.getStringCellValue())));
					} else {
						vect3.add("");
					}
					if (city != null) {
						vect3.add(String.valueOf(city.getStringCellValue()));
					} else {
						vect3.add("");
					}
					if (address != null) {
						vect3.add(String.valueOf(address.getStringCellValue()));

					} else {
						vect3.add("");
					}

				}
			}
			int count = 0;

			String[][] bankobj = new String[vect3.size() / 7][7];
			for (int i = 0; i < vect3.size() / 7; i++) {
				bankobj[i][0] = (String.valueOf(vect3.get(count++)));
				bankobj[i][1] = (String.valueOf(vect3.get(count++)));
				bankobj[i][2] = (String.valueOf(vect3.get(count++)));
				bankobj[i][3] = (String.valueOf(vect3.get(count++)));
				bankobj[i][4] = (String.valueOf(vect3.get(count++)));
				bankobj[i][5] = (String.valueOf(vect3.get(count++)));
				bankobj[i][6] = (String.valueOf(vect3.get(count++)));

			}
			String bankQuery = "DELETE FROM  HRMS_BANK ";
			boolean flag = getSqlModel().singleExecute(bankQuery);
			if (flag) {
				String bankQuery1 = " INSERT INTO HRMS_BANK(BANK_MICR_CODE,BANK_BSR_CODE,BANK_NAME,BRANCH_CODE,BRANCH_NAME,BRANCH_CITY,BRANCH_ADDRESS )values(?,?,?,?,?,?,?)";

				result = getSqlModel().singleExecute(bankQuery, bankobj);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		*//**
		 * ****************************This is for Division Master************************************
		 *//*
		try {
			Vector vect4 = new Vector();
			HSSFSheet sheet4 = wb.getSheet("Division Master");

			for (int j = 2; j <= sheet4.getLastRowNum(); j++) {

				HSSFRow row = sheet4.getRow(j);
				HSSFCell divisionCode = row.getCell((short) 0);
				HSSFCell divisionName = row.getCell((short) 1);
				HSSFCell divisionDiscription = row.getCell((short) 2);
				HSSFCell address = row.getCell((short) 3);
				HSSFCell city = row.getCell((short) 4);
				HSSFCell pinCode = row.getCell((short) 5);
				HSSFCell telephone = row.getCell((short) 6);
				HSSFCell website = row.getCell((short) 7);
				HSSFCell bank = row.getCell((short) 8);
				HSSFCell accountNo = row.getCell((short) 9);
				HSSFCell professionalTaxRegNo = row.getCell((short) 10);
				HSSFCell panNo = row.getCell((short) 11);
				HSSFCell tanNo = row.getCell((short) 12);
				if (!(String.valueOf(divisionName.getStringCellValue()).equals(""))) {

					vect4.add(String.valueOf(divisionCode.getNumericCellValue()));
					vect4.add(String.valueOf(divisionName.getStringCellValue()));

					if (divisionDiscription != null) {
						vect4.add(String.valueOf(divisionDiscription.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (address != null) {
						vect4.add(String.valueOf(address.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (city != null) {
						vect4.add(String.valueOf(city.getNumericCellValue()));
					} else {
						vect4.add("");
					}
					if (pinCode != null) {
						vect4.add(String.valueOf(pinCode.getNumericCellValue()));
					} else {
						vect4.add("");
					}
					if (telephone != null) {
						vect4.add(String.valueOf(telephone.getNumericCellValue()));
					} else {
						vect4.add("");
					}
					if (website != null) {
						vect4.add(String.valueOf(website.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (bank != null) {
						vect4.add(String.valueOf(bank.getNumericCellValue()));
					} else {
						vect4.add("");
					}
					if (accountNo != null) {
						vect4.add(String.valueOf(accountNo.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (professionalTaxRegNo != null) {
						vect4.add(String.valueOf(professionalTaxRegNo.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (panNo != null) {
						vect4.add(String.valueOf(panNo.getStringCellValue()));
					} else {
						vect4.add("");
					}
					if (tanNo != null) {
						vect4.add(String.valueOf(tanNo.getStringCellValue()));
					} else {
						vect4.add("");
					}
				}
			}
			int count = 0;
			String[][] obj4 = new String[vect4.size() / 13][13];
			logger.info("Size------=-= " + vect4.size());
			for (int i = 0; i < vect4.size() / 13; i++) {

				obj4[i][0] = (String.valueOf(vect4.get(count++)));
				obj4[i][1] = (String.valueOf(vect4.get(count++)));
				obj4[i][2] = (String.valueOf(vect4.get(count++)));
				obj4[i][3] = (String.valueOf(vect4.get(count++)));
				obj4[i][4] = (String.valueOf(vect4.get(count++)).toUpperCase());
				obj4[i][5] = (String.valueOf(vect4.get(count++)));
				obj4[i][6] = (String.valueOf(vect4.get(count++)));
				obj4[i][7] = (String.valueOf(vect4.get(count++)));
				obj4[i][8] = (String.valueOf(vect4.get(count++)));
				obj4[i][9] = (String.valueOf(vect4.get(count++)));
				obj4[i][10] = (String.valueOf(vect4.get(count++)));
				obj4[i][11] = (String.valueOf(vect4.get(count++)));
				obj4[i][12] = (String.valueOf(vect4.get(count++)));

			}
			String divQuery = " DELETE  FROM  HRMS_DIVISION ";
			boolean flag = getSqlModel().singleExecute(divQuery);
			if (flag) {
				String query = "INSERT INTO HRMS_DIVISION(DIV_ID,DIV_NAME,DIV_DESC,DIV_ADDRESS1,DIV_CITY_ID,DIV_PINCODE,DIV_TELEPHONE,DIV_WEBSITE,DIV_BANK,DIV_ACCOUNT_NUMBER,DIV_PTAX_REG,DIV_PANNO,DIV_TANNO)"
						+ "VALUES(32,?,?,?,(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE upper(LOCATION_NAME)=?),?,?,?,(SELECT BANK_MICR_CODE FROM HRMS_BANK WHERE BANK_NAME=?),?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj4);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//**
		 * ********************For the Department Master**********************************************
		 *//*
		try {
			Vector vect5 = new Vector();
			HSSFSheet sheet5 = wb.getSheet("Department Master");

			for (int j = 2; j <= sheet5.getLastRowNum(); j++) {

				HSSFRow row = sheet5.getRow(j);
				HSSFCell departmentCode = row.getCell((short) 0);
				HSSFCell departmentName = row.getCell((short) 1);
				HSSFCell departmentDiscription = row.getCell((short) 2);
				HSSFCell departmentAbbrivation = row.getCell((short) 3);

				if (!(String.valueOf(departmentName.getStringCellValue()).equals(""))) {
					vect5.add(String.valueOf(departmentCode.getNumericCellValue()));
					vect5.add(String.valueOf(departmentName.getStringCellValue()));
					vect5.add(String.valueOf(departmentDiscription.getStringCellValue()));
					vect5.add(String.valueOf(departmentAbbrivation.getStringCellValue()));

				}
			}
			int count = 0;

			String[][] obj5 = new String[vect5.size() / 4][4];
			logger.info("Size------=-= " + vect5.size());
			for (int i = 0; i < vect5.size() / 4; i++) {
				obj5[i][0] = (String.valueOf(vect5.get(count++)));
				obj5[i][1] = (String.valueOf(vect5.get(count++)));
				obj5[i][2] = (String.valueOf(vect5.get(count++)));
				obj5[i][3] = (String.valueOf(vect5.get(count++)));

			}
			String deptWQuery = " DELETE  FROM  HRMS_DEPT ";
			boolean flag = getSqlModel().singleExecute(deptWQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_DEPT(DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_ABBR)"
						+ " VALUES(?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj5);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		*//**
		 * *************************For Shift Master******************************
		 *//*

		try {
			Vector vect6 = new Vector();
			HSSFSheet sheet6 = wb.getSheet("Shift Master");

			for (int j = 2; j <= sheet6.getLastRowNum(); j++) {

				HSSFRow row = sheet6.getRow(j);
				HSSFCell shiftCode = row.getCell((short) 0);
				HSSFCell shiftName = row.getCell((short) 1);
				HSSFCell shiftStartTime = row.getCell((short) 2);
				HSSFCell shiftEndTime = row.getCell((short) 3);
				HSSFCell firstHalfDay = row.getCell((short) 4);
				HSSFCell secondHalfDay = row.getCell((short) 5);
				HSSFCell shiftWorkHours = row.getCell((short) 6);
				if (!(String.valueOf(shiftName.getStringCellValue()).equals(""))) {
					vect6.add(String.valueOf(shiftCode.getNumericCellValue()));
					vect6.add(String.valueOf(shiftName.getStringCellValue()));
					if (!(shiftStartTime.getStringCellValue().equals(""))&& !(shiftStartTime.getStringCellValue() == null)
							&& !shiftStartTime.getStringCellValue().equals("null")) {

						System.out.println("hahha--------"+ shiftStartTime.getStringCellValue());
						vect6.add(String.valueOf(shiftStartTime.getStringCellValue()));
					} else {
						System.out.println("else------------------");
						vect6.add("");
					}

					vect6.add(getBlankIfNull(String.valueOf(shiftEndTime.getStringCellValue())));

					if (firstHalfDay != null) {
						vect6.add(String.valueOf(firstHalfDay.getStringCellValue()));
					} else {
						vect6.add("");
					}

					if (secondHalfDay != null) {
						vect6.add(String.valueOf(secondHalfDay.getStringCellValue()));
					} else {
						vect6.add("");
					}
					if (shiftWorkHours != null) {
						vect6.add(String.valueOf(shiftWorkHours.getStringCellValue()));
					} else {
						vect6.add("");
					}

				}
			}
			int count = 0;

			String[][] obj6 = new String[vect6.size() / 7][7];
			logger.info("Size------=-= " + vect6.size());
			for (int i = 0; i < vect6.size() / 7; i++) {
				obj6[i][0] = (String.valueOf(vect6.get(count++)));
				obj6[i][1] = (String.valueOf(vect6.get(count++)));
				obj6[i][2] = (String.valueOf(vect6.get(count++)));
				obj6[i][3] = (String.valueOf(vect6.get(count++)));
				obj6[i][4] = (String.valueOf(vect6.get(count++)));
				obj6[i][5] = (String.valueOf(vect6.get(count++)));
				obj6[i][6] = (String.valueOf(vect6.get(count++)));

			}
			String shiftQuery = " DELETE  FROM  HRMS_SHIFT ";
			boolean flag = getSqlModel().singleExecute(shiftQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_SHIFT(SHIFT_ID,SHIFT_NAME,SHIFT_START_TIME,SHIFT_END_TIME,SHIFT_FIRST_HALF_DAY,SHIFT_SECOND_HALF_DAY,SHIFT_WORK_HOURS)"
						+ " VALUES(?,?,TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?)";
				result = getSqlModel().singleExecute(query, obj6);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//**
		 * *******************************************Designation Master*****************************
		 *//*

		try {
			Vector vect7 = new Vector();
			HSSFSheet sheet7 = wb.getSheet("Designation Master");
			for (int j = 2; j <= sheet7.getLastRowNum(); j++) {

				HSSFRow row = sheet7.getRow(j);
				HSSFCell designationCode = row.getCell((short) 0);
				HSSFCell designationName = row.getCell((short) 1);
				HSSFCell designationAbbreviation = row.getCell((short) 2);
				HSSFCell designationDescription = row.getCell((short) 3);

				if (!(String.valueOf(designationName.getStringCellValue())
						.equals(""))) {
					vect7.add(String.valueOf(designationCode.getNumericCellValue()));
					vect7.add(String.valueOf(designationName.getStringCellValue()));
							
					if (designationAbbreviation != null) {
						vect7.add(String.valueOf(designationAbbreviation.getStringCellValue()));
					} else {
						vect7.add("");
					}

					if (designationDescription != null) {
						vect7.add(String.valueOf(designationDescription.getStringCellValue()));
					} else {
						vect7.add("");
					}

				}
			}
			int count = 0;

			String[][] obj7 = new String[vect7.size() / 4][4];
			logger.info("Size------=-= " + vect7.size());
			for (int i = 0; i < vect7.size() / 4; i++) {
				obj7[i][0] = (String.valueOf(vect7.get(count++)));
				obj7[i][1] = (String.valueOf(vect7.get(count++)));
				obj7[i][2] = (String.valueOf(vect7.get(count++)));
				obj7[i][3] = (String.valueOf(vect7.get(count++)));

			}
			String desgQuery = " DELETE  FROM  HRMS_RANK ";
			boolean flag = getSqlModel().singleExecute(desgQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_RANK(RANK_ID,RANK_NAME,RANK_ABBR,RANK_DESC)"
						+ " VALUES(?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj7);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//** **************************Pay Bill****************************** *//*
		try {
			Vector vect8 = new Vector();

			HSSFSheet sheet8 = wb.getSheet("Pay Bill  Master");
			for (int k = 2; k <= sheet8.getLastRowNum(); k++) {

				System.out.println("in side for --------");
				HSSFRow row = sheet8.getRow(k);

				HSSFCell paybillcode = row.getCell((short) 0);
				HSSFCell paybillgoup = row.getCell((short) 1);

				if (!(String.valueOf(paybillgoup.getStringCellValue()).equals(""))) {
					logger.info("..........."+ String.valueOf(paybillcode.getNumericCellValue()));
					vect8.add(String.valueOf(paybillcode.getNumericCellValue()));
					vect8.add(String.valueOf(paybillgoup.getStringCellValue()));

				}
			}
			int count = 0;

			String[][] obj8 = new String[vect8.size() / 2][2];
			for (int i = 0; i < vect8.size() / 2; i++) {
				obj8[i][0] = (String.valueOf(vect8.get(count++)));
				obj8[i][1] = (String.valueOf(vect8.get(count++)));

			}

			String paybillQuery = "DELETE FROM  HRMS_PAYBILL";
			boolean flag = getSqlModel().singleExecute(paybillQuery);
			if (flag) {
				String gradeQuery = " INSERT INTO HRMS_PAYBILL(PAYBILL_ID,PAYBILL_GROUP)values(?,?)";

				result = getSqlModel().singleExecute(gradeQuery, obj8);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		*//** ***********************For Grade***************** *//*

		try {
			Vector vect9 = new Vector();
			HSSFSheet sheet9 = wb.getSheet("Grade Master");
			for (int k = 2; k <= sheet9.getLastRowNum(); k++) {

				HSSFRow row = sheet9.getRow(k);

				HSSFCell gradecode = row.getCell((short) 0);
				HSSFCell gradename = row.getCell((short) 1);
				HSSFCell gradedesc = row.getCell((short) 2);
				HSSFCell gradeabbr = row.getCell((short) 3);
				System.out.println("gradedesc------------" + gradedesc);
				// System.out.println("name.getStringCellValue()-----------"+name.getStringCellValue());
				if (!(String.valueOf(gradename.getStringCellValue()).equals(""))) {
					logger.info("..........."+ String.valueOf(gradecode.getNumericCellValue()));
					vect9.add(String.valueOf(gradecode.getNumericCellValue()));

					vect9.add(String.valueOf(gradename.getStringCellValue()));

					if (gradedesc != null) {
						vect9.add(checkNull(String.valueOf(gradedesc.getStringCellValue())));
					} else {

						vect9.add("");
					}
					if (gradeabbr != null) {
						vect9.add(String.valueOf(gradeabbr.getStringCellValue()));
					} else {
						vect9.add("");
					}

				}
			}
			int count = 0;

		
			String[][] obj9 = new String[vect9.size() / 4][4];
			for (int i = 0; i < vect9.size() / 4; i++) {

				obj9[i][0] = (String.valueOf(vect9.get(count++)));
				obj9[i][1] = (String.valueOf(vect9.get(count++)));
				obj9[i][2] = (String.valueOf(vect9.get(count++)));
				obj9[i][3] = (String.valueOf(vect9.get(count++)));

			}

			String gradQuery = "DELETE FROM  HRMS_CADRE ";
			boolean flag = getSqlModel().singleExecute(gradQuery);
			if (flag) {
				String gradeQuery = " INSERT INTO HRMS_CADRE(CADRE_ID,CADRE_NAME,CADRE_DESC,CADRE_ABBR)values(?,?,?,?)";

				result = getSqlModel().singleExecute(gradeQuery, obj9);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		*//** *************************Cast Master*************************** *//*

		try {
			Vector vect10 = new Vector();
			HSSFSheet sheet10 = wb.getSheet("Caste Master");

			logger.info(" get Last Record ++++====" + sheet10.getLastRowNum());
			for (int j = 2; j <= sheet10.getLastRowNum(); j++) {

				HSSFRow row = sheet10.getRow(j);
				HSSFCell castCode = row.getCell((short) 0);
				HSSFCell castName = row.getCell((short) 1);

				if (!(String.valueOf(castName.getStringCellValue()).equals(""))) {
					vect10.add(String.valueOf(castCode.getNumericCellValue()));
					vect10.add(String.valueOf(castName.getStringCellValue()));

				}
			}
			int count = 0;

			String[][] obj10 = new String[vect10.size() / 2][2];
			logger.info("Size------=-= " + vect10.size());
			for (int i = 0; i < vect10.size() / 2; i++) {
				obj10[i][0] = (String.valueOf(vect10.get(count++)));
				obj10[i][1] = (String.valueOf(vect10.get(count++)));

			}
			String castQuery = " DELETE  FROM  HRMS_CAST ";
			boolean flag = getSqlModel().singleExecute(castQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_CAST(CAST_ID,CAST_NAME)"
						+ " VALUES(?,?)";
				result = getSqlModel().singleExecute(query, obj10);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		*//** **********************Cast Category************************** *//*

		try {
			Vector vect11 = new Vector();
			HSSFSheet sheet11 = wb.getSheet("Caste Category");

			for (int j = 2; j <= sheet11.getLastRowNum(); j++) {

				HSSFRow row = sheet11.getRow(j);
				HSSFCell categoryCode = row.getCell((short) 0);
				HSSFCell categoryName = row.getCell((short) 1);

				if (!(String.valueOf(categoryName.getStringCellValue())
						.equals(""))) {
					vect11.add(String.valueOf(categoryCode
							.getNumericCellValue()));
					vect11.add(String
							.valueOf(categoryName.getStringCellValue()));

				}
			}
			int count = 0;

			String[][] obj11 = new String[vect11.size() / 2][2];
			logger.info("Size------=-= " + vect11.size());
			for (int i = 0; i < vect11.size() / 2; i++) {
				obj11[i][0] = (String.valueOf(vect11.get(count++)));
				obj11[i][1] = (String.valueOf(vect11.get(count++)));

			}
			String castcatQuery = " DELETE  FROM  HRMS_CATG ";
			boolean flag = getSqlModel().singleExecute(castcatQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_CATG(CATG_ID,CATG_NAME)"
						+ " VALUES(?,?)";
				result = getSqlModel().singleExecute(query, obj11);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		*//** ***************************Leave Master****************************** *//*
		try {
			Vector vect12 = new Vector();
			HSSFSheet sheet12 = wb.getSheet("Leave Master");

			for (int j = 2; j <= sheet12.getLastRowNum(); j++) {
				HSSFRow row = sheet12.getRow(j);
				HSSFCell leaveCode = row.getCell((short) 0);
				HSSFCell leaveType = row.getCell((short) 1);
				HSSFCell leaveAbbreiavtion = row.getCell((short) 2);
				if (!(String.valueOf(leaveType.getStringCellValue()).equals(""))) {
					vect12.add(String.valueOf(leaveCode.getNumericCellValue()));
					vect12.add(String.valueOf(leaveType.getStringCellValue()));
					if (leaveAbbreiavtion != null) {
						vect12.add(String.valueOf(leaveAbbreiavtion.getStringCellValue()));
					} else {
						vect12.add("");
					}
				}
			}
			int count = 0;

			String[][] obj12 = new String[vect12.size() / 3][3];
			logger.info("Size------=-= " + vect12.size());
			for (int i = 0; i < vect12.size() / 3; i++) {
				obj12[i][0] = (String.valueOf(vect12.get(count++)));
				obj12[i][1] = (String.valueOf(vect12.get(count++)));
				obj12[i][2] = (String.valueOf(vect12.get(count++)));
			}
			String leavQuery = " DELETE  FROM  HRMS_LEAVE ";
			boolean flag = getSqlModel().singleExecute(leavQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_LEAVE(LEAVE_ID,LEAVE_NAME,LEAVE_ABBR)"
						+ " VALUES(?,?,?)";
				result = getSqlModel().singleExecute(query, obj12);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		*//**
		 * ********Qualification Maseter************************************************************
		 *//*

		try {
			Vector vect13 = new Vector();
			HSSFSheet sheet13 = wb.getSheet("Qualification Master");

			for (int j = 2; j <= sheet13.getLastRowNum(); j++) {

				HSSFRow row = sheet13.getRow(j);
				HSSFCell qualificationCode = row.getCell((short) 0);
				HSSFCell qualificationType = row.getCell((short) 1);
				HSSFCell qualificationAbbreiavtion = row.getCell((short) 2);
				HSSFCell qualificationlevel = row.getCell((short) 3);
				HSSFCell qualificationDescription = row.getCell((short) 4);

				if (!(String.valueOf(qualificationType.getStringCellValue()).equals(""))) {
					vect13.add(String.valueOf(qualificationCode.getNumericCellValue()));
					vect13.add(String.valueOf(qualificationType.getStringCellValue()));
					if (qualificationAbbreiavtion != null) {
						vect13.add(String.valueOf(qualificationAbbreiavtion.getStringCellValue()));
					} else {
						vect13.add("");
					}
					if (qualificationlevel != null) {
						if ((String.valueOf(qualificationlevel.getStringCellValue()).equals("UnderGraduate")))

						{
							vect13.add("UG");
						}
						else if ((String.valueOf(qualificationlevel.getStringCellValue()).equals("Diploma"))) {
							vect13.add("DI");
						}
						else if (qualificationlevel.getStringCellValue().equals("Graduate")) {
							vect13.add("DI");
						} 
						else if ((String.valueOf(qualificationlevel.getStringCellValue()).equals("PostGraduate"))) {
							vect13.add("PG");
						}
						else if ((String.valueOf(qualificationlevel.getStringCellValue()).equals("Phd"))) {
							vect13.add("PH");
						} else {
							vect13.add("");
						}
					} else {
						vect13.add("");
					}

					if (qualificationDescription != null) {
						vect13.add(String.valueOf(qualificationDescription.getStringCellValue()));
					} else {
						vect13.add("");
					}
				} else {
					vect13.add("");
				}
			}
			int count = 0;

			String[][] obj13 = new String[vect13.size() / 5][5];
			logger.info("Size------=-= " + vect13.size());
			for (int i = 0; i < vect13.size() / 5; i++) {
				obj13[i][0] = (String.valueOf(vect13.get(count++)));
				obj13[i][1] = (String.valueOf(vect13.get(count++)));
				obj13[i][2] = (String.valueOf(vect13.get(count++)));
				obj13[i][3] = (String.valueOf(vect13.get(count++)));
				obj13[i][4] = (String.valueOf(vect13.get(count++)));
			}
			String quaQuery = " DELETE  FROM  HRMS_QUA ";
			boolean flag = getSqlModel().singleExecute(quaQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_QUA(QUA_ID,QUA_NAME,QUA_ABBR,QUA_LEVEL,QUA_DESC)"
						+ " VALUES(?,?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj13);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		*//** ****************for the Employee type master********************** *//*

		try {
			Vector vect14 = new Vector();
			HSSFSheet sheet14 = wb.getSheet("Employee Type Master");

			for (int j = 2; j <= sheet14.getLastRowNum(); j++) {

				HSSFRow row = sheet14.getRow(j);
				HSSFCell employeeTypeCode = row.getCell((short) 0);
				HSSFCell employeeType = row.getCell((short) 1);
				HSSFCell typeAbbreiavtion = row.getCell((short) 2);

				if (!(String.valueOf(employeeType.getStringCellValue()).equals(""))) {
					vect14.add(String.valueOf(employeeTypeCode.getNumericCellValue()));
					vect14.add(String.valueOf(employeeType.getStringCellValue()));
					if (typeAbbreiavtion != null) {
						vect14.add(String.valueOf(typeAbbreiavtion.getStringCellValue()));
					} else {
						vect14.add("");
					}

				}
			}
			int count = 0;

			String[][] obj14 = new String[vect14.size() / 3][3];
			logger.info("Size------=-= " + vect14.size());
			for (int i = 0; i < vect14.size() / 3; i++) {
				obj14[i][0] = (String.valueOf(vect14.get(count++)));
				obj14[i][1] = (String.valueOf(vect14.get(count++)));
				obj14[i][2] = (String.valueOf(vect14.get(count++)));
			}
			String empTQuery = " DELETE  FROM  HRMS_EMP_TYPE ";
			boolean flag = getSqlModel().singleExecute(empTQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_EMP_TYPE(TYPE_ID,TYPE_NAME,TYPE_ABBR)"
						+ " VALUES(?,?,?)";
				result = getSqlModel().singleExecute(query, obj14);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//** *********************Officeial Details**************** */

		try {

			Vector vect16 = new Vector();
			HSSFSheet sheet16 = wb.getSheet("Official Details");

			for (int j = 2; j <= sheet16.getLastRowNum(); j++) {

				HSSFRow row = sheet16.getRow(j);
				HSSFCell employee = row.getCell((short) 0);
				HSSFCell title = row.getCell((short) 1);
				HSSFCell firstName = row.getCell((short) 2);
				HSSFCell middleName = row.getCell((short) 3);
				HSSFCell lastName = row.getCell((short) 4);
				HSSFCell division = row.getCell((short) 5);
				HSSFCell branch = row.getCell((short) 6);
				HSSFCell department = row.getCell((short) 7);
				HSSFCell designation = row.getCell((short) 8);
				HSSFCell shift = row.getCell((short) 9);
				HSSFCell empType = row.getCell((short) 10);
				HSSFCell payBill = row.getCell((short) 11);
				HSSFCell reportTo = row.getCell((short) 12);
				HSSFCell status = row.getCell((short) 13);
				HSSFCell gender = row.getCell((short) 14);
				HSSFCell DOB = row.getCell((short) 15);
				HSSFCell dateOfJoin = row.getCell((short) 16);
				HSSFCell dateOfLeave = row.getCell((short) 17);

				if (!(String.valueOf(firstName.getStringCellValue()).equals(""))) {
					vect16.add(String.valueOf(employee.getStringCellValue()));
					vect16.add(String.valueOf(firstName.getStringCellValue()));
					if (title != null) {
						vect16.add(String.valueOf(title.getStringCellValue()));
					} else {
						vect16.add("");
					}

					if (middleName != null) {
						vect16.add(String.valueOf(middleName.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (lastName != null) {
						vect16.add(String.valueOf(lastName.getStringCellValue()));
					} else {
						vect16.add("");
					}

					if (division != null) {
						vect16.add(String.valueOf(division.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (branch != null) {
						vect16.add(String.valueOf(branch.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (department != null) {
						vect16.add(String.valueOf(department.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (designation != null) {
						vect16.add(String.valueOf(designation.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (shift != null) {
						vect16.add(String.valueOf(shift.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (empType != null) {
						vect16.add(String.valueOf(empType.getStringCellValue()));
					} else {
						vect16.add("");
					}

					if (payBill != null) {
						vect16.add(String.valueOf(payBill.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (reportTo != null) {
						vect16.add(String.valueOf(reportTo.getStringCellValue()).split(" ")[0]);
					} else {
						vect16.add("");
					}
					if (status != null) {
						if ((String.valueOf(status.getStringCellValue()).equals("Service"))) {

							vect16.add("S");
						} else if ((String.valueOf(status.getStringCellValue()).equals("Retired"))) {
								
							vect16.add("R");
						}
						else if ((String.valueOf(status.getStringCellValue()).equals("Resigend"))) {
								

							vect16.add("N");
						}
						else if ((String.valueOf(status.getStringCellValue()).equals("Terminated"))) {
								

							vect16.add("E");
						}

						else {
							vect16.add("");
						}
					} else {
						vect16.add("");
					}

					if (gender != null) {
						if ((String.valueOf(gender.getStringCellValue())	.equals("Male"))) {
							

							vect16.add("M");
						} else if ((String.valueOf(gender.getStringCellValue()).equals("Female"))) {
								

							vect16.add("F");
						} else if ((String.valueOf(gender.getStringCellValue()).equals("Others"))) {
								

							vect16.add("O");
						} else {
							vect16.add("");
						}
					} else {
						vect16.add("");
					}

					if (DOB != null) {
						vect16.add(String.valueOf(DOB.getStringCellValue()));
					} else {
						vect16.add("");
					}
					if (dateOfJoin != null) {
						vect16.add(String.valueOf(dateOfJoin.getStringCellValue()));
								
					} else {
						vect16.add("");
					}
					if (dateOfLeave != null) {
						vect16.add(String.valueOf(dateOfLeave.getStringCellValue()));
								
					} else {
						vect16.add("");
					}
				} 
				else {
					vect16.add("");
				}

				int count = 0;

				String[][] obj16 = new String[vect16.size() / 18][18];
				Object[][] myObj=new Object[vect16.size()/18][12];
				logger.info("Size--11111----=-= " + vect16.size());
				for (int i = 0; i < vect16.size() / 18; i++) {
					
					obj16[i][0] = (String.valueOf(vect16.get(count++)));
					myObj[i][0]=obj16[i][0];
					
					obj16[i][1] = (String.valueOf(vect16.get(count++)));
					myObj[i][1]=obj16[i][1];
					
					obj16[i][2] = (String.valueOf(vect16.get(count++)));
					myObj[i][2]=obj16[i][2];
					
					obj16[i][3] = (String.valueOf(vect16.get(count++)));
					myObj[i][3]=obj16[i][3];
					
					obj16[i][4] = (String.valueOf(vect16.get(count++)));
					myObj[i][4]=obj16[i][4];
					
					obj16[i][5] = (String.valueOf(vect16.get(count++)));
					obj16[i][6] = (String.valueOf(vect16.get(count++)));
					
					obj16[i][7] = (String.valueOf(vect16.get(count++)));
					myObj[i][5]=obj16[i][7];
					
					obj16[i][8] = (String.valueOf(vect16.get(count++)));
					myObj[i][6]=obj16[i][8];
					
					obj16[i][9] = (String.valueOf(vect16.get(count++)));
					obj16[i][10] = (String.valueOf(vect16.get(count++)));
					myObj[i][7]=obj16[i][10];
					
					obj16[i][11] = (String.valueOf(vect16.get(count++)));
					obj16[i][12] = (String.valueOf(vect16.get(count++)));
					myObj[i][8]=obj16[i][12];
					obj16[i][13] = (String.valueOf(vect16.get(count++)));
					obj16[i][14] = (String.valueOf(vect16.get(count++)));
					myObj[i][9]=obj16[i][14];
					
					obj16[i][15] = (String.valueOf(vect16.get(count++)));
					myObj[i][10]=obj16[i][15];
					
					obj16[i][16] = (String.valueOf(vect16.get(count++)));
					obj16[i][17] = (String.valueOf(vect16.get(count++)));
					myObj[i][11]=obj16[i][17];

				}
				
				for (int i = 0; i < obj16.length; i++) {
					for (int k = 0; k < obj16[0].length; k++) {
						System.out.println("obj16------"+String.valueOf(obj16[i][k]));
					}
					
				}
			//	String officeQuery = " DELETE  FROM  HRMS_EMP_OFFC ";
				//boolean flag = getSqlModel().singleExecute(officeQuery);
				
				/*	String query = "INSERT INTO HRMS_EMP_OFFC(EMP_ID,EMP_TOKEN,EMP_FNAME,EMP_MNAME,EMP_LNAME,EMP_DIV,EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_SHIFT,EMP_TYPE,EMP_PAYBILL,EMP_REPORTING_OFFICER,EMP_STATUS,EMP_GENDER,EMP_DOB,EMP_GRADE,EMP_REGULAR_DATE,EMP_LEAVE_DATE)"
							+ " VALUES((SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_OFFC) ,?,?,?,?,(SELECT DIV_ID FROM HRMS_DIVISION WHERE DIV_NAME=?),(SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_NAME=?),(SELECT DEPT_ID FROM HRMS_DEPT WHERE DEPT_NAME=?),"
							+ "(SELECT RANK_ID FROM HRMS_RANK WHERE upper(RANK_NAME)=upper(?)),(SELECT SHIFT_ID FROM HRMS_SHIFT WHERE SHIFT_NAME =?),(SELECT TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_NAME =?),(SELECT PAYBILL_ID FROM HRMS_PAYBILL WHERE PAYBILL_GROUP =?),(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'))";
					result = getSqlModel().singleExecute(query, obj16);
					*/
				String myQuery=" INSERT INTO HRMS_EMP_OFFC(EMP_ID,EMP_TOKEN,EMP_FNAME,EMP_TITLE_CODE,EMP_MNAME,EMP_LNAME,EMP_DIV, "
						+" EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_SHIFT,EMP_TYPE,EMP_PAYBILL,EMP_REPORTING_OFFICER, "
						+" EMP_STATUS,EMP_GENDER,EMP_DOB,EMP_GRADE,EMP_REGULAR_DATE) "
						+" VALUES((SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_OFFC) ,?,?,(SELECT TITLE_CODE FROM HRMS_TITLE WHERE UPPER(TITLE_NAME)=UPPER(?)),"
						+" ?,?,1,1,(SELECT DEPT_ID FROM HRMS_DEPT WHERE UPPER(DEPT_NAME)=UPPER(?)),(SELECT RANK_ID FROM HRMS_RANK WHERE UPPER(RANK_NAME)=UPPER(?)),"
						+" 1,(SELECT TYPE_ID FROM HRMS_EMP_TYPE WHERE UPPER(TYPE_NAME) =UPPER(?)),null,"
						+" (SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),'S',?,TO_DATE(?,'DD.MM.YYYY'),null,TO_DATE(?,'DD.MM.YYYY'))";
				result = getSqlModel().singleExecute(myQuery, myObj);
					return result;
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		/** ************Personal Details******************************* *//*

		try {
			Vector vect18 = new Vector();
			HSSFSheet sheet18 = wb.getSheet("Pearsonal Details");

			logger.info("====  getLast Row--====== " + sheet18.getLastRowNum());

			for (int m = 2; m <= sheet18.getLastRowNum(); m++) {
				HSSFRow row1 = sheet18.getRow(m);
				HSSFCell nationality = row1.getCell((short) 18);
				HSSFCell bloodgroup = row1.getCell((short) 19);
				HSSFCell religion = row1.getCell((short) 20);
				HSSFCell castCategory = row1.getCell((short) 21);
				HSSFCell cast = row1.getCell((short) 22);
				HSSFCell subcast = row1.getCell((short) 23);
				HSSFCell weight = row1.getCell((short) 24);
				HSSFCell height = row1.getCell((short) 25);
				HSSFCell isHandicap = row1.getCell((short) 26);
				HSSFCell handicapDesc = row1.getCell((short) 27);
				HSSFCell maritalStatus = row1.getCell((short) 28);
				HSSFCell marriageDate = row1.getCell((short) 29);
				HSSFCell hobbies = row1.getCell((short) 30);
				HSSFCell idMark = row1.getCell((short) 31);
				HSSFCell passPortNo = row1.getCell((short) 32);
				HSSFCell passportExpDate = row1.getCell((short) 33);
				HSSFCell homeTown = row1.getCell((short) 34);

				if (nationality != null) {
					vect18
							.add(String.valueOf(nationality
									.getStringCellValue()));
				} else {
					vect18.add("");
				}

				if (bloodgroup != null) {

					if ((String.valueOf(bloodgroup.getStringCellValue()).equals("A+"))) {
						
						vect18.add("A+");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("A-"))) {
							
						vect18.add("A-");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("AB+"))) {
							

						vect18.add("AB+");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("AB-"))) {
							

						vect18.add("AB-");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("B+"))) {
							

						vect18.add("B+");
					} else if ((String.valueOf(bloodgroup.getStringCellValue())	.equals("B-"))) {
						

						vect18.add("B-");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("O+"))) {
							

						vect18.add("O+");
					} else if ((String.valueOf(bloodgroup.getStringCellValue()).equals("O-"))) {
							

						vect18.add("O-");
					} else {
						vect18.add("");
					}
				} else {
					vect18.add("");
				}

				if (religion != null) {
					vect18.add(String.valueOf(religion.getStringCellValue()));
				} else {
					vect18.add("");
				}

				if (castCategory != null) {
					vect18.add(String.valueOf(castCategory.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (cast != null) {
					vect18.add(String.valueOf(cast.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (subcast != null) {
					vect18.add(String.valueOf(subcast.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (weight != null) {
					vect18.add(String.valueOf(weight.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (height != null) {
					vect18.add(String.valueOf(weight.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (isHandicap != null) {
					if ((String.valueOf(isHandicap.getStringCellValue()).equals("Yes"))) {
						vect18.add("Y");
					} else if ((String.valueOf(isHandicap.getStringCellValue()).equals("No"))) {
						vect18.add("N");
					} else {
						vect18.add("");
					}
				} else {
					vect18.add("");
				}
				if (handicapDesc != null) {
					vect18.add(String.valueOf(handicapDesc.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (handicapDesc != null) {
					vect18.add(String.valueOf(handicapDesc.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (maritalStatus != null) {
					if ((String.valueOf(maritalStatus.getStringCellValue()).equals("Widower"))) {
						vect18.add("A");
					} else if ((String.valueOf(maritalStatus.getStringCellValue()).equals("Divorce"))) {
						vect18.add("D");
					} else if ((String.valueOf(maritalStatus.getStringCellValue()).equals("Married"))) {
						vect18.add("M");
					} else if ((String.valueOf(maritalStatus.getStringCellValue()).equals("UnMarried"))) {
						vect18.add("U");
					} else if ((String.valueOf(maritalStatus.getStringCellValue()).equals("Widow"))) {
						vect18.add("W");
					} else {
						vect18.add("");
					}
				}

				else {
					vect18.add("");
				}

				if (marriageDate != null) {
					vect18.add(String.valueOf(marriageDate.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (hobbies != null) {
					vect18.add(String.valueOf(hobbies.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (idMark != null) {
					vect18.add(String.valueOf(idMark.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (passPortNo != null) {
					vect18.add(String.valueOf(passPortNo.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (passportExpDate != null) {
					vect18.add(String.valueOf(passportExpDate.getStringCellValue()));
				} else {
					vect18.add("");
				}
				if (homeTown != null) {
					vect18.add(String.valueOf(homeTown.getStringCellValue()));
				}
				else {
					vect18.add("");
				}
			}
				int count1 = 0;

				String[][] obj18 = new String[vect18.size() / 17][17];
				logger.info("Size--222222----=-= " + vect18.size());
				for (int k = 0; k < vect18.size() / 17; k++) {
					obj18[k][0] = (String.valueOf(vect18.get(count1++)));
					obj18[k][1] = (String.valueOf(vect18.get(count1++)));
					obj18[k][2] = (String.valueOf(vect18.get(count1++)));
					obj18[k][3] = (String.valueOf(vect18.get(count1++)));
					obj18[k][4] = (String.valueOf(vect18.get(count1++)));
					obj18[k][5] = (String.valueOf(vect18.get(count1++)));
					obj18[k][6] = (String.valueOf(vect18.get(count1++)));
					obj18[k][7] = (String.valueOf(vect18.get(count1++)));
					obj18[k][8] = (String.valueOf(vect18.get(count1++)));
					obj18[k][9] = (String.valueOf(vect18.get(count1++)));
					obj18[k][10] = (String.valueOf(vect18.get(count1++)));
					obj18[k][11] = (String.valueOf(vect18.get(count1++)));
					obj18[k][12] = (String.valueOf(vect18.get(count1++)));
					obj18[k][13] = (String.valueOf(vect18.get(count1++)));
					obj18[k][14] = (String.valueOf(vect18.get(count1++)));
					obj18[k][15] = (String.valueOf(vect18.get(count1++)));
					obj18[k][16] = (String.valueOf(vect18.get(count1++))
							.toUpperCase());

				}
				String personalQuery1 = " DELETE  FROM  HRMS_EMP_PERS ";
				boolean flag2 = getSqlModel().singleExecute(personalQuery1);
				if (flag2) {
					String query2 = "INSERT INTO HRMS_EMP_PERS(EMP_ID,EMP_NATIONALITY,EMP_BLDGP,EMP_RELIGION,EMP_CASTE_CATG,EMP_CASTE,EMP_SUBCAST,EMP_WEIGHT,EMP_HEIGHT,EMP_ISHANDICAP,EMP_HANDI_DESC,EMP_MARITAL_STATUS,EMP_MARRG_DATE,EMP_HOBBY,EMP_IDMARK,EMP_PASSPORT,EMP_PASSPORT_EXPDATE,EMP_HOMETOWN) "
							+ " VALUES((SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_PERS),?,?,?,(SELECT CATG_ID FROM HRMS_CATG WHERE CATG_NAME=?),(SELECT CAST_ID FROM HRMS_CAST WHERE CAST_NAME=?),?,?,?,?,?,?,?,?,?,?,?,(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE upper(LOCATION_NAME)=?))";
					result = getSqlModel().singleExecute(query2, obj18);
					return result;
				}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		*//**
		 * *****************Salary * Details**********************************
		 *//*

		try {
			Vector vect19 = new Vector();

			HSSFSheet sheet19 = wb.getSheet("Salary Details");

			for (int n = 2; n <= sheet19.getLastRowNum(); n++) {
				HSSFRow row2 = sheet19.getRow(n);
				HSSFCell PFNumber = row2.getCell((short) 35);
				HSSFCell PanNo = row2.getCell((short) 36);
				HSSFCell salaryACNo = row2.getCell((short) 37);
				HSSFCell salaryBank = row2.getCell((short) 38);
				HSSFCell reimbursmentACNo = row2.getCell((short) 39);
				HSSFCell reimbursmentBank = row2.getCell((short) 40);
				HSSFCell pensionable = row2.getCell((short) 41);
				HSSFCell paymode = row2.getCell((short) 42);
				HSSFCell ESICNO = row2.getCell((short) 43);

				if (PFNumber != null) {
					vect19.add(String.valueOf(PFNumber.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (PanNo != null) {
					vect19.add(String.valueOf(PanNo.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (salaryACNo != null) {
					vect19.add(String.valueOf(salaryACNo.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (salaryBank != null) {
					vect19.add(String.valueOf(salaryBank.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (reimbursmentACNo != null) {
					vect19.add(String.valueOf(reimbursmentACNo.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (reimbursmentBank != null) {
					vect19.add(String.valueOf(reimbursmentBank.getStringCellValue()));
				} else {
					vect19.add("");
				}
				if (pensionable != null) {
					if ((String.valueOf(pensionable.getStringCellValue()).equals("Yes"))) {
						vect19.add("Y");
					} else if ((String.valueOf(pensionable.getStringCellValue()).equals("No"))) {
						vect19.add("N");
					} else {
						vect19.add("");
					}
				} else {
					vect19.add("");
				}
				if (paymode != null) {
					if ((String.valueOf(paymode.getStringCellValue()).equals("Cash"))) {
						vect19.add("C");
					} else if ((String.valueOf(paymode.getStringCellValue()).equals("Cheque"))) {
						vect19.add("H");
					} else if ((String.valueOf(paymode.getStringCellValue()).equals("Transfer"))) {
						vect19.add("T");
					} else {
						vect19.add("");
					}
				} else {
					vect19.add("");
				}

				if (ESICNO != null) {
					vect19.add(String.valueOf(ESICNO.getStringCellValue()));
				} else {
					vect19.add("");
				}
			}
				int count2 = 0;

				String[][] obj19 = new String[vect19.size() / 9][9];
				logger.info("Size---333333---=-= " + vect19.size());
				for (int l = 0; l < vect19.size() / 9; l++) {
					obj19[l][0] = (String.valueOf(vect19.get(count2++)));
					obj19[l][1] = (String.valueOf(vect19.get(count2++)));
					obj19[l][2] = (String.valueOf(vect19.get(count2++)));
					obj19[l][3] = (String.valueOf(vect19.get(count2++)));
					obj19[l][4] = (String.valueOf(vect19.get(count2++)));
					obj19[l][5] = (String.valueOf(vect19.get(count2++)));
					obj19[l][6] = (String.valueOf(vect19.get(count2++)));
					obj19[l][7] = (String.valueOf(vect19.get(count2++)));
					obj19[l][8] = (String.valueOf(vect19.get(count2++)));
				}
				String salQuery = " DELETE  FROM  HRMS_SALARY_MISC ";
				boolean flag3 = getSqlModel().singleExecute(salQuery);
				if (flag3) {
					String query4 = "INSERT INTO HRMS_SALARY_MISC(EMP_ID,SAL_GPFNO,SAL_PANNO,SAL_ACCNO_REGULAR,SAL_MICR_REGULAR,SAL_MICR_PENSION,SAL_REIMBANK,SAL_PENSIONABLE,SAL_PAY_MODE,SAL_ESCINUMBER)"
							+ " values((SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_SALARY_MISC),?,?,(SELECT BANK_MICR_CODE FROM HRMS_BANK WHERE BANK_NAME=?),?,?,(SELECT BANK_MICR_CODE FROM HRMS_BANK WHERE BANK_NAME=?),?,?,?)";
					result = getSqlModel().singleExecute(query4, obj19);
					return result;

				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		*//*****************Family Details***************//*

		try {
			Vector vect20 = new Vector();
			HSSFSheet sheet20 = wb.getSheet("Family Details");
			for (int j = 2; j <= sheet20.getLastRowNum(); j++) {

				HSSFRow row = sheet20.getRow(j);
				HSSFCell employee = row.getCell((short) 0);
				HSSFCell firstName = row.getCell((short) 1);
				HSSFCell middleName = row.getCell((short) 2);
				HSSFCell lastName = row.getCell((short) 3);
				HSSFCell relation = row.getCell((short) 4);
				HSSFCell maritialStatus = row.getCell((short) 5);
				HSSFCell gender = row.getCell((short) 6);
				HSSFCell email = row.getCell((short) 7);
				HSSFCell phone = row.getCell((short) 8);
				HSSFCell DOB = row.getCell((short) 9);
				HSSFCell profession = row.getCell((short) 10);
				HSSFCell address = row.getCell((short) 11);
				HSSFCell identification = row.getCell((short) 12);
				HSSFCell otherInfo = row.getCell((short) 13);

				if (!(String.valueOf(employee.getStringCellValue()).equals(""))) {
					vect20.add(String.valueOf(employee.getStringCellValue()).split(" ")[0]);
					if (firstName != null) {
						vect20.add(String.valueOf(firstName	.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (middleName != null) {
						vect20.add(String.valueOf(middleName.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (lastName != null) {
						vect20.add(String.valueOf(lastName.getStringCellValue()));
					} else {
						vect20.add("");
					}

					if (relation != null) {
						vect20.add(String.valueOf(relation.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (maritialStatus != null) {

						if ((String.valueOf(maritialStatus.getStringCellValue()).equals("Unmarried"))) {
							vect20.add("U");
						} else if ((String.valueOf(maritialStatus.getStringCellValue()).equals("Widower"))) {
							vect20.add("A");
						} else if ((String.valueOf(maritialStatus.getStringCellValue()).equals("Divorce"))) {
								
							vect20.add("D");
						} else if ((String.valueOf(maritialStatus.getStringCellValue()).equals("Married"))) {
								
							vect20.add("M");
						} else if ((String.valueOf(maritialStatus.getStringCellValue()).equals("Widow"))) {
								
							vect20.add("W");
						} else {
							vect20.add("");
						}
					} else {
						vect20.add("");
					}

					if (gender != null) {
						if ((String.valueOf(gender.getStringCellValue()).equals("Male"))) {
								

							vect20.add("M");
						} 
						else if ((String.valueOf(gender.getStringCellValue()).equals("Female"))) {
							vect20.add("F");
						}
						else if ((String.valueOf(gender.getStringCellValue()).equals("Others"))) {
								

							vect20.add("O");

						} else {
							vect20.add("");
						}
					} else {
						vect20.add("");
					}
					if (email != null) {
						vect20.add(String.valueOf(email.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (phone != null) {
						vect20.add(String.valueOf(phone.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (DOB != null) {
						vect20.add(String.valueOf(DOB.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (profession != null) {
						vect20.add(String.valueOf(profession.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (address != null) {
						vect20.add(String.valueOf(address.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (identification != null) {
						vect20.add(String.valueOf(identification.getStringCellValue()));
					} else {
						vect20.add("");
					}
					if (otherInfo != null) {
						vect20.add(String.valueOf(otherInfo.getStringCellValue()));
					} else {
						vect20.add("");
					}
				}
			}
			int count = 0;

			String[][] obj20 = new String[vect20.size() / 14][14];
			logger.info("Size------=-= " + vect20.size());
			for (int i = 0; i < vect20.size() / 14; i++) {
				obj20[i][0] = (String.valueOf(vect20.get(count++)));
				obj20[i][1] = (String.valueOf(vect20.get(count++)));
				obj20[i][2] = (String.valueOf(vect20.get(count++)));
				obj20[i][3] = (String.valueOf(vect20.get(count++)));
				obj20[i][4] = (String.valueOf(vect20.get(count++)));
				obj20[i][5] = (String.valueOf(vect20.get(count++)));
				obj20[i][6] = (String.valueOf(vect20.get(count++)));
				obj20[i][7] = (String.valueOf(vect20.get(count++)));
				obj20[i][8] = (String.valueOf(vect20.get(count++)));
				obj20[i][9] = (String.valueOf(vect20.get(count++)));
				obj20[i][10] = (String.valueOf(vect20.get(count++)));
				obj20[i][11] = (String.valueOf(vect20.get(count++)));
				obj20[i][12] = (String.valueOf(vect20.get(count++)));
				obj20[i][13] = (String.valueOf(vect20.get(count++)));
			}
			String fmlyQuery = " DELETE FROM HRMS_EMP_FML ";
			boolean flag = getSqlModel().singleExecute(fmlyQuery);
			if (flag) {
				String query = "INSERT INTO HRMS_EMP_FML(FML_ID,EMP_ID,FML_FNAME,FML_MNAME,FML_LNAME,FML_RELATION,FML_MARITAL_STATUS,FML_GENDER,FML_EMAIL,FML_PH,FML_DOB,FML_PROFESSION,FML_ADDRESS,FML_IDMARK,FML_OTHERINFO)"
						+ " VALUES((SELECT NVL(MAX(FML_ID),0)+1 FROM HRMS_EMP_FML ),(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,?,?,?,?,?,?,?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj20);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		/*
		*//***********************Credit Master**********************//*
		try {
			Vector vect21 = new Vector();
			HSSFSheet sheet21 = wb.getSheet("Credit Master");
			for (int k = 2; k <= sheet21.getLastRowNum(); k++) {

				HSSFRow row = sheet21.getRow(k);

				HSSFCell creditcode = row.getCell((short) 0);
				HSSFCell creditname = row.getCell((short) 1);
				HSSFCell creditabbr = row.getCell((short) 2);
				HSSFCell paidsal = row.getCell((short) 3);
				HSSFCell arrear = row.getCell((short) 4);
				HSSFCell credittaxable = row.getCell((short) 5);
				HSSFCell creditperiod = row.getCell((short) 6);
				HSSFCell creditpriority = row.getCell((short) 7);
				if (!(String.valueOf(creditname.getStringCellValue()).equals(""))) {

					vect21.add(String.valueOf(creditname.getStringCellValue()));

					if (creditcode != null) {
						vect21.add(String.valueOf(creditcode.getNumericCellValue()));
					} else {
						vect21.add("");
					}

					if (creditabbr != null) {
						vect21.add(String.valueOf(creditabbr.getStringCellValue()));
					} else {
						vect21.add("");
					}
					if (paidsal != null) {
						if ((String.valueOf(paidsal.getStringCellValue()).equals("Yes"))) {
							vect21.add("Y");
						}

						else if ((String.valueOf(paidsal.getStringCellValue()).equals("No"))) {
							vect21.add("N");
						}

						else {
							vect21.add("");
						}
					} else {
						vect21.add("");
					}
					if (arrear != null) {
						if ((String.valueOf(arrear.getStringCellValue()).equals("Yes"))) {
							vect21.add("Y");
						}

						else if ((String.valueOf(arrear.getStringCellValue()).equals("No"))) {
							vect21.add("N");
						}

						else {
							vect21.add("");
						}
					} else {
						vect21.add("");
					}

					if (credittaxable != null) {
						if ((String.valueOf(credittaxable.getStringCellValue()).equals("Yes"))) {
							vect21.add("Y");
						}

						else if ((String.valueOf(credittaxable.getStringCellValue()).equals("No"))) {
							vect21.add("N");
						} else {
							vect21.add("");
						}
					} else {
						vect21.add("");
					}
				}

				if (creditperiod != null) {
					vect21.add(String.valueOf(creditperiod.getStringCellValue()));
				} else {
					vect21.add("");
				}
				if (creditpriority != null) {
					String creditprior = "";
					if (creditpriority.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						creditprior = String.valueOf(creditpriority.getNumericCellValue());
						//logger.info("Employee Code in if------"+add[c][0]);
					} else if (creditpriority.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						creditprior = creditpriority.getStringCellValue();
						//logger.info("Employee Code in else if------"+add[c][0]);
					}
					vect21.add(creditprior);
				} else {
					vect21.add("");
				}
			}

			int count = 0;

			//System.out.println("vect.size()uuuuuuuuu"+vect.size());
			String[][] obj21 = new String[vect21.size() / 8][8];
			for (int i = 0; i < vect21.size() / 8; i++) {
				System.out.println("entry to for loop------------------");
				obj21[i][0] = (String.valueOf(vect21.get(count++)));
				obj21[i][1] = (String.valueOf(vect21.get(count++)));
				obj21[i][2] = (String.valueOf(vect21.get(count++)));
				obj21[i][3] = (String.valueOf(vect21.get(count++)));
				obj21[i][4] = (String.valueOf(vect21.get(count++)));
				obj21[i][5] = (String.valueOf(vect21.get(count++)));
				obj21[i][6] = (String.valueOf(vect21.get(count++)));
				obj21[i][7] = (String.valueOf(vect21.get(count++)));
			}

			String credQuery = "DELETE FROM HRMS_CREDIT_HEAD";
			boolean flag = getSqlModel().singleExecute(credQuery);
			if (flag) {
				String creditQuery = " INSERT INTO HRMS_CREDIT_HEAD(CREDIT_CODE, CREDIT_NAME, CREDIT_ABBR,CREDIT_PAYFLAG, CREDIT_APPLICABLE_ARREARS, CREDIT_TAXABLE_FLAG,CREDIT_PERIODICITY,CREDIT_PRIORITY)"
						+ "values(?,?,?,?,?,?,?,?)";

				result = getSqlModel().singleExecute(creditQuery, obj21);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		
		/**************Employeee Credit Configuration***********//*

		try {
			Vector vect22 = new Vector();
			HSSFSheet sheet22 = wb.getSheet("Employee Credit Configuration");

			for (int j = 2; j <= sheet22.getLastRowNum(); j++) {

				HSSFRow row = sheet22.getRow(j);
				HSSFCell employee = row.getCell((short) 0);
				HSSFCell salaryHeader = row.getCell((short) 1);
				HSSFCell amount = row.getCell((short) 2);

				if (!(String.valueOf(employee.getStringCellValue()).equals(""))) {
					vect22.add(String.valueOf(employee.getStringCellValue().split(" ")[0]));
					if (salaryHeader != null) {
						vect22.add(String.valueOf(salaryHeader.getStringCellValue()));
					} else {
						vect22.add("");
					}
					if (amount != null) {
						vect22.add(String.valueOf(amount.getStringCellValue()));
					} else {
						vect22.add("");
					}
				}
			}
			int count = 0;

			String[][] obj22 = new String[vect22.size() / 3][3];
			logger.info("Size------=-= " + vect22.size());
			for (int i = 0; i < vect22.size() / 3; i++) {
				obj22[i][0] = (String.valueOf(vect22.get(count++)));
				obj22[i][1] = (String.valueOf(vect22.get(count++)));
				obj22[i][2] = (String.valueOf(vect22.get(count++)));
			}
			String empCreQuery = "	 DELETE FROM HRMS_EMP_CREDIT ";
			boolean flag = getSqlModel().singleExecute(empCreQuery);
			if (flag) {

				String query = "INSERT INTO HRMS_EMP_CREDIT(EMP_ID,CREDIT_CODE,CREDIT_AMT)"
						+ " VALUES((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),(SELECT CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE upper(CREDIT_NAME)=?),?)";
				result = getSqlModel().singleExecute(query, obj22);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		/**************************Qualification Details**********************************//*
		try {
			Vector vect23 = new Vector();

			HSSFSheet sheet23 = wb.getSheet("Qualification Details");
			for (int j = 2; j <= sheet23.getLastRowNum(); j++) {

				HSSFRow row = sheet23.getRow(j);
				HSSFCell employee = row.getCell((short) 0);
				HSSFCell qualification = row.getCell((short) 1);
				HSSFCell institute = row.getCell((short) 2);
				HSSFCell university = row.getCell((short) 3);
				HSSFCell dateofPassing = row.getCell((short) 4);
				HSSFCell grade = row.getCell((short) 5);
				HSSFCell percentage = row.getCell((short) 6);
				HSSFCell isTechnicale = row.getCell((short) 7);
				logger.info("==== getLastRow--====== "
						+ String.valueOf(employee.getStringCellValue()));
				if (!(String.valueOf(employee.getStringCellValue()).equals(""))) {
					vect23.add(String.valueOf(employee.getStringCellValue()).split(" ")[0]);
					if (qualification != null) {
						vect23.add(String.valueOf(qualification.getStringCellValue()));
					} else {
						vect23.add("");
					}
					if (institute != null) {
						vect23.add(String.valueOf(institute.getStringCellValue()));
					} else {
						vect23.add("");
					}
					if (university != null) {
						vect23.add(String.valueOf(university.getStringCellValue()));
					} else {
						vect23.add("");
					}

					if (dateofPassing != null) {
						vect23.add(String.valueOf(dateofPassing.getStringCellValue()));
					} else {
						vect23.add("");
					}
					if (grade != null) {
						vect23.add(String.valueOf(grade.getStringCellValue()));
					} else {
						vect23.add("");
					}
					if (percentage != null) {
						vect23.add(String.valueOf(percentage.getStringCellValue()));
					} else {
						vect23.add("");
					}
					if (isTechnicale != null) {
						vect23.add(String.valueOf(isTechnicale.getStringCellValue()));
					} else {
						vect23.add("");
					}
				}
			}
			int count = 0;

			String[][] obj23 = new String[vect23.size() / 8][8];
			logger.info("Size------=-=	 " + vect23.size());
			for (int i = 0; i < vect23.size() / 8; i++) {
				obj23[i][0] = (String.valueOf(vect23.get(count++)));
				obj23[i][1] = (String.valueOf(vect23.get(count++)));
				obj23[i][2] = (String.valueOf(vect23.get(count++)));
				obj23[i][3] = (String.valueOf(vect23.get(count++)));
				obj23[i][4] = (String.valueOf(vect23.get(count++)));
				obj23[i][5] = (String.valueOf(vect23.get(count++)));
				obj23[i][6] = (String.valueOf(vect23.get(count++)));
				obj23[i][7] = (String.valueOf(vect23.get(count++)));
			}
			String quaDtlQuery = " DELETE FROM HRMS_EMP_QUA ";
			boolean flag = getSqlModel().singleExecute(quaDtlQuery);
			if (flag) {
				String query = "INSERT INTO HRMS_EMP_QUA(EMP_ID,QUA_ID,QUA_INST,QUA_UNIV,QUA_YEAR,QUA_GRD,QUA_PER,QUA_ISTECH)"
						+ " VALUES((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),(SELECT	 QUA_ID FROM HRMS_QUA WHERE QUA_NAME=?),?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj23);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*//************Employee Experience*************//*

		try {
			Vector vect24 = new Vector();
			HSSFSheet sheet24 = wb.getSheet("Employee Experience");
			for (int j = 2; j <= sheet24.getLastRowNum(); j++) {

				HSSFRow row = sheet24.getRow(j);
				HSSFCell employee = row.getCell((short) 0);
				HSSFCell previousUnitEmployee = row.getCell((short) 1);
				HSSFCell jobDescription = row.getCell((short) 2);
				HSSFCell designation = row.getCell((short) 3);
				HSSFCell joiningDate = row.getCell((short) 4);
				HSSFCell relievingDate = row.getCell((short) 5);
				HSSFCell employeeLastSalary = row.getCell((short) 6);
				HSSFCell CTCinLac = row.getCell((short) 7);

				if (!(String.valueOf(employee.getStringCellValue()).equals(""))) {
					vect24.add(String.valueOf(employee.getStringCellValue()).split(" ")[0]);
					if (previousUnitEmployee != null) {
						vect24.add(String.valueOf(previousUnitEmployee.getStringCellValue()));
					} else {
						vect24.add("");
					}
					if (jobDescription != null) {
						vect24.add(String.valueOf(jobDescription.getStringCellValue()));
					} else {
						vect24.add("");
					}
					if (designation != null) {
						vect24.add(String.valueOf(designation.getStringCellValue()));
					} else {
						vect24.add("");
					}

					if (joiningDate != null) {
						vect24.add(String.valueOf(joiningDate.getStringCellValue()));
					} else {
						vect24.add("");
					}
					if (relievingDate != null) {
						vect24.add(String.valueOf(relievingDate.getStringCellValue()));
					} else {

						vect24.add("");

					}
					if (employeeLastSalary != null) {

						vect24.add(String.valueOf(employeeLastSalary.getStringCellValue()));
					} else {
						vect24.add("");
					}
					if (CTCinLac != null) {
						vect24.add(String.valueOf(CTCinLac.getStringCellValue()));
					} else {
						vect24.add("");
					}
				}	
				else {
					vect24.add("");
				}
			}
			int count = 0;

			String[][] obj24 = new String[vect24.size() / 8][8];
			logger.info("Size------=-= " + vect24.size());
			for (int i = 0; i < vect24.size() / 8; i++) {
				obj24[i][0] = (String.valueOf(vect24.get(count++)));
				obj24[i][1] = (String.valueOf(vect24.get(count++)));
				obj24[i][2] = (String.valueOf(vect24.get(count++)));
				obj24[i][3] = (String.valueOf(vect24.get(count++)));
				obj24[i][4] = (String.valueOf(vect24.get(count++)));
				obj24[i][5] = (String.valueOf(vect24.get(count++)));
				obj24[i][6] = (String.valueOf(vect24.get(count++)));
				obj24[i][7] = (String.valueOf(vect24.get(count++)));
			}
			String empExpQuery = " DELETE FROM HRMS_EMP_EXP ";
			boolean flag = getSqlModel().singleExecute(empExpQuery);
			if (flag) {
				String query = "INSERT INTO HRMS_EMP_EXP(EMP_ID,EXP_ID,EXP_JOBDESC,EXP_JOBTITLE,EXP_JOINING_DATE,EXP_RELIEVING_DATE,EXP_LASTSAL,EXP_SCALE_OF_PAY)"
						+ " VALUES((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE  EMP_TOKEN=?),?,?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(query, obj24);
				return result;
			}
		 
		}catch (Exception e) {
			e.printStackTrace();
		}

		*//****************Address Details************//*
		
		
		try{
			
			
			Vector vect25= new Vector();
			HSSFSheet sheet25 = wb.getSheet("Address Details"); 
			for (int k = 2; k<=sheet25.getLastRowNum();k++) {
			
			
			HSSFRow row =sheet25.getRow(k);
			
			HSSFCell employee = row.getCell((short) 0);
			HSSFCell addresstype =row.getCell((short) 1);
			HSSFCell address = row.getCell((short) 2);
			HSSFCell city = row.getCell((short) 3); 
			HSSFCell state =row.getCell((short) 4);
			HSSFCell pincode = row.getCell((short) 5);
			HSSFCell phone1 = row.getCell((short) 6); 
			HSSFCell phone2 =row.getCell((short) 7); 
			HSSFCell faxno = row.getCell((short) 8);
			HSSFCell mobileno = row.getCell((short) 9); 
			HSSFCell emailid = row.getCell((short)10); 
			HSSFCell country = row.getCell((short) 11);
			
			
			if(!(String.valueOf(employee.getStringCellValue()).equals(""))) { 
				//vect25.add(String.valueOf(paybillcode.getNumericCellValue()));
			
				vect25.add(String.valueOf(employee.getStringCellValue()).split(" ")[0]);
			
			if(addresstype!=null) {
			if((String.valueOf(addresstype.getStringCellValue()).equals("Official"))) {
				vect25.add("P"); }
			
			else
			if((String.valueOf(addresstype.getStringCellValue()).equals("Permanet"))) {
				vect25.add("P"); }
			
			
			else
			if((String.valueOf(addresstype.getStringCellValue()).equals("Local"))) {
				vect25.add("L"); }
			
			else
			if((String.valueOf(addresstype.getStringCellValue()).equals("Emergency"))) {
				vect25.add("E");
				}
			else {
				vect25.add(""); 
				}
			} else 
			{
				vect25.add(""); 
				}
			
			
			if(address!=null) {
				vect25.add(String.valueOf(address.getStringCellValue()));
				} 
			else {
					vect25.add(""); 
					}
			if(city!=null) {
						vect25.add(String.valueOf(city.getStringCellValue())); 
						}
			else {
				vect25.add("");
							} 
			if(state!=null) {
				vect25.add(String.valueOf(state.getStringCellValue()));
								} 
			else {
				vect25.add(""); 
			}
				
			if(pincode!=null) {
				vect25.add(String.valueOf(pincode.getStringCellValue()));
				}
			else {
					vect25.add(""); 
					} if(phone1!=null) {
						vect25.add(String.valueOf(phone1.getStringCellValue())); 
						}
					else {
						vect25.add("");
							
					}
					if(phone2!=null) {
						vect25.add(String.valueOf(phone2.getStringCellValue()));
								
					}
					else {
						vect25.add(""); 
									}
					if(faxno!=null) {
						vect25.add(String.valueOf(faxno.getStringCellValue())); 
										} 
					else {
						vect25.add("");
											} 
					if(mobileno!=null) {
						vect25.add(String.valueOf(mobileno.getStringCellValue()));
					} 
					else {
						vect25.add(""); 
							}
					if(emailid!=null) {
								vect25.add(String.valueOf(emailid.getStringCellValue()));
								}
					else {
							vect25.add(""); 
					}
					if(country!=null) {
						vect25.add(String.valueOf(country.getStringCellValue())); 
							} 
					else {
						vect25.add(""); 
						}
			
			}
			else{
				vect25.add(""); 
				break;
			} 
			}	 int count=0;
			
			//System.out.println("vect.size()uuuuuuuuu"+vect.size());
	String[][] obj25=new String[vect25.size()/12][12]; 
	for(int i=0;i<vect25.size()/12;i++) {
			System.out.println("entry to for loop------------------");
			obj25[i][0]=(String.valueOf(vect25.get(count++)));
			obj25[i][1]=(String.valueOf(vect25.get(count++)));
			obj25[i][2]=(String.valueOf(vect25.get(count++)));
			obj25[i][3]=(String.valueOf(vect25.get(count++)).toUpperCase());
			obj25[i][4]=(String.valueOf(vect25.get(count++)));
			obj25[i][5]=(String.valueOf(vect25.get(count++)));
			obj25[i][6]=(String.valueOf(vect25.get(count++)));
			obj25[i][7]=(String.valueOf(vect25.get(count++)));
			obj25[i][8]=(String.valueOf(vect25.get(count++)));
			obj25[i][9]=(String.valueOf(vect25.get(count++)));
			obj25[i][10]=(String.valueOf(vect25.get(count++)));
			obj25[i][11]=(String.valueOf(vect25.get(count++))); 
			}
			
			
			String addressQuery = "DELETE FROM HRMS_EMP_ADDRESS";
			boolean flag =getSqlModel().singleExecute(addressQuery); 
			if(flag) {
				String addssQuery = "	INSERT INTO HRMS_EMP_ADDRESS(EMP_ID,ADD_TYPE,ADD_1,ADD_CITY,ADD_STATE,ADD_PINCODE,ADD_PH1,ADD_PH2,ADD_FAX,ADD_MOBILE,ADD_EMAIL,ADD_CNTRY)" +
						"values((SELECT	EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_NAME=?),?,?,?,?,?,?,?,?,?)";
			
			result = getSqlModel().singleExecute(addssQuery,obj25); 
			return	result;
			
			 }
		
		}catch(Exception e)
			{ 
				e.printStackTrace(); 
			}
		
		*//********************leave balance excel sheet***********************//*
		
		
		try{
			 
			  Vector vect27=new Vector();
			  HSSFSheet sheet27 = wb.getSheet("Leave Balance"); 
			  for (int k = 2; k<=sheet27.getLastRowNum(); k++) {
			  HSSFRow row =sheet27.getRow(k);
			  
			  HSSFCell employee = row.getCell((short) 0);
			  HSSFCell leavetype = row.getCell((short) 1); 
			  HSSFCell leaveentitle = row.getCell((short) 2);
			  HSSFCell avlbalance = row.getCell((short) 3);
			   			 
			  if(!(String.valueOf(employee.getStringCellValue()).equals(""))) { 
				
				  vect27.add(String.valueOf(employee.getStringCellValue()).split(" ")[0]);
			  
			  if(leavetype!=null) {
				  vect27.add(String.valueOf(leavetype.getStringCellValue()));
				  }
			  else {
					 vect27.add(""); 
				  } 
			  if(leaveentitle!=null) {
				  vect27.add(String.valueOf(leaveentitle.getStringCellValue()));
				 } 
			  else {
					  vect27.add("");
				  }
			  if(avlbalance!=null) {
				 vect27.add(String.valueOf(avlbalance.getStringCellValue())); 
				  }
			  else {
				  vect27.add(""); 
				  } 
			  } 
			   
			  }	
			  int count=0;
			  
			  String[][] obj27=new String[vect27.size()/4][4]; 
			  for(int i=0;i<vect27.size()/4;i++) {
			  System.out.println("entry to for loop------------------");
			  obj27[i][0]=(String.valueOf(vect27.get(count++)));
			  obj27[i][1]=(String.valueOf(vect27.get(count++)));
			  obj27[i][2]=(String.valueOf(vect27.get(count++)));
			  obj27[i][3]=(String.valueOf(vect27.get(count++)));
			  }
			  		  
			  String balncQuery = "DELETE FROM HRMS_LEAVE_BALANCE";
			  boolean flag = getSqlModel().singleExecute(balncQuery);
			  if(flag) { 
				  String leaveQuery = "INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID,LEAVE_CODE,LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE)values((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),(SELECT LEAVE_ID FROM HRMS_LEAVE WHERE LEAVE_NAME=?),?,?)";
			  
			       result = getSqlModel().singleExecute(leaveQuery,obj27);
			       return result; 
			       }
			  
		  }catch(Exception e) 
		  { 
			  e.printStackTrace(); 
		  }*/
		return false;

	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	
	public String getBlankIfNull(String str) {
		if (!(str.equals("")) && !(str == null) && !str.equals("null")) {

			return str;
		} else {
			return "";
		}

	}

}
