/*
 * Added by manish sakpal
 */


package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.Master.CustomerMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import com.ibm.icu.impl.Utility;

public class CustomerMasterModel extends ModelBase
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TravelAgencyListModel.class);

	public void getAgencyList(CustomerMasterBean cbean,HttpServletRequest request) 
	{
		Object[][] selData = null;
		ArrayList list = new ArrayList();
		try {
				String selQuery = " SELECT NVL(TRAVEL_CUST_NAME,''), NVL(TRAVEL_CUST_CONTACT_PERSON,''),TRAVEL_CUST_ID,NVL(LOCATION_NAME,'')  FROM TMS_TRAVEL_CUSTOMER " 
								  +" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_CITY) " 
								  +" ORDER BY TRAVEL_CUST_ID";

					selData = getSqlModel().getSingleResult(selQuery);
		} 
		catch (Exception e) 
		{
			logger.error("exception in due query", e);
		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(cbean.getMyPage(), selData.length, 20);
		if (pageIndex == null) 
		{
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
		cbean.setMyPage("1");
		if (selData == null) 
		{

		} 
		else 
			if (selData.length == 0) 
			{

			} 
			else 
			{
				cbean.setTotalRecords("" + selData.length);
				cbean.setModeLength("true");
				try 
					{
						for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) 
						{
							cbean.setModeLength("true");
							CustomerMasterBean beanItt = new CustomerMasterBean();
							beanItt.setCustomerName(checkNull(String.valueOf(selData[i][0])));
							beanItt.setContactPerson(checkNull(String.valueOf(selData[i][1])));
							beanItt.setCustomercode(checkNull(String.valueOf(selData[i][2])));
							beanItt.setCity(checkNull(String.valueOf(selData[i][3])));
							list.add(beanItt);
						}
					} 
				catch (Exception e) 
				{
					logger.error("exception in for loop dueData", e);
				}
				cbean.setCustomerList(list);
			}
	}

	public String checkNull(String result) 
	{
		if (result == null || result.equals("null")) 
		{	
			return "";
		}// end of if
		else 
		{
			return result;
		}// end of else
	}

	
	
/* for inserting record */
	public boolean addData(CustomerMasterBean cbean) 
	{
		boolean result = false;
		try 
		{			
			Object addObj[][] = new Object[1][7];
			addObj[0][0] = cbean.getCustomerName();
			addObj[0][1] = cbean.getContactPerson();
			addObj[0][2] = cbean.getAddress();
			addObj[0][3] = cbean.getPhone();
			addObj[0][4] = cbean.getEmailId();
			addObj[0][5] = cbean.getCityId();
			addObj[0][6] = cbean.getProjectId();
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) 
			{
				String autoCodeQuery = " SELECT NVL(MAX(TRAVEL_CUST_ID),0) FROM TMS_TRAVEL_CUSTOMER ";

				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);

				if (data != null && data.length > 0) 
				{
					cbean.setCustomercode(String.valueOf(data[0][0]));
				}
			}
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	} // end of addData method


	
/**
* following function is called to delete a record
* 
* @param cbean
* @return
*/
	public boolean delRecord(CustomerMasterBean cbean) 
	{
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching
		// 	button
		del[0][0] = cbean.getCustomercode();
		return getSqlModel().singleExecute(getQuery(3), del);
	}//End of delRecord method


	


/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(CustomerMasterBean cbean) 
	{
		boolean result = false;
		String query = "SELECT TRAVEL_CUST_ID,TRAVEL_CUST_NAME,TRAVEL_CUST_CONTACT_PERSON,TRAVEL_CUST_ADDRESS,TRAVEL_CUST_CITY,TRAVEL_CUST_PHONE,TRAVEL_CUST_EMAIL FROM TMS_TRAVEL_CUSTOMER WHERE UPPER(TRAVEL_CUST_NAME) LIKE '"
					+ cbean.getCustomerName().trim().toUpperCase()
					+ "' AND TRAVEL_CUST_ID not in(" + cbean.getCustomercode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) 
		{
			result = true;
		}// end of if
		return result;

	}

	
/* for modifing the record */
	public boolean update(CustomerMasterBean cbean) 
	{
		boolean result=false;
		
		try {
			if (!checkDuplicateMod(cbean)) {
				Object addObj[][] = new Object[1][8];
				addObj[0][0] = cbean.getCustomerName();
				addObj[0][1] = cbean.getContactPerson();
				addObj[0][2] = cbean.getAddress();
				addObj[0][3] = cbean.getPhone();
				addObj[0][4] = cbean.getEmailId();
				addObj[0][5] = cbean.getCityId();
				addObj[0][6] = cbean.getProjectId();
				addObj[0][7] = cbean.getCustomercode();

				result= getSqlModel().singleExecute(getQuery(2), addObj);
			}// end of if
			else
				result= false;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Exception occurred=============>"+e);
		}
		return result;
	}
	
	

