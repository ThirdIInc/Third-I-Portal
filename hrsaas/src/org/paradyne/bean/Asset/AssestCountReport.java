/**
 * 
 */
package org.paradyne.bean.Asset;

import org.paradyne.lib.BeanBase;

/**
 * @author Anantha lakshmi
 * 
 */
public class AssestCountReport extends BeanBase {

	private String assetTypeName = "";
	private String assetSubTypeName= "";
	private String wareHouseName = "";
	private String assetTypeCode = "";
	private String assetSubTypeCode = "";
	private String wareHouseCode = "";
	
	public String getAssetTypeCode() {
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}
	public String getAssetSubTypeCode() {
		return assetSubTypeCode;
	}
	public void setAssetSubTypeCode(String assetSubTypeCode) {
		this.assetSubTypeCode = assetSubTypeCode;
	}
	public String getWareHouseCode() {
		return wareHouseCode;
	}
	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	public String getAssetSubTypeName() {
		return assetSubTypeName;
	}
	public void setAssetSubTypeName(String assetSubTypeName) {
		this.assetSubTypeName = assetSubTypeName;
	}
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
}
