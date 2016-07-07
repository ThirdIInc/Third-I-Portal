package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ApprovalSettings;
import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.bean.D1.DepartmentandLocationChangeBean;
import org.paradyne.bean.D1.RegionMasterBean;
import org.paradyne.bean.D1.VenodrMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class VendorModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VendorModel.class);

	/** Generating the list Onload */
	public void onloadListData(VenodrMasterBean venodrMasterBean,
			HttpServletRequest request) {

		Object[][] regData = getSqlModel().getSingleResult(getQuery(3));

		if (regData != null && regData.length > 0) {
			venodrMasterBean.setModeLength("true");

			venodrMasterBean
					.setTotalNoOfRecords(String.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(venodrMasterBean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				venodrMasterBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				VenodrMasterBean bean = new VenodrMasterBean();
				bean.setVendorCode(checkNull(String.valueOf(regData[i][0])));
				bean.setEinNumber(checkNull(String.valueOf(regData[i][2])));
				bean.setVendorName(checkNull(String.valueOf(regData[i][1])));
				bean.setVendorAppDate(checkNull(String.valueOf(regData[i][27])));

				List.add(bean);
			}// end of loop
			System.out.println("in main page---" + List.size());
			venodrMasterBean.setVendorsList(List);
		}

		else {

			venodrMasterBean.setVendorsList(null);

		}
	}

	//Check Null Function//	

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean insertVendorData(VenodrMasterBean venodrMasterBean) {

		if (!checkDuplicateAdd(venodrMasterBean)) {
		Object addObj[][] = new Object[1][25];
		
		String query1 = "SELECT NVL(MAX(VENDOR_ID),0)+1 FROM  HRMS_D1_VENDOR";
		Object[][] vendorId = getSqlModel().getSingleResult(query1);
		
		addObj[0][0] = checkNull(String.valueOf(vendorId[0][0]));
		addObj[0][1] = venodrMasterBean.getVendorName().trim();
		addObj[0][2] = venodrMasterBean.getEinNumber().trim();
		addObj[0][3] = venodrMasterBean.getPostboxAddress().trim();
		addObj[0][4] = venodrMasterBean.getCityId().trim();
		addObj[0][5] = venodrMasterBean.getState().trim();
		addObj[0][6] = venodrMasterBean.getZip().trim();
		addObj[0][7] = venodrMasterBean.getPhoneNumber().trim();
		addObj[0][8] = venodrMasterBean.getSendPO().trim();
		addObj[0][9] = venodrMasterBean.getContactName().trim();
		addObj[0][10] = venodrMasterBean.getRemitName().trim();
		addObj[0][11] = venodrMasterBean.getRemitAddress().trim();
		addObj[0][12] = venodrMasterBean.getDiscPercent().trim();
		addObj[0][13] = venodrMasterBean.getNetDays().trim();
		addObj[0][14] = venodrMasterBean.getComments1().trim();
		addObj[0][15] = venodrMasterBean.getClassCode().trim();
		addObj[0][16] = venodrMasterBean.getMinOrder().trim();
		addObj[0][17] = venodrMasterBean.getFreightMessage().trim();
		addObj[0][18] = venodrMasterBean.getTaxMessage().trim();
		addObj[0][19] = venodrMasterBean.getShipVia().trim();
		addObj[0][20] = venodrMasterBean.getFob().trim();
		addObj[0][21] = venodrMasterBean.getComments2().trim();
		addObj[0][22] = venodrMasterBean.getRemitCityId().trim();
		addObj[0][23] = venodrMasterBean.getRemitState().trim();
		addObj[0][24] = venodrMasterBean.getRemitZip().trim();
		

		/*for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				logger.info("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}*/

		venodrMasterBean.setVendorCode(String.valueOf(vendorId[0][0]));
		return getSqlModel().singleExecute(getQuery(1), addObj);

	}
		else{
			
			return false;
			}
			
		}		
		
	/* for checking duplicate entry of record during insertion*/

	public boolean checkDuplicateAdd(VenodrMasterBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_VENDOR WHERE UPPER(VENDOR_NAME) LIKE '"
				+ bean.getVendorName().trim().toUpperCase() + "'" +"AND UPPER(VENDOR_EIN_NUMBER) LIKE '" +bean.getEinNumber().trim().toUpperCase()+"'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}	
	
	
	
	/**  Modifing the record */
	public boolean updateVendorData(VenodrMasterBean venodrMasterBean) {
		
		Object addObj[][] = new Object[1][25];

		addObj[0][0] = venodrMasterBean.getVendorName().trim();
		addObj[0][1] = venodrMasterBean.getEinNumber().trim();
		addObj[0][2] = venodrMasterBean.getPostboxAddress().trim();
		addObj[0][3] = venodrMasterBean.getCityId().trim();
		addObj[0][4] = venodrMasterBean.getState().trim();
		addObj[0][5] = venodrMasterBean.getZip().trim();
		addObj[0][6] = venodrMasterBean.getPhoneNumber().trim();
		addObj[0][7] = venodrMasterBean.getSendPO().trim();
		addObj[0][8] = venodrMasterBean.getContactName().trim();
		addObj[0][9] = venodrMasterBean.getRemitName().trim();
		addObj[0][10] = venodrMasterBean.getRemitAddress().trim();
		addObj[0][11] = venodrMasterBean.getDiscPercent().trim();
		addObj[0][12] = venodrMasterBean.getNetDays().trim();
		addObj[0][13] = venodrMasterBean.getComments1().trim();
		addObj[0][14] = venodrMasterBean.getClassCode().trim();
		addObj[0][15] = venodrMasterBean.getMinOrder().trim();
		addObj[0][16] = venodrMasterBean.getFreightMessage().trim();
		addObj[0][17] = venodrMasterBean.getTaxMessage().trim();
		addObj[0][18] = venodrMasterBean.getShipVia().trim();
		addObj[0][19] = venodrMasterBean.getFob().trim();
		addObj[0][20] = venodrMasterBean.getComments2().trim();
		addObj[0][21] = venodrMasterBean.getRemitCityId().trim();
		addObj[0][22] = venodrMasterBean.getRemitState().trim();
		addObj[0][23] = venodrMasterBean.getRemitZip().trim();
		addObj[0][24] = venodrMasterBean.getVendorCode().trim();

	/*	for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				logger.info("updateObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}*/

		return getSqlModel().singleExecute(getQuery(2), addObj);

	
			
		}		
		
		
		
	/* for checking duplicate entry of record during updation*/
	public boolean checkDuplicateUpdate(VenodrMasterBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_VENDOR WHERE UPPER(VENDOR_NAME) LIKE '"
				+ bean.getVendorName().trim().toUpperCase() + "'" +"AND UPPER(VENDOR_EIN_NUMBER) LIKE '" +bean.getEinNumber().trim().toUpperCase()+"'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}	 
	
	
public void getStateCountry(VenodrMasterBean addDet) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ addDet.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {
			addDet.setState(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				//addDet.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else{
				//addDet.setCountry("");
			}
		}// end of if
	}

	
	public void getremitStateCountry(VenodrMasterBean addDet) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ addDet.getRemitCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {
			addDet.setRemitState(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				//addDet.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else{
				//addDet.setCountry("");
			}
		}// end of if
	}
	
	public boolean delete(VenodrMasterBean venodrMasterBean, HttpServletRequest request) {
		boolean result = false;
System.out.println("venodrMasterBean.getVendorCode() in delete method call ======"+ venodrMasterBean.getVendorCode());
		String deleteId = venodrMasterBean.getVendorCode();

		String delQuery = "DELETE FROM HRMS_D1_VENDOR WHERE VENDOR_ID="+deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	
	
	public void editVendorApp(VenodrMasterBean venodrMasterBean,
			HttpServletRequest request) {

		try {

			System.out.println("inside editVendorApp method ##########");
			System.out.println("venodrMasterBean.getVendorCode()=="	+ venodrMasterBean.getVendorCode());
			String setQuery = " SELECT VENDOR_NAME, VENDOR_EIN_NUMBER, VENDOR_PO_ADDRESS,VENDOR_ZIP, VENDOR_CITY,HRMS_LOCATION.LOCATION_NAME, VENDOR_STATE,"
					+ " VENDOR_PHONE_NO, VENDOR_SEND_PO, VENDOR_CONTACT_NAME, VENDOR_REMIT_NAME, VENDOR_REMIT_ADDRESS, VEDOR_DISC_PERCENT,"
					+ " VENDOR_NET_DAYS, VENDOR_COMMENTS1, VENDOR_CLASS_CODE, VENDOR_MIN_ORDER, VENDOR_FRIEGHT_MSG, VENDOR_TAX_MSG, VENDOR_SHIP_VIA, VENDOR_FOB,  "
					+ " VENDOR_COMMENTS2, VENDOR_REMIT_CITY,HRMS_LOCATION.LOCATION_NAME, VENDOR_REMIT_STATE, VENDOR_REMIT_ZIP "
					+ " FROM HRMS_D1_VENDOR"
					+ " LEFT JOIN HRMS_LOCATION on(HRMS_LOCATION.LOCATION_CODE=HRMS_D1_VENDOR.VENDOR_CITY)"
					+ " WHERE VENDOR_ID= " + venodrMasterBean.getVendorCode();

			Object[][] data = getSqlModel().getSingleResult(setQuery);

			venodrMasterBean.setVendorName(checkNull(String.valueOf(data[0][0])));
			venodrMasterBean.setEinNumber(checkNull(String.valueOf(data[0][1])));
			venodrMasterBean.setPostboxAddress(checkNull(String.valueOf(data[0][2])));
			venodrMasterBean.setZip(checkNull(String.valueOf(data[0][3])));
			venodrMasterBean.setCityId(checkNull(String.valueOf(data[0][4])));
			venodrMasterBean.setCity(checkNull(String.valueOf(data[0][5])));
			venodrMasterBean.setState(checkNull(String.valueOf(data[0][6])));
			venodrMasterBean.setPhoneNumber(checkNull(String.valueOf(data[0][7])));
			//venodrMasterBean.setSendPO(checkNull(String.valueOf(data[0][8])));
			if (String.valueOf(data[0][8]).equals("Y")) {
				venodrMasterBean.setRadioValue(checkNull(String.valueOf(data[0][8])));
				request.setAttribute("sendPO", String.valueOf(data[0][8]));
			}else if (String.valueOf(data[0][8]).equals("N")) {
				venodrMasterBean.setRadioValue(checkNull(String.valueOf(data[0][8])));
				request.setAttribute("sendPO", String.valueOf(data[0][8]));
			}
			
			venodrMasterBean.setContactName(checkNull(String.valueOf(data[0][9])));
			venodrMasterBean.setRemitName(checkNull(String.valueOf(data[0][10])));
			venodrMasterBean.setRemitAddress(checkNull(String.valueOf(data[0][11])));
			venodrMasterBean.setDiscPercent(checkNull(String.valueOf(data[0][12])));
			venodrMasterBean.setNetDays(checkNull(String.valueOf(data[0][13])));
			venodrMasterBean.setComments1(checkNull(String.valueOf(data[0][14])));
			venodrMasterBean.setClassCode(checkNull(String.valueOf(data[0][15])));
			venodrMasterBean.setMinOrder(checkNull(String.valueOf(data[0][16])));
			venodrMasterBean.setFreightMessage(checkNull(String.valueOf(data[0][17])));
			venodrMasterBean.setTaxMessage(checkNull(String.valueOf(data[0][18])));
			venodrMasterBean.setShipVia(checkNull(String.valueOf(data[0][19])));
			venodrMasterBean.setFob(checkNull(String.valueOf(data[0][20])));
			venodrMasterBean.setComments2(checkNull(String.valueOf(data[0][21])));
			venodrMasterBean.setRemitCityId(checkNull(String.valueOf(data[0][22])));
			venodrMasterBean.setRemitCity(checkNull(String.valueOf(data[0][23])));
		    venodrMasterBean.setRemitState(checkNull(String.valueOf(data[0][24])));
		    venodrMasterBean.setRemitZip(checkNull(String.valueOf(data[0][25])));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteCheckedRecords(VenodrMasterBean venodrMasterBean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
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
	

	
/*	public void getRecord(VenodrMasterBean bean) {
			
			System.out.println("here ingetRecord-------------------------------------------- ");
		
			Object[][] data = getSqlModel().getSingleResult(getQuery(5));
			bean.setDivisionCode(checkNull(String.valueOf(data[0][0])));
			bean.setApprovalDivision(checkNull(String.valueOf(data[0][1])));
			

		}*/
		
	
	
	
}
