/**
 * 
 */
package org.struts.action.Asset;

import java.util.HashMap;

import org.paradyne.bean.Asset.AssetSubTypes;
import org.paradyne.model.Asset.AssetSubTypesModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 *
 */
public class AssetSubTypesAction extends ParaActionSupport {
	
	AssetSubTypes assetSubTypes ;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		assetSubTypes = new AssetSubTypes();
		assetSubTypes.setMenuCode(633);
		// TODO Auto-generated method stub

	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return assetSubTypes;
	}

	public AssetSubTypes getAssetSubTypes() {
		return assetSubTypes;
	}

	public void setAssetSubTypes(AssetSubTypes assetSubTypes) {
		this.assetSubTypes = assetSubTypes;
	}
	public void prepare_withLoginProfileDetails(){
		AssetSubTypesModel model = new AssetSubTypesModel();
		model.initiate(context, session);
		model.Data(assetSubTypes, request);
		String quer = "SELECT UNIT_CODE,UNIT_NAME FROM HRMS_UNIT_MASTER WHERE UNIT_ISACTIVE='Y' ";
		Object[][] iterator = model.getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		for (int i = 0; i < iterator.length; i++) {
			mp.put(String.valueOf(iterator[i][0]), String
					.valueOf(iterator[i][1]));

		}
		assetSubTypes.setHashmap(mp);
		model.terminate();
	}
	
	public String input() throws Exception {
		logger.info("input");
		assetSubTypes.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String addNew() throws Exception {
		try {
			reset();
			getNavigationPanel(2);
			System.out.println("addNew");
			return "showData";
		}
		catch(Exception e){

			return null;
		}
	}
	public String callPage(){
		AssetSubTypesModel model = new AssetSubTypesModel();
		model.initiate(context, session);
		model.Data(assetSubTypes, request);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String addToList()
	{
		try {
			AssetSubTypesModel model = new AssetSubTypesModel();
			model.initiate(context, session);
			
			if(assetSubTypes.getHiddenEdit().equals(""))
			{
				boolean isDuplicate =model.addToList(assetSubTypes, request);
				if(isDuplicate)
				{
					addActionMessage("Duplicate record found.");
				}
			}
			else{
				
				
				boolean isDuplicate =model.updateList(assetSubTypes, request);
				if(isDuplicate)
				{
					addActionMessage("Duplicate record found.");
				}
			}
			assetSubTypes.setPropertyName("");
			assetSubTypes.setProperUnit("");
			assetSubTypes.setHiddenEdit("");
			model.terminate();
			getNavigationPanel(2);
			assetSubTypes.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}
	public String f9actionCategory(){
		
		String query="SELECT ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY WHERE ASSET_ISACTIVE = 'Y' ORDER BY ASSET_CATEGORY_CODE" ;
		
		String[] headers={"Asset Category Code", "Asset Category Name"};
		
		String[] headerWidth={"20", "80"};
		
		String[] fieldNames={"assetCategoryCode","assetCategoryName"};
		
		
		int[] columnIndex={0,1};
		
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		

		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
public String f9action(){
		
		final String query = "SELECT ASSET_SUBTYPE_CODE,ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME, ASSET_SUBTYPE_FLAG,ASSET_SUBTYPE_UNIT, " +
								" HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE,ASSET_INVENTORY_TYPE FROM HRMS_ASSET_SUBTYPE " +
								" INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE)" + 
								" ORDER BY ASSET_SUBTYPE_CODE ";
				
		final String[] headers = {"Code" , this.getMessage("assetsubtype.cat") , this.getMessage("assetsubtype.name")};
		final String[] headerWidth = {"20" , "40" , "40"};
		final String[] fieldNames = {"assetsubTypeCode" , "assetCategoryName" , "assetSubTypeName" , "returnType" , "unit" , "assetCategoryCode" , "invType"};
		final int[] columnIndex = {0 , 1 , 2 , 3 , 4 , 5 , 6};
		final String submitFlag = "true";
		final String submitToMethod = "AssetSubTypes_showData1.action";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}

	public String save() {
		boolean result = false;
		final AssetSubTypesModel model = new AssetSubTypesModel();
		model.initiate(context, session);
		if (this.assetSubTypes.getAssetsubTypeCode().equals("")) {
		//if (assetSubTypes.getHiddenassetCategoryCode().equals("")) {
			result = model.save(this.assetSubTypes , request);
			if (result) {
				this.addActionMessage("Record saved successfully.");
				//setAssetSubTypes(assetSubTypes);
				//assetSubTypes.setAssetCategoryCode(assetSubTypes.getAssetCategoryCode());
				//reset();
			} else {
				this.addActionMessage("Duplicate entry found.");
			}

		} else {
			result = model.update(this.assetSubTypes , request);
			if (result) {
				this.addActionMessage("Record updated successfully.");
				//setAssetSubTypes(assetSubTypes);
				//reset();
			} else {
				this.addActionMessage("Duplicate entry found.");
			}
		}
		model.Data(this.assetSubTypes, request);
		
		model.setItteratorData(assetSubTypes, assetSubTypes.getAssetsubTypeCode());
		getNavigationPanel(3);
		model.terminate();
		return "showData";
	}
	public String reset() {
		this.assetSubTypes.setAssetCategoryCode("");
		this.assetSubTypes.setAssetCategoryName("");
		assetSubTypes.setAssetsubTypeCode("");
		this.assetSubTypes.setAssetSubTypeName("");
		this.assetSubTypes.setCheckid("");
		this.assetSubTypes.setLeadTime("");
		this.assetSubTypes.setReturnType("");
		this.assetSubTypes.setUnit("");
		this.assetSubTypes.setReOrderLevel("");
		this.assetSubTypes.setInvType("");
		this.assetSubTypes.setIsActive("");
		this.assetSubTypes.setPropertyName("");
		this.assetSubTypes.setProperUnit("");
		this.assetSubTypes.setSafetystock("");
		
		this.getNavigationPanel(2);
		return "showData";
	}
	public String cancel() throws Exception {
		this.reset();
		this.getNavigationPanel(1);
		return "success";
		
	}
	public String showData1() {
		try {
			final AssetSubTypesModel model = new AssetSubTypesModel();
			model.initiate(context , session);
			this.logger.info("in show data action class");
			this.logger.info("i am before showdata method call>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			model.showData(this.assetSubTypes);
			this.logger.info("i am after showdata method call>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			this.getNavigationPanel(3);
			this.logger.info("i am after navigation pannel called>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "showData";
	}
	public String callForEdit(){
		try {
			final AssetSubTypesModel model = new AssetSubTypesModel();
			model.initiate(context, session);
			model.setData(this.assetSubTypes);
			this.getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}
	public String delete() {
		final AssetSubTypesModel model = new AssetSubTypesModel();
		boolean result = false;
		model.initiate(context, session);
		result = model.delete(this.assetSubTypes);
		if (result) {
			this.addActionMessage("Record deleted successfully.");
			this.setAssetSubTypes(this.assetSubTypes);
			this.reset();
		} else {
			this.addActionMessage("This record is referenced in other resources.So can't delete.");
		}
		this.getNavigationPanel(1);
		model.Data(this.assetSubTypes, request);
		model.terminate();
		return "success";
	}
	public String deleteMultiple() throws Exception {
		final String[] code = request.getParameterValues("hdeleteCode");

		final AssetSubTypesModel model = new AssetSubTypesModel();

		model.initiate(context, session);

		final boolean result = model.deleteCheckedRecords(this.assetSubTypes , code);

		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
		} else {
			this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		this.getNavigationPanel(1);
		model.Data(this.assetSubTypes , request);
		model.terminate();

		return "success";

	}
	

	public String report(){
		final AssetSubTypesModel model = new AssetSubTypesModel();
		model.initiate(context, session);
		model.report(this.assetSubTypes , request , response);
		model.terminate();
		return null;
	}
	
	// Updated by Anantha lakshmi
	/* method name : delete Properties.
	 * purpose     : to delete the Properties from list
	 * return type : String
	 */
	public String deleteAssetSubType() {
		try {
			final String[] propertyName = request.getParameterValues("propertyNameItt");
			final String[] propertyUnitItt = request.getParameterValues("propertyUnitItt");
			final String[] propertyUnitName = request.getParameterValues("propertyUnitNameItt");
			final AssetSubTypesModel model = new AssetSubTypesModel();
			model.initiate(context, session);
			model.deleteAssetSubType(this.assetSubTypes , propertyName , propertyUnitName , propertyUnitItt);
			model.terminate();
			this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "showData";
	}
	
}
