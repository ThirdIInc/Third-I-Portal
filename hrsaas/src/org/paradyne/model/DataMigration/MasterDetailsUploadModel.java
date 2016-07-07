package org.paradyne.model.DataMigration;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class MasterDetailsUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MasterDetailsUploadModel.class);

	public void uploadAssetCtgryDtls(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		try {
			String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
			MigrateExcelData.getFile(filePath);

			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

			Object[][] assetCatName = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, 
				columnInformation.get(1));

			boolean res = MigrateExcelData.isFileToBeUploaded();

			if(res) {
				Object[][] assetCatDetailsObj = new Object[assetCatName.length][2];
				Object[][] maxAssetId = getSqlModel().getSingleResult("SELECT NVL(MAX(ASSET_CATEGORY_CODE),0)+1 FROM HRMS_ASSET_CATEGORY");
				int assetId = Integer.parseInt(String.valueOf(maxAssetId[0][0]));

				for(int i = 0; i < assetCatDetailsObj.length; i++) {
					assetCatDetailsObj[i][0] = assetId++;
					try {
						assetCatDetailsObj[i][1] = String.valueOf(assetCatName[i][0]);
					} catch(Exception e) {
						assetCatDetailsObj[i][1] = "";
					}
				}

				String assetCatQuery = " INSERT INTO HRMS_ASSET_CATEGORY(ASSET_CATEGORY_CODE, ASSET_CATEGORY_NAME) VALUES(?, ?) ";
				boolean insertResult = getSqlModel().singleExecute(assetCatQuery, assetCatDetailsObj);

				if(insertResult) {
					empDetails.setStatus("Success");
				} else {
					empDetails.setStatus("Fail");
					empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate "
						+ "records. Upload the sheet again to transfer the data.");
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer "
					+ "the records.");
			}
			empDetails.setUploadName("assetCategory");
		} catch(Exception e) {
			logger.error("Exception in uploadAssetCtgryDtls:" + e);
		}
	}

	public void uploadAssetSubTypDtls(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		try {
			String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

			String catQuery = "SELECT ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY ORDER BY ASSET_CATEGORY_CODE";
			Object[][] catObj = getSqlModel().getSingleResult(catQuery);
			Object[][] assetCategoryName = MigrateExcelData.uploadExcelData(1, catObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));

			Object[][] assetSubTypeName = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(2));

			Object[][] retTypObj = new Object[2][2];
			retTypObj[0][0] = "R";
			retTypObj[0][1] = "Returnable";
			retTypObj[1][0] = "N";
			retTypObj[1][1] = "Non-Returnable";
			Object[][] assetRetrunType = MigrateExcelData.uploadExcelData(3, retTypObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

			Object[][] invCodeObj = new Object[2][2];
			invCodeObj[0][0] = "I";
			invCodeObj[0][1] = "Itemized Inventory";
			invCodeObj[1][0] = "S";
			invCodeObj[1][1] = "Bulk Inventory";
			Object[][] inventoryCodeType = MigrateExcelData.uploadExcelData(4, invCodeObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(4));

			Object[][] reOrderLevel = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.NUMBER_INTEGER_TYPE, columnInformation.get(5));

			Object[][] leadTime = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.NUMBER_INTEGER_TYPE, columnInformation.get(6));

			String assetUnitQuery = "SELECT UNIT_CODE,UNIT_NAME FROM HRMS_UNIT_MASTER";
			Object[][] assetUnitObj = getSqlModel().getSingleResult(assetUnitQuery);

			Object[][] assetUnit = MigrateExcelData.uploadExcelData(7, assetUnitObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(7));

			boolean res = MigrateExcelData.isFileToBeUploaded();

			if(res) {
				Object[][] assetSybTypeDetailsObj = new Object[assetCategoryName.length][8];
				Object[][] maxsubTypeId = getSqlModel().getSingleResult("SELECT NVL(MAX(ASSET_SUBTYPE_CODE),0)+1 FROM HRMS_ASSET_SUBTYPE");
				int assetId = Integer.parseInt(String.valueOf(maxsubTypeId[0][0]));

				for(int i = 0; i < assetSybTypeDetailsObj.length; i++) {
					assetSybTypeDetailsObj[i][0] = assetId++;
					try {
						assetSybTypeDetailsObj[i][1] = String.valueOf(assetCategoryName[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][1] = "";
					}
					try {
						assetSybTypeDetailsObj[i][2] = String.valueOf(assetSubTypeName[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][2] = "";
					}
					try {
						assetSybTypeDetailsObj[i][3] = String.valueOf(assetRetrunType[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][3] = "";
					}
					try {
						assetSybTypeDetailsObj[i][4] = String.valueOf(inventoryCodeType[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][4] = "";
					}
					try {
						assetSybTypeDetailsObj[i][5] = String.valueOf(reOrderLevel[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][5] = "";
					}
					try {
						assetSybTypeDetailsObj[i][6] = String.valueOf(leadTime[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][6] = "";
					}
					try {
						assetSybTypeDetailsObj[i][7] = String.valueOf(assetUnit[i][0]);
					} catch(Exception e) {
						assetSybTypeDetailsObj[i][7] = "";
					}
				}

				String assetCatQuery = " INSERT INTO HRMS_ASSET_SUBTYPE(ASSET_SUBTYPE_CODE, ASSET_CATEGORY_CODE,"
					+ "ASSET_SUBTYPE_NAME, ASSET_SUBTYPE_FLAG,ASSET_INVENTORY_TYPE,ASSET_REORDER_LEVEL,"
					+ "ASSET_SUBTYPE_LEADTIME,ASSET_SUBTYPE_UNIT) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
				boolean insertResult = getSqlModel().singleExecute(assetCatQuery, assetSybTypeDetailsObj);

				if(insertResult) {
					empDetails.setStatus("Success");
				} else {
					empDetails.setStatus("Fail");
					empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate "
						+ "records. Upload the sheet again to transfer the data.");
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer "
					+ "the records.");
			}
			empDetails.setUploadName("assetSubType");
		} catch(Exception e) {
			logger.error("Exception in uploadAssetSubTypDtls:" + e);
		}
	}

	public void uploadBranchDtls(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);

		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] branchName = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(1));
		Object[][] abbrevation = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(2));
		Object[][] addressOne = MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
		Object[][] addressTwo = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
		Object[][] addressThr = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));

		Object cityMaster[][] = getSqlModel().getSingleResult("SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE=2");
		Object[][] city = MigrateExcelData.uploadExcelData(6, cityMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(6));
		Object[][] pin = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.NUMBER_INTEGER_TYPE, columnInformation.get(7));
		Object[][] telPhone = MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.STRING_TYPE, columnInformation.get(8));

		boolean res = MigrateExcelData.isFileToBeUploaded();

		if(res) {
			Object[][] branchDetailsObj = new Object[branchName.length][9];
			Object[][] maxBrnchId = getSqlModel().getSingleResult("(SELECT NVL(MAX(CENTER_ID),0)+1 FROM HRMS_CENTER)");
			int brnchId = Integer.parseInt(String.valueOf(maxBrnchId[0][0]));

			for(int i = 0; i < branchDetailsObj.length; i++) {
				try {
					branchDetailsObj[i][1] = branchName[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][1] = "";
				}
				branchDetailsObj[i][0] = brnchId++;
				try {
					branchDetailsObj[i][2] = abbrevation[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][2] = "";
				}
				try {
					branchDetailsObj[i][3] = addressOne[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][3] = "";
				}
				try {
					branchDetailsObj[i][4] = addressTwo[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][4] = "";
				}
				try {
					branchDetailsObj[i][5] = addressThr[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][5] = "";
				}
				try {
					branchDetailsObj[i][6] = city[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][6] = "";
				}
				try {
					branchDetailsObj[i][7] = pin[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][7] = "";
				}
				try {
					branchDetailsObj[i][8] = telPhone[i][0];
				} catch(Exception e) {
					branchDetailsObj[i][8] = "";
				}

			}
			String brnchQuery = " INSERT INTO HRMS_CENTER (CENTER_ID, CENTER_NAME, CENTER_ABBR, CENTER_ADDRESS1, CENTER_ADDRESS2, CENTER_ADDRESS3, "
				+ " CENTER_LOCATION,CENTER_PINCODE,CENTER_TELEPHONE ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			boolean insertResult = getSqlModel().singleExecute(brnchQuery, branchDetailsObj);

			if(insertResult) {
				empDetails.setStatus("Success");
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
					+ "Upload the sheet again to transfer the data.");
			}
		} else {
			empDetails.setStatus("Fail");
			empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the "
				+ "records.");
		}
		empDetails.setUploadName("branch");
	}

	public void uploadBranchHoliday(EmpDetailsUpload bean) {
		String filePath = bean.getDataPath() + bean.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object branchMasterObj[][] = getSqlModel().getSingleResult("SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER");
		Object[][] branchData = MigrateExcelData.uploadExcelData(1, branchMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));

		Object holidayDateMaster[][] = getSqlModel().getSingleResult("SELECT TO_CHAR(HOLI_DATE,'MM/DD/YYYY'), TO_CHAR(HOLI_DATE,'MM/DD/YYYY') "
			+ "FROM HRMS_HOLIDAY ORDER BY HOLI_DATE");
		Object[][] holidayDate = MigrateExcelData.uploadExcelData(2, holidayDateMaster, MigrateExcelData.MASTER_DATE_TYPE, columnInformation.get(2));

		boolean res = MigrateExcelData.isFileToBeUploaded();

		if(res) {
			Object[][] holidayDateObj = new Object[branchData.length][2];

			for(int i = 0; i < holidayDateObj.length; i++) {
				holidayDateObj[i][0] = branchData[i][0];
				holidayDateObj[i][1] = holidayDate[i][0];
			}

			String branchHolidayQuery = "INSERT INTO HRMS_HOLIDAY_BRANCH(CENTER_ID, HOLI_DATE) VALUES(?, TO_DATE(?, 'MM/DD/YYYY'))";
			boolean insertResult = getSqlModel().singleExecute(branchHolidayQuery, holidayDateObj);

			if(insertResult) {
				bean.setStatus("Success");
			} else {
				bean.setStatus("Fail");
				bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
					+ "Upload the sheet again to transfer the data.");
			}
		} else {
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		bean.setUploadName("branchHoliday");
	}

	public void uploadDeptDtls(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] deptName = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(1));
		Object[][] abbrevation = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(2));

		boolean res = MigrateExcelData.isFileToBeUploaded();

		if(res) {
			Object[][] deptDetailsObj = new Object[deptName.length][3];
			Object[][] maxDeptId = getSqlModel().getSingleResult("(SELECT NVL(MAX(DEPT_ID),0)+1 FROM HRMS_DEPT)");
			int brnchId = Integer.parseInt(String.valueOf(maxDeptId[0][0]));

			for(int i = 0; i < deptDetailsObj.length; i++) {
				try {
					deptDetailsObj[i][1] = deptName[i][0];
				} catch(Exception e) {
					deptDetailsObj[i][1] = "";
				}
				deptDetailsObj[i][0] = brnchId++;

				try {
					deptDetailsObj[i][2] = abbrevation[i][0];
				} catch(Exception e) {
					deptDetailsObj[i][2] = "";
				}
			}
			String deptQuery = "INSERT INTO HRMS_DEPT(DEPT_ID, DEPT_NAME, DEPT_ABBR) VALUES(?, ?, ?)";
			boolean insertResult = getSqlModel().singleExecute(deptQuery, deptDetailsObj);

			if(insertResult) {
				empDetails.setStatus("Success");
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
					+ "Upload the sheet again to transfer the data.");
			}
		} else {
			empDetails.setStatus("Fail");
			empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the "
				+ "records.");
		}
		empDetails.setUploadName("department");
	}

	public void uploadDesgnDtls(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		try {
			String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

			Object[][] desgnName = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(1));
			Object[][] abbrevation = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE_WITHOUT_SPL_CHARS, columnInformation.get(2));

			Object[][] statusMatser = new Object[2][2];
			statusMatser[0][0] = "Y";
			statusMatser[0][1] = "Active";
			statusMatser[1][0] = "N";
			statusMatser[1][1] = "Deactive";
			Object[][] status = MigrateExcelData.uploadExcelData(3, statusMatser, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

			boolean res = MigrateExcelData.isFileToBeUploaded();

			if(res) {
				Object[][] desgnDetailsObj = new Object[desgnName.length][4];
				Object[][] maxDesgnId = getSqlModel().getSingleResult("SELECT NVL(MAX(RANK_ID),0)+1 FROM HRMS_RANK");
				int desgnId = Integer.parseInt(String.valueOf(maxDesgnId[0][0]));

				for(int i = 0; i < desgnDetailsObj.length; i++) {
					desgnDetailsObj[i][0] = desgnId++;
					try {
						desgnDetailsObj[i][1] = String.valueOf(desgnName[i][0]);
					} catch(Exception e) {
						desgnDetailsObj[i][1] = "";
					}
					try {
						desgnDetailsObj[i][2] = String.valueOf(abbrevation[i][0]);
					} catch(Exception e) {
						desgnDetailsObj[i][2] = "";
					}
					try {
						desgnDetailsObj[i][3] = String.valueOf(status[i][0]);
					} catch(Exception e) {
						desgnDetailsObj[i][3] = "A";
					}
				}

				String desgnQuery = " INSERT INTO HRMS_RANK(RANK_ID, RANK_NAME, RANK_ABBR, IS_ACTIVE) VALUES(?, ?, ?, ?) ";
				boolean insertResult = getSqlModel().singleExecute(desgnQuery, desgnDetailsObj);

				if(insertResult) {
					empDetails.setStatus("Success");
				} else {
					empDetails.setStatus("Fail");
					empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate "
						+ "records. Upload the sheet again to transfer the data.");
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer "
					+ "the records.");
			}
			empDetails.setUploadName("designation");
		} catch(Exception e) {
			logger.error("Exception in uploadDesgnDtls:" + e);
		}
	}

	public void uploadHolidayMaster(EmpDetailsUpload bean) {
		String filePath = bean.getDataPath() + bean.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] holidayDate = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.DATE_TYPE, columnInformation.get(1));
		Object[][] holidayDesc = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));

		boolean res = MigrateExcelData.isFileToBeUploaded();

		if(res) {
			Object[][] holidayDateObj = new Object[holidayDate.length][2];

			for(int i = 0; i < holidayDateObj.length; i++) {
				holidayDateObj[i][0] = holidayDate[i][0];
				holidayDateObj[i][1] = holidayDesc[i][0];

			}
			String holidayQuery = "INSERT INTO HRMS_HOLIDAY(HOLI_DATE,HOLI_DESC) VALUES(TO_DATE(?,'MM/DD/YYYY'),?)";
			boolean insertResult = getSqlModel().singleExecute(holidayQuery, holidayDateObj);

			if(insertResult) {
				bean.setStatus("Success");
			} else {
				bean.setStatus("Fail");
				bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
					+ "Upload the sheet again to transfer the data.");
			}
		} else {
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		bean.setUploadName("holiday");
	}
}