package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetMaster;
import org.paradyne.model.Asset.*;
import org.paradyne.model.admin.master.CadreModel;
import org.struts.lib.ParaActionSupport;

public class AssetMasterAction extends ParaActionSupport {
	AssetMaster assetmaster;

	@Override
	public void prepare_local() throws Exception {
		assetmaster = new AssetMaster();
		assetmaster.setMenuCode(379);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		AssetMasterModel model = new AssetMasterModel();
		model.initiate(context, session);
		model.Data(assetmaster, request);
		model.terminate();
	}

	public String callPage() throws Exception {
		AssetMasterModel model = new AssetMasterModel();
		model.initiate(context, session);
		model.Data(assetmaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public Object getModel() {
		// TODO Auto-generated method stub
		return assetmaster;
	}

	public String input() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			// model.initiate(context, session);
			// model.Data(assetmaster, request);
			getNavigationPanel(1);
			// model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in input");
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "showData";
		} catch (Exception e) {
			logger.info("Exception in addNew");
			return null;
		}
	}

	public String save() throws Exception {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			String invCode = "";
			// logger.info("assetmaster.getListLength()========="+assetmaster.getListLength());
			String invCodeArray[] = new String[Integer.parseInt(assetmaster
					.getListLength())];
			String warehouseCode[] = request
					.getParameterValues("warehouseCodeIterator");
			String quantityIterator[] = request
					.getParameterValues("quantityIterator");
			String availableIterator[] = request
					.getParameterValues("availableIterator");
			String assignFlag[] = request
					.getParameterValues("assignFlagIterator");
			String leasedFlag[] = request.getParameterValues("isLeased");
			String leasedDate[] = request.getParameterValues("leaseDate");
			String insuranceFlag[] = request.getParameterValues("isInsured");
			String insureanceDate[] = request.getParameterValues("insureDate");
			// logger.info("leasedFlag Length - "+leasedFlag.length);
			// logger.info("leasedDate Length - "+leasedDate.length);
			// logger.info("insuranceFlag Length - "+insuranceFlag.length);
			// logger.info("insureanceDate Length - "+insureanceDate.length);
			/**
			 * Added by vishwambhar
			 */
			String inventoryCodeItt[] = request
					.getParameterValues("inventoryCodeItt");
			String propertyCode[] = request.getParameterValues("propertyCode");
			Object[][] rows = null;
			if ((inventoryCodeItt != null && inventoryCodeItt.length > 0)
					&& (propertyCode != null && propertyCode.length > 0)) {
				rows = new Object[inventoryCodeItt.length][propertyCode.length];
				for (int i = 0; i < inventoryCodeItt.length; i++) {

					rows[i] = request.getParameterValues(String.valueOf(i));

				}
			}
			for (int i = 1; i <= Integer.parseInt(assetmaster.getListLength()); i++) {
				invCode = request.getParameter("inventoryCodeIterator" + i);
				invCodeArray[i - 1] = invCode;

			}
			boolean result;
			if (assetmaster.getCode().equals("")) {
				logger.info("before add method");
				result = model.save(assetmaster, invCodeArray, warehouseCode,
						assignFlag, quantityIterator, availableIterator,
						leasedFlag, leasedDate, insuranceFlag, insureanceDate,
						rows, inventoryCodeItt, propertyCode);
				logger.info("result===" + result);
				if (result) {
					addActionMessage(getMessage("save"));
					// reset();
				} else {
					addActionMessage(getMessage("save.error"));
					showInventoryList();
				}
			} else {
				result = model.update(assetmaster, invCodeArray, warehouseCode,
						assignFlag, quantityIterator, availableIterator,
						leasedFlag, leasedDate, insuranceFlag, insureanceDate,
						rows, inventoryCodeItt, propertyCode);
				if (result) {
					addActionMessage(getMessage("update"));
				} else {
					addActionMessage(getMessage("update.error"));
					showInventoryList();
				}
			}
			getNavigationPanel(3);
			assetmaster.setEnableAll("N");
			model.Data(assetmaster, request);
			assetRecord();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showData";
	}

