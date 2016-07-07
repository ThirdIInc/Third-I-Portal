package org.struts.action.Asset;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Asset.AssetDirectAssignment;
import org.paradyne.model.Asset.AssetDirectAssignmentModel;
import org.paradyne.model.admin.master.TaskModel;

public class AssetDirectAssignmentAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AssetDirectAssignmentAction.class);

	AssetDirectAssignment assigndirassign;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		assigndirassign = new AssetDirectAssignment();
		assigndirassign.setMenuCode(2055);
	

	}

	public String input() {
		try {
			AssetDirectAssignmentModel model = new AssetDirectAssignmentModel();
			model.initiate(context, session);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return assigndirassign;
	}

	public AssetDirectAssignment getAssigndirassign() {
		return assigndirassign;
	}

	public void setAssigndirassign(AssetDirectAssignment assigndirassign) {
		this.assigndirassign = assigndirassign;
	}

	public String addNew() {
		try {
			
			assigndirassign.setAssetassignmentid("");

			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String resetList() {
		assigndirassign.setAssetcategorycode("");
		assigndirassign.setAssetcategory("");
		assigndirassign.setAssetsubtypecode("");
		assigndirassign.setAssetsubtype("");
		//assigndirassign.setEmployeecode("");
		//assigndirassign.setEmployeename("");
		//assigndirassign.setVendorid("");
		//assigndirassign.setVendorname("");
		assigndirassign.setAssignDate("");
		assigndirassign.setAssetassignmentid("");
		//assigndirassign.setUsertypeRadioOptionValue("");
		assigndirassign.setInventoryname("");
		assigndirassign.setInventorycode("");
		assigndirassign.setAssignquantity("");
		assigndirassign.setAvailablequantity("");
		assigndirassign.setUsertype("");
		assigndirassign.setAssetcode("");

		return SUCCESS;
	}

	
	public String reset() {
		assigndirassign.setAssetcategorycode("");
		assigndirassign.setAssetcategory("");
		assigndirassign.setAssetsubtypecode("");
		assigndirassign.setAssetsubtype("");
		assigndirassign.setEmployeecode("");
		assigndirassign.setEmployeename("");
		assigndirassign.setVendorid("");
		assigndirassign.setVendorname("");
		assigndirassign.setAssignDate("");
		assigndirassign.setAssetassignmentid("");
		assigndirassign.setUsertypeRadioOptionValue("");
		assigndirassign.setInventoryname("");
		assigndirassign.setInventorycode("");
		assigndirassign.setAssignquantity("");
		assigndirassign.setAvailablequantity("");
		assigndirassign.setUsertype("");
		assigndirassign.setAssetcode("");

		return SUCCESS;
	}

	public String save() {
		try {

			AssetDirectAssignmentModel model = new AssetDirectAssignmentModel();
			model.initiate(context, session);
			boolean result;

			result = model.save(assigndirassign);

			if (result) {
				//addActionMessage(getMessage("save"));
				addActionMessage("Record assign successfully");
				resetList();
			} else {
				addActionMessage(getMessage("save.error"));
			}

			//model.setMealVoucherDtl(mealVoucher);
			assigndirassign.setEnableAll("Y");
			
			model.showList(assigndirassign);
			
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String showList()
	{
		try {
			AssetDirectAssignmentModel model = new AssetDirectAssignmentModel();
			model.initiate(context, session);
			model.showList(assigndirassign);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String f9assetcategory() throws Exception {

		String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,''),NVL('',' '),NVL('',' ') FROM HRMS_ASSET_CATEGORY "
				+ "  ORDER BY ASSET_CATEGORY_CODE ";

		String[] headers = { getMessage("asst.code"), getMessage("assetCat") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "assetcategorycode", "assetcategory" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9assetcode() throws Exception {

		String query = " SELECT  ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER  ORDER BY ASSET_MASTER_CODE  ";

		String[] headers = { getMessage("assetDirAssign.assetcode") };

		String[] headerWidth = { "20" };

		String[] fieldNames = { "assetcode" };

		int[] columnIndex = { 0 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	 

	public String f9actionSubType() throws Exception {

		logger.info("asset category code in F9==="
				+ assigndirassign.getAssetcategorycode());
		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,''),ASSET_INVENTORY_TYPE FROM HRMS_ASSET_SUBTYPE "
				//+" LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
				+ " WHERE ASSET_CATEGORY_CODE ="
				+ assigndirassign.getAssetcategorycode()
				+ " ORDER BY ASSET_SUBTYPE_CODE ";

		String[] headers = { getMessage("asst.code"),
				getMessage("asset.subType") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "assetsubtypecode", "assetsubtype" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionEmp() throws Exception {

		String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' "
				+ "'||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_ID"
				+ " FROM " + " HRMS_EMP_OFFC ";
		
		query += getprofileQuery(assigndirassign);

		query += " AND EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "employeeToken", "employeename","employeecode" };

		int[] columnIndex = { 0, 1 ,2 };

		String submitFlag = "true";

		String submitToMethod = "AssetDirectAssign_showList.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9vendor() throws Exception {

		String query = " SELECT VENDOR_CODE,VENDOR_NAME FROM  HRMS_VENDOR  where VENDOR_TYPE = 'E' AND VENDOR_ISACTIVE='Y' ORDER BY VENDOR_CODE ";

		String[] headers = { "vendor code", "vendor name" };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "vendorid", "vendorname" };

		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";

		String submitToMethod = "AssetDirectAssign_showList.action";


		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String callPage() throws Exception {
		try {
			AssetDirectAssignmentModel model = new AssetDirectAssignmentModel();
			model.initiate(context, session);

			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	
	public String f9inventory() throws Exception {

		String query = " SELECT HRMS_ASSET_CATEGORY.ASSET_CATEGORY_NAME,HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_NAME,ASSET_INVENTORY_CODE,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_ITEM_CODE " 
				+"FROM HRMS_ASSET_MASTER_DTL " 
				+"INNER JOIN HRMS_ASSET_MASTER  ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) " 				 
				+"INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE=HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE) "
				+"INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE) "
				+ "INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE "
				+ "AND WAREHOUSE_RESPONSIBLE_PERSON="
				+ assigndirassign.getUserEmpId()
				+ ") "
				+ "WHERE HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE!=0 ORDER BY ASSET_ITEM_CODE ";

		String[] headers = {"Asset Category","Asset SubType", getMessage("assetDirAssign.inventory"),
				"Available Qualtity" };

		String[] headerWidth = { "20","30","30","20" };

		String[] fieldNames = {"assetcategory","assetsubtype", "inventorycode", "availablequantity","assetcategorycode","assetsubtypecode","assetautoid" };

		int[] columnIndex = { 0, 1,2,3,4,5,6 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
