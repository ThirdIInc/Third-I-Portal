/**
 * 
 */
package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetAssignment;
import org.paradyne.model.Asset.AssetAssignmentModel;
import org.paradyne.model.Asset.AssetEmployeeModel;
import org.paradyne.model.Asset.RateListModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 *
 */
public class AssetAssignmentAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	AssetAssignment assetAssign;
	
	public void prepare_local() throws Exception {
		assetAssign = new AssetAssignment();
		assetAssign.setMenuCode(635);

	}
	public void prepare_withLoginProfileDetails()throws Exception{
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		assetAssign.setPendingFlag("true");
		model.showRecords(assetAssign,request);
		//model.checkInventoryAvail(assetAssign);
		model.terminate();
	}
	
	public String callPage() throws Exception {
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		assetAssign.setPendingFlag("true");
		model.showRecords(assetAssign,request);
		//model.checkInventoryAvail(assetAssign);
		model.terminate();
		
		return INPUT;

	}
	public String checkRequest(){
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		
		//model.checkInventoryAvail(assetAssign);
		
		String status=request.getParameter("status");
		if(status.equals("P")){
			assetAssign.setPendingFlag("true");
			model.showRecords(assetAssign,request);
		}else if (status.equals("R")){
			assetAssign.setPendingFlag("false");
			assetAssign.setShowFlag("false");
			model.requestFromOther(assetAssign,request);
		}
		model.terminate();
		return SUCCESS;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return assetAssign;
	}

	public AssetAssignment getAssetAssign() {
		return assetAssign;
	}

	public void setAssetAssign(AssetAssignment assetAssign) {
		this.assetAssign = assetAssign;
	}
	public String f9actionInventory() throws Exception {
		String invCode = "";
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		
		String wareHouseCode = model.getWarehouseCodes(assetAssign);
		/*
		 * filter the Inventory code from selection  which is already present
		 * in the list
		 * 
		 * 
		 */
		try {
			for (int i = 1; i <= Integer.parseInt(assetAssign
					.getTableList()); i++)
				invCode += "'" + request.getParameter("inventoryCode" + i)
						+ "',";
			invCode = (invCode.substring(0, invCode.length() - 1)).replaceAll(
					"''", "' '");
		} catch (Exception e) {
			invCode = "' '";
		}
		
		
		logger.info("wareHouseCode in the list ===" + wareHouseCode);
		logger.info("invcode in the list ===" + invCode);
		logger.info("asset category code in f9actionInventory==="+ assetAssign.getHiddenCategoryCode());
		logger.info("asset SubType code in f9actionInventory==="+ assetAssign.getHiddenSubTypeCode());

		String query = "SELECT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE FROM HRMS_ASSET_MASTER_DTL  "
				+ " INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
				+ " WHERE ASSET_ASSISGN_FLAG ='U' AND ASSET_CATEGORY_CODE ="+ assetAssign.getHiddenCategoryCode()+" AND ASSET_SUBTYPE_CODE ="+ assetAssign.getHiddenSubTypeCode()
				+ " AND HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE NOT IN ("+ invCode+ ") AND ASSET_WAREHOUSE_CODE IN("+ wareHouseCode+ ")";

		

		String[] headers={getMessage("asstCode"), getMessage("assetInvCd"),getMessage("assetQtyAvailable")};
		String[] headerWidth = { "20", "60", "20" };

		String[] fieldNames = {
				"assetMasterCode" + assetAssign.getRowId(),
				"inventoryCode" + assetAssign.getRowId(),
				"quantityAvailable" + assetAssign.getRowId() };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String transferAsset(){
		logger.info("inside transferAsset action");
		AssetAssignmentModel model = new AssetAssignmentModel();
		boolean result=false;
		String invCodeArray[] = new String[Integer.parseInt(request.getParameter("tableList"))];
		String masterCodeArray[] = new String[Integer.parseInt(request.getParameter("tableList"))];
		String quantityAssigned[] = request.getParameterValues("quantityAssigned");
		String sourceWarehouse[] = request.getParameterValues("sourceWarehouse");
		String destWarehouse[] = request.getParameterValues("destWarehouse");
		String inventoryCode="";
		String masterCode="";
		String reqCode[] = request.getParameterValues("reqCode");
		String invType[] = request.getParameterValues("returnFlagIterator");
		model.initiate(context, session);
		for (int i = 1; i <= Integer.parseInt((request.getParameter("tableList"))); i++) {
			inventoryCode = request.getParameter("inventoryCode" + i);
			masterCode = request.getParameter("assetMasterCode" + i);
			invCodeArray[i - 1] = inventoryCode;
			masterCodeArray[i - 1] = masterCode;
		}
		result=model.updateWarehouseCode(invCodeArray,masterCodeArray,quantityAssigned,sourceWarehouse,reqCode,destWarehouse);
		if(result){
			addActionMessage("Asset transfered successfully !");
		}else {
			addActionMessage("Asset cant be transfred !");
		}
		assetAssign.setPendingFlag("false");
		model.requestFromOther(assetAssign,request);
		model.terminate();
		return SUCCESS;
	}
	public String cancelRequest(){
		boolean result= false;
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		result = model.cancelRequest(assetAssign.getHiddenReqCode());
		if(result){
			addActionMessage("Asset request canceled.");
		}else{
			if(result){
				addActionMessage("Asset request can't be canceled.");
			}
		}
		assetAssign.setPendingFlag("false");
		model.requestFromOther(assetAssign,request);
		model.terminate();
		return SUCCESS;
	}
	
	public String f9actionEmp() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  ";
		
		
		query += getprofileQuery(assetAssign);
		
		query += " AND EMP_STATUS='S' ORDER BY EMP_ID";

	
		
		
		
		String[] headers={getMessage("employee.id"), getMessage("employee")};
		
		
		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "selectedEmpId", "selectedEmpName", "selectedEmpCode" };

		int[] columnIndex = { 0, 1, 2 };

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "true";

		String submitToMethod = "AssetAssignment_showSelected.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String showSelected(){
		AssetAssignmentModel model = new AssetAssignmentModel();
		model.initiate(context, session);
		model.showSelectedRecords(assetAssign);
		assetAssign.setPendingFlag("true");
		model.terminate();
		return SUCCESS;
	}
	
}
