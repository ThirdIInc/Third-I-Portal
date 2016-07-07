package org.struts.action.admin.srd;

import java.util.TreeMap;
import org.paradyne.bean.admin.srd.AddressDetails;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.struts.lib.ParaActionSupport;
import com.lowagie.text.Image;

/**
 * @author prajakta.bhandare
 * @date 21 Jan 2013
 */
public class AddressDetailsAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AddressDetailsAction.class);
	AddressDetails addDet;
	String poolDir = "";
	String fileName = "";

	/**
	 * @return the addDet
	 */
	public AddressDetails getAddDet() {
		return addDet;
	}
	/**
	 * @param addDet
	 *            the addDet to set
	 */
	public void setAddDet(AddressDetails addDet) {
		this.addDet = addDet;
	}

	public Object getModel() {
		return addDet;
	}
	/**
	 * Method to create instance of bean.
	 */

	public void prepare_local() throws Exception {
		addDet = new AddressDetails();
		addDet.setMenuCode(48);
	}
	/**
	 * Method to save or modify address details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {

		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		String str = "";
		//if (addDet.getAddId().equals("")) {
	   //if (addDet.getNewFlag().equals("")) {
		System.out.println("TYPE+++++++++++++"+addDet.getType());
		if (addDet.getParaId().equals("")) {//if address type already exists
			String query = " SELECT rownum,HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) "
					+ " ,(CENTER_NAME),HRMS_RANK.RANK_NAME,ADD_TYPE,ADD_1,ADD_2,ADD_3,HRMS_LOCATION.LOCATION_NAME,ADD_STATE,ADD_CNTRY,NVL(ADD_PH1,' '),NVL(ADD_PH2,' '),ADD_EXTENSION,NVL(ADD_FAX,' '), "
					+ " NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' '),ADD_CITY,ADD_PINCODE FROM HRMS_EMP_ADDRESS "
					+ " LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " WHERE HRMS_EMP_ADDRESS.ADD_TYPE = '" + addDet.getType() +"' AND HRMS_EMP_ADDRESS.EMP_ID ="+addDet.getEmpId()+""; 
			Object data[][] = model.getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				String typeQuery="SELECT MOD_NAME, MOD_VALUE FROM HRMS_DATA_MODIFICATION WHERE MOD_TYPE='addressType' AND MOD_VALUE='"+addDet.getType()+"'";
				Object typeData[][]=model.getSqlModel().getSingleResult(typeQuery);
				addActionMessage(""+typeData[0][0]+" address Already Added");
			}//end if address type already exists
			else{
				str = model.addAddress(addDet,request);
			}//end of else	
		}// end of if
		else {
			str = model.updateAddress(addDet,request);
		}// end of else
		addDet.setParaId("");
		addDet.setEditFlag(false);
		addDetRecord();
		getProfileImage();
		model.terminate();
		addActionMessage(getText(str));
		addDet.setAddress1("");
		addDet.setAddress2("");
		addDet.setAddress3("");
		addDet.setCityName("");
		addDet.setCountry("");
		addDet.setEmailId("");
		addDet.setFaxNo("");
		addDet.setMobNo("");
		addDet.setPhone1("");
		addDet.setPhone2("");
		addDet.setExtension("");
		addDet.setStateName("");
		addDet.setCityId("");
		addDet.setType("");
		addDet.setPinCode("");
		addDet.setNewFlag("");
		return SUCCESS;
	}

	/**
	 * Method to set Address Particulars after edit or delete.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addDetRecord() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		reset();
		model.getAddRecord(addDet,request);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Method to set values of all the fields in the form for edit.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		reset();
		addDet.setEditFlag(true);
		model.getRecord(addDet,addDet.getParaId());
		model.getAddRecord(addDet,request);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Method to delete the address details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		boolean result = model.delAddRecord(addDet,request);
		if (result) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		
		
		
			addDet.setEmeAddress("");
			addDet.setAbbrEmeAddress("");
			addDet.setEmeState("");
			addDet.setEmeCity("");
			addDet.setEmePinCode("");
			addDet.setEmePhoneNum("");
			addDet.setEmeMobileNum("");
			addDet.setEmeEmail("");
			addDet.setAbbrEmeEmail("");
			addDet.setEmeExtNum("");
			addDet.setEmeType("");
			addDet.setEmeCountry("");
			addDet.setEmeFaxNum("");
			addDet.setOffiType("");
			addDet.setEmeFlag(false);
		
		addDet.setParaId("");
		addDetRecord();
		addDet.setEditFlag(false);
		getProfileImage();
		model.terminate();
		return SUCCESS;

	}

	/**
	 * Method to get the login user address details while form loading.
	 * 
	 * @throws Exception
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		try {
			addDet.setEmeFlag(false);
			addDet.setOffiFlag(false);
			addDet.setLocalFlag(false);
			model.initiate(context, session);
			if (addDet.isGeneralFlag()) {
				addDet.setIsNotGeneralUser("false");
				addDet.setEmpId(addDet.getUserEmpId());
			}// end of if
			else {
				addDet.setIsNotGeneralUser("true");
				String str = (String) request.getSession().getAttribute("srdEmpCode");
				if(str==null || str=="" || str.equals("null")){	
					str=addDet.getUserEmpId();
				}
				addDet.setEmpId(str);
			}// end of else
			addDetRecord();
			addDet.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			logger.error(e);
			e.printStackTrace();
		}
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("Address");
		addDet.setAssetmap(map);
		dmu.terminate();
		
		
	}

	/**
	 * Method to select the employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),NVL(CENTER_NAME,' '),HRMS_RANK.RANK_NAME,EMP_ID, "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
		query += getprofileQuery(addDet);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "20", "30", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "empName", "empCent", "rank",
				"empId", "empName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AddressDetails_addDetRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the CIty.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9cityaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("city.code"), getMessage("city") };

		String[] headerWidth = { "10", "45" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "cityId", "cityName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AddressDetails_getState.action ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		addDet.setMasterMenuCode(20);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to set the city,state and country after searching the record.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getState() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		model.getStateCountry(addDet);
		model.getAddRecord(addDet,request);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}
	
	/** Method to add new record
	 * @return String
	 */
	public String add(){
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		addDet.setNewFlag("false");
		try {
			addDetRecord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addDet.setEditFlag(true);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}
	
	/** Method to reset all fields on form
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			addDet.setOffiType("");
			addDet.setEmeAddress("");
			addDet.setEmeState("");
			addDet.setEmeCity("");
			addDet.setEmePinCode("");
			addDet.setEmePhoneNum("");
			addDet.setEmeMobileNum("");
			addDet.setEmeEmail("");
			addDet.setEmeExtNum("");
			addDet.setEmeType("");
			addDet.setEmeCountry("");
			addDet.setEmeFlag(false);
			addDet.setEmeFaxNum("");
			addDet.setOffiFaxNum("");
			addDet.setLocalFaxNum("");
			addDet.setAbbrEmeAddress("");
			addDet.setAbbrEmeEmail("");
		}// end of try block
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}// end of catch block
		return "success";

	}
	
	/** Method to cancel current operation
	 * @return String
	 * @throws Exception
	 */
	public String cancelFunc() throws Exception {
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		addDet.setNewFlag("false");
		addDetRecord();
		addDet.setEditFlag(false);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}
	
	/** Method to set profile name and profile image for employee
	 * 
	 */
	public void getProfileImage(){
		AddressDetailsModel model = new AddressDetailsModel();
		model.initiate(context, session);
		 model.getImage(addDet);
		 String photo = addDet.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		addDet.setFlag("false");
		String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}
	//Added by Tinshuk Banafar Ends
}
