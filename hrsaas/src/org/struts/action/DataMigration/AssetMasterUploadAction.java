package org.struts.action.DataMigration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.paradyne.bean.DataMigration.AssetMasterUpload;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.DataMigration.AssetMasterUploadModel;
import org.struts.lib.ParaActionSupport;

public class AssetMasterUploadAction extends ParaActionSupport {

	static Logger logger = Logger.getLogger(AssetMasterUploadAction.class);
	
	AssetMasterUpload assetMaster;
	
	public void prepare_local() throws Exception {
		assetMaster = new AssetMasterUpload();
		assetMaster.setMenuCode(1048);
	}
	
	public Object getModel() {
		return assetMaster;
	}
	
	public String input() {
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"
					+ poolName + "/";
			assetMaster.setDataPath(dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
/*	OLD METHOD
	public void downloadTemplate() {
		try {
			String templateName = request.getParameter("templateName");
			String dataPath = getText("data_path");
			String filePath = dataPath + "\\DataMigration\\Templates\\" + templateName;
			System.out.println("####### FILE PATH ##############"+filePath);
			MigrateExcelData.openTemplate(request, response, templateName, filePath);
		} catch(Exception e) {
			logger.error("Exception in downloadTemplate in action:" + e);
		}
	}*/
	
	public void downloadTemplate(){
		try {
			AssetMasterUploadModel model = new AssetMasterUploadModel();
			model.initiate(context, session);
			model.generateXlsTemplate(assetMaster, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	public String uploadAssetMasterDetails() {
		try {
			AssetMasterUploadModel model = new AssetMasterUploadModel();
			model.initiate(context, session);
			model.uploadAssetMasterDetails(response,request,assetMaster);
			//model.uploadAssetMasterTemplate(response,request,assetMaster);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String reset() {
		try {
			assetMaster.setUploadFileName("");
			assetMaster.setUploadName("");
			assetMaster.setStatus("Fail");
			assetMaster.setNote("");
			assetMaster.setAssetCategoryCode("");
			assetMaster.setAssetCategoryName("");
			assetMaster.setAssetSubTypeCode("");
			assetMaster.setAssetSubTypeName("");
		} catch(Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}
	
	public String f9assetMasterCode() {
		
		try {
			String query = " SELECT ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER "
				+ " ORDER BY ASSET_MASTER_CODE ";
			String[] headers = { "Asset Maseter Code" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "assetMasterCode" };
			int[] columnIndex = { 0 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public String f9assetCategory() {

		try {
			String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,'') FROM HRMS_ASSET_CATEGORY "
					+ "  WHERE ASSET_ISACTIVE = 'Y' ORDER BY ASSET_CATEGORY_CODE ";
			String[] headers = { "Category Code", "Category Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "assetCategoryCode", "assetCategoryName"};
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9assetSubType() {
		try {
			logger.info("asset category code in F9==="
					+ assetMaster.getAssetCategoryCode());
			String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,'') FROM HRMS_ASSET_SUBTYPE "
					+ " WHERE ASSET_SUBTYPE_ISACTIVE = 'Y' AND ASSET_CATEGORY_CODE ="
					+ assetMaster.getAssetCategoryCode()
					+ " ORDER BY ASSET_SUBTYPE_CODE ";
			String[] headers = { "Asset Sub Type Code", "Asset Sub Type"};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "assetSubTypeCode", "assetSubTypeName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
}
