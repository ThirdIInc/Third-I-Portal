package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Asset.RateList;
import org.paradyne.bean.Asset.VendorMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Mangesh Jadhav
 * 18-08-2008
 * RateListModel class to write business logic to add , update, view the
 * Vendor rate list
 */
public class RateListModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ModelBase.class);
	
	public void showList(RateList bean, HttpServletRequest request)
	{
		String sql="SELECT DISTINCT RATELIST_VENDOR, VENDOR_NAME ,LOCATION_NAME ,RATELIST_CODE FROM HRMS_ASSET_RATELIST "+ 
			 "INNER JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_RATELIST.RATELIST_VENDOR and HRMS_VENDOR.VENDOR_TYPE='S') "+
			 "INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) "+ 
			 "ORDER BY RATELIST_VENDOR ";
		Object[][] obj=getSqlModel().getSingleResult(sql);

		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		
		
		 if(pageIndex[4].equals("1"))
			   bean.setMyPage("1");
		  			
			
						ArrayList<Object> list = new ArrayList<Object>();
						if (obj != null && obj.length > 0) {
			
						//	for (int i = 0; i < obj.length; i++) {
							 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
			
								     RateList bean1 = new RateList();
								     
								     bean1.setIteratorvendorcode(checkNull(String.valueOf(obj[i][0])));
								     bean1.setIteratorvendorname(checkNull(String.valueOf(obj[i][1])));
								     bean1.setIteratorcityname(checkNull(String.valueOf(obj[i][2])));
								     bean1.setIteratorratecode(checkNull(String.valueOf(obj[i][3])));
			
									
								list.add(bean1);
							}
							
						
						 bean.setTotalRecords(""+obj.length);
				}
				else{
				
					bean.setRecordsLength("false");
					
				}
				//bean.setEmpList(list);
				bean.setTotalRecords(String.valueOf(list.size()));
			    if(list.size()>0)
			    	bean.setRecordsLength("true");
			    else
			    	bean.setRecordsLength("false");

   bean.setIteratorlist(list);
		
	}
	/* method name : showRecord 
	 * purpose     : show the rate list of the selected vendor
	 * return type : void
	 * parameter   : RateList instance
	 */
	public void showRecord(RateList bean) {
		Object[] vendorCode = new Object[1];
		vendorCode[0] = bean.getRateCode();
		Object[][] ratelist = getSqlModel().getSingleResult(getQuery(1),
				vendorCode);
		if (ratelist != null) {
			ArrayList<Object> tableList = new ArrayList<Object>();
			/*
			 * set the rate list of the selected vendor in the Order list
			 * 
			 */
			for (int i = 0; i < ratelist.length; i++) {
				RateList bean1 = new RateList();
				bean1.setItemIterator(String.valueOf(ratelist[i][1]));
				bean1.setPriceIterator(String.valueOf(ratelist[i][0]));
				bean1.setItemCodeIterator(String.valueOf(ratelist[i][2]));
				bean1.setUnitIterator(String.valueOf(ratelist[i][3]));
				tableList.add(bean1);
			}// end of for loop
			bean.setRateList(tableList);

		} // end of if
	}
	/* method name : showRecordForVendor 
	 * purpose     : show the rate list of the selected vendor
	 * return type : void
	 * parameter   : RateList instance
	 */
	public void showRecordForVendor(RateList bean) {
		Object[] vendorCode = new Object[1];
		vendorCode[0] = bean.getVendorCode();
		Object[][] rateList = getSqlModel().getSingleResult(getQuery(5),
				vendorCode);
		try {
			if (rateList != null && rateList.length != 0) {
				bean.setRateCode(String.valueOf(rateList[0][0]));
				showRecord(bean);
			} // end of if
			else
				bean.setRateCode("");
		} catch (Exception e) {
			bean.setRateCode("");
			e.printStackTrace();
			logger.info("exception in showRecordForVendor()"+e);
		}
	}
	/* method name : addItem 
	 * purpose     : add the Item with its price in the list
	 * return type : boolean
	 * parameter   : RateList instance, String[] itemCode, String[] itemName,
			String[] price,String []unit
	 */
	public boolean addItem(RateList bean, String[] itemCode, String[] itemName,
			String[] price,String []unit) {
		ArrayList<Object> tableList = new ArrayList<Object>();

		if (itemCode != null) {
			/*
			 * set the rate list in the Order list
			 * 
			 */
			for (int i = 0; i < price.length; i++) {
				RateList bean1 = new RateList();
				bean1.setItemIterator(itemName[i]);
				bean1.setItemCodeIterator(itemCode[i]);
				bean1.setPriceIterator(price[i]);
				bean1.setUnitIterator(unit[i]);
				tableList.add(bean1);
			} // end of for loop
			/*
			 * check for duplicate entry of asset sub type
			 * 
			 */
			for (int i = 0; i < price.length; i++) {
				if (String.valueOf(itemCode[i]).equals(bean.getSubTypeCode())) {
					return false;
				} // end of if
			} // end of for loop
		} // end of if
		bean.setItemIterator(bean.getSubTypeName());
		bean.setItemCodeIterator(bean.getSubTypeCode());
		bean.setPriceIterator(bean.getPrice());
		bean.setUnitIterator(bean.getUnit());
		tableList.add(bean);

		bean.setRateList(tableList);
		return true;
	}
	/* method name : showList 
	 * purpose     : show the Items with its price in the list
	 * return type : boolean
	 * parameter   : RateList instance, String[] itemCode, String[] itemName,
			String[] price,String []unit
	 */
	public boolean showList(RateList bean, String[] itemCode, String[] itemName,
			String[] price,String []unit) {
		ArrayList<Object> tableList = new ArrayList<Object>();

		if (itemCode != null) {
			/*
			 * show the rate list of the selected vendor in the Order list
			 * 
			 */
			for (int i = 0; i < price.length; i++) {
				RateList bean1 = new RateList();
				bean1.setItemIterator(itemName[i]);
				bean1.setItemCodeIterator(itemCode[i]);
				bean1.setPriceIterator(price[i]);
				bean1.setUnitIterator(unit[i]);
				tableList.add(bean1);
			} // end of for loop
		} // end of if
		bean.setRateList(tableList);
		return true;
	}
	/* method name : editItem 
	 * purpose     : edit the Items with its price from the list
	 * return type : boolean
	 * parameter   : RateList instance, String[] itemCode,
			String[] itemName, String[] price,String []unit
	 */
	public boolean editItem(RateList bean, String[] itemCode,
			String[] itemName, String[] price,String []unit) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (itemCode != null) {
			/*
			 * set the rate list of the selected vendor in the Order list after editing
			 * 
			 */
			for (int i = 0; i < price.length; i++) {
				RateList bean1 = new RateList();
				if (i == Integer.parseInt(bean.getParaId()) - 1) {
					bean1.setItemIterator(bean.getSubTypeName());
					bean1.setItemCodeIterator(bean.getSubTypeCode());
					bean1.setPriceIterator(bean.getPrice());
					bean1.setUnitIterator(bean.getUnit());
				}  // end of if
				else {
					if (String.valueOf(itemCode[i]).equals(
							bean.getSubTypeCode())) {
						return false;
					} // end of if
					else {
						bean1.setItemIterator(itemName[i]);
						bean1.setItemCodeIterator(itemCode[i]);
						bean1.setPriceIterator(price[i]);
						bean1.setUnitIterator(unit[i]);
					} // end of else
				} // end of else
				tableList.add(bean1);
			} // end of for loop
		} // end of if

		bean.setRateList(tableList);
		return true;
	}
	/* method name : removeItem 
	 * purpose     : remove the Item from the list
	 * return type : boolean
	 * parameter   : RateList instance, String[] itemCode,
			String[] itemName, String[] price,String []unit
	 */
	public void removeItem(RateList bean, String[] itemCode, String[] itemName,
			String[] price,String [] unit) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (itemCode != null) {
			for (int i = 0; i < price.length; i++) {

				if (i == Integer.parseInt(bean.getParaId()) - 1) {

				} // end of if
				else {
					RateList bean1 = new RateList();
					bean1.setItemIterator(itemName[i]);
					bean1.setItemCodeIterator(itemCode[i]);
					bean1.setPriceIterator(price[i]);
					bean1.setUnitIterator(unit[i]);
					tableList.add(bean1);
				} // end of else

			} // end of for loop
		} // end of if

		bean.setRateList(tableList);
	}
	/* method name : saveItem 
	 * purpose     : save the vendor rate list
	 * return type : boolean
	 * parameter   : RateList instance, , String[] itemCode, String[] price
	 */
	public boolean saveItem(RateList bean, String[] itemCode, String[] price) {
		boolean result = false;
		try {
			Object[][] vendorCode = new Object[1][1];
			vendorCode[0][0] = bean.getVendorCode();
			result = getSqlModel().singleExecute(getQuery(2), vendorCode);   // insert vendor code in the HRMS_ASSET_RATELIST
			if (result) {
				Object[][] rateCode = getSqlModel().getSingleResult(
				"SELECT MAX(RATELIST_CODE) FROM HRMS_ASSET_RATELIST");
				bean.setRateCode(String.valueOf(rateCode[0][0]));
				Object[][] priceData = new Object[itemCode.length][3];
				/*
				 * insert the rate list in the HRMS_ASSET_RATELIST_DTL table
				 * 
				 */
				for (int i = 0; i < priceData.length; i++) {
					priceData[i][0] = bean.getRateCode();
					priceData[i][1] = itemCode[i];
					priceData[i][2] = price[i];

				} // end of for
				result = getSqlModel().singleExecute(getQuery(4), priceData);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in saveItem()"+e);
		}
		return result;
	}
	/* method name : updateItem 
	 * purpose     : update the vendor rate list
	 * return type : boolean
	 * parameter   : RateList instance,String[] itemCode, String[] price
	 */
	public boolean updateItem(RateList bean, String[] itemCode, String[] price) {
		boolean result = false;
		Object[][] vendorData = new Object[1][2];
		vendorData[0][0] = bean.getVendorCode();
		vendorData[0][1] = bean.getRateCode();
		result = getSqlModel().singleExecute(getQuery(3), vendorData);
		if (result) {
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_RATELIST_DTL WHERE RATELIST_CODE="
					+ bean.getRateCode());
			Object[][] priceData = new Object[itemCode.length][3];
			/*
			 * insert the rate list in the HRMS_ASSET_RATELIST_DTL table
			 * 
			 */
			for (int i = 0; i < priceData.length; i++) {
				priceData[i][0] = bean.getRateCode();
				priceData[i][1] = itemCode[i];
				priceData[i][2] = price[i];
				logger.info("priceData [i][0]=" + priceData[i][0]);
				logger.info("priceData [i][1]=" + priceData[i][1]);
				logger.info("priceData [i][2]=" + priceData[i][2]);
			} // end of for loop
			result = getSqlModel().singleExecute(getQuery(4), priceData);
		} // end of if
		return result;
	}
	/* method name : getReport 
	 * purpose     : to show the PDF report for selected vendor
	 * return type : void
	 * parameter   : HttpServletRequest request,
			HttpServletResponse response, RateList instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, RateList bean) {
		String s = "\nAsset Rate List \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);

		Object[] vendorCode = new Object[1];
		vendorCode[0] = bean.getRateCode();
		Object[][] tab = getSqlModel().getSingleResult(getQuery(1),
				vendorCode);

		Object[][] tableData = new Object[tab.length][4];
		/*
		 * get the retrieved data in an Object to set it in the table
		 * 
		 */
		for (int i = 0; i < tableData.length; i++) {
			tableData[i][0] = String.valueOf(i + 1);
			tableData[i][1] = checkNull(String.valueOf(tab[i][1]));
			tableData[i][2] = checkNull(String.valueOf(tab[i][0]));
			tableData[i][3] = checkNull(String.valueOf(tab[i][3]));

		} // end of for loop
		Object data[][] = new Object[1][4];

		data[0][0] = "Vendor Name ";
		data[0][1] = ": " + bean.getVendorName();
		data[0][2] = "City Name ";
		data[0][3] = ": " + bean.getCityName();

		int[] bcellWidth = { 20, 30, 20, 30 };
		int[] bcellAlign = { 0, 0, 0, 0 };
		rg.addFormatedText(s, 6, 0, 1, 0);

		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);


		int cellwidth[] = { 10, 25, 25, 10 };
		int alignment[] = { 1, 0, 0, 0};


		rg.addFormatedText("Rate List :", 6, 0, 0, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		String colnames[] = { "Sr.No", "Item", "Price","Unit"};
		rg.tableBody(colnames, tableData, cellwidth, alignment);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);



		rg.createReport(response);

	}
	/* method name : delete 
	 * purpose     : to delete rate list of the selected vendor
	 * return type : void
	 * parameter   : RateList instance
	 */
	public boolean delete(RateList bean) {
		boolean result;

		result = getSqlModel().singleExecute(
				"DELETE FROM HRMS_ASSET_RATELIST WHERE RATELIST_CODE="
				+ bean.getRateCode());
		if (result) {
			result = getSqlModel().singleExecute(
					"DELETE FROM HRMS_ASSET_RATELIST_DTL WHERE RATELIST_CODE="
					+ bean.getRateCode());
		} // end of if
		return result;
	}
	/*
	 * method name : deletecheckedRecords purpose : to delete the checked
	 * records return type : boolean parameter : bean,code
	 */

	public boolean deletecheckedRecords(RateList bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					String sqlquery="DELETE FROM HRMS_ASSET_RATELIST_DTL  WHERE RATELIST_CODE = "+code[i];
					result = getSqlModel().singleExecute(sqlquery);
					if(result)
					{
						String delratelst="DELETE FROM HRMS_ASSET_RATELIST  WHERE RATELIST_CODE = "+code[i];
						result = getSqlModel().singleExecute(delratelst);
					}
					if (!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else
	}
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	public void editData(RateList ratelist) {
		// TODO Auto-generated method stub
		
	}
}
