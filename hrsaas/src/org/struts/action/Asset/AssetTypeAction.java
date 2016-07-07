package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetType;
import org.paradyne.model.Asset.AssetTypeModel;

public class AssetTypeAction extends org.struts.lib.ParaActionSupport {
	AssetType assetType;

	@Override
	public void prepare_local() throws Exception {
		assetType = new AssetType();
		assetType.setMenuCode(390);
	}

	public Object getModel() {

		return assetType;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public String addNew() {
		try {
			getNavigationPanel(2);
			System.out.println("addNew");
			return "showData";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String cancel() throws Exception {

		try {
			getNavigationPanel(1);
			reset();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in cancel action:" + e);
			return null;
		}
	}

	public String save() throws Exception {
		getNavigationPanel(3);
		logger.info("On save");
		AssetTypeModel model = new AssetTypeModel();
		logger.info("2");
		model.initiate(context, session);

		/*
		 * logger.info("3"); boolean result ;
		 * result=model.checkAsset(assetType); if(!result){ addActionMessage("
		 * Asset Name Already Exist"); }else{
		 * 
		 * 
		 * if(assetType.getAssetCode().equals("")){ result =
		 * model.addAsset(assetType); if(result) { addActionMessage("Record
		 * saved successfully !");
		 *  } else { addActionMessage("Asset can not be added"); } }else{
		 * 
		 * result = model.modRank(assetType); if(result) {
		 * addActionMessage("Record modified successfully !");
		 *  } else { addActionMessage("Asset can not be modified"); } }
		 * logger.info("4"); logger.info("In Asset
		 * save------------result="+result);
		 * 
		 * 
		 * 
		 * if(result) { reset();
		 *  }}
		 */
		String result = "";
		if (assetType.getAssetCode().equals("")) {
			// saving method
			// System.out.println("data saved"+ assetType.getAssetCode());
			result = model.addAsset(assetType);

		} else { // modifying method
			// System.out.println("data modified"+ assetType.getAssetCode());
			result = model.modAsset(assetType);
		}
		model.Data(assetType, request);
		addActionMessage(getText(result));

		model.terminate();
		return "showData";

		/*
		 * model.terminate(); logger.info("5"); return "success";
		 */
	}

	public String data() throws Exception {
		AssetTypeModel model = new AssetTypeModel();
		model.initiate(context, session);
		model.callForEdit(assetType);
		getNavigationPanel(3);
		model.terminate();
		return "titleData";
	}

	public String reset1() {
		reset();
		getNavigationPanel(2);
		return "showData";
	}

	public String reset() {
		assetType.setAssetCode("");
		assetType.setAssetname("");
		assetType.setType("");
		assetType.setUnit("");
		// getNavigationPanel(2);
		return "showData";
	}

	public String delete() throws Exception {
		AssetTypeModel model = new AssetTypeModel();
		model.initiate(context, session);
		logger.info("before delete asset code ------------> "
				+ assetType.getAssetCode());
		boolean result = model.deleteAsset(assetType);
		if (result) {
			addActionMessage("Record Deleted Successfully.");
			reset();
		} else
			addActionMessage("This record is referenced in other resources.So cannot delete.");
		logger.info("after delete asset code ------------> "
				+ assetType.getAssetCode());
		model.Data(assetType, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String f9action() throws Exception {

		String query = " SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,''),3,4 FROM HRMS_ASSET_CATEGORY ORDER BY ASSET_CATEGORY_CODE ";

		String[] headers = { "Asset Code", getMessage("assettype.name") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "assetCode", "assetname" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		logger.info("asset f9 - method");
		String submitToMethod = "AssetTypes_data1.action";

		/*
		 * String query = " SELECT ASSET_CATEGORY_CODE,
		 * NVL(ASSET_CATEGORY_NAME,''),DECODE(ASSET_CATEGORY_TYPE,'R','Returnable','N','Non-Returnable'),ASSET_CATEGORY_TYPE,ASSET_CATEGORY_UNIT
		 * FROM HRMS_ASSET_CATEGORY ORDER BY ASSET_CATEGORY_CODE ";
		 * 
		 * 
		 * String[] headers={"Asset Code", "Asset Category Name","Asset Category
		 * Type"};
		 * 
		 * String[] headerWidth={"20", "60","20"};
		 * 
		 * String[] fieldNames={"assetCode","assetname","type","type","unit"};
		 * 
		 * 
		 * int[] columnIndex={0,1,2,3,4};
		 */

		logger.info("f99999999999999999999999");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String data1() {
		try {
			logger.info("ggggg....");
			AssetTypeModel model = new AssetTypeModel();
			model.initiate(context, session);
			// logger.info("asset action data1");
			model.data1(assetType);
			getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			logger.error("error in asset action data1 --- " + e);
		}
		return "showData";
	}

	public String report() {
		AssetTypeModel model = new AssetTypeModel();
		model.initiate(context, session);
		String label[] = { "Sr. No", getMessage("assettype.name") };
		// model.report(assetType);
		model.getReport(assetType, request, response, context, session, label);
		model.terminate();
		// return "report";
		return null;
	}

	public String input() throws Exception {
		assetType.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		AssetTypeModel model = new AssetTypeModel();
		model.initiate(context, session);

		model.Data(assetType, request);
		model.terminate();
	}

	public String callPage() throws Exception {

		AssetTypeModel model = new AssetTypeModel();
		model.initiate(context, session);
		model.Data(assetType, request);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;

	}

	public String callForEdit() throws Exception {
		try {
			AssetTypeModel model = new AssetTypeModel();
			model.initiate(context, session);
			model.callForEdit(assetType);
			model.Data(assetType, request);
			model.terminate();
			getNavigationPanel(3);
			assetType.setEnableAll("N");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String deleteCheckedRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		getNavigationPanel(1);
		AssetTypeModel model = new AssetTypeModel();

		model.initiate(context, session);
		// logger.info("code -- "+code.toString());
		boolean result = model.deleteCheckedRecords(assetType, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			// reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.Data(assetType, request);
		model.terminate();

		return SUCCESS;

	}

}
