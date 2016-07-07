package org.paradyne.model.Asset;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Asset.AssetReturn;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * 
 * @author krishna Date 18/08/2008
 */
public class AssetReturnModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * method to show the record
	 * 
	 * @param bean
	 * @return string
	 */
	public String show(AssetReturn bean) {
		String result = "";

		try {

			String query = " SELECT ROWNUM, NVL(ASSET_CATEGORY_NAME,''),NVL(ASSET_SUBTYPE_NAME,''),ASSET_INVENTORY_CODE,0,'','N',HRMS_ASSET_MASTER.ASSET_MASTER_CODE ,NVL(HRMS_ASSET_MASTER_DTL.ASSET_ITEM_CODE,0),ASSET_CATEGORY_CODE, ASSET_SUBTYPE_CODE "
					+ " FROM  HRMS_ASSET_MASTER_DTL "
					+ " INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE =HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "
					+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_MASTER.ASSET_CATEGORY_CODE = HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE)	LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE = HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE) ";

			query += " WHERE 1=1 ";

			if (!bean.getVendorId().equals("0")) {
				query += " AND ASSET_ASSIGNED_VENDOR="
						+ bean.getVendorId().trim();
			} else {
				query += " AND ASSET_ASSIGNED_EMP =" + bean.getEmpId();
			}
			query += " AND ASSET_SUBTYPE_FLAG = 'R' ";

			if (!bean.getStatus().equals("A")) {
				query += " AND ASSET_ASSISGN_FLAG='" + bean.getStatus() + "'";
			}// end of if
			else {
				query += " AND ASSET_ASSISGN_FLAG='A'";
			}

			bean.setWarehouseCode(getWarehouseForEmp(bean));
			Object[][] Data = getSqlModel().getSingleResult(query);
			if (Data.length < 1) {
				result = "No matching records found ";
			}// end of if
			else {
				ArrayList<Object> retlist = new ArrayList<Object>();
				for (int i = 0; i < Data.length; i++) {
					AssetReturn bean1 = new AssetReturn();
					bean1.setRowNum(checkNull(String.valueOf(Data[i][0])));
					bean1.setCategory(checkNull(String.valueOf(Data[i][1])));
					bean1.setSubType(checkNull(String.valueOf(Data[i][2])));
					bean1.setInventory(checkNull(String.valueOf(Data[i][3])));
					bean1.setAppCode(checkNull(String.valueOf(Data[i][4])));
					bean1.setHDate(checkNull(String.valueOf(Data[i][5])));
					if (!String.valueOf(Data[i][6]).equals("N")) {
						bean1.setDisb("Y");
						bean1.setChk("true");
					}// end of if
					bean1
							.setReturnStatus(checkNull(String
									.valueOf(Data[i][6])));
					bean1.setHiddenReturnStatus(checkNull(String
							.valueOf(Data[i][6])));
					bean1.setMasterCode(checkNull(String.valueOf(Data[i][7])));
					logger.info("krishna " + bean1.getMasterCode());

					bean1.setHiddenAutoCodeItt(checkNull(String
							.valueOf(Data[i][8])));

					if (!bean.getEmpId().equals("")) {
						bean1.setEmployeeOrVendorType("E");
						bean1.setEmployeeOrVendorCode(bean.getEmpId());
					} else {
						bean1.setEmployeeOrVendorType("V");
						bean1.setEmployeeOrVendorCode(bean.getVendorId());
					}

					bean1.setAssetTypeCodeItt(checkNull(String
							.valueOf(Data[i][9])));

					bean1.setAssetSubTypeCodeItt(checkNull(String
							.valueOf(Data[i][10])));

					retlist.add(bean1);

				}// end of for loop
				bean.setRetList(retlist);
				result = "";
			}// end of else

		} catch (Exception e) {
			e.printStackTrace();
			result = "No matching records found ";
		}

		return result;
	}

	/**
	 * method to show the record
	 * 
	 * @param bean
	 * @return string
	 */
	public String show_OLD(AssetReturn bean) {
		String result = "";

		try {
			String query = "SELECT ROWNUM,nvl(ASSET_CATEGORY_NAME,''),nvl(ASSET_SUBTYPE_NAME,''),ASSET_INVENTORY_CODE,ASSET_APPL_CODE,TO_CHAR(ASSET_RETURN_DATE,'DD-MM-YYYY'),ASSET_RETURN_FLAG,ASSET_MASTER_CODE ,NVL(ASSET_ITEM_CODE,0) FROM HRMS_ASSET_APP_ASSIGNEMENT"
					+ "	LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_APP_ASSIGNEMENT.ASSET_CATEGORY_CODE = HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE)"
					+ "	LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_APP_ASSIGNEMENT.ASSET_SUBTYPE_CODE = HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE)";
			// + " LEFT JOIN HRMS_ASSET_APPLICATION ON
			// (HRMS_ASSET_APP_ASSIGNEMENT.ASSET_APPL_CODE =
			// HRMS_ASSET_APPLICATION.ASSET_APPL_CODE)"
			// + " WHERE ASSET_APPL_CODE IN (SELECT ASSET_APPL_CODE FROM
			// HRMS_ASSET_APPLICATION WHERE
			// HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="

			query += " WHERE 1=1 ";

			if (!bean.getVendorId().equals("0")) {
				query += " AND ASSET_VENDOR_ID=" + bean.getVendorId().trim();
			} else {
				query += " AND ASSET_EMP_ID =" + bean.getEmpId();
			}
			query += " AND ASSET_SUBTYPE_FLAG = 'R' AND ASSET_RETURN_FLAG = 'N'";

			if (!bean.getStatus().equals("A")) {
				query += " AND ASSET_RETURN_FLAG='" + bean.getStatus() + "'";
			}// end of if

			bean.setWarehouseCode(getWarehouseForEmp(bean));
			Object[][] Data = getSqlModel().getSingleResult(query);
			if (Data.length < 1) {
				result = "No matching records found ";
			}// end of if
			else {
				ArrayList<Object> retlist = new ArrayList<Object>();
				for (int i = 0; i < Data.length; i++) {
					AssetReturn bean1 = new AssetReturn();
					bean1.setRowNum(checkNull(String.valueOf(Data[i][0])));
					bean1.setCategory(checkNull(String.valueOf(Data[i][1])));
					bean1.setSubType(checkNull(String.valueOf(Data[i][2])));
					bean1.setInventory(checkNull(String.valueOf(Data[i][3])));
					bean1.setAppCode(checkNull(String.valueOf(Data[i][4])));
					bean1.setHDate(checkNull(String.valueOf(Data[i][5])));
					if (!String.valueOf(Data[i][6]).equals("N")) {
						bean1.setDisb("Y");
						bean1.setChk("true");
					}// end of if
					bean1
							.setReturnStatus(checkNull(String
									.valueOf(Data[i][6])));
					bean1.setHiddenReturnStatus(checkNull(String
							.valueOf(Data[i][6])));
					bean1.setMasterCode(checkNull(String.valueOf(Data[i][7])));
					logger.info("krishna " + bean1.getMasterCode());

					bean1.setHiddenAutoCodeItt(checkNull(String
							.valueOf(Data[i][8])));

					retlist.add(bean1);

				}// end of for loop
				bean.setRetList(retlist);
				result = "";
			}// end of else

		} catch (Exception e) {
			e.printStackTrace();
			result = "No matching records found ";
		}

		return result;
	}

	/**
	 * method to modify the Asset Return
	 * 
	 * @param assetReturn
	 * @param request
	 * @return boolean
	 */
	public boolean modAsset(AssetReturn assetReturn, HttpServletRequest request) {

		boolean result = false;
		Object updateData[][] = null;
		try {

			String assetCataegoryCode[] = request
					.getParameterValues("assetTypeCodeItt");
			String subType[] = request
					.getParameterValues("assetSubTypeCodeItt");

			String employeeOrVendorType[] = request
					.getParameterValues("employeeOrVendorType");

			String employeeOrVendorCode[] = request
					.getParameterValues("employeeOrVendorCode");

			String invCOde[] = request.getParameterValues("inventory");
			String retDate[] = request.getParameterValues("hDate");
			String flag[] = request.getParameterValues("returnStatus");
			String masterCode[] = request.getParameterValues("masterCode");

			String hiddenAutoCodeItt[] = request
					.getParameterValues("hiddenAutoCodeItt");

			logger.info("flag length=" + flag.length);

			System.out.println("invCOde.length----------------"
					+ invCOde.length);

			if (invCOde != null && invCOde.length > 0) {
				updateData = new Object[invCOde.length][3];
				Object updateMaster[][] = new Object[invCOde.length][1];
				Object returnAsset[][] = new Object[invCOde.length][3];
				Object insertData[][] = new Object[invCOde.length][8];

				for (int i = 0; i < invCOde.length; i++) {

					String selectQuery = " SELECT * FROM HRMS_ASSET_APP_ASSIGNEMENT "
							+ " WHERE ASSET_ITEM_CODE=" + hiddenAutoCodeItt[i];

					Object[][] isDataAvailble = getSqlModel().getSingleResult(
							selectQuery);

					if (!retDate[i].equals("") && retDate[i] != null) {
						updateData[i][0] = retDate[i];
						updateData[i][1] = String.valueOf(flag[i]);
						updateData[i][2] = hiddenAutoCodeItt[i];
					}
					if (String.valueOf(flag[i]).equals("Y")) {
						returnAsset[i][0] = "U";
						returnAsset[i][1] = "1";
					} // end of if
					else if (String.valueOf(flag[i]).equals("N")) {
						returnAsset[i][0] = "A";
						returnAsset[i][1] = "0";
					} // end of else
					else {
						returnAsset[i][0] = String.valueOf(flag[i]);
						returnAsset[i][1] = "0";
					}// end of else
					// updateData[i][2] = applCode[i];

					returnAsset[i][2] = hiddenAutoCodeItt[i];

					/*
					 * returnAsset[i][2] = masterCode[i]; returnAsset[i][3] =
					 * invCOde[i]; returnAsset[i][4] =
					 * assetReturn.getWarehouseCode();
					 */

					updateMaster[i][0] = masterCode[i];

					if (isDataAvailble != null && isDataAvailble.length == 0) {

						if (!String.valueOf(retDate[i]).equals("")) {
							String str = String
									.valueOf(employeeOrVendorType[i]);
							String strVal = "";
							if (str.equals("E")) {
								strVal = assetReturn.getEmpId();
							} else {
								strVal = assetReturn.getVendorId();
							}
							String insertQuery = " INSERT INTO HRMS_ASSET_APP_ASSIGNEMENT(ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_INVENTORY_CODE,ASSET_MASTER_CODE,ASSET_RETURN_FLAG,ASSET_RETURN_DATE , ";
							if (str.equals("E")) {
								insertQuery += " ASSET_EMP_ID ";
							}
							if (str.equals("V")) {
								insertQuery += " ASSET_VENDOR_ID ";
							}
							insertQuery += " ,ASSET_ITEM_CODE,ASSET_OWNED_BY )";

							insertQuery += " VALUES (" + assetCataegoryCode[i]
									+ "," + subType[i] + ",'"
									+ String.valueOf(invCOde[i]) + "',"
									+ String.valueOf(masterCode[i])
									+ ",'Y',TO_DATE('"
									+ String.valueOf(retDate[i])
									+ "','DD-MM-YYYY'), ";

							if (str.equals("E")) {
								insertQuery += strVal;
							}
							if (str.equals("V")) {
								insertQuery += strVal;
							}
							insertQuery += "," + hiddenAutoCodeItt[i] + ",'"
									+ str + "'" + ")";

							result = getSqlModel().singleExecute(insertQuery);

						} else {
							result = getSqlModel().singleExecute(getQuery(1),
									updateData);
						}
					}

				}// end of for loop

				result = getSqlModel().singleExecute(getQuery(2), returnAsset);

				for (int i = 0; i < invCOde.length; i++) {
					if (String.valueOf(flag[i]).equals("Y")) {
						String query = "UPDATE HRMS_ASSET_MASTER SET ASSET_ASSIGNED=(SELECT SUM(ASSET_QUANTITY-ASSET_AVAILABLE) FROM HRMS_ASSET_MASTER_DTL "
								+ "WHERE ASSET_MASTER_CODE="
								+ masterCode[i]
								+ ") ,ASSET_AVAILABLE=(SELECT SUM(ASSET_AVAILABLE) FROM HRMS_ASSET_MASTER_DTL "
								+ "WHERE ASSET_MASTER_CODE="
								+ masterCode[i]
								+ "),ASSET_STATUS='U' WHERE ASSET_MASTER_CODE="
								+ masterCode[i];
						getSqlModel().singleExecute(query);
					} // end of if
				} // end of for loop
				logger.info("warehouse code====="
						+ assetReturn.getWarehouseCode());

				if (assetReturn.getVendorId().equals("0")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("ASSET RETURN  - MAIL TO APPLICANT");

					String module = "Asset Assignment";
					String msgType = "A";
					String alertLevel = "1";

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, assetReturn.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, assetReturn.getUserEmpId());

					template.configMailAlert();
					try {

						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				} else if (assetReturn.getEmpId().equals("0")) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("ASSET RETURN  - VENDOR MAIL TO APPLICANT");

					String module = "Asset Assignment";
					String msgType = "A";
					String alertLevel = "1";

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, assetReturn.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, assetReturn.getUserEmpId());

					template.configMailAlert();
					try {

						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				}
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * method to modify the Asset Return
	 * 
	 * @param assetReturn
	 * @param request
	 * @return boolean
	 */
	public boolean modAsset_OLD(AssetReturn assetReturn,
			HttpServletRequest request) {

		boolean result = false;
		Object updateData[][] = null;
		try {

			String invCOde[] = request.getParameterValues("inventory");
			String retDate[] = request.getParameterValues("hDate");
			String flag[] = request.getParameterValues("returnStatus");
			String masterCode[] = request.getParameterValues("masterCode");

			String hiddenAutoCodeItt[] = request
					.getParameterValues("hiddenAutoCodeItt");

			logger.info("flag length=" + flag.length);

			if (invCOde != null && invCOde.length > 0) {
				updateData = new Object[invCOde.length][3];
				Object updateMaster[][] = new Object[invCOde.length][1];
				Object returnAsset[][] = new Object[invCOde.length][3];

				for (int i = 0; i < invCOde.length; i++) {

					if (!retDate[i].equals("") && retDate[i] != null) {
						updateData[i][0] = retDate[i];
						updateData[i][1] = String.valueOf(flag[i]);
						updateData[i][2] = hiddenAutoCodeItt[i];
					}
					if (String.valueOf(flag[i]).equals("Y")) {
						returnAsset[i][0] = "U";
						returnAsset[i][1] = "1";
					} // end of if
					else if (String.valueOf(flag[i]).equals("N")) {
						returnAsset[i][0] = "A";
						returnAsset[i][1] = "0";
					} // end of else
					else {
						returnAsset[i][0] = String.valueOf(flag[i]);
						returnAsset[i][1] = "0";
					}// end of else
					// updateData[i][2] = applCode[i];

					returnAsset[i][2] = hiddenAutoCodeItt[i];

					/*
					 * returnAsset[i][2] = masterCode[i]; returnAsset[i][3] =
					 * invCOde[i]; returnAsset[i][4] =
					 * assetReturn.getWarehouseCode();
					 */

					updateMaster[i][0] = masterCode[i];
				}// end of for loop

				result = getSqlModel().singleExecute(getQuery(2), returnAsset);

				for (int i = 0; i < invCOde.length; i++) {
					if (String.valueOf(flag[i]).equals("Y")) {
						String query = "UPDATE HRMS_ASSET_MASTER SET ASSET_ASSIGNED=(SELECT SUM(ASSET_QUANTITY-ASSET_AVAILABLE) FROM HRMS_ASSET_MASTER_DTL "
								+ "WHERE ASSET_MASTER_CODE="
								+ masterCode[i]
								+ ") ,ASSET_AVAILABLE=(SELECT SUM(ASSET_AVAILABLE) FROM HRMS_ASSET_MASTER_DTL "
								+ "WHERE ASSET_MASTER_CODE="
								+ masterCode[i]
								+ "),ASSET_STATUS='U' WHERE ASSET_MASTER_CODE="
								+ masterCode[i];
						getSqlModel().singleExecute(query);
					} // end of if
				} // end of for loop
				logger.info("warehouse code====="
						+ assetReturn.getWarehouseCode());

				result = getSqlModel().singleExecute(getQuery(1), updateData);
				if (assetReturn.getVendorId().equals("0")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("ASSET RETURN  - MAIL TO APPLICANT");

					String module = "Asset Assignment";
					String msgType = "A";
					String alertLevel = "1";

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, assetReturn.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, assetReturn.getEmpId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, assetReturn.getUserEmpId());

					template.configMailAlert();
					try {

						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				} else if (assetReturn.getEmpId().equals("0")) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("ASSET RETURN  - VENDOR MAIL TO APPLICANT");

					String module = "Asset Assignment";
					String msgType = "A";
					String alertLevel = "1";

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, assetReturn.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, assetReturn.getVendorId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, assetReturn.getUserEmpId());

					template.configMailAlert();
					try {

						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				}
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * METHOD TO GET THE EMPLOYEE'S WAREHOUSE CODE
	 * 
	 * @param AssetReturn
	 *            instance
	 * @return string
	 */
	public String getWarehouseForEmp(AssetReturn bean) {
		String wareHouseReturn = "";

		String warehouseQuery = "SELECT WAREHOUSE_CODE FROM HRMS_WAREHOUSE_BRANCH WHERE WAREHOUSE_BRANCH=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ bean.getEmpId() + ")";
		Object[][] warehouseCode = getSqlModel()
				.getSingleResult(warehouseQuery);

		try {
			for (int i = 0; i < warehouseCode.length; i++)
				wareHouseReturn += "" + warehouseCode[i][0] + ",";
			wareHouseReturn = wareHouseReturn.substring(0, wareHouseReturn
					.length() - 1);
		} catch (Exception e) {
			wareHouseReturn = "0";
			logger.info("Exception in getWarehouseForEmp()" + e);
		}
		return wareHouseReturn;
	}

	/**
	 * method to check the null values and make them as blanks
	 * 
	 * @param result
	 * @return string
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
