package org.paradyne.model.DataMigration;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import org.paradyne.bean.DataMigration.AssetMasterUpload;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigrateExcelData;

import com.lowagie.text.Font;

public class AssetMasterUploadModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AssetMasterUploadModel.class);

	/*
	 * OLD METHOD FOR REFERENCE PURPOSE DO NOT DELETE public void
	 * uploadAssetMasterTemplate(HttpServletResponse response,
	 * HttpServletRequest request, AssetMasterUpload assetMaster) { // TODO
	 * Auto-generated method stub String
	 * filePath=assetMaster.getDataPath()+""+assetMaster.getUploadFileName();
	 * System.out.println("############# FILE PATH #############"+filePath);
	 * assetMaster.setUploadName("AssetMaster"); //to create object of the file
	 * MigrateExcelData.getFile(filePath);
	 * 
	 *//**
	 * Get column numbers with mandatory information
	 */
	/*
	 * HashMap<Integer, Boolean> columnInformation =
	 * MigrateExcelData.isColumnsMandatory();
	 * 
	 * Object[][] assetTypeMaster = getSqlModel().getSingleResult(" SELECT
	 * ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY ");
	 * Object[][] assetSubMaster = getSqlModel().getSingleResult(" SELECT
	 * ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME FROM HRMS_ASSET_SUBTYPE ");
	 * Object[][] vendorMaster = getSqlModel().getSingleResult(" SELECT
	 * VENDOR_CODE, VENDOR_NAME FROM HRMS_VENDOR ORDER BY VENDOR_CODE ");
	 * Object[][] wareHouseMaster = getSqlModel().getSingleResult(" SELECT
	 * WAREHOUSE_CODE,WAREHOUSE_NAME FROM HRMS_WAREHOUSE_MASTER ORDER BY
	 * WAREHOUSE_CODE "); Object[][] assetTypeObj=null; Object[][]
	 * assetSubTypeObj=null; Object[][] vendorObj=null; Object[][]
	 * wareHouseObj=null;
	 * 
	 * if(assetTypeMaster != null && assetTypeMaster.length > 0) {
	 * assetTypeObj=MigrateExcelData.uploadExcelData(1, assetTypeMaster,
	 * MigrateExcelData.MASTER_TYPE, columnInformation.get(1)); }
	 * if(assetSubMaster != null && assetSubMaster.length > 0) {
	 * assetSubTypeObj=MigrateExcelData.uploadExcelData(2, assetSubMaster,
	 * MigrateExcelData.MASTER_TYPE, columnInformation.get(2)); }
	 * if(vendorMaster != null && vendorMaster.length > 0) {
	 * vendorObj=MigrateExcelData.uploadExcelData(3, vendorMaster,
	 * MigrateExcelData.MASTER_TYPE, columnInformation.get(3)); }
	 * if(wareHouseMaster != null && wareHouseMaster.length > 0) {
	 * wareHouseObj=MigrateExcelData.uploadExcelData(9, wareHouseMaster,
	 * MigrateExcelData.MASTER_TYPE, columnInformation.get(9)); } Object
	 * [][]purchaseDateObj= MigrateExcelData.uploadExcelData(4, null,
	 * MigrateExcelData.DATE_TYPE, columnInformation.get(4));
	 * 
	 * Object [][]purchasePriceObj= MigrateExcelData.uploadExcelData(5, null,
	 * MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(5));
	 * 
	 * Object [][]assetTotalQua= MigrateExcelData.uploadExcelData(6, null,
	 * MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(6));
	 * 
	 * Object [][]assetTotalAvailable= MigrateExcelData.uploadExcelData(7, null,
	 * MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(7));
	 * 
	 * Object [][]assetDesc= MigrateExcelData.uploadExcelData(8, null,
	 * MigrateExcelData.STRING_TYPE, columnInformation.get(8));
	 * 
	 * 
	 * Object [][]inventoryCode= MigrateExcelData.uploadExcelData(10, null,
	 * MigrateExcelData.STRING_TYPE, columnInformation.get(10));
	 * 
	 * Object [][]leaseRenewDate= MigrateExcelData.uploadExcelData(11, null,
	 * MigrateExcelData.DATE_TYPE, columnInformation.get(11));
	 * 
	 * Object [][]insuranceRenewDate= MigrateExcelData.uploadExcelData(12, null,
	 * MigrateExcelData.DATE_TYPE, columnInformation.get(12));
	 * 
	 * Object [][]assetDtlQuan= MigrateExcelData.uploadExcelData(13, null,
	 * MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(13));
	 * 
	 * Object [][]assetDtlAvailable= MigrateExcelData.uploadExcelData(14, null,
	 * MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(14));
	 * 
	 * Object[][]statusMaster=new Object[4][2]; statusMaster[0][0] ="U";
	 * statusMaster[0][1] ="Available";
	 * 
	 * statusMaster[1][0] ="A"; statusMaster[1][1] ="Assigned";
	 * 
	 * statusMaster[2][0] ="L"; statusMaster[2][1] ="Lost";
	 * 
	 * statusMaster[3][0] ="D"; statusMaster[3][1] ="Damaged"; Object
	 * [][]assetStaus= MigrateExcelData.uploadExcelData(15, statusMaster,
	 * MigrateExcelData.MASTER_TYPE, columnInformation.get(15));
	 * 
	 * boolean res = MigrateExcelData.isFileToBeUploaded(); if(res){ String
	 * assetTypeCheck=""; String assetSubTypeCheck=""; String vendorCheck="";
	 * String purchaseDateCheck=""; String purchasePriceCheck=""; String
	 * warehouseCheck=""; assetTypeCheck=String.valueOf(assetTypeObj[0][0]);
	 * assetSubTypeCheck=String.valueOf(assetSubTypeObj[0][0]);
	 * 
	 * try { vendorCheck = String.valueOf(vendorObj[0][0]); } catch (Exception
	 * e) { vendorCheck=""; } try { purchaseDateCheck =
	 * String.valueOf(purchaseDateObj[0][0]); } catch (Exception e) {
	 * purchaseDateCheck=""; } try { purchasePriceCheck =
	 * String.valueOf(purchasePriceObj[0][0]); } catch (Exception e) {
	 * purchasePriceCheck=""; } try { warehouseCheck =
	 * String.valueOf(wareHouseObj[0][0]); } catch (Exception e) {
	 * warehouseCheck=""; } Object [][]
	 * maxAssetMasterCodeObj=getSqlModel().getSingleResult("SELECT
	 * NVL(MAX(ASSET_MASTER_CODE),0)+1 FROM HRMS_ASSET_MASTER"); int
	 * maxAssetCode=Integer.parseInt(String.valueOf(maxAssetMasterCodeObj[0][0]));
	 * Object [][]assetInsertObj=null; Object [][]assetDtlInsertObj=new
	 * Object[assetTypeObj.length][10]; Vector <Object []>assetInsertVect=new
	 * Vector<Object []>();
	 * 
	 * String assetHdrInsertQuery ="INSERT INTO HRMS_ASSET_MASTER (
	 * ASSET_CATEGORY_CODE, ASSET_SUBTYPE_CODE, ASSET_QUANTITY," +"
	 * ASSET_PURCHASE_DATE, ASSET_PURCHASE_PRICE, ASSET_ASSIGNED,
	 * ASSET_AVAILABLE, ASSET_DESCRIPTION," +" ASSET_MASTER_CODE, ASSET_STATUS,
	 * ASSET_VENDOR ) VALUES ( ?,?,?,TO_DATE(?,'MM-DD-YYYY'),?,?,?,?,?,?,?)";
	 * 
	 * String assetDtlInsertQuery ="INSERT INTO HRMS_ASSET_MASTER_DTL (
	 * ASSET_MASTER_CODE, ASSET_INVENTORY_CODE, ASSET_WAREHOUSE_CODE," +"
	 * ASSET_ASSISGN_FLAG, ASSET_QUANTITY, ASSET_AVAILABLE, ASSET_IS_ON_LEASE,
	 * ASSET_LEASE_RENEW_DATE," +" ASSET_IS_INSURED, ASSET_INSURANCE_RENEW_DATE )
	 * VALUES
	 * (?,?,?,?,?,?,?,TO_DATE(?,'MM-DD-YYYY'),?,TO_DATE(?,'MM-DD-YYYY'))"; int
	 * hdrLength=1; for (int i = 0; i < assetTypeObj.length; i++) { if(i!=0){
	 * if(!(assetTypeCheck.equals(String.valueOf(assetTypeObj[i][0])) &&
	 * assetSubTypeCheck.equals(String.valueOf(assetSubTypeObj[i][0])) &&
	 * vendorCheck.equals(String.valueOf(vendorObj[i][0])) &&
	 * purchaseDateCheck.equals(String.valueOf(purchaseDateObj[i][0])) &&
	 * purchasePriceCheck.equals(String.valueOf(purchasePriceObj[i][0])) &&
	 * warehouseCheck.equals(String.valueOf(wareHouseObj[i][0])))){ hdrLength++;
	 * maxAssetCode++; assetTypeCheck=String.valueOf(assetTypeObj[i][0]);
	 * assetSubTypeCheck=String.valueOf(assetSubTypeObj[i][0]);
	 * vendorCheck=String.valueOf(vendorObj[i][0]);
	 * purchaseDateCheck=String.valueOf(purchaseDateObj[i][0]);
	 * purchasePriceCheck=String.valueOf(purchasePriceObj[i][0]);
	 * warehouseCheck=String.valueOf(wareHouseObj[i][0]);
	 * 
	 * Object []tempObj= new Object [12]; tempObj[0] =
	 * String.valueOf(assetTypeObj[i][0]); tempObj[1] =
	 * String.valueOf(assetSubTypeObj[i][0]); tempObj[2] =
	 * String.valueOf(assetTotalQua[i][0]); tempObj[3] =
	 * String.valueOf(purchaseDateObj[i][0]); tempObj[4] =
	 * String.valueOf(purchasePriceObj[i][0]); tempObj[5] =
	 * Double.parseDouble(String.valueOf(assetTotalQua[i][0]))-Double.parseDouble(String.valueOf(assetTotalAvailable[i][0]));
	 * tempObj[6] = String.valueOf(assetTotalAvailable[i][0]); tempObj[7] =
	 * String.valueOf(assetDesc[i][0]); tempObj[8] =
	 * String.valueOf(maxAssetCode);
	 * 
	 * if(String.valueOf(assetTotalAvailable[i][0]).equals("0.0")||String.valueOf(assetTotalAvailable[i][0]).equals("0")){
	 * tempObj[9] = "A"; }else{ tempObj[9] = "U"; } tempObj[10] =
	 * String.valueOf(vendorObj[i][0]); assetInsertVect.add(tempObj); } }else{
	 * 
	 * Object []tempObj= new Object [11]; tempObj[0] =
	 * String.valueOf(assetTypeObj[i][0]); tempObj[1] =
	 * String.valueOf(assetSubTypeObj[i][0]); tempObj[2] =
	 * String.valueOf(assetTotalQua[i][0]); tempObj[3] =
	 * String.valueOf(purchaseDateObj[i][0]); tempObj[4] =
	 * String.valueOf(purchasePriceObj[i][0]); tempObj[5] =
	 * Double.parseDouble(String.valueOf(assetTotalQua[i][0]))-Double.parseDouble(String.valueOf(assetTotalAvailable[i][0]));
	 * tempObj[6] = String.valueOf(assetTotalAvailable[i][0]); tempObj[7] =
	 * String.valueOf(assetDesc[i][0]); tempObj[8] =
	 * String.valueOf(maxAssetCode);
	 * 
	 * if(String.valueOf(assetTotalAvailable[i][0]).equals("0.0")||String.valueOf(assetTotalAvailable[i][0]).equals("0")){
	 * tempObj[9] = "A"; }else{ tempObj[9] = "U"; }
	 * 
	 * tempObj[10] = String.valueOf(vendorObj[i][0]);
	 * 
	 * assetInsertVect.add(tempObj); } assetDtlInsertObj[i][0]
	 * =String.valueOf(maxAssetCode); assetDtlInsertObj[i][1]
	 * =String.valueOf(inventoryCode[i][0]); assetDtlInsertObj[i][2]
	 * =String.valueOf(wareHouseObj[i][0]); assetDtlInsertObj[i][3]
	 * =String.valueOf(assetStaus[i][0]); assetDtlInsertObj[i][4]
	 * =String.valueOf(assetDtlQuan[i][0]); assetDtlInsertObj[i][5]
	 * =String.valueOf(assetDtlAvailable[i][0]);
	 * 
	 * if(String.valueOf(leaseRenewDate[i][0]).equals("")){
	 * assetDtlInsertObj[i][6] ="N"; }else{ assetDtlInsertObj[i][6] ="Y"; }
	 * 
	 * assetDtlInsertObj[i][7] =String.valueOf(leaseRenewDate[i][0]);
	 * 
	 * if(String.valueOf(insuranceRenewDate[i][0]).equals("")){
	 * assetDtlInsertObj[i][8] ="N"; }else{ assetDtlInsertObj[i][8] ="Y"; }
	 * assetDtlInsertObj[i][9] =String.valueOf(insuranceRenewDate[i][0]); } //
	 * end of for loop
	 * 
	 * if(assetInsertVect.size()>0){ assetInsertObj = new
	 * Object[assetInsertVect.size()][11]; for (int j = 0; j <
	 * assetInsertObj.length; j++) { assetInsertObj[j]
	 * =(Object[])assetInsertVect.get(j); } } Object [] queryArray=new
	 * Object[2]; queryArray [0]= assetHdrInsertQuery; queryArray [1]=
	 * assetDtlInsertQuery;
	 * 
	 * Vector<Object[][]> dataVector= new Vector<Object[][]>();
	 * dataVector.add(assetInsertObj); dataVector.add(assetDtlInsertObj);
	 * 
	 * boolean result = getSqlModel().multiExecute(queryArray, dataVector);
	 * 
	 * if(result) { assetMaster.setStatus("Success"); } else {
	 * assetMaster.setStatus("Fail"); assetMaster.setNote("Duplicate records
	 * found. Please verify the data in the sheet and data in the system to
	 * remove the duplicate records. " + "Upload the sheet again to transfer the
	 * data."); } }else{ assetMaster.setStatus("Fail");
	 * assetMaster.setNote("Please download the attached sheet to verify and
	 * resolve the integrity issues. Upload the sheet again to transfer the
	 * records."); } } //end of outermost if
	 */
	public void generateXlsTemplate(AssetMasterUpload bean,
			HttpServletResponse response) {
		try {
			String fileName = "ASSET_REPORT";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg = getReport(rg, bean);
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReportGenerator getReport(ReportGenerator rg, AssetMasterUpload bean) {
		try {
			/* Adding extra columns dynamically as per the sub type */
			String propertiesQuery = "";
			Object[][] propertiesObj = null;
			if (!bean.getAssetSubTypeCode().equals("")) {
				propertiesQuery = " SELECT NVL(PROPERTY_ATTRIBUTE,' ') FROM HRMS_ASSET_PROPERTY_MASTER "
						+ " WHERE ASSET_SUBTYPE = "
						+ bean.getAssetSubTypeCode() + "ORDER BY PROPERTY_ID";

				propertiesObj = getSqlModel().getSingleResult(propertiesQuery);
			}
			String[] header = null;
			if (propertiesObj != null && propertiesObj.length > 0) {
				header = new String[16 + propertiesObj.length];
			} else {
				header = new String[16];
			}
			System.out.println("############ header.length #############"
					+ header.length);

			header[0] = "Asset Category Name ";
			header[1] = "Asset Sub-Type Name";
			// header[2] = "Vendor Name";
			header[2] = "Purchase Date";
			header[3] = "Purchase Price";
			header[4] = "Asset Quantity";
			header[5] = "Asset Available";
			header[6] = "Asset Description";
			header[7] = "Warehouse Name";
			header[8] = "Inventory Code";
			header[9] = "Lease Renewal Date";
			header[10] = "Insurance Renewal Date";
			// header[12] = "Quantity";
			// header[13] = "Available";
			header[11] = "Status";
			header[12] = "Owned By";
			header[13] = "Assigned Employee";
			header[14] = "Assigned Vendor";
			header[15] = "Vendor Name";

			/* Only if sub type has properties this code executes */

			if (propertiesObj != null && propertiesObj.length > 0) {
				int headerIndex = header.length - propertiesObj.length;
				System.out
						.println("############## headerIndex ################"
								+ headerIndex);
				for (int i = 0; i < propertiesObj.length; i++) {
					header[headerIndex + i] = String
							.valueOf(propertiesObj[i][0]);
				}
			}
			int[] alignment = new int[header.length];
			int[] cellwidth = new int[header.length];
			for (int i = 0; i < header.length; i++) {
				alignment[i] = 1;
				cellwidth[i] = 20;
			}
			Object[][] advanceData = new Object[1][header.length];
			for (int i = 0; i < header.length; i++) {
				if ((i == 0 || i == 1 || i == 4 || i == 5 || i == 7 || i == 8 || i == 11 )) {
					advanceData[0][i] = "*";
				} else if ((i == 2 || i == 9 || i == 10)) {
					advanceData[0][i] = "mm/dd/yyyy";
				} else if (i == 12) {
					advanceData[0][i] = "Employee/Vendor";
				} else {
					advanceData[0][i] = "";
				}
			}
			rg.tableBody(header, advanceData, cellwidth, alignment);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public void uploadAssetMasterDetails(HttpServletResponse response,
			HttpServletRequest request, AssetMasterUpload assetMaster) {

		try {
			String filePath = assetMaster.getDataPath() + ""
					+ assetMaster.getUploadFileName();
			System.out.println("############# FILE PATH #############"
					+ filePath);
			assetMaster.setUploadName("AssetMaster");
			// to create object of the file
			MigrateExcelData.getFile(filePath);
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData
					.isColumnsMandatory();
			Object[][] assetTypeMaster = getSqlModel()
					.getSingleResult(
							" SELECT ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY ");
			Object[][] assetSubMaster = getSqlModel()
					.getSingleResult(
							" SELECT ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME FROM HRMS_ASSET_SUBTYPE ");
			Object[][] assignedEmp = getSqlModel()
			.getSingleResult(
					" SELECT EMP_ID, EMP_TOKEN  FROM HRMS_EMP_OFFC ORDER BY EMP_TOKEN ");
			
			
			Object[][] vendorMaster = getSqlModel()
					.getSingleResult(
							" SELECT VENDOR_CODE, VENDOR_NAME FROM HRMS_VENDOR ORDER BY VENDOR_CODE ");
			
			Object[][] wareHouseMaster = getSqlModel()
					.getSingleResult(
							" SELECT WAREHOUSE_CODE,WAREHOUSE_NAME  FROM HRMS_WAREHOUSE_MASTER ORDER BY WAREHOUSE_CODE ");
			Object[][] assetTypeObj = null;
			Object[][] assetSubTypeObj = null;
			Object[][] assignedEmployeeObj = null;
			Object[][] vendorObj = null;
			Object[][] PurchaseVendorObj = null;
			Object[][] wareHouseObj = null;
			if (assetTypeMaster != null && assetTypeMaster.length > 0) {
				assetTypeObj = MigrateExcelData.uploadExcelData(1,
						assetTypeMaster, MigrateExcelData.MASTER_TYPE,
						columnInformation.get(1));
			}
			if (assetSubMaster != null && assetSubMaster.length > 0) {
				assetSubTypeObj = MigrateExcelData.uploadExcelData(2,
						assetSubMaster, MigrateExcelData.MASTER_TYPE,
						columnInformation.get(2));
			}
			  /*if(assignedEmp != null && assignedEmp.length > 0) {
				  assignedEmployeeObj=MigrateExcelData.uploadExcelData(14, assignedEmp,
			  MigrateExcelData.MASTER_TYPE, columnInformation.get(14)); 
			  }
			
			  if(vendorMaster != null && vendorMaster.length > 0) {
				  vendorObj=MigrateExcelData.uploadExcelData(15, vendorMaster,
			  MigrateExcelData.MASTER_TYPE, columnInformation.get(15)); 
			  }*/
			  
			  if(assignedEmp != null && assignedEmp.length > 0) {
				  assignedEmployeeObj=MigrateExcelData.uploadExcelData(14, assignedEmp,
			  MigrateExcelData.MASTER_TYPE, false); 
			  }
			
			  if(vendorMaster != null && vendorMaster.length > 0) {
				  vendorObj=MigrateExcelData.uploadExcelData(15, vendorMaster,
			  MigrateExcelData.MASTER_TYPE, false); 
			  }
			  if(vendorMaster != null && vendorMaster.length > 0) {
				  PurchaseVendorObj=MigrateExcelData.uploadExcelData(16, vendorMaster,
			  MigrateExcelData.MASTER_TYPE, false); 
			  }
						
			if (wareHouseMaster != null && wareHouseMaster.length > 0) {
				wareHouseObj = MigrateExcelData.uploadExcelData(8,
						wareHouseMaster, MigrateExcelData.MASTER_TYPE,
						columnInformation.get(8));
			}
			Object[][] purchaseDateObj = MigrateExcelData.uploadExcelData(3,
					null, MigrateExcelData.DATE_TYPE, columnInformation.get(3));
			Object[][] purchasePriceObj = MigrateExcelData.uploadExcelData(4,
					null, MigrateExcelData.NUMBER_DOUBLE_TYPE,
					columnInformation.get(4));
			Object[][] assetTotalQua = MigrateExcelData.uploadExcelData(5,
					null, MigrateExcelData.NUMBER_DOUBLE_TYPE,
					columnInformation.get(5));
			Object[][] assetTotalAvailable = MigrateExcelData.uploadExcelData(
					6, null, MigrateExcelData.NUMBER_DOUBLE_TYPE,
					columnInformation.get(6));
			Object[][] assetDesc = MigrateExcelData.uploadExcelData(7, null,
					MigrateExcelData.STRING_TYPE, columnInformation.get(7));
			
			
		/*	Object[][] inventoryCode = MigrateExcelData.uploadExcelData(9,
					null, MigrateExcelData.STRING_TYPE, columnInformation
							.get(9));
			*/
			Object[][] inventoryCode = MigrateExcelData.uploadExcelData(9,
					null, MigrateExcelData.IS_DUPLICATE, columnInformation
							.get(9));
			
			
			Object[][] leaseRenewDate = MigrateExcelData
					.uploadExcelData(10, null, MigrateExcelData.DATE_TYPE,
							columnInformation.get(10));
			Object[][] insuranceRenewDate = MigrateExcelData.uploadExcelData(
					11, null, MigrateExcelData.DATE_TYPE, columnInformation
							.get(11));
			 Object [][]assetDtlQuan= MigrateExcelData.uploadExcelData(5, null,
			 MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(5));
			//Object[][] assetDtlQuan = null;
			Object [][]assetDtlAvailable= MigrateExcelData.uploadExcelData(6,
			 null, MigrateExcelData.NUMBER_DOUBLE_TYPE,
			 columnInformation.get(6));
			//Object[][] assetDtlAvailable = null;
			Object[][] statusMaster = new Object[4][2];
			statusMaster[0][0] = "U";
			statusMaster[0][1] = "Available";
			statusMaster[1][0] = "A";
			statusMaster[1][1] = "Assigned";
			statusMaster[2][0] = "L";
			statusMaster[2][1] = "Lost";
			statusMaster[3][0] = "D";
			statusMaster[3][1] = "Damaged";
			Object[][] assetStaus = MigrateExcelData.uploadExcelData(12,
					statusMaster, MigrateExcelData.MASTER_TYPE,
					columnInformation.get(12));
			Object[][] assetOwnedBy = MigrateExcelData.uploadExcelData(13,
					null, MigrateExcelData.STRING_TYPE, columnInformation
							.get(13));
			
			String propertiesQuery = "";
			Object[][] propertiesObj = null;
			if (!assetMaster.getAssetSubTypeCode().equals("")) {
				propertiesQuery = " SELECT NVL(PROPERTY_ATTRIBUTE,' '), PROPERTY_ID FROM HRMS_ASSET_PROPERTY_MASTER "
						+ " WHERE ASSET_SUBTYPE = "
						+ assetMaster.getAssetSubTypeCode()
						+ " ORDER BY PROPERTY_ID";

				propertiesObj = getSqlModel().getSingleResult(propertiesQuery);
			}
			int no_of_rows = 0;
			int no_of_cols = 0;
			Vector<Object[][]> propertiesV = new Vector<Object[][]>();
			if (propertiesObj != null && propertiesObj.length > 0) {

				for (int i = 1; i <= propertiesObj.length; i++) {
					Object[][] propertyObj = MigrateExcelData.uploadExcelData(
							16 + i, null, MigrateExcelData.STRING_TYPE,
							columnInformation.get(16+ i));
					no_of_rows = propertyObj.length;
					no_of_cols = propertiesObj.length;
					propertiesV.add(propertyObj);
				}
			}
			Object finalProp[][] = new Object[no_of_rows][no_of_cols];
			int count = 0;
			if (propertiesObj != null && propertiesObj.length > 0) {
				for (Iterator iterator = propertiesV.iterator(); iterator
						.hasNext();) {
					Object[][] property_Obj = (Object[][]) iterator.next();
					int inner_count = 0;
					for (int i = 0; i < property_Obj.length; i++) {
						finalProp[inner_count++][count] = String
								.valueOf(property_Obj[i][0]);
					}
					count++;
				}
			}
			int totalPropertyValues = 0;
			if (inventoryCode != null && inventoryCode.length > 0) {
				totalPropertyValues = inventoryCode.length * no_of_cols;
			}
			boolean res = MigrateExcelData.isFileToBeUploaded();
			if (res) {
				String assetTypeCheck = "";
				String assetSubTypeCheck = "";
				String vendorCheck = "";
				String purchaseDateCheck = "";
				String purchasePriceCheck = "";
				String warehouseCheck = "";
				String assignedEmployeeCheck = "";
				
				assetTypeCheck = String.valueOf(assetTypeObj[0][0]);
				assetSubTypeCheck = String.valueOf(assetSubTypeObj[0][0]);

				try {
					// vendorCheck = String.valueOf(vendorObj[0][0]);
					vendorCheck = String.valueOf("");
				} catch (Exception e) {
					vendorCheck = "";
				}
				try {
					purchaseDateCheck = String.valueOf(purchaseDateObj[0][0]);
				} catch (Exception e) {
					purchaseDateCheck = "";
				}
				try {
					purchasePriceCheck = String.valueOf(purchasePriceObj[0][0]);
				} catch (Exception e) {
					purchasePriceCheck = "";
				}
				try {
					warehouseCheck = String.valueOf(wareHouseObj[0][0]);
				} catch (Exception e) {
					warehouseCheck = "";
				}
				
				
				try {
					assignedEmployeeCheck = String.valueOf(assignedEmployeeObj[0][0]);
				} catch (Exception e) {
					assignedEmployeeCheck = "";
				}
				
				Object[][] maxAssetMasterCodeObj = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(ASSET_MASTER_CODE),0)+1 FROM HRMS_ASSET_MASTER");
				int maxAssetCode = Integer.parseInt(String
						.valueOf(maxAssetMasterCodeObj[0][0]));
				// Object [][]assetInsertObj=null;
				Object[][] assetDtlInsertObj = new Object[assetTypeObj.length][15];
				// Vector <Object []>assetInsertVect=new Vector<Object []>();

				String assetHdrInsertQuery = "INSERT INTO HRMS_ASSET_MASTER ( ASSET_CATEGORY_CODE, ASSET_SUBTYPE_CODE, ASSET_QUANTITY,"
						+ " ASSET_PURCHASE_DATE, ASSET_PURCHASE_PRICE, ASSET_ASSIGNED, ASSET_AVAILABLE, ASSET_DESCRIPTION,"
						+ " ASSET_MASTER_CODE, ASSET_STATUS, ASSET_VENDOR ) VALUES ( ?,?,?,TO_DATE(?,'MM-DD-YYYY'),?,?,?,?,?,?,?)";

				String assetDtlInsertQuery = "INSERT INTO HRMS_ASSET_MASTER_DTL ( ASSET_MASTER_CODE, ASSET_INVENTORY_CODE, ASSET_WAREHOUSE_CODE,"
						+ " ASSET_ASSISGN_FLAG, ASSET_QUANTITY, ASSET_AVAILABLE, ASSET_IS_ON_LEASE, ASSET_LEASE_RENEW_DATE,"
						+ " ASSET_IS_INSURED, ASSET_INSURANCE_RENEW_DATE,ASSET_OWNED_BY,ASSET_ASSIGNED_EMP, ASSET_ASSIGNED_VENDOR, ASSET_ITEM_CODE,ASSET_PURCHASE_VENDOR ) VALUES (?,?,?,?,?,?,?,TO_DATE(?,'MM-DD-YYYY'),?,TO_DATE(?,'MM-DD-YYYY'),?,?,?,?,?)";

				String propertyQuery = "INSERT INTO HRMS_ASSET_PROPERTIES(ASSET_INVENTORY_CODE, ASSET_PROPERTY_ID, ASSET_PROPERTY_VALUE, ASSET_MASTER_CODE) VALUES(?,?,?,?)";

				Object[][] assetMasterInsertObj = new Object[1][11];
				assetTypeCheck = String.valueOf(assetTypeObj[0][0]);
				assetSubTypeCheck = String.valueOf(assetSubTypeObj[0][0]);
				vendorCheck = String.valueOf("");
				purchaseDateCheck = String.valueOf(purchaseDateObj[0][0]);
				purchasePriceCheck = String.valueOf(purchasePriceObj[0][0]);
				warehouseCheck = String.valueOf(wareHouseObj[0][0]);

				assetMasterInsertObj[0][0] = String.valueOf(assetTypeObj[0][0]);
				assetMasterInsertObj[0][1] = String
						.valueOf(assetSubTypeObj[0][0]);
				assetMasterInsertObj[0][2] = String
						.valueOf(assetTotalQua[0][0]);
				assetMasterInsertObj[0][3] = String
						.valueOf(purchaseDateObj[0][0]);
				assetMasterInsertObj[0][4] = String
						.valueOf(purchasePriceObj[0][0]);
				assetMasterInsertObj[0][5] = Double.parseDouble(String
						.valueOf(assetTotalQua[0][0]))
						- Double.parseDouble(String
								.valueOf(assetTotalAvailable[0][0]));
				assetMasterInsertObj[0][6] = String
						.valueOf(assetTotalAvailable[0][0]);
				assetMasterInsertObj[0][7] = String.valueOf(assetDesc[0][0]);
				assetMasterInsertObj[0][8] = String.valueOf(maxAssetCode);

				if (String.valueOf(assetTotalAvailable[0][0]).equals("0.0")
						|| String.valueOf(assetTotalAvailable[0][0])
								.equals("0")) {
					assetMasterInsertObj[0][9] = "A";
				} else {
					assetMasterInsertObj[0][9] = "U";
				}
				assetMasterInsertObj[0][10] = String.valueOf("");

				Object[][] assetItemCodeObj = getSqlModel()
						.getSingleResult(
								" SELECT NVL(MAX(ASSET_ITEM_CODE),0)+1 FROM HRMS_ASSET_MASTER_DTL ");

				int counter = Integer.parseInt(String
						.valueOf(assetItemCodeObj[0][0]));

				for (int i = 0; i < assetTypeObj.length; i++) {
					assetDtlInsertObj[i][0] = String.valueOf(maxAssetCode);
					assetDtlInsertObj[i][1] = String
							.valueOf(inventoryCode[i][0]);
					assetDtlInsertObj[i][2] = String
							.valueOf(wareHouseObj[i][0]);
					assetDtlInsertObj[i][3] = String.valueOf(assetStaus[i][0]);
					 assetDtlInsertObj[i][4]
					 =String.valueOf(assetDtlQuan[i][0]);
					//assetDtlInsertObj[i][4] = "";
					 assetDtlInsertObj[i][5]
					 =String.valueOf(assetDtlAvailable[i][0]);
					//assetDtlInsertObj[i][5] = "";

					if (String.valueOf(leaseRenewDate[i][0]).equals("")) {
						assetDtlInsertObj[i][6] = "N";
					} else {
						assetDtlInsertObj[i][6] = "Y";
					}

					assetDtlInsertObj[i][7] = String
							.valueOf(leaseRenewDate[i][0]);

					if (String.valueOf(insuranceRenewDate[i][0]).equals("")) {
						assetDtlInsertObj[i][8] = "N";
					} else {
						assetDtlInsertObj[i][8] = "Y";
					}
					assetDtlInsertObj[i][9] = String
							.valueOf(insuranceRenewDate[i][0]);

					if (!String.valueOf(assetOwnedBy[i][0]).equals("")) {
						assetDtlInsertObj[i][10] = String.valueOf(
								assetOwnedBy[i][0]).charAt(0);
					} else {
						assetDtlInsertObj[i][10] = "";
					}
					
					if (!String.valueOf(assetOwnedBy[i][0]).equals("")) {
						if(String.valueOf(assetOwnedBy[i][0]).equalsIgnoreCase("Employee"))
						{
							assetDtlInsertObj[i][11] = String.valueOf(assignedEmployeeObj[i][0]);
							assetDtlInsertObj[i][12] ="";
						}
						else if(String.valueOf(assetOwnedBy[i][0]).equalsIgnoreCase("Vendor")) {
							assetDtlInsertObj[i][11] ="";
							assetDtlInsertObj[i][12] = String.valueOf(vendorObj[i][0]);
							
						}
						else
						{
							assetDtlInsertObj[i][11] = "";
							assetDtlInsertObj[i][12] ="";
						}
					}
					
					System.out.println("assignedEmployeeObj[i][0]------------- " +assignedEmployeeObj[i][0]);
			
					
					
					
					/*		if (!String.valueOf(assignedEmployeeObj[i][0]).equals("")) {
						Object[][] assignedEmpObj = getSqlModel()
								.getSingleResult(
										"SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN ='"
												+ String
														.valueOf(assignedEmployeeObj[i][0])
												+ "'");
						System.out.println("assignedEmpObj[0][0]------------- " +assignedEmpObj[0][0]);
						
						assetDtlInsertObj[i][11] = String
								.valueOf(assignedEmpObj[0][0]);
					}*/
					System.out.println("vendorObj[i][0]------------- " +vendorObj[i][0]);
					

					if (assetItemCodeObj != null && assetItemCodeObj.length > 0) {
						assetDtlInsertObj[i][13] = counter++;
					}
					
					if (PurchaseVendorObj != null && PurchaseVendorObj.length > 0) {
						assetDtlInsertObj[i][14] =PurchaseVendorObj[i][0] ;
					}

				} // end of for loop

				/* Inserting property & its values */

				Object[][] finalPropertyObject = new Object[totalPropertyValues][4];
				if (inventoryCode != null && inventoryCode.length > 0) {
					int finalCount = 0;
					if (propertiesObj != null && propertiesObj.length > 0) {
						for (int j = 0; j < inventoryCode.length; j++) {
							for (int innerMost = 0; innerMost < propertiesObj.length; innerMost++) {
								finalPropertyObject[finalCount][0] = String
										.valueOf(inventoryCode[j][0]);// Inventory
								// code
								finalPropertyObject[finalCount][1] = String
										.valueOf(propertiesObj[innerMost][1]);// property
								// ID
								finalPropertyObject[finalCount][2] = String
										.valueOf(finalProp[j][innerMost]); // finalProp
								// Object
								// property
								// value
								finalPropertyObject[finalCount][3] = String
										.valueOf(maxAssetCode);// Asset Master
								// Code
								finalCount++;
							}
						}
					}
				}
				int totalQueries = 0;
				if (propertiesObj != null && propertiesObj.length > 0) {
					totalQueries = 3;
				} else {
					totalQueries = 2;
				}
				Vector<Object[][]> dataVector = new Vector<Object[][]>();
				dataVector.add(assetMasterInsertObj);
				dataVector.add(assetDtlInsertObj);

				Object[] queryArray = new Object[totalQueries];
				queryArray[0] = assetHdrInsertQuery;
				queryArray[1] = assetDtlInsertQuery;
				if (propertiesObj != null && propertiesObj.length > 0) {
					queryArray[2] = propertyQuery;
					dataVector.add(finalPropertyObject);
				}

				boolean result = false;

				try {
					result = getSqlModel().multiExecute(queryArray, dataVector);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result) {
					assetMaster.setStatus("Success");
				} else {
					assetMaster.setStatus("Fail");
					assetMaster
							.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
									+ "Upload the sheet again to transfer the data.");
				}
			} else {
				assetMaster.setStatus("Fail");
				assetMaster
						.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of outermost if
	
	
	public void checkForDuplicateInventoryCodes()
	{
		try {
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
