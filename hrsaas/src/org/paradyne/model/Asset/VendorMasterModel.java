package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.VendorMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author Krishna
 * 
 */

public class VendorMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VendorMasterModel.class);

	/*
	 * method name : add purpose : to add the record return type : boolean
	 * parameter : bean
	 */
	public boolean add(VendorMaster bean) {
		if (!checkDuplicate(bean)) {
			Object addObj[][] = new Object[1][9];

			addObj[0][0] = checkNull(String
					.valueOf(bean.getVendorName().trim()));
			addObj[0][1] = checkNull(String.valueOf(bean.getVendorAdd().trim()));
			addObj[0][2] = checkNull(String.valueOf(bean.getVendorCon().trim()));
			addObj[0][3] = checkNull(String.valueOf(bean.getVendorEmail()
					.trim()));
			addObj[0][4] = checkNull(String.valueOf(bean.getCtyId().trim()));
			addObj[0][5] = checkNull(String.valueOf(bean.getStateId()));
			addObj[0][6] = checkNull(String.valueOf(bean.getPinCode()));
			addObj[0][7] = checkNull(String.valueOf(bean.getIsActive()));
			addObj[0][8] = checkNull(String.valueOf(bean.getVendortype()));
			String query="SELECT NVL(MAX(VENDOR_CODE),0)+1 FROM HRMS_VENDOR";
			Object [][] repData = getSqlModel().getSingleResult(query);
			
			boolean flag= getSqlModel().singleExecute(getQuery(1), addObj);
			
			if(flag)
			{
				 if(repData!=null&& repData.length>0)
					bean.setVendorCode(String.valueOf(repData[0][0]));
				 return flag;
			}else
				return false;
		} // end of if
		else {
			return false;
		}// end of else
	}

	/*
	 * method name : mod purpose : to modify the record return type : boolean
	 * parameter : bean
	 */
	public boolean mod(VendorMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][10];
			modObj[0][0] = checkNull(String
					.valueOf(bean.getVendorName().trim()));
			modObj[0][1] = checkNull(String.valueOf(bean.getVendorAdd().trim()));
			modObj[0][2] = checkNull(String.valueOf(bean.getVendorCon().trim()));
			modObj[0][3] = checkNull(String.valueOf(bean.getVendorEmail()
					.trim()));
			modObj[0][4] = checkNull(String.valueOf(bean.getCtyId().trim()));
			modObj[0][5] = checkNull(String.valueOf(bean.getStateId().trim()));
			modObj[0][6] = checkNull(String.valueOf(bean.getPinCode().trim()));
			modObj[0][7] = checkNull(String.valueOf(bean.getIsActive().trim()));
			modObj[0][8] = checkNull(String.valueOf(bean.getVendortype().trim()));
			modObj[0][9] = checkNull(String.valueOf(bean.getVendorCode().trim()));
			
			
			
			return getSqlModel().singleExecute(getQuery(2), modObj);
		}// end of if
		else {
			return false;
		}// end of else

	}

	/*
	 * method name : checkDuplicate purpose : for checking duplicate entry of
	 * record during insertion return type : boolean parameter : bean
	 */

	public boolean checkDuplicate(VendorMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_VENDOR WHERE UPPER(VENDOR_NAME) LIKE '"
				+ bean.getVendorName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/*
	 * method name : checkDuplicateMod purpose : for checking duplicate entry of
	 * record during modification return type : boolean parameter : bean
	 */

	public boolean checkDuplicateMod(VendorMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_VENDOR WHERE UPPER(VENDOR_NAME) LIKE '"
				+ bean.getVendorName().trim().toUpperCase()
				+ "' AND VENDOR_CODE not in(" + bean.getVendorCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/*
	 * method name : delete purpose : to delete the record return type : boolean
	 * parameter : bean
	 */
	public boolean delete(VendorMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = String.valueOf(bean.getVendorCode());
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/*
	 * method name : setData purpose : for setting the bean properties return
	 * type : void parameter : bean
	 */
	public void setData(VendorMaster bean) {
		String query = "SELECT nvl(VENDOR_EMAIL,''),VENDOR_PINCODE,VENDOR_TYPE,VENDOR_ISACTIVE FROM HRMS_VENDOR WHERE VENDOR_CODE ="
				+ bean.getVendorCode();
		Object setData[][] = getSqlModel().getSingleResult(query);
		bean.setVendorEmail(checkNull(String.valueOf(setData[0][0])));
		bean.setPinCode(checkNull(String.valueOf(setData[0][1])));
		bean.setVendortype(String.valueOf(setData[0][2]));
		bean.setIsActive(String.valueOf(setData[0][3]));
	}

	/*
	 * method name : setState purpose : for setting the state property of bean
	 * return type : void parameter : bean
	 */
	public void setState(VendorMaster bean) {

		String query = "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 1 AND LOCATION_CODE="
				+ bean.getLocParentCode();
		Object setState[][] = getSqlModel().getSingleResult(query);
		if (setState.length > 0) {
			bean.setStateId(checkNull(String.valueOf(setState[0][0])));
			bean.setVendorState(checkNull(String.valueOf(setState[0][1])));
		} else {
			bean.setStateId("");
			bean.setVendorState("");
		}
	}

	/*
	 * method name : delete purpose : to display the data in the form while
	 * loading return type : void parameter : bean,request
	 */

	public void Data(VendorMaster bean, HttpServletRequest request) {
		
		
		
	Object [][] obj = getSqlModel().getSingleResult(getQuery(4));
		
		
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
			
								     VendorMaster bean1 = new VendorMaster();
			
									bean1.setVendorCode(checkNull(String.valueOf(obj[i][0])));
									bean1.setVendorName(checkNull(String.valueOf(obj[i][1])));
									bean1.setIsActive(checkNull(String.valueOf(obj[i][2])));
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

    bean.setRecordList(list);

		
		
		
		

		
	}

	/*
	 * method name : calforedit purpose : to edit the record return type : void
	 * parameter : bean
	 */

	public void calforedit(VendorMaster bean ,String code) {
		String query = "SELECT VENDOR_CODE,NVL(VENDOR_NAME,''),NVL(VENDOR_ADDRESS,''),NVL(VENDOR_CONTACT_NO,''),NVL(VENDOR_EMAIL,''),NVL(VENDOR_CITY,''),NVL(VENDOR_STATE,''), "
				+ " NVL(L1.LOCATION_NAME,'') AS CITY,NVL(L2.LOCATION_NAME,'') AS STATE ,NVL(VENDOR_PINCODE,'') ,VENDOR_TYPE,VENDOR_ISACTIVE FROM HRMS_VENDOR"
				+ " LEFT JOIN HRMS_LOCATION L1 ON(HRMS_VENDOR.VENDOR_CITY=L1.LOCATION_CODE)"
				+ " LEFT JOIN HRMS_LOCATION L2 ON(HRMS_VENDOR.VENDOR_STATE=L2.LOCATION_CODE) where VENDOR_CODE="
				+ code;

		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setVendorCode(checkNull(String.valueOf(data[0][0])));
		bean.setVendorName(checkNull(String.valueOf(data[0][1])));
		bean.setVendorAdd(checkNull(String.valueOf(data[0][2])));
		bean.setVendorCon(checkNull(String.valueOf(data[0][3])));
		bean.setVendorEmail(checkNull(String.valueOf(data[0][4])));
		bean.setCtyId(checkNull(String.valueOf(data[0][5])));
		bean.setStateId(checkNull(String.valueOf(data[0][6])));
		bean.setVendorCty(checkNull(String.valueOf(data[0][7])));
		bean.setVendorState(checkNull(String.valueOf(data[0][8])));
		bean.setPinCode(checkNull(String.valueOf(data[0][9])));
		bean.setVendortype(checkNull(String.valueOf(data[0][10])));
		bean.setIsActive(String.valueOf(data[0][11]));

	}

	/*
	 * method name : calfordelete purpose : to delete the record return type :
	 * boolean parameter : bean
	 */
	public boolean calfordelete(VendorMaster bean) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/*
	 * method name : deletecheckedRecords purpose : to delete the checked
	 * records return type : boolean parameter : bean,code
	 */

	public boolean deletecheckedRecords(VendorMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
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
	 * method name : checkNull purpose : to check the null values and to make
	 * them as blank. return type : String parameter : result
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/*
	 * method name : report purpose : to generate the report for the selected
	 * application return type : void parameter : bean,request,response
	 */
	public void report(VendorMaster bean, HttpServletRequest request,
			HttpServletResponse response,String appCol[]) {

		String s = "\n\nVendor Master Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = "SELECT VENDOR_CODE,NVL(VENDOR_NAME,''),NVL(VENDOR_ADDRESS,''),NVL(VENDOR_CONTACT_NO,''),"
				+ "NVL(VENDOR_EMAIL,''), NVL(L1.LOCATION_NAME,'') AS CITY,NVL(L2.LOCATION_NAME,'') AS STATE,NVL(VENDOR_PINCODE,'') FROM HRMS_VENDOR"
				+ " LEFT JOIN HRMS_LOCATION L1 ON(HRMS_VENDOR.VENDOR_CITY=L1.LOCATION_CODE)"
				+ " LEFT JOIN HRMS_LOCATION L2 ON(HRMS_VENDOR.VENDOR_STATE=L2.LOCATION_CODE) ORDER BY VENDOR_CODE";
		// IMS_STATION_CODE,
		Object result[][] = getSqlModel().getSingleResult(query);

		rg.addFormatedText(s, 6, 0, 1, 0);
		String date = "select To_char(Sysdate,'dd-mm-yyyy') from dual";
		Object[][] DATE = getSqlModel().getSingleResult(date);
		if (result.length > 0)

		{

			Object[][] tabledata = new Object[result.length][9];

			for (int i = 0; i < result.length; i++) {
				tabledata[i][0] = String.valueOf(i + 1);
				tabledata[i][1] = checkNull(String.valueOf(result[i][0]));
				tabledata[i][2] = checkNull(String.valueOf(result[i][1]));
				tabledata[i][3] = checkNull(String.valueOf(result[i][2]));
				tabledata[i][4] = checkNull(String.valueOf(result[i][3]));
				tabledata[i][5] = checkNull(String.valueOf(result[i][4]));
				tabledata[i][6] = checkNull(String.valueOf(result[i][5]));
				tabledata[i][7] = checkNull(String.valueOf(result[i][6]));
				tabledata[i][8] = checkNull(String.valueOf(result[i][7]));
			}// end of for loop
			int[] bcellWidth = { 20, 20, 20, 30, 30, 30, 40, 40, 40 };
			int[] bcellAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		/*	String appCol[] = { "Sr no", "Code ", "Name", "Address",
					"Contact No", "E-mail", "City", "State", "Pin Code" };*/
			rg.addText("Date:" + String.valueOf(DATE[0][0]), 2, 2, 0);
			rg.tableBody(appCol, tabledata, bcellWidth, bcellAlign);
		}// end of if

		rg.createReport(response);

	}

}
