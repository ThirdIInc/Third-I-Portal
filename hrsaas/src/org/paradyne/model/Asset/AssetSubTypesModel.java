/**
 * 
 */
package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Asset.AssetSubTypes;
import org.paradyne.bean.Asset.AssetType;
import org.paradyne.bean.Asset.WareHouseMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj Date 25/07/2008 AssetSubTypesModel class to write business
 *         logic to add, update, view asset sub type
 */
public class AssetSubTypesModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * method name : Data purpose : to show the assets sub type in the list
	 * return type : void parameter : AssetSubTypes instance, HttpServletRequest
	 * request
	 */

	public void Data(AssetSubTypes bean, HttpServletRequest request) {

		Object[][] obj = getSqlModel().getSingleResult(getQuery(4));

		// Object repData[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
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

			// for (int i = 0; i < obj.length; i++) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				AssetSubTypes bean1 = new AssetSubTypes();
				bean1.setCategoryIterator(checkNull(String.valueOf(obj[i][0])));
				bean1.setSubTypeCodeIterator(checkNull(String
						.valueOf(obj[i][1])));
				bean1.setHdeleteCode(checkNull(String.valueOf(obj[i][1])));
				bean1
						.setReturnTypeIterator(checkNull(String
								.valueOf(obj[i][3])));
				bean1.setSubTypeNameIterator(checkNull(String
						.valueOf(obj[i][2])));
				bean1.setIsActive(checkNull(String.valueOf(obj[i][4])));
				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
		} else {
			bean.setListLength("false");
		}

		if (list.size() > 0)
			bean.setListLength("true");
		else
			bean.setListLength("false");

		bean.setIteratorlist(list);

		/*
		 * Object [][] repData = getSqlModel().getSingleResult(getQuery(4)); int
		 * REC_TOTAL = 0; int To_TOT = 20; int From_TOT = 0; int pg1=0; int
		 * PageNo1=1;//---------- REC_TOTAL = repData.length; int
		 * no_of_pages=Math.round(REC_TOTAL/20); double row =
		 * (double)repData.length/20.0;
		 * 
		 * java.math.BigDecimal value1 = new java.math.BigDecimal(row);
		 * 
		 * int rowCount1
		 * =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
		 * 
		 * 
		 * ArrayList<Object> obj=new ArrayList<Object>();
		 * request.setAttribute("abc", rowCount1);
		 * 
		 * //PageNo
		 * if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals("
		 * ")) { PageNo1=1; From_TOT=0; To_TOT=20;
		 * 
		 * if(To_TOT >repData.length){ To_TOT=repData.length; } // end of if
		 * bean.setMyPage("1"); } // end of if else{
		 * 
		 * pg1= Integer.parseInt(bean.getMyPage()); PageNo1=pg1;
		 * 
		 * if(pg1 ==1){ From_TOT=0; To_TOT=20; } // end of if else{
		 * To_TOT=To_TOT*pg1; From_TOT=(To_TOT-20); } // end of else if(To_TOT
		 * >repData.length){ To_TOT=repData.length; } // end of if } // end of
		 * else request.setAttribute("xyz", PageNo1);
		 * 
		 * setting the data in the list according to the page no.
		 * 
		 * for(int i=From_TOT;i<To_TOT;i++){ //setting AssetSubTypes bean1 =
		 * new AssetSubTypes ();
		 * 
		 * bean1.setCategoryIterator(String.valueOf(repData[i][0]));
		 * bean1.setSubTypeCodeIterator(String.valueOf(repData[i][1]));
		 * bean1.setHdeleteCode(String.valueOf(repData[i][1]));
		 * bean1.setReturnTypeIterator(String.valueOf(repData[i][3]));
		 * bean1.setSubTypeNameIterator(String.valueOf(repData[i][2]));
		 * 
		 * obj.add(bean1); } // end of for loop if(obj.size()>0)
		 * bean.setListLength("true"); else bean.setListLength("false");
		 * bean.setIteratorlist(obj);
		 */
	}

	/*
	 * method name : save purpose : to add the asset sub type in the
	 * HRMS_ASSET_SUBTYPE return type : boolean parameter : AssetSubTypes
	 * instance
	 */

	public boolean save(AssetSubTypes bean, HttpServletRequest request) {
		Object[][] addObj = new Object[1][9]; // insert Object
		/*
		 * getting the form data in the insert Object
		 * 
		 */
		addObj[0][0] = bean.getAssetCategoryCode();
		addObj[0][1] = bean.getAssetSubTypeName();
		addObj[0][2] = bean.getReturnType();
		addObj[0][3] = bean.getUnit();
		addObj[0][4] = bean.getLeadTime();
		addObj[0][5] = bean.getReOrderLevel();
		addObj[0][6] = bean.getInvType();
		addObj[0][7] = bean.getIsActive();
		addObj[0][8] = bean.getSafetystock();
		logger.info("asset sub type model --> before result");
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		logger.info("result -- > " + result);
		try {
			if (result) {
				logger.info("record saved successfully");
				String query = "select max(ASSET_SUBTYPE_CODE) from HRMS_ASSET_SUBTYPE";
				Object record[][] = getSqlModel().getSingleResult(query);
				if (record != null && record.length > 0)
					bean.setAssetsubTypeCode(String.valueOf(record[0][0]));
				logger.info("new code is ------> " + record[0][0]);

				String[] propertyName = request
						.getParameterValues("propertyNameItt");
				String[] propertyUnit = request
						.getParameterValues("propertyUnitItt");

				String propertyIdquery = " select nvl(max(PROPERTY_ID),0)+1 from HRMS_ASSET_PROPERTY_MASTER ";
				Object propertyIdqueryObj[][] = getSqlModel().getSingleResult(
						propertyIdquery);
				int count = 0;
				if (propertyIdqueryObj != null && propertyIdqueryObj.length > 0) {
					count = Integer.parseInt(String
							.valueOf(propertyIdqueryObj[0][0]));
				}

				if (propertyName != null && propertyName.length > 0) {
					Object insertObj[][] = new Object[propertyName.length][4];
					for (int i = 0; i < propertyUnit.length; i++) {
						insertObj[i][0] = bean.getAssetsubTypeCode();
						insertObj[i][1] = count++;
						insertObj[i][2] = propertyName[i];
						insertObj[i][3] = propertyUnit[i];
					}
					result = getSqlModel()
							.singleExecute(getQuery(8), insertObj);
				}

			}
		} catch (Exception e) {
			logger.error("assetsubTypeModel setAssetMethod" + e);
		}

		return result; // insert query
	}

	/*
	 * method name : update purpose : to update the asset sub type in the
	 * HRMS_ASSET_SUBTYPE return type : boolean parameter : AssetSubTypes
	 * instance
	 */
	public boolean update(AssetSubTypes bean, HttpServletRequest request) {
		Object[][] updateObj = new Object[1][10]; // update Object
		/*
		 * getting the form data in the update Object
		 * 
		 */

		boolean result = false;
		updateObj[0][0] = bean.getAssetCategoryCode();
		updateObj[0][1] = bean.getAssetSubTypeName();
		updateObj[0][2] = bean.getReturnType();
		updateObj[0][3] = bean.getUnit();
		updateObj[0][4] = bean.getLeadTime();
		updateObj[0][5] = bean.getReOrderLevel();
		updateObj[0][6] = bean.getInvType();
		updateObj[0][7] = bean.getIsActive();
		updateObj[0][8] = bean.getSafetystock();
		updateObj[0][9] = bean.getAssetsubTypeCode();

		result = getSqlModel().singleExecute(getQuery(2), updateObj); // update

		String query = "Delete from HRMS_ASSET_PROPERTY_MASTER  where ASSET_SUBTYPE= "
				+ bean.getAssetsubTypeCode();
		result = getSqlModel().singleExecute(query);

		String[] propertyName = request.getParameterValues("propertyNameItt");
		String[] propertyUnit = request.getParameterValues("propertyUnitItt");

		String propertyIdquery = " select nvl(max(PROPERTY_ID),0)+1 from HRMS_ASSET_PROPERTY_MASTER ";
		Object propertyIdqueryObj[][] = getSqlModel().getSingleResult(
				propertyIdquery);
		int count = 0;
		if (propertyIdqueryObj != null && propertyIdqueryObj.length > 0) {
			count = Integer.parseInt(String.valueOf(propertyIdqueryObj[0][0]));
		}

		if (propertyName != null) {
			Object insertObj[][] = new Object[propertyName.length][4];
			for (int i = 0; i < propertyUnit.length; i++) {
				insertObj[i][0] = bean.getAssetsubTypeCode();
				insertObj[i][1] = count++;
				insertObj[i][2] = propertyName[i];
				insertObj[i][3] = propertyUnit[i];
			}
			result = getSqlModel().singleExecute(getQuery(8), insertObj);
		}

		setItteratorData(bean, bean.getAssetsubTypeCode());

		return result;

		// query
	}

	/*
	 * method name : delete purpose : to delete the asset sub type from the
	 * HRMS_ASSET_SUBTYPE return type : boolean parameter : AssetSubTypes
	 * instance
	 */
	public boolean delete(AssetSubTypes bean) {
		boolean result = false;
		Object[][] obj = new Object[1][1];
		obj[0][0] = bean.getAssetsubTypeCode();
		result = getSqlModel().singleExecute(getQuery(5), obj);

		if (result) {
			String query = "Delete from HRMS_ASSET_PROPERTY_MASTER  where ASSET_SUBTYPE= "
					+ bean.getAssetsubTypeCode();
			result = getSqlModel().singleExecute(query);
		}

		return result;
	}

	/*
	 * method name : checkNull purpose : check the null value return type :
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
	 * method name : showData purpose : to show the asset sub type from the
	 * HRMS_ASSET_SUBTYPE on the screen return type : void parameter :
	 * AssetSubTypes instance
	 */
	public void showData(AssetSubTypes bean) {

		try {
			logger
					.info("in show data action class$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			Object[] param = new Object[1];
			param[0] = bean.getAssetsubTypeCode();
			Object[][] data = getSqlModel().getSingleResult(getQuery(3), param);
			System.out.println("Object [][]>>>>>>>>>>" + data[0][0]);
			/*
			 * to set the form parameters on the screen
			 * 
			 */
			bean.setAssetCategoryCode(String.valueOf(data[0][0]));
			// System.out.println("data[0][0]====="+data[0][0]);
			bean.setAssetCategoryName(checkNull(String.valueOf(data[0][1])));
			// System.out.println("data[0][0]====="+data[0][1]);
			bean.setAssetsubTypeCode(String.valueOf(data[0][2]));
			// System.out.println("data[0][0]====="+data[0][2]);
			bean.setAssetSubTypeName(checkNull(String.valueOf(data[0][3])));
			// System.out.println("data[0][0]====="+data[0][3]);
			bean.setReturnType(checkNull(String.valueOf(data[0][4])));
			// System.out.println("data[0][0]====="+data[0][4]);
			bean.setUnit(checkNull(String.valueOf(data[0][5])));
			// System.out.println("data[0][0]====="+data[0][5]);
			bean.setLeadTime(checkNull(String.valueOf(data[0][6])));
			// System.out.println("data[0][0]====="+data[0][6]);
			bean.setReOrderLevel(checkNull(String.valueOf(data[0][7])));
			// System.out.println("data[0][0]====="+data[0][7]);
			bean.setInvType(checkNull(String.valueOf(data[0][8])));
			System.out.println("data[0][0]=====" + data[0][8]);
			// bean.setHiddenInvType(String.valueOf(data[0][8]));
			bean.setIsActive(checkNull(String.valueOf(data[0][9])));
			System.out.println("data[0][0]=====" + data[0][9]);
			bean.setSafetystock(checkNull(String.valueOf(data[0][10])));
			logger.info("show data model class");
			setItteratorData(bean, bean.getAssetsubTypeCode());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * method name : setData purpose : to show the asset sub type from the
	 * HRMS_ASSET_SUBTYPE on the screen for editing return type : void parameter :
	 * AssetSubTypes instance
	 */
	public void setData(AssetSubTypes bean) {
		try {
			Object[] param = new Object[1];
			param[0] = bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(getQuery(3), param);
			/*
			 * to set the form parameters on the screen for editing
			 * 
			 */
			bean.setAssetCategoryCode(String.valueOf(data[0][0]));
			bean.setAssetCategoryName(String.valueOf(data[0][1]));
			bean.setAssetsubTypeCode(String.valueOf(data[0][2]));
			bean.setAssetSubTypeName(String.valueOf(data[0][3]));
			bean.setReturnType(String.valueOf(data[0][4]));
			bean.setUnit(String.valueOf(data[0][5]));
			bean.setLeadTime(checkNull(String.valueOf(data[0][6])));
			bean.setReOrderLevel(checkNull(String.valueOf(data[0][7])));
			bean.setInvType(String.valueOf(data[0][8]));
			bean.setHiddenInvType(String.valueOf(data[0][8]));
			bean.setIsActive(String.valueOf(data[0][9]));
			bean.setSafetystock(checkNull(String.valueOf(data[0][10])));
			bean.setHiddenassetCategoryCode(String.valueOf(data[0][0]));
			setItteratorData(bean, bean.getHiddencode());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setItteratorData(AssetSubTypes bean, String code) {
		try {
			String query = " select PROPERTY_ID,PROPERTY_ATTRIBUTE ,UNIT_ID "
					+ " from HRMS_ASSET_PROPERTY_MASTER "
					+ " where ASSET_SUBTYPE=" + code;
			Object[][] resultObj = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (resultObj != null && resultObj.length > 0) {
				for (int i = 0; i < resultObj.length; i++) {
					AssetSubTypes localbean = new AssetSubTypes();
					localbean.setPropertyNameItt(String
							.valueOf(resultObj[i][1]));
					localbean.setPropertyUnitItt(String
							.valueOf(resultObj[i][2]));
					localbean.setPropertyUnitNameItt(getUnitName(String
							.valueOf(resultObj[i][2])));
					list.add(localbean);
				}
			}
			bean.setPropertyList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * method name : deleteCheckedRecords purpose : to delete the checked asset
	 * sub type from the HRMS_ASSET_SUBTYPE return type : boolean parameter :
	 * AssetSubTypes instance, String[] code
	 */
	public boolean deleteCheckedRecords(AssetSubTypes bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(5), delete);
					if (!result)
						count++;
				} // end of if
			} // end of for loop
		} // end of if
		if (count != 0) {
			result = false;
			return result;
		} // end of if
		else {
			result = true;
			return result;
		} // end of else
	}

	/*
	 * method name : report purpose : to show the asset sub type report return
	 * type : void parameter : AssetSubTypes instance, HttpServletRequest
	 * request, HttpServletResponse response
	 */
	public void report(AssetSubTypes assetSubTypes, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String s = "\nAsset Sub-Type Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = "SELECT ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME ,"
				+ " DECODE(ASSET_SUBTYPE_FLAG,'R','Returnable','N','Non-Returnable'),ASSET_SUBTYPE_CODE FROM HRMS_ASSET_SUBTYPE "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE) "
				+ " ORDER BY ASSET_SUBTYPE_CODE ";

		Object[][] Data = getSqlModel().getSingleResult(query);
		String date = "select To_char(Sysdate,'dd-mm-yyyy') from dual";
		Object[][] DATE = getSqlModel().getSingleResult(date);

		Object[][] resulttable = new Object[Data.length][4];
		if (Data.length > 0) {
			/*
			 * get the retrieved data in the Object to set it in the table
			 * format.
			 * 
			 */
			for (int i = 0; i < resulttable.length; i++) {
				resulttable[i][0] = String.valueOf(i + 1);
				resulttable[i][1] = checkNull(String.valueOf(Data[i][0]));
				resulttable[i][2] = checkNull(String.valueOf(Data[i][1]));
				resulttable[i][3] = checkNull(String.valueOf(Data[i][2]));

			} // end of for loop
			String colnames[] = { "Sr.No", "Asset Category Name",
					"Asset Sub-Type Name", "Asset Return Type" };

			int cellwidth[] = { 10, 30, 40, 20 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addText(s, 2, 1, 0);
			rg.addText("Date:" + String.valueOf(DATE[0][0]), 2, 2, 0);
			rg.tableBody(colnames, resulttable, cellwidth, alignment);
		} // end of if

		rg.createReport(response);
	}

	public boolean addToList(AssetSubTypes assetSubTypes,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		boolean isDuplicate = false;
		try {

			ArrayList<Object> list = new ArrayList<Object>();

			String[] propertyNameItt = request
					.getParameterValues("propertyNameItt");
			String[] propertyUnitItt = request
					.getParameterValues("propertyUnitItt");
			String[] propertyUnitNameItt = request
					.getParameterValues("propertyUnitNameItt");
			if (propertyNameItt != null && propertyNameItt.length > 0) {
				for (int i = 0; i < propertyUnitItt.length; i++) {
					AssetSubTypes localbean = new AssetSubTypes();
					localbean.setPropertyNameItt(propertyNameItt[i]);
					localbean.setPropertyUnitItt(propertyUnitItt[i]);
					if (localbean.getPropertyNameItt().equalsIgnoreCase(
							assetSubTypes.getPropertyName())&&(localbean.getPropertyUnitItt().equals(assetSubTypes.getProperUnit()))) {
						isDuplicate = true;
					}
					localbean.setPropertyUnitNameItt(propertyUnitNameItt[i]);
					list.add(localbean);
				}
			}

			if (!isDuplicate) {
				AssetSubTypes bean = new AssetSubTypes();
				bean.setPropertyNameItt(assetSubTypes.getPropertyName());
				bean.setPropertyUnitItt(assetSubTypes.getProperUnit());
				bean.setPropertyUnitNameItt(getUnitName(assetSubTypes
						.getProperUnit()));
				list.add(bean);

			}
			assetSubTypes.setPropertyList(list);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return isDuplicate;
	}

	public String getUnitName(String unitCode) {
		String unitName = "";
		String quer = "SELECT UNIT_NAME FROM HRMS_UNIT_MASTER  where  UNIT_CODE="
				+ unitCode;
		Object[][] data = getSqlModel().getSingleResult(quer);
		if (data != null && data.length > 0) {
			unitName = String.valueOf(data[0][0]);
		}
		return unitName;
	}

	// Updated by Anantha lakshmi
	/*
	 * method name : delete Properties purpose : to delete the Properties from
	 * list return type : void parameter : AssetSubTypes instance, String[]
	 * propertyName,String[] propertyUnitName
	 */
	public void deleteAssetSubType(AssetSubTypes bean, String[] propertyName,
			String[] propertyUnitName, String[] propertyUnitItt) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		for (int i = 0; i < propertyUnitName.length; i++) {
			AssetSubTypes bean1 = new AssetSubTypes();
			/*
			 * remove the selected branch
			 */
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setPropertyNameItt(propertyName[i]);
				bean1.setPropertyUnitItt(propertyUnitItt[i]);
				bean1.setPropertyUnitNameItt(propertyUnitName[i]);
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setPropertyList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
	}

	public boolean updateList(AssetSubTypes assetSubTypes,
			HttpServletRequest request) {

		boolean isDuplicate = false;
		try {

			ArrayList<Object> list = new ArrayList<Object>();

			String[] propertyNameItt = request
					.getParameterValues("propertyNameItt");
			String[] propertyUnitItt = request
					.getParameterValues("propertyUnitItt");
			String[] propertyUnitNameItt = request
					.getParameterValues("propertyUnitNameItt");
			if (propertyNameItt != null && propertyNameItt.length > 0) {

				for (int i = 0; i < propertyNameItt.length; i++) {
					if (propertyNameItt[i].equalsIgnoreCase(assetSubTypes
							.getPropertyName())&&(propertyUnitItt[i].equals(assetSubTypes.getProperUnit()))) {
						isDuplicate = true;
					}
				}

				for (int i = 0; i < propertyUnitItt.length; i++) {

					if (i == Integer.parseInt(assetSubTypes.getHiddenEdit()) - 1) {

						if (isDuplicate) {
							AssetSubTypes localbean = new AssetSubTypes();
							localbean.setPropertyNameItt(propertyNameItt[i]);
							localbean.setPropertyUnitItt(propertyUnitItt[i]);
							localbean
									.setPropertyUnitNameItt(propertyUnitNameItt[i]);
							list.add(localbean);
						} else {
							AssetSubTypes bean = new AssetSubTypes();
							bean.setPropertyNameItt(assetSubTypes
									.getPropertyName());
							bean.setPropertyUnitItt(assetSubTypes
									.getProperUnit());
							bean
									.setPropertyUnitNameItt(getUnitName(assetSubTypes
											.getProperUnit()));
							list.add(bean);
						}

					} else {
						AssetSubTypes localbean = new AssetSubTypes();
						localbean.setPropertyNameItt(propertyNameItt[i]);
						localbean.setPropertyUnitItt(propertyUnitItt[i]);
						localbean
								.setPropertyUnitNameItt(propertyUnitNameItt[i]);
						list.add(localbean);
					}

				}

				assetSubTypes.setPropertyList(list);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDuplicate;
	}
}
