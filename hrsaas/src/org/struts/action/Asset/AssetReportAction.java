/**
 * 
 */
package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetReport;
import org.paradyne.model.Asset.AssetReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 *
 */
public class AssetReportAction extends ParaActionSupport {
	AssetReport assetReport;

	public void prepare_local() throws Exception {
		assetReport= new AssetReport();
		assetReport.setMenuCode(657);

	}

	public Object getModel() {
		
		return assetReport;
	}

public String f9actionWarehouse() throws Exception {
		
		String query = " SELECT WAREHOUSE_CODE,WAREHOUSE_NAME  FROM HRMS_WAREHOUSE_MASTER ORDER BY WAREHOUSE_CODE";
		
		
		String[] headers={getMessage("assetWareHouseCd"), getMessage("assetWaraHouse")};
		String[] headerWidth={"20", "80"};
		
		
		String[] fieldNames={"warehouseCode","warehouseName"};
		
		int[] columnIndex={0,1};
		
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

public String f9actionVendor() throws Exception {
	
	String query = " SELECT VENDOR_CODE, VENDOR_NAME FROM HRMS_VENDOR  where VENDOR_TYPE='E' ORDER BY VENDOR_CODE ";
	
    String[] headers={getMessage("assetVendCd"), getMessage("assetVendorName")};
	String[] headerWidth={"20", "80"};
	
	
	String[] fieldNames={"vendorCode","vendorName"};
	
	int[] columnIndex={0,1};
	
	
	String submitFlag = "false";
	
	
	String submitToMethod="";
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}

public String f9actionBranch() throws Exception {
	
	String query = " SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID ";
	String[] headers={getMessage("branch.code"), getMessage("branch")};	
	String[] headerWidth={"20", "80"};
	String[] fieldNames={"branchCode","branchName"};
	int[] columnIndex={0,1};
	String submitFlag = "false";
	String submitToMethod="";
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	return "f9page";
}
public String f9actionCategory() throws Exception {
	
	String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,'') FROM HRMS_ASSET_CATEGORY " 
			//+" LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
			+" ORDER BY ASSET_CATEGORY_CODE ";		
	
	String[] headers={getMessage("assetCd"), getMessage("assetSub")};
	String[] headerWidth={"20", "80"};
	
	String[] fieldNames={"categoryCode","categoryName"};
	
	
	int[] columnIndex={0, 1};
	
	
	String submitFlag = "false";
	
	
	String submitToMethod="";
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}
public String f9actionSubType() throws Exception {
	
		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,'') FROM HRMS_ASSET_SUBTYPE " ;
				//+" LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
			if(!assetReport.getCategoryCode().equals("")){
				query +="WHERE ASSET_CATEGORY_CODE="+assetReport.getCategoryCode();
			}
		
		query +=" ORDER BY ASSET_SUBTYPE_CODE ";		
		
		
		String[] headers={getMessage("assetCd"), getMessage("assetSubType")};
		
		String[] headerWidth={"20", "80"};
		
		String[] fieldNames={"subTypeCode","subTypeName"};
		
		
		int[] columnIndex={0, 1};
		
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String changeReturnType(){
		if(assetReport.getReturnType().equals("R")){
			assetReport.setReturnFlag("true");
		}else {
			assetReport.setReturnFlag("false");
		}
		return SUCCESS;
	}
	public String reset(){
		assetReport.setAvailability("");
		assetReport.setFromDate("");
		assetReport.setReportType("");
		assetReport.setReturnFlag("true");
		assetReport.setReturnType("");
		assetReport.setSubTypeCode("");
		assetReport.setSubTypeName("");
		assetReport.setToDate("");
		assetReport.setVendorCode("");
		assetReport.setVendorName("");
		assetReport.setWarehouseCode("");
		assetReport.setWarehouseName("");
		assetReport.setCategoryCode("");
		assetReport.setCategoryName("");
	
		assetReport.setLeaFromDate("");
		assetReport.setLeaToDate("");
		assetReport.setInsFromDate("");
		assetReport.setInsToDate("");
		
		return SUCCESS;
	}
	public String report(){
		try {
			AssetReportModel model = new AssetReportModel();
			model.initiate(context, session);
			model.getReport(request, response, assetReport);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public AssetReport getAssetReport() {
		return assetReport;
	}

	public void setAssetReport(AssetReport assetReport) {
		this.assetReport = assetReport;
	}

}
