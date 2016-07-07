package org.struts.action.Asset;

import org.paradyne.bean.Asset.AssetReturn;
import org.paradyne.model.Asset.AssetReturnModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author krishna
 * 
 */
public class AssetReturnAction extends ParaActionSupport {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	/**
	 * Declare AssetReturn reference variable
	 */
	AssetReturn assetReturn;

	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		assetReturn = new AssetReturn();
		assetReturn.setMenuCode(645);
		
	}

	public Object getModel() {
		return assetReturn;
	}
	public String f9Selectvendor() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "select VENDOR_CODE,VENDOR_NAME from hrms_vendor "+
					"where VENDOR_TYPE='E' order by VENDOR_CODE ";
		
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers={getMessage("assetVendorCode"), getMessage("assetVendorName")};
		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "vendorId", "vendorName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */

		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/*
	 * method to show all the details for the selected application return :
	 * String
	 */
	
	public String resetList()
	{
		try {
			assetReturn.setRetList(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String f9Selectemp() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
		
		query += getprofileQuery(assetReturn);
			
		query += " ORDER BY EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers={getMessage("employee.id"), getMessage("employee")};
		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empTokenNo", "empName", "empId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */

		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * method to save the record
	 * 
	 * @return string
	 */
	public String save()

	{
		boolean result;
		try {
			AssetReturnModel model = new AssetReturnModel();
			model.initiate(context, session);
			result = model.modAsset(assetReturn, request);
			if (result) {
				addActionMessage(getMessage("update"));
			}// end of if
			reset();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * method to show the record
	 * 
	 * @return string
	 */
	public String show() {
		try {
			AssetReturnModel model = new AssetReturnModel();
			model.initiate(context, session);
			String result = model.show(assetReturn);
			assetReturn.setShowFlag("true");
			if (!result.equals("")) {
				addActionMessage(result);
				assetReturn.setShowFlag("false");
				//reset();
			}// end of if
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method to reset the form
	 * 
	 * @return form
	 */
	public String reset() {
		assetReturn.setEmpId("");
		assetReturn.setEmpName("");
		assetReturn.setEmpTokenNo("");
		assetReturn.setRowNum("");
		assetReturn.setCategory("");
		assetReturn.setSubType("");
		assetReturn.setInventory("");
		assetReturn.setAppCode("");
		assetReturn.setHDate("");
		assetReturn.setMasterCode("");
		assetReturn.setDisb("");
		assetReturn.setStatus("");
		assetReturn.setShowFlag("false");
		assetReturn.setRetList(null);
		assetReturn.setVendorId("");
		assetReturn.setVendorName("");
		assetReturn.setEmployeeOrVendorCode("");
		assetReturn.setEmployeeOrVendorType("");
		assetReturn.setAssetSubTypeCodeItt("");
		assetReturn.setAssetTypeCodeItt("");
		return "success";

	}

	/**
	 * @return the assetReturn
	 */
	public AssetReturn getAssetReturn() {
		return assetReturn;
	}

	/**
	 * @param assetReturn
	 *            the assetReturn to set
	 */
	public void setAssetReturn(AssetReturn assetReturn) {
		this.assetReturn = assetReturn;
	}

}
