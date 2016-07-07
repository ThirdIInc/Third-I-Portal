/**
 * 
 */
package org.paradyne.model.Asset;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssetMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;

/**
 * @author mangeshj Date 01/08/2008 AssetMasterModel class to write business
 *         logic to add, update, delete, view the Assets in the Asset master
 */
public class AssetMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/*
	 * method name : save purpose : add the new record in asset master return
	 * type : boolean parameter : AssetMaster instance, String[]
	 * invCode,String[] warehouseCode, String[] assignFlag, String[]
	 * quantityIterator, String[] availableIterator, String[] leasedFlag,
	 * String[] leasedDate, String[] insuranceFlag, String[] insureanceDate
	 */

	public boolean save(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] assignFlag,
			String[] quantityIterator, String[] availableIterator,
			String[] leasedFlag, String[] leasedDate, String[] insuranceFlag,
			String[] insureanceDate, Object[][] propertyValueOb,
			String[] inventoryCodeItt, String[] propertyCode) {
		logger.info("inside add method");
		Object addObj[][] = new Object[1][9];
		boolean result = false;
		addObj[0][0] = String.valueOf(bean.getPurchaseDate().trim());
		addObj[0][1] = String.valueOf(bean.getAssetCode().trim());
		addObj[0][2] = String.valueOf(bean.getPrice().trim());

		addObj[0][3] = String.valueOf(bean.getDescription().trim());
		addObj[0][4] = bean.getSubTypeCode().trim();
		addObj[0][5] = bean.getQuant().trim();
		addObj[0][6] = bean.getAssigned();
		addObj[0][7] = bean.getAvailable();
		addObj[0][8] = bean.getVendorCode();

		/*
		 * add the record in the ASSET_MASTER TABLE
		 * 
		 * 
		 */
		result = getSqlModel().singleExecute(getQuery(1), addObj); // INSERT
		// QUERY

		/*
		 * 
		 * ADD THE INVENTORY DETAILS IN ASSET_MASTER_DTL TABLE
		 * 
		 * 
		 */
		if (result) {
			Object[][] masterCode = getSqlModel().getSingleResult(
					"SELECT MAX(ASSET_MASTER_CODE) FROM HRMS_ASSET_MASTER");
			bean.setCode(String.valueOf(masterCode[0][0]));
			Object[][] dtlObject = new Object[invCode.length][11];
			if (getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_MASTER_CODE="
							+ bean.getCode())) {

				String query = " SELECT NVL(MAX(ASSET_ITEM_CODE),0)+1 FROM HRMS_ASSET_MASTER_DTL ";
				int dtlCode = 0;

				Object dtlCodeObj[][] = getSqlModel().getSingleResult(query);

				dtlCode = Integer.parseInt(String.valueOf(dtlCodeObj[0][0]));

				System.out.println("dtlCode   " + dtlCode);

				if (dtlCodeObj != null && dtlCodeObj.length > 0) {

					for (int i = 0; i < invCode.length; i++) {
						logger.info("i - " + (i + 1) + "lease - "
								+ leasedFlag[i] + "  insu - "
								+ insuranceFlag[i]);
						dtlObject[i][0] = bean.getCode();
						dtlObject[i][1] = invCode[i];
						dtlObject[i][2] = warehouseCode[i];
						dtlObject[i][3] = assignFlag[i];
						dtlObject[i][4] = quantityIterator[i];
						dtlObject[i][5] = availableIterator[i];
						dtlObject[i][6] = leasedFlag[i];
						dtlObject[i][7] = checkNull(leasedDate[i]);
						dtlObject[i][8] = insuranceFlag[i];
						dtlObject[i][9] = checkNull(insureanceDate[i]);

						dtlObject[i][9] = checkNull(insureanceDate[i]);

						dtlObject[i][10] = dtlCode++;

						// insert into ASSET_MASTER_DTL
					}
					result = getSqlModel()
							.singleExecute(getQuery(5), dtlObject);

				}

			} // end of if
			if (bean.getPurchaseFlag().equals("true")) {

				String query = "UPDATE HRMS_ASSET_PURCHASE_DTL SET PURCHASE_STATUS='PS' WHERE PURCHASE_CODE="
						+ bean.getHiddenPurchaseCode()
						+ " AND PURCHASE_ASSET_CODE=" + bean.getSubTypeCode();
				getSqlModel().singleExecute(query);
				String checkStatus = "SELECT COUNT(PURCHASE_CODE) FROM HRMS_ASSET_PURCHASE_DTL WHERE PURCHASE_STATUS='PE' AND PURCHASE_CODE="
						+ bean.getHiddenPurchaseCode();
				Object count[][] = getSqlModel().getSingleResult(checkStatus);
				if (String.valueOf(count[0][0]).equals("0")) {
					getSqlModel().singleExecute(
							"UPDATE HRMS_ASSET_PURCHASE_HDR SET INWARD_STATUS='PS' WHERE PURCHASE_CODE="
									+ bean.getHiddenPurchaseCode());
					// ------------------------Process Manager
					// Alert------------------------start
					ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
					processAlerts.initiate(context, session);

					String applnID = bean.getHiddenPurchaseCode();
					String module = "Purchase Inward";
					processAlerts.removeProcessAlert(applnID, module);
					// ------------------------Process Manager
					// Alert------------------------end

				} // end of if
			} // end of if
		} // end of if

		int count = 0;

		if ((inventoryCodeItt != null && inventoryCodeItt.length > 0)
				&& (propertyCode != null && propertyCode.length > 0)) {
			String query = "  INSERT INTO HRMS_ASSET_PROPERTIES(ASSET_INVENTORY_CODE, ASSET_PROPERTY_ID, ASSET_PROPERTY_VALUE ,ASSET_MASTER_CODE) "
					+ " VALUES(?,?,?,?)";
			Object saveObj[][] = new Object[inventoryCodeItt.length
					* propertyCode.length][4];
			for (int i = 0; i < inventoryCodeItt.length; i++) {
				for (int j = 0; j < propertyCode.length; j++) {
					saveObj[count][0] = inventoryCodeItt[i];
					saveObj[count][1] = propertyCode[j];
					saveObj[count][2] = propertyValueOb[i][j];
					saveObj[count][3] = bean.getCode();

					count++;
				}
			}

			result = getSqlModel().singleExecute(query, saveObj);
		}

		return result;
	}

	/*
	 * method name : update() purpose : UPDATE THE EXISTING RECORD IN ASSET
	 * MASTER return type : boolean parameter : AssetMaster instance, String[]
	 * invCode,String[] warehouseCode, String[] assignFlag, String[]
	 * quantityIterator, String[] availableIterator, String[] leasedFlag,
	 * String[] leasedDate, String[] insuranceFlag, String[] insureanceDate
	 */
	public boolean update(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] assignFlag,
			String[] quantityIterator, String[] availableIterator,
			String[] leasedFlag, String[] leasedDate, String[] insuranceFlag,
			String[] insureanceDate, Object[][] rows,
			String[] inventoryCodeItt, String[] propertyCode) {
		Object modObj[][] = new Object[1][9];
		boolean result = false;
		modObj[0][0] = String.valueOf(bean.getPurchaseDate());
		modObj[0][1] = String.valueOf(bean.getAssetCode());
		modObj[0][2] = String.valueOf(bean.getPrice());

		modObj[0][3] = String.valueOf(bean.getDescription());
		modObj[0][4] = bean.getSubTypeCode().trim();
		modObj[0][5] = bean.getQuant();
		modObj[0][6] = bean.getAvailable();
		modObj[0][7] = bean.getVendorCode();
		modObj[0][8] = String.valueOf(bean.getCode());

		logger.info("bean.getPurchaseDate())===" + bean.getPurchaseDate());
		logger.info("bean.getSubTypeCode()==" + modObj[0][4] + "==");
		logger.info("bean.getAssetCode()===" + bean.getAssetCode());
		logger.info("bean.getPrice()===" + bean.getPrice());

		logger.info("bean.getDescription()===" + bean.getDescription());
		logger.info("bean.getCode()===" + bean.getCode());
		result = getSqlModel().singleExecute(getQuery(2), modObj);
		if (result) {
			if (getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_MASTER_CODE="
							+ bean.getCode())) {
				Object[][] dtlObject = new Object[invCode.length][11];

				int dtlCode = 0;
				
				String query = " SELECT NVL(MAX(ASSET_ITEM_CODE),0)+1 FROM HRMS_ASSET_MASTER_DTL ";

				Object dtlCodeObj[][] = getSqlModel().getSingleResult(query);

				dtlCode = Integer.parseInt(String.valueOf(dtlCodeObj[0][0]));

				System.out.println("dtlCode   " + dtlCode);
				for (int i = 0; i < invCode.length; i++) {
					logger.info("i - " + (i + 1) + "lease - " + leasedFlag[i]
							+ "  insu - " + insuranceFlag[i]);
					dtlObject[i][0] = bean.getCode();
					dtlObject[i][1] = invCode[i];
					dtlObject[i][2] = warehouseCode[i];
					dtlObject[i][3] = assignFlag[i];
					dtlObject[i][4] = quantityIterator[i];
					dtlObject[i][5] = availableIterator[i];
					dtlObject[i][6] = leasedFlag[i];
					dtlObject[i][7] = checkNull(leasedDate[i]);
					dtlObject[i][8] = insuranceFlag[i];
					dtlObject[i][9] = checkNull(insureanceDate[i]);
					dtlObject[i][10] = dtlCode ++;
				}
				result = getSqlModel().singleExecute(getQuery(5), dtlObject);
			}
		} // end of if
		if (result) {

			if (result) {

				if ((inventoryCodeItt != null && inventoryCodeItt.length > 0)
						&& (propertyCode != null && propertyCode.length > 0)) {

					String deleteQuery = " delete from  HRMS_ASSET_PROPERTIES "
							+ " where ASSET_MASTER_CODE=" + bean.getCode();
					result = getSqlModel().singleExecute(deleteQuery);

					String query = "  INSERT INTO HRMS_ASSET_PROPERTIES(ASSET_INVENTORY_CODE, ASSET_PROPERTY_ID, ASSET_PROPERTY_VALUE ,ASSET_MASTER_CODE) "
							+ " VALUES(?,?,?,?)";

					int count = 0;
					Object saveObj[][] = new Object[inventoryCodeItt.length
							* propertyCode.length][4];
					for (int i = 0; i < inventoryCodeItt.length; i++) {
						for (int j = 0; j < propertyCode.length; j++) {
							saveObj[count][0] = inventoryCodeItt[i];
							saveObj[count][1] = propertyCode[j];
							saveObj[count][2] = rows[i][j];
							saveObj[count][3] = bean.getCode();

							count++;
						}
					}

					result = getSqlModel().singleExecute(query, saveObj);
				}

			}
		}
		return result;

	}

	/*
	 * method name : delete() purpose : DELETE THE EXISTING RECORD FROM ASSET
	 * MASTER return type : boolean parameter : AssetMaster instance
	 */
	public boolean delete(AssetMaster bean) {
		boolean result = false;
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = String.valueOf(bean.getCode());

		Object sqlQuery[] = new Object[2];
		sqlQuery[0] = "DELETE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_MASTER_CODE= ? ";
		sqlQuery[1] = String.valueOf(getQuery(3));

		java.util.Vector dataVector = new Vector();

		dataVector.add(delObj);
		dataVector.add(delObj);

		result = getSqlModel().multiExecute(sqlQuery, dataVector);

		String delQuery = " delete from HRMS_ASSET_PROPERTIES where ASSET_MASTER_CODE="
				+ bean.getCode();

		result = getSqlModel().singleExecute(delQuery);
		/*
		 * boolean result = (getSqlModel().singleExecute("DELETE FROM
		 * HRMS_ASSET_MASTER_DTL WHERE ASSET_MASTER_CODE="+bean.getCode()));
		 * if(result) return getSqlModel().singleExecute(getQuery(3), delObj);
		 * else return false;
		 */

		return result;
	}

	/*
	 * method name : getAssetRecord() purpose : display the records according to
	 * selected master code return type : boolean parameter : AssetMaster
	 * instance
	 */
	public void getAssetRecord(AssetMaster bean, HttpServletRequest request) {
		try {
			Object addObj[] = new Object[1];
			addObj[0] = bean.getCode();
			logger.info("addObj===" + addObj[0]);
			/*
			 * select data from ASSET_MASTER
			 * 
			 * 
			 */
			Object[][] data = getSqlModel()
					.getSingleResult(getQuery(4), addObj);
			logger.info("data.length======" + data.length);
			bean.setCode(String.valueOf(data[0][0]));
			// bean.setInvCode(checkNull(String.valueOf(data[0][1])));
			bean.setPurchaseDate(checkNull(String.valueOf(data[0][1])));
			bean.setAssetCode(checkNull(String.valueOf(data[0][2])));
			bean.setAssetname(checkNull(String.valueOf(data[0][3])));
			bean.setPrice(checkNull(String.valueOf(data[0][4])));
			bean.setDescription(checkNull(String.valueOf(data[0][5])));
			bean.setSubTypeCode(checkNull(String.valueOf(data[0][6])));
			bean.setSubTypeName(checkNull(String.valueOf(data[0][7])));
			bean.setQuant(checkNull(String.valueOf(data[0][8])));
			bean.setAvailable(checkNull(String.valueOf(data[0][9])));
			bean.setAssigned(checkNull(String.valueOf(data[0][10])));
			bean.setVendorCode(checkNull(String.valueOf(data[0][11])));
			bean.setVendorName(checkNull(String.valueOf(data[0][12])));
			bean.setStatus(String.valueOf(data[0][13]));
			bean.setInvType(checkNull(String.valueOf(data[0][14])));
			// bean.setWarehouseCode(String.valueOf(data[0][15]));
			// bean.setWarehouseName(String.valueOf(data[0][16]));
			logger.info("asset category code==" + bean.getAssetCode());
			String invQuery = "SELECT ASSET_INVENTORY_CODE ,ASSET_WAREHOUSE_CODE,WAREHOUSE_NAME,ASSET_ASSISGN_FLAG,NVL(ASSET_QUANTITY,0),NVL(ASSET_AVAILABLE,0),"
					+ " nvl(ASSET_IS_ON_LEASE,'N'),NVL(TO_CHAR(ASSET_LEASE_RENEW_DATE,'DD-MM-YYYY'),''),"
					+ " nvl(ASSET_IS_INSURED,'N'),NVL(TO_CHAR(ASSET_INSURANCE_RENEW_DATE,'DD-MM-YYYY'),'')"
					+ " FROM HRMS_ASSET_MASTER_DTL"
					+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON(HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE) "
					+ " WHERE ASSET_MASTER_CODE ="
					+ bean.getCode()
					+ " ORDER BY ASSET_WAREHOUSE_CODE,HRMS_ASSET_MASTER_DTL.rowid";
			Object[][] inventoryData = getSqlModel().getSingleResult(invQuery);
			ArrayList<Object> tableList = new ArrayList<Object>();
			int quantityCheck = 0;
			for (int i = 0; i < inventoryData.length; i++) {
				AssetMaster bean1 = new AssetMaster();
				bean1.setInventoryCodeIterator(String
						.valueOf(inventoryData[i][0]));
				bean1.setWarehouseCodeIterator(String
						.valueOf(inventoryData[i][1]));
				bean1.setWarehouseNameIterator(String
						.valueOf(inventoryData[i][2]));
				bean1
						.setAssignFlagIterator(String
								.valueOf(inventoryData[i][3]));
				bean1.setQuantityIterator(String.valueOf(inventoryData[i][4]));
				bean1.setAvailableIterator(String.valueOf(inventoryData[i][5]));
				quantityCheck += Double.parseDouble(String
						.valueOf(inventoryData[i][4])); // add quantity of all
				bean1.setIsLeased(String.valueOf(inventoryData[i][6]));
				bean1.setLeaseDate(checkNull(String
						.valueOf(inventoryData[i][7])));
				bean1.setIsInsured(String.valueOf(inventoryData[i][8]));
				bean1.setInsureDate(checkNull(String
						.valueOf(inventoryData[i][9])));
				// inventories
				tableList.add(bean1);
			} // end of for loop
			bean.setTableList(tableList);
			logger.info("quantityCheck==" + quantityCheck);
			bean.setTableLength(String.valueOf(quantityCheck));
			bean.setListLength(String.valueOf(tableList.size()));
			// TODO Auto-generated method stub

			/*
			 * 
			 * Coded By VISHWAMBHAAR
			 */
			Object dataObj[][] = null;
			String query = "  select ASSET_SUBTYPE, PROPERTY_ID, PROPERTY_ATTRIBUTE, UNIT_NAME , UNIT_ID from HRMS_ASSET_PROPERTY_MASTER "
					+ "  inner join HRMS_UNIT_MASTER  on(HRMS_UNIT_MASTER.UNIT_CODE = HRMS_ASSET_PROPERTY_MASTER.UNIT_ID) "
					+ " where asset_subtype="
					+ bean.getSubTypeCode()
					+ "  order by PROPERTY_ID ";
			dataObj = getSqlModel().getSingleResult(query);
			/**
			 * SET PROPERTY HEADER
			 */
			bean.setPropertyHeadList(null);
			ArrayList<Object> propertyHeadList = new ArrayList<Object>();
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					AssetMaster bean1 = new AssetMaster();
					bean1.setPropertyName(checkNull(String
							.valueOf(dataObj[i][2])));
					bean1.setPropertyCode(checkNull(String
							.valueOf(dataObj[i][1])));
					bean1.setPropertyUnit(checkNull(String
							.valueOf(dataObj[i][3])));

					propertyHeadList.add(bean1);
				}
				bean.setPropertyHeadList(propertyHeadList);
			}
			/**
			 * SET PROPERTY DATA
			 */
			ArrayList<Object> tableListArr = new ArrayList<Object>();
			bean.setPropertyList(null);
			String query1 = " select ASSET_INVENTORY_CODE ,ASSET_PROPERTY_ID,ASSET_PROPERTY_VALUE from  HRMS_ASSET_PROPERTIES "
					+ " where ASSET_MASTER_CODE="
					+ bean.getCode()
					+ " order by ASSET_INVENTORY_CODE,ASSET_PROPERTY_ID ";
			Object inventorydataObj[][] = getSqlModel().getSingleResult(query1);
			int length = 0;
			if ((inventorydataObj != null && inventorydataObj.length > 0)
					&& (dataObj != null && dataObj.length > 0)) {
				length = inventorydataObj.length / dataObj.length;
			}
			int counter = 0;
			if (length > 0) {
				for (int i = 1; i <= length; i++) {
					ArrayList<Object> tableDtlList = new ArrayList<Object>();
					AssetMaster bean1 = new AssetMaster();

					bean1.setInventoryCodeItt(String
							.valueOf(inventorydataObj[counter][0]));

					if (dataObj != null && dataObj.length > 0) {
						for (int k = 0; k < dataObj.length; k++) {
							AssetMaster beanDtl = new AssetMaster();
							beanDtl.setPropertyNameDtl(checkNull(String
									.valueOf(inventorydataObj[counter][2])));
							tableDtlList.add(beanDtl);
							counter++;
						}
						bean1.setPropertyListDtl(tableDtlList);
					}

					tableListArr.add(bean1);
					bean.setPropertyDataFlag("true");
					bean.setTabDisplay("true");
				}
				bean.setPropertyList(tableListArr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// /////////

	}

	/*
	 * show existing record if any after selecting the sub type whose inventory
	 * type is same for all
	 * 
	 * method name :checkInventoryExist
	 */
	/*
	 * public void checkInventoryExist(AssetMaster bean){ logger.info("inside
	 * checkInventoryExist*********************************************");
	 * String query="SELECT ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER WHERE
	 * ASSET_SUBTYPE_CODE="+bean.getSubTypeCode() ; Object [][] masterCode
	 * =getSqlModel().getSingleResult(query); try{ if(masterCode !=null){ if
	 * (masterCode.length!=0){ bean.setCode(String.valueOf(masterCode[0][0]));
	 * getAssetRecord(bean); } } }catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * method name : generateInventory() purpose :Generate inventory list
	 * according to quantity given & set it into the list for sub type whose
	 * inventory type is Itemized Inventory code return type : void parameter :
	 * AssetMaster instance, String []invCode, String []warehouseCode,String
	 * []warehouseName,String []assignFlag,String []quantityIterator, String
	 * []availableIterator
	 */
	public void generateInventory(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] warehouseName,
			String[] assignFlag, String[] quantityIterator,
			String[] availableIterator, String[] leasedFlag,
			String[] leasedDate, String[] insuranceFlag, String[] insureanceDate) {

		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (invCode != null) {
				/*
				 * set the previous inventory List if any
				 * 
				 */
				for (int i = 0; i < invCode.length; i++) {
					AssetMaster bean1 = new AssetMaster();
					bean1.setInventoryCodeIterator(invCode[i]);
					bean1.setWarehouseCodeIterator(warehouseCode[i]);
					bean1.setWarehouseNameIterator(warehouseName[i]);
					bean1.setQuantityIterator(quantityIterator[i]);
					bean1.setAvailableIterator(availableIterator[i]);
					bean1.setAssignFlagIterator(assignFlag[i]);
					bean1.setIsLeased(leasedFlag[i]);
					bean1.setLeaseDate(leasedDate[i]);
					bean1.setIsInsured(insuranceFlag[i]);
					bean1.setInsureDate(insureanceDate[i]);
					tableList.add(bean1);
				} // end of for loop
			} // end of if
			for (int i = 1; i <= Integer.parseInt(bean.getQuantityWareHouse()); i++) {
				AssetMaster bean1 = new AssetMaster();
				if (i < 10)
					bean1.setInventoryCodeIterator(bean.getInvPrefix() + "0"
							+ i);
				else
					bean1.setInventoryCodeIterator(bean.getInvPrefix() + i);
				bean1.setWarehouseCodeIterator(bean.getWarehouseCode());
				bean1.setWarehouseNameIterator(bean.getWarehouseName());
				bean1.setAssignFlagIterator("U");
				bean1.setQuantityIterator("1");
				bean1.setAvailableIterator("1");
				bean1.setIsLeased("N");
				bean1.setLeaseDate("");
				bean1.setIsInsured("N");
				bean1.setInsureDate("");
				tableList.add(bean1);
			} // end of for loop
			bean.setTableList(tableList);
			logger.info("tableList.size()==" + tableList.size());
			bean.setTableLength(String.valueOf(tableList.size()));
			bean.setListLength(String.valueOf(tableList.size()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * method name : addInventory() purpose : Add inventory into list for sub
	 * type whose inventory type is Bulk Inventory code return type : boolean
	 * parameter : AssetMaster instance, String [] invCode,String
	 * []warehouseCode,String []warehouseName,String []assignFlag,String
	 * []quantityIterator, String []availableIterator
	 */
	public boolean addInventory(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] warehouseName,
			String[] assignFlag, String[] quantityIterator,
			String[] availableIterator) {

		ArrayList<Object> tableList = new ArrayList<Object>();
		double quantityCheck = 0.0;

		if (invCode != null) {
			/*
			 * set the previous inventory List if any
			 * 
			 */
			logger.info("in addInventory inside if");
			for (int j = 0; j < invCode.length; j++) {
				AssetMaster bean1 = new AssetMaster();
				bean1.setInventoryCodeIterator(invCode[j]);
				bean1.setWarehouseCodeIterator(warehouseCode[j]);
				bean1.setWarehouseNameIterator(warehouseName[j]);
				bean1.setQuantityIterator(Utility
						.twoDecimals(quantityIterator[j]));
				bean1.setAvailableIterator(availableIterator[j]);
				bean1.setAssignFlagIterator(assignFlag[j]);
				tableList.add(bean1);
				quantityCheck += Double.parseDouble(quantityIterator[j]);
			} // end of if

		} // end of if

		AssetMaster bean1 = new AssetMaster();
		bean1.setInventoryCodeIterator(bean.getInvPrefix());
		bean1.setWarehouseCodeIterator(bean.getWarehouseCode());
		bean1.setWarehouseNameIterator(bean.getWarehouseName());
		bean1.setQuantityIterator(Utility.twoDecimals(bean
				.getQuantityWareHouse()));
		bean1.setAvailableIterator(Utility.twoDecimals(bean
				.getQuantityWareHouse()));
		bean1.setAssignFlagIterator("U");
		quantityCheck += Double.parseDouble(bean.getQuantityWareHouse());
		tableList.add(bean1);

		bean.setTableList(tableList);
		logger.info("tableList.size()==" + tableList.size());
		logger.info("quantityCheck==" + quantityCheck);
		bean.setTableLength(String.valueOf(quantityCheck));
		bean.setListLength(String.valueOf(tableList.size()));
		logger.info("setListLength==" + bean.getListLength());
		return true;
	}

	/*
	 * method name : addInventory() purpose : remove the Inventory from list
	 * return type : void parameter : AssetMaster instance, String []invCode,
	 * String []warehouseCode,String []warehouseName,String []assignFlag,String
	 * []quantityIterator, String []availableIterator
	 */
	public void removeInventory(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] warehouseName,
			String[] assignFlag, String[] quantityIterator,
			String[] availableIterator) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		double quantityCheck = 0.0;
		for (int i = 0; i < invCode.length; i++) {
			AssetMaster bean1 = new AssetMaster();
			if (Integer.parseInt(bean.getParaId()) != i + 1) {
				bean1.setInventoryCodeIterator(invCode[i]);
				bean1.setWarehouseCodeIterator(warehouseCode[i]);
				bean1.setWarehouseNameIterator(warehouseName[i]);
				bean1.setAssignFlagIterator(assignFlag[i]);
				bean1.setQuantityIterator(Utility
						.twoDecimals(quantityIterator[i]));
				bean1.setAvailableIterator(availableIterator[i]);
				quantityCheck += Double.parseDouble(Utility
						.twoDecimals(quantityIterator[i]));
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setTableList(tableList);
		// bean.setTableLength(String.valueOf(tableList.size()));
		logger.info("quantityCheck==" + quantityCheck);
		bean.setTableLength(String.valueOf(quantityCheck));
		bean.setListLength(String.valueOf(tableList.size()));
	}

	/*
	 * method name : showInventoryList() purpose : show the Inventory List
	 * return type : void parameter : AssetMaster instance, String []invCode,
	 * String []warehouseCode,String []warehouseName,String []assignFlag,String
	 * []quantityIterator, String []availableIterator
	 */
	public void showInventoryList(AssetMaster bean, String[] invCode,
			String[] warehouseCode, String[] warehouseName,
			String[] assignFlag, String[] quantityIterator,
			String[] availableIterator) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		int quantityCheck = 0;
		if (invCode != null) {
			for (int i = 0; i < invCode.length; i++) {
				AssetMaster bean1 = new AssetMaster();

				bean1.setInventoryCodeIterator(invCode[i]);
				bean1.setWarehouseCodeIterator(warehouseCode[i]);
				bean1.setWarehouseNameIterator(warehouseName[i]);
				bean1.setAssignFlagIterator(assignFlag[i]);
				bean1.setQuantityIterator(Utility
						.twoDecimals(quantityIterator[i]));
				bean1.setAvailableIterator(availableIterator[i]);
				quantityCheck += Double.parseDouble(quantityIterator[i]);
				tableList.add(bean1);

			} // end of for loop
			bean.setTableList(tableList);
		} // end of if
		// bean.setTableLength(String.valueOf(tableList.size()));
		logger.info("quantityCheck==" + quantityCheck);
		bean.setTableLength(String.valueOf(quantityCheck));
	}

	/*
	 * method name : checkNull purpose : to check the null value return type :
	 * String parameter : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/*
	 * method name : showCategoryName purpose : show category name in the
	 * respective field return type : void parameter : AssetMaster instance
	 */
	public void showCategoryName(AssetMaster bean) {
		try {
			String query = "SELECT HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME,ASSET_INVENTORY_TYPE FROM HRMS_ASSET_SUBTYPE "
					+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE)"
					+ " WHERE ASSET_SUBTYPE_CODE=" + bean.getSubTypeCode();
			Object category[][] = getSqlModel().getSingleResult(query);
			bean.setAssetCode(String.valueOf(category[0][0]));
			bean.setAssetname(String.valueOf(category[0][1]));
			bean.setInvType(String.valueOf(category[0][2]));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * method name : report purpose : show the PDF report for selected master
	 * code. return type : void parameter : AssetMaster instance,
	 * HttpServletRequest request, HttpServletResponse response
	 */

	public void report(AssetMaster bean, HttpServletRequest request,
			HttpServletResponse response) {
		String s = "\nASSET MASTER REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);

		/**
		 * Added by deepak VENDOR_TYPE='S' in query
		 */
		String headerQuery = "SELECT ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME,NVL(VENDOR_NAME,' '),ASSET_QUANTITY,NVL(TO_CHAR(ASSET_PURCHASE_DATE,'DD-MM-YYYY'),' '), "
				+ " NVL(ASSET_PURCHASE_PRICE,''),ASSET_ASSIGNED,ASSET_AVAILABLE,ASSET_DESCRIPTION FROM HRMS_ASSET_MASTER "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE) "
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
				+ " LEFT JOIN HRMS_VENDOR ON(HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_MASTER.ASSET_VENDOR and VENDOR_TYPE='S') "
				+ " WHERE ASSET_MASTER_CODE=" + bean.getCode();

		String dtlQuery = "SELECT ROWNUM,ASSET_INVENTORY_CODE,WAREHOUSE_NAME";

		if (bean.getItemizedFlag().equals("true"))
			dtlQuery += ",NVL(TO_CHAR(ASSET_LEASE_RENEW_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(ASSET_INSURANCE_RENEW_DATE,'DD-MM-YYYY'),' ')";

		dtlQuery += " ,HRMS_ASSET_MASTER_DTL.ASSET_QUANTITY,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE, "
				+ " DECODE(ASSET_ASSISGN_FLAG,'U','AVAILABLE','A','ASSIGNED','L','LOST','D','DAMAGED')"
				+ " FROM HRMS_ASSET_MASTER_DTL "
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE) "
				+ " WHERE ASSET_MASTER_CODE=" + bean.getCode();

		Object[][] hearderData = getSqlModel().getSingleResult(headerQuery);
		Object[][] data = getSqlModel().getSingleResult(dtlQuery);

		Object headers[][] = new Object[5][4];
		headers[0][0] = "Asset Category";
		headers[0][1] = ": " + String.valueOf(hearderData[0][0]);
		headers[0][2] = "Asset Sub-Type ";
		headers[0][3] = ": " + String.valueOf(hearderData[0][1]);

		headers[1][0] = "Vendor Name";
		headers[1][1] = ": " + checkNull(String.valueOf(hearderData[0][2]));
		headers[1][2] = "Quantity ";
		headers[1][3] = ": " + String.valueOf(hearderData[0][3]);

		headers[2][0] = "Purchase Date";
		headers[2][1] = ": " + checkNull(String.valueOf(hearderData[0][4]));
		headers[2][2] = "Purchase Price ";
		headers[2][3] = ": " + checkNull(String.valueOf(hearderData[0][5]));

		headers[3][0] = "Asset Assigned ";
		headers[3][1] = ": " + checkNull(String.valueOf(hearderData[0][6]));
		headers[3][2] = "Asset Available ";
		headers[3][3] = ": " + checkNull(String.valueOf(hearderData[0][7]));

		headers[4][0] = "Description  ";
		headers[4][1] = ": " + checkNull(String.valueOf(hearderData[0][8]));
		headers[4][2] = "";
		headers[4][3] = "";

		int cellwidth[] = { 20, 30, 20, 30 };
		int alignment[] = { 0, 0, 0, 0 };
		String[] colnames;
		int[] cellwidth1;
		int[] alignment1;
		if (bean.getItemizedFlag().equals("true")) {
			colnames = new String[8];
			String dtlcols[] = { "Sr.No", "Inventory Code", "Warehouse",
					"Lease Renewal Date", "Insurance Renewal Date",
					"Asset Quantity", "Asset Available", "Asset Status" };
			colnames = dtlcols;

			int[] dtlcelwidth = { 10, 20, 20, 20, 20, 20, 15, 15 };
			int[] dtlalign = { 1, 0, 0, 1, 1, 1, 1, 0 };
			cellwidth1 = dtlcelwidth;
			alignment1 = dtlalign;
		} else {
			colnames = new String[6];
			String dtlcols[] = { "Sr.No", "Inventory Code", "Warehouse",
					"Asset Quantity", "Asset Available", "Asset Status" };
			colnames = dtlcols;
			int[] dtlcelwidth = { 10, 20, 20, 20, 15, 15 };
			int[] dtlalign = { 1, 0, 0, 1, 1, 0 };
			cellwidth1 = dtlcelwidth;
			alignment1 = dtlalign;
		}

		Object[][] heading = new Object[1][1];
		heading[0][0] = "Inventory Details :";
		String date = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] today = getSqlModel().getSingleResult(date);

		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addText("Date: " + today[0][0], 0, 2, 0);
		rg.addFormatedText("", 1, 0, 2, 3);

		rg.tableBodyNoBorder(headers, cellwidth, alignment);
		// rg.addFormatedText("Inventory Details:", 6, 0, 0, 0);
		rg.tableBodyBold(heading, new int[] { 100 }, new int[] { 0 });
		// rg.addFormatedText("", 6, 0, 0, 0);
		rg.tableBody(colnames, data, cellwidth1, alignment1);
		rg.createReport(response);

	}

	/*
	 * method name : Data purpose : to show the assets type in the list return
	 * type : void parameter : AssetType instance, HttpServletRequest request
	 */

	public void Data(AssetMaster bean, HttpServletRequest request) {
		// TODO Auto-generated method stub

		String query = " SELECT DISTINCT ASSET_MASTER_CODE,ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME,ASSET_INVENTORY_TYPE FROM HRMS_ASSET_MASTER "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE) "
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
				+ " INNER JOIN HRMS_ASSET_MASTER_DTL ON(HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE =HRMS_ASSET_MASTER.ASSET_MASTER_CODE)"
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE)"
				+ " WHERE WAREHOUSE_RESPONSIBLE_PERSON = "
				+ bean.getUserEmpId() + " ORDER BY ASSET_MASTER_CODE ";
		Object obj[][] = getSqlModel().getSingleResult(query);

		String pageIndex[] = Utility.doPaging(bean.getMyPage(), obj.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));

		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

			// for (int i = 0; i < obj.length; i++) {s
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				AssetMaster bean1 = new AssetMaster();

				bean1.setCode(checkNull(String.valueOf(obj[i][0])));
				bean1.setAssetname(checkNull(String.valueOf(obj[i][1])));
				bean1.setSubTypeName(checkNull(String.valueOf(obj[i][2])));
				bean1.setInvType(checkNull(String.valueOf(obj[i][3])));
				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
			bean.setMakeList("true");
		} else {
			bean.setMakeList("false");
		}
		bean.setIteratorList(list);
	}// End of Data

	public void callForEdit(AssetMaster bean) {
		// TODO Auto-generated method stub

	}

	public boolean deleteCheckedRecords(AssetMaster bean, String[] code) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			int count = 0;
			Object delObj[][] = new Object[1][1];
			logger.info("no. of records want to be delete : " + code.length);
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					delObj[0][0] = code[i];
					Object sqlQuery[] = new Object[2];
					sqlQuery[0] = "DELETE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_MASTER_CODE= ? ";
					sqlQuery[1] = String.valueOf(getQuery(3));

					java.util.Vector dataVector = new Vector();

					dataVector.add(delObj);
					dataVector.add(delObj);

					result = getSqlModel().multiExecute(sqlQuery, dataVector);
					if (!result) {
						++count;
					}
					String deleteQuery = "   DELETE FROM HRMS_ASSET_PROPERTIES "
							+ " WHERE ASSET_MASTER_CODE=" + delObj[0][0];
					result = getSqlModel().singleExecute(deleteQuery);

				}// End of if
			}// Enf of for
			if (count != 0)
				result = false;
			else
				result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void showPropertyData(HttpServletRequest request, String[] invCode,
			AssetMaster assetmaster) {

		try {
			// TODO Auto-generated method stub
			Object dataObj[][] = null;
			String query = "  select ASSET_SUBTYPE, PROPERTY_ID, PROPERTY_ATTRIBUTE, UNIT_NAME , UNIT_ID from HRMS_ASSET_PROPERTY_MASTER "
					+ "  inner join HRMS_UNIT_MASTER  on(HRMS_UNIT_MASTER.UNIT_CODE = HRMS_ASSET_PROPERTY_MASTER.UNIT_ID) "
					+ " where asset_subtype="
					+ assetmaster.getSubTypeCode()
					+ "  order by PROPERTY_ID ";
			dataObj = getSqlModel().getSingleResult(query);

			/**
			 * SET PROPERTY HEADER
			 */
			assetmaster.setPropertyHeadList(null);
			ArrayList<Object> propertyHeadList = new ArrayList<Object>();
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					AssetMaster bean1 = new AssetMaster();
					bean1.setPropertyName(String.valueOf(dataObj[i][2]));
					bean1.setPropertyCode(String.valueOf(dataObj[i][1]));
					bean1.setPropertyUnit(checkNull(String
							.valueOf(dataObj[i][3])));
					propertyHeadList.add(bean1);
				}
				assetmaster.setPropertyHeadList(propertyHeadList);
			}
			/**
			 * SET PROPERTY DATA
			 */
			ArrayList<Object> tableList = new ArrayList<Object>();

			assetmaster.setPropertyList(null);

			String inventoryCodeItt[] = request
					.getParameterValues("inventoryCodeItt");

			if (inventoryCodeItt != null && inventoryCodeItt.length > 0) {
				/*
				 * set the previous inventory List if any
				 * 
				 */
				for (int i = 0; i < inventoryCodeItt.length; i++) {
					ArrayList<Object> tableDtlList = new ArrayList<Object>();
					AssetMaster bean1 = new AssetMaster();
					bean1.setInventoryCodeItt(inventoryCodeItt[i]);

					if (dataObj != null && dataObj.length > 0) {
						for (int k = 0; k < dataObj.length; k++) {
							String rows[] = request.getParameterValues(String
									.valueOf(k));
							AssetMaster beanDtllocal = new AssetMaster();
							System.out.println("rows[j]:::::::::::::::"
									+ rows[k]);
							beanDtllocal.setPropertyNameDtl(rows[k]);
							tableDtlList.add(beanDtllocal);
							for (int j = 0; j < rows.length; j++) {

							}

						}
						bean1.setPropertyListDtl(tableDtlList);
					}
					tableList.add(bean1);
				} // end of for loop
			} // end of if

			if (Integer.parseInt(assetmaster.getQuantityWareHouse()) > 0) {
				for (int i = 1; i <= Integer.parseInt(assetmaster
						.getQuantityWareHouse()); i++) {
					ArrayList<Object> tableDtlList = new ArrayList<Object>();
					AssetMaster bean1 = new AssetMaster();
					if (i < 10) {
						bean1.setInventoryCodeItt(assetmaster.getInvPrefix()
								+ "0" + i);
					} else {
						bean1.setInventoryCodeItt(assetmaster.getInvPrefix()
								+ i);
					}

					if (dataObj != null && dataObj.length > 0) {
						for (int k = 0; k < dataObj.length; k++) {
							AssetMaster beanDtl = new AssetMaster();

							tableDtlList.add(beanDtl);
						}
						bean1.setPropertyListDtl(tableDtlList);
					}

					tableList.add(bean1);
					assetmaster.setPropertyDataFlag("true");
				}
				assetmaster.setPropertyList(tableList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getPropertyData(AssetMaster assetmaster) {
		Object dataObj[][] = null;
		try {

			String query = "  select ASSET_SUBTYPE, PROPERTY_ID, PROPERTY_ATTRIBUTE, UNIT_ID from HRMS_ASSET_PROPERTY_MASTER "
					+ " where asset_subtype="
					+ assetmaster.getSubTypeCode()
					+ "  order by PROPERTY_ID ";
			dataObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataObj;
	}
}