/*
* for selecting the data from list and setting those data in respective
* fields
*/
	public void calforedit(CustomerMasterBean cbean, String custID) 
	{

		try 
		{
			String query = " SELECT NVL(TRAVEL_CUST_NAME,''), NVL(TRAVEL_CUST_CONTACT_PERSON,''), NVL(TRAVEL_CUST_ADDRESS,''), NVL(TRAVEL_CUST_PHONE,''), NVL(TRAVEL_CUST_EMAIL,''), "
						   +" TRAVEL_CUST_CITY,NVL(LOCATION_NAME,''),TRAVEL_PROJECT_ID,NVL(PROJECT_NAME,'')    FROM TMS_TRAVEL_CUSTOMER "
						   +" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_CITY)"
						   +" LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_TRAVEL_CUSTOMER.TRAVEL_PROJECT_ID)"
						   +" WHERE  TRAVEL_CUST_ID = "+ custID + " ORDER BY TRAVEL_CUST_ID ";
			Object[][] data = getSqlModel().getSingleResult(query);
			cbean.setCustomerName(checkNull(String.valueOf(data[0][0])));
			cbean.setContactPerson(checkNull(String.valueOf(data[0][1])));
			cbean.setAddress(checkNull(String.valueOf(data[0][2])));
			cbean.setPhone(checkNull(String.valueOf(data[0][3])));	
			cbean.setEmailId(checkNull(String.valueOf(data[0][4])));	
			cbean.setCityId(checkNull(String.valueOf(data[0][5])));
			cbean.setCity(checkNull(String.valueOf(data[0][6])));
			cbean.setProjectId(checkNull(String.valueOf(data[0][7])));
			cbean.setProjectName(checkNull(String.valueOf(data[0][8])));	
			cbean.setCustomercode(custID);	
		} 
		catch (Exception e) 
		{
			// 	TODO: handle exception
		}

	}

	
	
	public boolean deleteCheckedRecords(CustomerMasterBean cbean, String[] code) 
	{
		boolean result = false;
		int count = 0;
		if (code != null) 
		{
			for (int i = 0; i < code.length; i++) 
			{
				if (!code[i].equals("")) 
				{
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;
				}// end of if
			} // end of for loop
		} // end of if
		if (count != 0) 
		{
			result = false;
			return result;
		} // end of if
		else 
		{
			result = true;
			return result;
		} // end of else		
	} //end of deleteCheckedRecords



	
	
public void getRecord(CustomerMasterBean cbean) 
{
	try 
	{  
		String query = " SELECT NVL(TRAVEL_CUST_NAME,''), NVL(TRAVEL_CUST_CONTACT_PERSON,''), NVL(TRAVEL_CUST_ADDRESS,''), NVL(TRAVEL_CUST_PHONE,''), NVL(TRAVEL_CUST_EMAIL,''), "
					   +" TRAVEL_CUST_CITY,NVL(LOCATION_NAME,''),TRAVEL_PROJECT_ID,NVL(PROJECT_NAME,'')    FROM TMS_TRAVEL_CUSTOMER "
					   +" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_CITY)"
					   +" LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_TRAVEL_CUSTOMER.TRAVEL_PROJECT_ID)"
					   +" WHERE  TRAVEL_CUST_ID = "+ cbean.getCustomercode() + " ORDER BY TRAVEL_CUST_ID ";
		Object[][] data = getSqlModel().getSingleResult(query);
		cbean.setCustomerName(checkNull(String.valueOf(data[0][0])));
		cbean.setContactPerson(checkNull(String.valueOf(data[0][1])));
		cbean.setAddress(checkNull(String.valueOf(data[0][2])));
		cbean.setPhone(checkNull(String.valueOf(data[0][3])));	
		cbean.setEmailId(checkNull(String.valueOf(data[0][4])));	
		cbean.setCityId(checkNull(String.valueOf(data[0][5])));
		cbean.setCity(checkNull(String.valueOf(data[0][6])));
		cbean.setProjectId(checkNull(String.valueOf(data[0][7])));
		cbean.setProjectName(checkNull(String.valueOf(data[0][8])));	
		cbean.setCustomercode(cbean.getCustomercode());	
	} 
	catch (Exception e) 
	{
		// 	TODO: handle exception
	}

}//End of getRecord

}
