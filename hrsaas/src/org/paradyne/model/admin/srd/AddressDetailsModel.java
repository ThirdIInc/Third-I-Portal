package org.paradyne.model.admin.srd;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import oracle.net.TNSAddress.AddressList;

import org.paradyne.bean.admin.srd.AddressDetails;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

import com.bouncycastle.asn1.ocsp.Request;

/**
 * @author prajakta.bhandare
 * @date 21 Jan 2013
 */

public class AddressDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AddressDetailsModel.class);
	org.paradyne.bean.admin.srd.AddressDetails addDet = null;
	AuditTrail trial = null;
	
	/**
	 * Method to add address of an employee.
	 * 
	 * @param bean
	 * @param request
	 * @return String
	 */
	public String addAddress(AddressDetails bean, HttpServletRequest request) {
		Object addObj[][] = new Object[1][15];
		addObj[0][0] = bean.getEmpId().trim();//employee id
		addObj[0][1] = bean.getType().trim();// address type
		addObj[0][2] = bean.getAddress1().trim();//address1
		addObj[0][3] = bean.getAddress2().trim();//address2
		addObj[0][4] = bean.getAddress3().trim();//address3
		addObj[0][5] = bean.getCityId().trim();//city id
		addObj[0][6] = bean.getStateName().trim();//state 
		addObj[0][7] = bean.getCountry().trim();//country
		addObj[0][8] = bean.getPhone1().trim();//phone no.1
		addObj[0][9] = bean.getPhone2().trim();//phone no.2
		addObj[0][10] = bean.getExtension().trim();//extension no.
		addObj[0][11] = bean.getFaxNo().trim();//fax no.
		addObj[0][12] = bean.getMobNo().trim();//mobile no.
		addObj[0][13] = bean.getEmailId().trim();//email id
		addObj[0][14] = bean.getPinCode().trim();//pincode
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {//if result
			return "Record saved Successfully";
		}// end of if
		else {
			return "Error while adding record";
		}// end of else
	}

	/**
	 * Method to update the address of an employee.
	 * 
	 * @param bean
	 * @return String
	 */
	public String updateAddress(AddressDetails bean, HttpServletRequest request) {
		String str = "";
		try {
			Object addObj[][] = new Object[1][15];
			addObj[0][0] = bean.getAddress1().trim();//address1
			addObj[0][1] = bean.getAddress2().trim();//address2
			addObj[0][2] = bean.getAddress3().trim();//address3
			addObj[0][3] = bean.getCityId().trim();//city id
			addObj[0][4] = bean.getStateName().trim();//state
			addObj[0][5] = bean.getCountry().trim();//country
			addObj[0][6] = bean.getPhone1().trim();//phone no.1
			addObj[0][7] = bean.getPhone2().trim();//phone no.2
			addObj[0][8] = bean.getExtension().trim();//extension no.
			addObj[0][9] = bean.getFaxNo().trim();//fax no.
			addObj[0][10] = bean.getMobNo().trim();//mobile no.
			addObj[0][11] = bean.getEmailId().trim();//email id
			addObj[0][12] = bean.getPinCode().trim();//pincode
			addObj[0][13] = bean.getEmpId().trim();//employee id
			addObj[0][14] = bean.getParaId().trim();//employee id
			/*if (bean.getParaId().equals("E")) {// if address type 'E'
				addObj[0][14] = bean.getEmeType().trim();
			} //end  if address type 'E'
			else if (bean.getParaId().equals("P")) {// if address type 'P'
				addObj[0][14] = bean.getOffiType().trim();
			}//end if address type 'P'
			else if (bean.getParaId().equals("L")) {// if address type 'L'
				addObj[0][14] = bean.getLocalType().trim();
			}//end  if address type 'L'
*/			boolean result = getSqlModel().singleExecute(getQuery(2), addObj);
			if (result) {// if result
				str = "Record updated Successfully";
			}// end of if
			else {
				str = "Error while updating record";
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * Method to get the Address details of an employee.
	 * 
	 * @param bean
	 */
	public void getRecord(AddressDetails bean, String paraId) {

		Object addObj[] = new Object[2];
		addObj[0] = paraId;// bean.getParaId();
		addObj[1] = bean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		if (data != null && data.length > 0) {//if data
			bean.setNewFlag("true");
			bean.setType(checkNull(String.valueOf(data[0][0])));//address type
			bean.setAddress1(checkNull(String.valueOf(data[0][1])));//address1
			bean.setAddress2(checkNull(String.valueOf(data[0][2])));//address2
			bean.setAddress3(checkNull(String.valueOf(data[0][3])));//address3
			bean.setCityName(checkNull(String.valueOf(data[0][4])));//city 
			bean.setStateName(checkNull(String.valueOf(data[0][5])));//state
			bean.setCountry(checkNull(String.valueOf(data[0][6])));//country
			bean.setPhone1(checkNull(String.valueOf(data[0][7])).trim());//phone no.1
			bean.setPhone2(checkNull(String.valueOf(data[0][8])).trim());//phone no.2
			bean.setExtension(checkNull(String.valueOf(data[0][9])).trim());//extension no.
			bean.setFaxNo(checkNull(String.valueOf(data[0][10])).trim());//fax no.
			bean.setMobNo(checkNull(String.valueOf(data[0][11])).trim());//mobile no.
			bean.setEmailId(checkNull(String.valueOf(data[0][12])).trim());//email id
			bean.setCityId(checkNull(String.valueOf(data[0][13])).trim());//city id
			bean.setPinCode(checkNull(String.valueOf(data[0][14])).trim());//pincode
			
				bean.setOffiType(checkNull(String.valueOf(data[0][0])));
			
		}//end if data
	}

	/**
	 * To set blank for null value of any field.
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	

	/**
	 * Method to delete the address details of an employee.
	 * @param bean
	 * @param request
	 * @return boolean
	 */
	public boolean delAddRecord(AddressDetails bean, HttpServletRequest request) {
		Object addObj[][] = new Object[1][2];
		addObj[0][0] = bean.getEmpId();//employee id
		addObj[0][1] = bean.getParaId();//address type
		return getSqlModel().singleExecute(getQuery(5), addObj);

	}

	/**
	 * Method to select the State.
	 * 
	 * @param addDet
	 */
	public void getStateCountry(AddressDetails addDet) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ addDet.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {// if data
			addDet.setStateName(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				addDet.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else
				addDet.setCountry("");
		}// end of if data
	}

	public void getImage(AddressDetails addDet2) {
		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ addDet2.getEmpId();
			Object[][] myPics = getSqlModel().getSingleResult(query);
			addDet2.setUploadFileName(String.valueOf(myPics[0][0]));
			addDet2.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
					+ String.valueOf(myPics[0][2]) + " "
					+ String.valueOf(myPics[0][3]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Method to Retrieve all Address details 
	 *  @author prajakta.bhandare
	 * @param bean
	 */
	public void getAddRecord(AddressDetails bean, HttpServletRequest request){
		try{
			String typeQuery="SELECT MOD_NAME, MOD_VALUE FROM HRMS_DATA_MODIFICATION WHERE MOD_TYPE='addressType' AND MOD_ACTIVE='Y' ORDER BY  MOD_NAME"; 
			Object[][] typeData=getSqlModel().getSingleResult(typeQuery);
			ArrayList<Object> addList = new ArrayList<Object>();
			if(typeData!=null && typeData.length > 0){//if address type present
				
				for (int i = 0; i < typeData.length; i++) {//start i for loop
					String addressQuery="";
					AddressDetails bean1 = new AddressDetails();
					addressQuery="SELECT getValue('addressType',ADD_TYPE),"
			 		+" (ADD_1||' '||ADD_2||' '||ADD_3),ADD_PINCODE,ADD_PH1,ADD_PH2,ADD_FAX, "
			 		+" ADD_MOBILE,NVL(ADD_EMAIL,' '),ADD_EXTENSION,HRMS_LOCATION.LOCATION_NAME,"
			 		+" ADD_STATE,ADD_CNTRY,ADD_TYPE FROM HRMS_EMP_ADDRESS "
			 		+" LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY)"
			 		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			 		+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
			 		+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
			 		+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
			 		+" WHERE HRMS_EMP_ADDRESS.EMP_ID ="+bean.getEmpId()+" AND ADD_TYPE='"+typeData[i][1]+"'";
				
				Object[][] addressData= getSqlModel().getSingleResult(addressQuery);
				if(addressData!=null && addressData.length > 0){//if data present
					bean1.setEmeFlag(true);
					bean1.setEmeType(checkNull(String.valueOf(addressData[0][0])));
					bean1.setEmeAddress(checkNull(String.valueOf(addressData[0][1])));
					if(bean1.getEmeAddress().length() > 20){//if address length greater than 20
						bean1.setAbbrEmeAddress(bean1.getEmeAddress().substring(0, 19)+"...");
					}//end if address length greater than 20
					else{
						bean1.setAbbrEmeAddress(bean1.getEmeAddress());
					}//end of else
					bean1.setEmePinCode(checkNull(String.valueOf(addressData[0][2])));
					String str1="";
					 if((String.valueOf(addressData[0][3])==null) || (String.valueOf(addressData[0][3]).equals("")) || (String.valueOf(addressData[0][3]).equals("null"))){//start of if
						 if(!(String.valueOf(addressData[0][4])==null) && !(String.valueOf(addressData[0][4]).equals("")) && !(String.valueOf(addressData[0][4]).equals("null"))){//start of nested if
							 str1 += checkNull(String.valueOf(addressData[0][4]));
						 } //end of nested if
					 }//end of if
					 if((String.valueOf(addressData[0][4])==null) || (String.valueOf(addressData[0][4]).equals("")) || (String.valueOf(addressData[0][4]).equals("null"))){//start of if
						 if(!(String.valueOf(addressData[0][3])==null) && !(String.valueOf(addressData[0][3]).equals("")) && !(String.valueOf(addressData[0][3]).equals("null"))){//start of if
						 str1 += checkNull(String.valueOf(addressData[0][3]));
						 }//end of nested if
					 }//end of if
					 if(!(String.valueOf(addressData[0][3])==null) && !(String.valueOf(addressData[0][3]).equals("")) && !(String.valueOf(addressData[0][3]).equals("null")) && !(String.valueOf(addressData[0][4])==null) && !(String.valueOf(addressData[0][4]).equals("")) && !(String.valueOf(addressData[0][4]).equals("null")) ){//start of if
						 	str1 += checkNull(String.valueOf(addressData[0][3]));
							str1 +=","+"\n";
							str1 += checkNull(String.valueOf(addressData[0][4]));
						
					 }//end of if
					 
					bean1.setEmePhoneNum(str1);
					bean1.setEmeFaxNum(checkNull(String.valueOf(addressData[0][5])));//emeFaxNum
					 bean1.setEmeMobileNum(checkNull(String.valueOf(addressData[0][6])));//emeMobileNum
					 bean1.setEmeEmail(checkNull(String.valueOf(addressData[0][7])));//emeEmail
					 if(bean.getEmeEmail().length() > 20){//if address length >20
							bean1.setAbbrEmeEmail(bean1.getEmeEmail().substring(0, 19)+"...");
						}//end if address length >20
						else
						{
							bean1.setAbbrEmeEmail(bean1.getEmeEmail());
						}//end of else
					 bean1.setEmeExtNum(checkNull(String.valueOf(addressData[0][8])));//emeExtNum
					 bean1.setEmeCity(checkNull(String.valueOf(addressData[0][9])));//emeCity
					 bean1.setEmeState(checkNull(String.valueOf(addressData[0][10])));//emeState
					 bean1.setEmeCountry(checkNull(String.valueOf(addressData[0][11])));//emeCountry
					 bean1.setOffiType(checkNull(String.valueOf(addressData[0][12])));//address type
					 bean1.setNoData("true");
				} //end if data present
				else{
					bean1.setNoData("false");
				}//end of else
				addList.add(bean1);
				}//end i for loop
				bean.setAddressList(addList);
				request.setAttribute("count", addList.size());
				if(bean.getParaId()!=null){
					bean.setOffiType(bean.getParaId());
				}
				
			}//end if address type present
			
		}catch (Exception e) {
		}
	}

}