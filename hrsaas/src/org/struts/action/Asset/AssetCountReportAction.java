/**
 * @author Ananthalakshmi
 * 
 */
package org.struts.action.Asset;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Asset.AssestCountReport;
import  org.paradyne.model.Asset.AssetCountReportModel;

public class AssetCountReportAction extends ParaActionSupport {

	AssestCountReport assetCountRpt;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	@Override
	public void prepare_local()  {
		assetCountRpt = new AssestCountReport();
		this.assetCountRpt.setMenuCode(2063);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return assetCountRpt;
	}

	public AssestCountReport getAssetCountRpt() {
		return assetCountRpt;
	}

	public void setAssetCountRpt(final AssestCountReport assetCountRpt) {
		this.assetCountRpt = assetCountRpt;
	}
	public void prepare_withLoginProfileDetails() {
		if (this.assetCountRpt.isGeneralFlag()) {
			final AssetCountReportModel model = new AssetCountReportModel();
			model.initiate(context , session);
			model.terminate();
		} 
	} 

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String generateReport() {
		final AssetCountReportModel model = new AssetCountReportModel();
		model.initiate(context, session);
		final String result = model.getReport(this.assetCountRpt , response);
		model.terminate();
		this.clear();
		return null;

	}// end of report

	/**
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9AssetType() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT ASSET_CATEGORY_CODE, NVL(ASSET_CATEGORY_NAME,'')" +
						" FROM HRMS_ASSET_CATEGORY   ORDER BY ASSET_CATEGORY_CODE ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		final String[] headers = {this.getMessage("assetTypeCode") , this.getMessage("assetTypeName")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"15" , "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"assetTypeCode", "assetTypeName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0 , 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action



	/**
	 * THIS METHOD IS USED FOR SELECTING DEVISION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9AssetSubType() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,'') " +
					   " FROM HRMS_ASSET_SUBTYPE " +  
					   " ORDER BY ASSET_SUBTYPE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

			final String[] headers = {this.getMessage("assetSubTypeCode") , this.getMessage("assetSubTypeName") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"15" , "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"assetSubTypeCode" , "assetSubTypeName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9WareHouse() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		final String query = " SELECT WAREHOUSE_CODE,WAREHOUSE_NAME  FROM HRMS_WAREHOUSE_MASTER " + 
							 " WHERE WAREHOUSE_RESPONSIBLE_PERSON ='"+assetCountRpt.getUserEmpId()+"' ORDER BY WAREHOUSE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		final String[] headers = {this.getMessage("wareHouseCode") , this.getMessage("wareHouseName")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"15" , "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"wareHouseCode" , "wareHouseName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0 , 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	} // end of f9dept
	
	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 */
	public String clear() {
		this.assetCountRpt.setAssetTypeName("");
		this.assetCountRpt.setAssetSubTypeName("");
		this.assetCountRpt.setWareHouseName("");
		this.assetCountRpt.setAssetTypeCode("");
		this.assetCountRpt.setAssetSubTypeCode("");
		this.assetCountRpt.setWareHouseCode("");
		return SUCCESS;
	} // end of clear

	
}  // end of class
