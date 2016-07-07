package org.paradyne.bean.DataMigration;

import org.paradyne.lib.BeanBase;

public class AssetMasterUpload extends BeanBase {

	private String dataPath;
	private String status = "Fail";
	private String note = "";
	private String uploadName;
	private String uploadFileName;
	
	private String assetMasterCode;
	private String assetCategoryName;
	private String assetCategoryCode;
	private String assetSubTypeCode;
	private String assetSubTypeName;
	


	public String getAssetCategoryName() {
		return assetCategoryName;
	}
	public void setAssetCategoryName(String assetCategoryName) {
		this.assetCategoryName = assetCategoryName;
	}
	public String getAssetCategoryCode() {
		return assetCategoryCode;
	}
	public void setAssetCategoryCode(String assetCategoryCode) {
		this.assetCategoryCode = assetCategoryCode;
	}
	public String getAssetSubTypeCode() {
		return assetSubTypeCode;
	}
	public void setAssetSubTypeCode(String assetSubTypeCode) {
		this.assetSubTypeCode = assetSubTypeCode;
	}
	public String getAssetSubTypeName() {
		return assetSubTypeName;
	}
	public void setAssetSubTypeName(String assetSubTypeName) {
		this.assetSubTypeName = assetSubTypeName;
	}

	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getAssetMasterCode() {
		return assetMasterCode;
	}
	public void setAssetMasterCode(String assetMasterCode) {
		this.assetMasterCode = assetMasterCode;
	}
	
}