	public String delete() {
		AssetMasterModel model = new AssetMasterModel();
		model.initiate(context, session);
		boolean result = model.delete(assetmaster);
		model.Data(assetmaster, request);
		if (result) {
			addActionMessage(getMessage("delete"));
			reset();
		} else {
			addActionMessage(getMessage("del.error"));
			showInventoryList();
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String deleteCheckedRecords() {
		try {
			String[] code = request.getParameterValues("hdeleteCode");
			getNavigationPanel(1);
			AssetMasterModel model = new AssetMasterModel();

			model.initiate(context, session);
			// logger.info("code -- "+code.toString());
			boolean result = model.deleteCheckedRecords(assetmaster, code);

			if (result) {
				addActionMessage(getText("delMessage", ""));
				// reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			reset();
			getNavigationPanel(1);
			model.Data(assetmaster, request);
			model.terminate();

			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in deleteChecked Records");
			return null;
		}
	}

	public String cancel() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			reset();
			model.initiate(context, session);
			model.Data(assetmaster, request);
			getNavigationPanel(1);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in cancel");
			return null;
		}
	}

	public String reset() {
		try {
			assetmaster.setCode("");
			assetmaster.setAssetCode("");
			assetmaster.setInvCode("");
			assetmaster.setPurchaseDate("");
			assetmaster.setPrice("");
			assetmaster.setAssetname("");
			assetmaster.setStatus("");
			assetmaster.setDescription("");
			assetmaster.setQuant("");
			assetmaster.setAssigned("");
			assetmaster.setAvailable("");
			assetmaster.setSubTypeCode("");
			assetmaster.setSubTypeName("");
			assetmaster.setVendorCode("");
			assetmaster.setVendorName("");
			assetmaster.setWarehouseCode("");
			assetmaster.setWarehouseName("");
			assetmaster.setItemizedFlag("false");
			assetmaster.setCommonFlag("false");
			assetmaster.setInvPrefix("");
			assetmaster.setInvType("");
			assetmaster.setFromInv("");
			assetmaster.setToInv("");
			assetmaster.setUpdateApp("false");
			assetmaster.setListLength("");
			assetmaster.setTabDisplay("false");
			assetmaster.setPropertyDataFlag("false");
			getNavigationPanel(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String f9actionCategory() throws Exception {

		String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,''),NVL('',' '),NVL('',' ') FROM HRMS_ASSET_CATEGORY "
				+ "  ORDER BY ASSET_CATEGORY_CODE ";

		String[] headers = { getMessage("asst.code"), getMessage("assetCat") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "assetCode", "assetname", "subTypeCode",
				"subTypeName" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionSubType() throws Exception {

		logger
				.info("asset category code in F9==="
						+ assetmaster.getAssetCode());
		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,''),ASSET_INVENTORY_TYPE FROM HRMS_ASSET_SUBTYPE "
				// +" LEFT JOIN HRMS_UNIT_MASTER ON
				// (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
				+ " WHERE ASSET_CATEGORY_CODE ="
				+ assetmaster.getAssetCode()
				+ " ORDER BY ASSET_SUBTYPE_CODE ";

		String[] headers = { getMessage("asst.code"),
				getMessage("asset.subType") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "subTypeCode", "subTypeName", "invType" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "AssetMaster_showForInventory.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9action() throws Exception {

		String query = " SELECT DISTINCT ASSET_MASTER_CODE,ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME,ASSET_INVENTORY_TYPE FROM HRMS_ASSET_MASTER "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE) "
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
				+ " INNER JOIN HRMS_ASSET_MASTER_DTL ON(HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE =HRMS_ASSET_MASTER.ASSET_MASTER_CODE)"
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE)"
				+ " WHERE WAREHOUSE_RESPONSIBLE_PERSON = "
				+ assetmaster.getUserEmpId() + " ORDER BY ASSET_MASTER_CODE ";

		String[] headers = { getMessage("asset.masterCode"),
				getMessage("asset.type"), getMessage("asset.subType") };
		String[] headerWidth = { "20", "40", "40" };

		String[] fieldNames = { "code", "assetname", "subTypeName", "invType" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";

		String submitToMethod = "AssetMaster_assetRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String showUnit(){ AssetMasterModel model = new
	 * AssetMasterModel(); model.initiate(context,session); String query
	 * ="SELECT UNIT_NAME FROM HRMS_ASSET_SUBTYPE " +" LEFT JOIN
	 * HRMS_UNIT_MASTER ON
	 * (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)" +"
	 * WHERE ASSET_SUBTYPE_CODE ="+assetmaster.getSubTypeCode(); Object
	 * [][]unit= model.getSqlModel().getSingleResult(query);
	 * assetmaster.setAssetUnit(String.valueOf(unit[0][0])); model.terminate();
	 * return SUCCESS; }
	 */
	public String f9actionVendor() throws Exception {

		String query = " SELECT VENDOR_CODE, VENDOR_NAME FROM HRMS_VENDOR where VENDOR_TYPE='S' ORDER BY VENDOR_CODE ";

		String[] headers = { getMessage("assetVendCode"),
				getMessage("vend.name") };
		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "vendorCode", "vendorName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionWarehouse() throws Exception {

		String query = " SELECT WAREHOUSE_CODE,WAREHOUSE_NAME  FROM HRMS_WAREHOUSE_MASTER "
				+ "WHERE WAREHOUSE_RESPONSIBLE_PERSON ="
				+ assetmaster.getUserEmpId() + " ORDER BY WAREHOUSE_CODE";

		String[] headers = { getMessage("assetWareHouseCode"),
				getMessage("asstWareHouse") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "warehouseCode", "warehouseName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String assetRecord() throws Exception {
		try {
			logger.info("I am in model");
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			assetmaster.setUpdateApp("true");
			model.getAssetRecord(assetmaster, request);
			if (assetmaster.getInvType().equals("I")) {
				assetmaster.setItemizedFlag("true");
				assetmaster.setCommonFlag("false");
			} else {
				assetmaster.setItemizedFlag("false");
				assetmaster.setCommonFlag("true");

			}
			
			assetmaster.setUpdateApp("false");
			getNavigationPanel(3);
			assetmaster.setEnableAll("N");
			 
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String report() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			model.report(assetmaster, request, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/*
	 * show existing record if any after selecting the sub type whose inventory
	 * type is same for all
	 * 
	 * method name :showForInventory
	 */
	public String showForInventory() {
		logger.info("in showfor inve type=" + assetmaster.getInvType());
		AssetMasterModel model = new AssetMasterModel();
		model.initiate(context, session);
		assetmaster.setTableLength("");
		if (assetmaster.getInvType().equals("I")) {
			assetmaster.setItemizedFlag("true");
			assetmaster.setCommonFlag("false");
		} else {
			assetmaster.setItemizedFlag("false");
			assetmaster.setCommonFlag("true");
			// model.checkInventoryExist(assetmaster);
		}
		// showInventoryList();
		assetmaster.setListLength("");
		getNavigationPanel(2);
		assetmaster.setEnableAll("Y");
		model.terminate();
		logger.info("in showfor inve flag=" + assetmaster.getItemizedFlag());
		return "showData";
	}

	/*
	 * Generate inventory list according to quantity given & set it into the
	 * list for sub type whose inventory type is Itemized Inventory code
	 * 
	 * method name: generateInventory
	 */
	public String generateInventory() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			String invCode[] = null;
			try {
				String invCodeString = "";
				logger.info("assetmaster.getListLength()========="
						+ assetmaster.getListLength());
				if (assetmaster.getListLength().equals("")) {
					assetmaster.setListLength("1");

				} else {
					invCode = new String[Integer.parseInt(assetmaster
							.getListLength())];
					// String invCode
					// []=request.getParameterValues("inventoryCodeIterator");
					for (int i = 1; i <= Integer.parseInt(assetmaster
							.getListLength()); i++) {
						invCodeString = request
								.getParameter("inventoryCodeIterator" + i);
						invCode[i - 1] = invCodeString;
					}
				}
				String warehouseCode[] = request
						.getParameterValues("warehouseCodeIterator");
				String warehouseName[] = request
						.getParameterValues("warehouseNameIterator");
				String quantityIterator[] = request
						.getParameterValues("quantityIterator");
				String availableIterator[] = request
						.getParameterValues("availableIterator");
				String assignFlag[] = request
						.getParameterValues("assignFlagIterator");
				String leasedFlag[] = request.getParameterValues("isLeased");
				String leasedDate[] = request.getParameterValues("leaseDate");
				String insuranceFlag[] = request
						.getParameterValues("isInsured");
				String insureanceDate[] = request
						.getParameterValues("insureDate");
				model.generateInventory(assetmaster, invCode, warehouseCode,
						warehouseName, assignFlag, quantityIterator,
						availableIterator, leasedFlag, leasedDate,
						insuranceFlag, insureanceDate);
				Object propertDataObj[][] = null;
				try {
					propertDataObj = model.getPropertyData(assetmaster);
					if (propertDataObj != null && propertDataObj.length > 0) {
						model.showPropertyData(request, invCode, assetmaster);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				if (assetmaster.getInvType().equals("I")) {
					assetmaster.setItemizedFlag("true");
					assetmaster.setCommonFlag("false");
					if (propertDataObj != null && propertDataObj.length > 0) {
						assetmaster.setTabDisplay("true");
					}
				} else {
					assetmaster.setItemizedFlag("false");
					assetmaster.setCommonFlag("true");

				}

				resetInventoryDetails();
				getNavigationPanel(2);
				assetmaster.setEnableAll("Y");
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String resetInventoryDetails() {
		assetmaster.setInvPrefix("");
		assetmaster.setWarehouseCode("");
		assetmaster.setWarehouseName("");
		assetmaster.setQuantityWareHouse("");
		assetmaster.setFromInv("");
		assetmaster.setToInv("");
		return SUCCESS;
	}

	/*
	 * add inventory into the list according to quantity given & set it into the
	 * list for sub type whose inventory type is same for all
	 * 
	 * 
	 */
	public String addInventory() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			boolean result = false;
			// String invCode
			// []=request.getParameterValues("inventoryCodeIterator");
			String invCodeString = "";
			String invCode[] = null;
			logger.info("assetmaster.getListLength()========="
					+ assetmaster.getListLength());
			if (assetmaster.getListLength().equals("")) {
				assetmaster.setListLength("1");

			} else {
				invCode = new String[Integer.parseInt(assetmaster
						.getListLength())];

				// String invCode
				// []=request.getParameterValues("inventoryCodeIterator");

				for (int i = 1; i <= Integer.parseInt(assetmaster
						.getListLength()); i++) {
					invCodeString = request
							.getParameter("inventoryCodeIterator" + i);
					invCode[i - 1] = invCodeString;
				}
			}
			String warehouseCode[] = request
					.getParameterValues("warehouseCodeIterator");
			String warehouseName[] = request
					.getParameterValues("warehouseNameIterator");
			String assignFlag[] = request
					.getParameterValues("assignFlagIterator");
			String quantityIterator[] = request
					.getParameterValues("quantityIterator");
			String availableIterator[] = request
					.getParameterValues("availableIterator");
			result = model.addInventory(assetmaster, invCode, warehouseCode,
					warehouseName, assignFlag, quantityIterator,
					availableIterator);
			/*
			 * check for duplicate entry
			 * 
			 */
			/*
			 * if(!result){ addActionMessage("Warehouse is already added !");
			 * showInventoryList();
			 * 
			 * }else
			 */
			resetInventoryDetails();
			if (assetmaster.getInvType().equals("I")) {
				assetmaster.setItemizedFlag("true");
				assetmaster.setCommonFlag("false");
			} else {
				assetmaster.setItemizedFlag("false");
				assetmaster.setCommonFlag("true");
			}
			getNavigationPanel(2);
			assetmaster.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String showInventoryList() {
		AssetMasterModel model = new AssetMasterModel();
		model.initiate(context, session);
		if (assetmaster.getInvType().equals("I")) {
			assetmaster.setItemizedFlag("true");
			assetmaster.setCommonFlag("false");
		} else {
			assetmaster.setItemizedFlag("false");
			assetmaster.setCommonFlag("true");
		}
		String invCodeString = "";
		String invCode[] = null;
		logger.info("assetmaster.getListLength()========="
				+ assetmaster.getListLength());
		if (assetmaster.getListLength().equals("")) {
			assetmaster.setListLength("1");

		} else {
			invCode = new String[Integer.parseInt(assetmaster.getListLength())];

			// String invCode
			// []=request.getParameterValues("inventoryCodeIterator");

			for (int i = 1; i <= Integer.parseInt(assetmaster.getListLength()); i++) {
				invCodeString = request.getParameter("inventoryCodeIterator"
						+ i);
				invCode[i - 1] = invCodeString;
			}
		}
		// String invCode
		// []=request.getParameterValues("inventoryCodeIterator");
		String warehouseCode[] = request
				.getParameterValues("warehouseCodeIterator");
		String warehouseName[] = request
				.getParameterValues("warehouseNameIterator");
		String assignFlag[] = request.getParameterValues("assignFlagIterator");
		String quantityIterator[] = request
				.getParameterValues("quantityIterator");
		String availableIterator[] = request
				.getParameterValues("availableIterator");
		model.showInventoryList(assetmaster, invCode, warehouseCode,
				warehouseName, assignFlag, quantityIterator, availableIterator);

		return SUCCESS;
	}

	/*
	 * 
	 * Remove the inventory from the list
	 * 
	 * 
	 * 
	 */
	public String removeInventory() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			if (assetmaster.getInvType().equals("I")) {
				assetmaster.setItemizedFlag("true");
				assetmaster.setCommonFlag("false");
			} else {
				assetmaster.setItemizedFlag("false");
				assetmaster.setCommonFlag("true");
			}
			String invCode[] = null;
			String invCodeString = "";
			logger.info("assetmaster.getListLength()========="
					+ assetmaster.getListLength());
			if (assetmaster.getListLength().equals("")) {
				assetmaster.setListLength("1");

			} else {
				invCode = new String[Integer.parseInt(assetmaster
						.getListLength())];
				// String invCode
				// []=request.getParameterValues("inventoryCodeIterator");
				for (int i = 1; i <= Integer.parseInt(assetmaster
						.getListLength()); i++) {
					invCodeString = request
							.getParameter("inventoryCodeIterator" + i);
					invCode[i - 1] = invCodeString;
				}
			}
			// String invCode []=
			// request.getParameterValues("inventoryCodeIterator");
			String warehouseCode[] = request
					.getParameterValues("warehouseCodeIterator");
			String warehouseName[] = request
					.getParameterValues("warehouseNameIterator");
			String assignFlag[] = request
					.getParameterValues("assignFlagIterator");
			String quantityIterator[] = request
					.getParameterValues("quantityIterator");
			String availableIterator[] = request
					.getParameterValues("availableIterator");
			model.removeInventory(assetmaster, invCode, warehouseCode,
					warehouseName, assignFlag, quantityIterator,
					availableIterator);
			getNavigationPanel(2);
			assetmaster.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	/**
	 * To edit particular record
	 * 
	 * @return
	 */
	public String callForEdit() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			// assetmaster.setUpdateApp("true");
			// model.callForEdit(assetmaster);
			assetmaster.setCode(assetmaster.getHiddencode());
			assetRecord();
			// model.getAssetRecord(assetmaster);
			getNavigationPanel(3);
			assetmaster.setEnableAll("N");
			//assetmaster.setTabDisplay("true");
			model.terminate();
			return "showData";
		} catch (Exception e) {
			logger.info("Exception in callForEdit");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String callForEditRecord()
	{
		//callForEdit();
		return "showData";
	}
	public String showForPurchase() {
		try {
			AssetMasterModel model = new AssetMasterModel();
			model.initiate(context, session);
			assetmaster.setPurchaseFlag("true");
			assetmaster.setHiddenPurchaseCode(request
					.getParameter("purchaseCode"));
			assetmaster.setVendorCode(request.getParameter("hiddenVendorCode"));
			assetmaster.setVendorName(request.getParameter("hiddenVendorName"));
			assetmaster.setSubTypeCode(request
					.getParameter("hiddenSubTypeCode"));
			assetmaster.setSubTypeName(request
					.getParameter("hiddenSubTypeName"));
			assetmaster.setQuant(request.getParameter("hiddenQuantity"));
			assetmaster.setPrice(request.getParameter("hiddentotalPrice"));
			assetmaster.setAvailable(request.getParameter("hiddenQuantity"));
			assetmaster.setAssigned("0");
			model.showCategoryName(assetmaster);
			assetmaster.setTableLength("");
			if (assetmaster.getInvType().equals("I")) {
				assetmaster.setItemizedFlag("true");
				assetmaster.setCommonFlag("false");
			} else {
				assetmaster.setItemizedFlag("false");
				assetmaster.setCommonFlag("true");
				//model.checkInventoryExist(assetmaster);
			}
			//showInventoryList();
			assetmaster.setListLength("");
			getNavigationPanel(4);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public AssetMaster getAssetmaster() {
		return assetmaster;
	}

	public void setAssetmaster(AssetMaster assetmaster) {
		this.assetmaster = assetmaster;
	}
}
