package org.struts.action.admin.srd;

import java.util.TreeMap;
import org.paradyne.bean.admin.srd.PersonnelDetail;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.PersonnelDetailModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;

/**
 * @author lakkichand
 * @date 03 May 2007
 */

/** Updated By
 * @author Prajakta.Bhandare
 * @date 12Dec 2012
 */
public class PersonnelDetailAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	PersonnelDetail persDetail;
	String poolDir = "";
	String fileName = "";
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonnelDetailAction.class);

	/**
	 * @return the persDetail
	 */
	public PersonnelDetail getPersDetail() {
		return persDetail;
	}

	/**
	 * @param persDetail
	 *            the persDetail to set
	 */
	public void setPersDetail(PersonnelDetail persDetail) {
		this.persDetail = persDetail;
	}

	public PersonnelDetail getModel() {
		return persDetail;
	}

	/**
	 * Method to create instance of bean.
	 */

	public void prepare_local() throws Exception {
		persDetail = new PersonnelDetail();
		persDetail.setMenuCode(44);
	}

	/**
	 * Method to get the login user personal details while form loading.
	 * 
	 * @throws Exception
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			PersonnelDetailModel model = new PersonnelDetailModel();
			model.initiate(context, session);
			String str = "";
			persDetail.setNoData("false");
			if (persDetail.isGeneralFlag()) {
				persDetail.setIsNotGeneralUser("false");
				persDetail.setEmployeeId(persDetail.getUserEmpId());
				model.getPersonnelDetailRecord(persDetail,request);
				model.getLangDetails(persDetail,request);
			} else {
				persDetail.setIsNotGeneralUser("true");
				
				 try {
			          str = (String)this.request.getSession().getAttribute(
			            "srdEmpCode");			        
			          if ((str.equals("null")) || (str.equals("")) || str.equals(null)) 
			          str= this.persDetail.getUserEmpId();
			        }
			        catch (Exception e)
			        {
			          str = this.persDetail.getUserEmpId();
			          System.out.print("PersonnelDetailAction After............" + str);
			        }
			    str = (String) request.getSession().getAttribute("srdEmpCode");
				if(str==null || str.equals("")){				
				 str=persDetail.getUserEmpId();
				persDetail.setEmployeeId(str);
				model.getPersonnelDetailRecord(persDetail,request);
				model.getLangDetails(persDetail,request);
			}
			DataModificatonUtil dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap map1 = dmu.getGenderXml("Blood");
			TreeMap map2 = dmu.getGenderXml("marriage");
			persDetail.setBgmap(map1);
			persDetail.setAssetmap(map2);
			persDetail.setEditFlag(false);
			dmu.terminate();
			getProfileImage();
			model.terminate();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Method to cancel personal details of employee
	 * @return String
	 */
	public String cancelFunc() {
		PersonnelDetailModel model = new PersonnelDetailModel();
		model.initiate(context, session);
		model.getPersonnelDetailRecord(persDetail, request);
		model.getLangDetails(persDetail, request);
		getProfileImage();
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("Blood");
		TreeMap map2 = dmu.getGenderXml("marriage");
		persDetail.setBgmap(map1);
		persDetail.setAssetmap(map2);
		persDetail.setEditFlag(false);
		dmu.terminate();
		model.terminate();
		return INPUT;
	}

	/**
	 * Method to save the personal details of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 * @author Prajakta.Bhandare
	 */
	public String save() throws Exception {
		PersonnelDetailModel model = new PersonnelDetailModel();
		model.initiate(context, session);
		boolean result = false, result1;
		result1 = model.checkID(persDetail);
		if (result1) {
			result = model.updatePersonnelDetails(persDetail, request);
			if (result) {
				model.getPersonnelDetailRecord(persDetail, request);
				model.getLangDetails(persDetail, request);
			}
		} else {
			result = model.addPersonnelDetails(persDetail, request);
			if (result) {
				model.getPersonnelDetailRecord(persDetail, request);
				model.getLangDetails(persDetail, request);
			}
		}// end of else
		getProfileImage();
		model.terminate();
		if (result) {
			if (result1) {
				addActionMessage(getMessage("update"));
			}// end of nested if
			else {
				addActionMessage(getMessage("save"));
			}// end of nested else

		}// end of if
		else {
			addActionMessage(getMessage("save.error"));
		}// end of else
		persDetail.setEditFlag(false);

		return SUCCESS;

	}

	/**
	 * Method to view the personal details of an Employee.
	 * @return String
	 * @throws Exception
	 */
	public String getDetailRecord() throws Exception {
		PersonnelDetailModel model = new PersonnelDetailModel();
		model.initiate(context, session);
		model.getPersonnelDetailRecord(persDetail, request);
		model.getLangDetails(persDetail, request);
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Method to select an employee
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
		query += getprofileQuery(persDetail);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "13", "30", "26", "26" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "employeeName", "center",
				"rank", "employeeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
		String submitToMethod = "PersonnelDetail_getDetailRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the caste category of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9castCataction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CATG_ID,CATG_NAME FROM HRMS_CATG ORDER BY CATG_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("catagory.code"),
				getMessage("caste.category") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "castCategoryCode", "castCategory" };

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
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		persDetail.setMasterMenuCode(33);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the religion of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9relgnaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT RELIGION_ID,RELIGION_NAME FROM HRMS_RELIGION ORDER BY RELIGION_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("religion.code"),
				getMessage("religion") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "religionCode", "religion" };

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
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		persDetail.setMasterMenuCode(39);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Caste of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9castaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CAST_ID,CAST_NAME FROM HRMS_CAST WHERE IS_ACTIVE='Y' ORDER BY CAST_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("caste.code"), getMessage("caste") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "castCode", "castName" };

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
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		persDetail.setMasterMenuCode(32);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**Method Name : f9cityaction()
	 * Used to select City From List
	 * @return String
	 * @throws Exception
	 */
	public String f9cityaction() throws Exception {
		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE=2 ORDER BY LOCATION_CODE ";
		String[] headers = { "Home Town Code", "Home Town" };
		String[] headerWidth = { "20", "20" };
		String[] fieldNames = { "homeTownCode", "homeTown" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/** Method to edit personal details of Employee
	 * @return String
	 */
	public String personelDetails() {
		PersonnelDetailModel model = new PersonnelDetailModel();
		model.initiate(context, session);
		model.getEditPersonnelDetailRecord(persDetail, request);
		model.getLangDetails(persDetail, request);
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map1 = dmu.getGenderXml("Blood");
		TreeMap map2 = dmu.getGenderXml("marriage");
		persDetail.setBgmap(map1);
		persDetail.setAssetmap(map2);
		persDetail.setEditFlag(true);
		dmu.terminate();
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/** Method to search languages for personal details
	 * @return String
	 * @throws Exception
	 */
	public String f9langaction() throws Exception {
		String[] languageCodeItt = request.getParameterValues("langCode");
		String langCode = "0";
		if (languageCodeItt != null) {
			for (int i = 0; i < languageCodeItt.length; i++) {
				if (i == 0) {
					langCode += languageCodeItt[i];
				} else {
					langCode += "," + languageCodeItt[i];
				}
			}
		}
		String query = "SELECT LANGUAGE_CODE, LANGUAGE_NAME FROM HRMS_LANGUAGE WHERE LANGUAGE_IS_ACTIVE='Y'AND LANGUAGE_CODE NOT IN("
				+ langCode + ")";
		String[] headers = { getMessage("persDetail.langCode"),
				getMessage("languageName") };
		String[] headerWidth = { "20", "20" };
		String[] fieldNames = { "languageCode", "langName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/** Method to add/edit Language details of Employee 
	 * @return String
	 * @author Prajakta.Bhandare
	 */
	public String addToList() {

		boolean flag = false;
		try {
			PersonnelDetailModel model = new PersonnelDetailModel();
			model.initiate(context, session);

			if (persDetail.getLangEditFlag().equals("Y")) {
				flag = model.modToList(persDetail, request);
				if (flag) {
					addActionMessage("Language Updated Successfully");
				} else {
					addActionMessage("Error While updating language");
				}
				persDetail.setLangName("");
				persDetail.setLanguageCode("");
				persDetail.setParaId("");
				persDetail.setLangEditFlag("N");
			} else {
				flag = model.addToList(persDetail, request);
				if (flag) {
					addActionMessage("Language Added Successfully");
				} else {
					addActionMessage("Error While adding language");
				}
				persDetail.setLangName("");
				persDetail.setLanguageCode("");
				persDetail.setParaId("");
				persDetail.setLangEditFlag("N");
			}
			model.getLangDetails(persDetail, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/** Method to delete Language Details
	 * @return String
	 */
	public String deleteToList() {
		try {
			boolean str = false;
			PersonnelDetailModel model = new PersonnelDetailModel();
			model.initiate(context, session);
			str = model.delLangDetails(persDetail);
			persDetail.setLangName("");
			persDetail.setLanguageCode("");
			persDetail.setParaId("");
			persDetail.setLangEditFlag("N");
			if (str) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("delete.error"));
			}
			model.getLangDetails(persDetail, request);

			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/** Method to get profile image of employee
	 * 
	 */
	public void getProfileImage() {
		try {
			PersonnelDetailModel model = new PersonnelDetailModel();
			model.initiate(context, session);
			model.getImage(persDetail);
			String photo = persDetail.getUploadFileName();
			String str = (String) session.getAttribute("session_pool");
			persDetail.setFlag("false");
			String img = model.getServletContext().getRealPath("//")
					+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
			try {
				Image image = Image.getInstance(img);
			}// end of try
			catch (Exception e) {
				photo = "NoImage.jpg";
			}
			request.setAttribute("profilePhoto", photo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String reset() {
		persDetail.setBloodGroup("");
		persDetail.setMaritalStatus("");
		persDetail.setMarriageDate("");
		persDetail.setReligion("");
		persDetail.setCastCategory("");
		persDetail.setCastName("");
		persDetail.setSubCast("");
		persDetail.setDesc("");
		persDetail.setNationality("");
		persDetail.setHeight("");
		persDetail.setWeight("");
		persDetail.setMarkId("");
		persDetail.setHobbies("");
		persDetail.setIsHandiCap("");
		persDetail.setIsConvicted("");
		persDetail.setDiseases("");
		persDetail.setAilments("");
		persDetail.setAllergies("");
		persDetail.setCriminalActs("");
		persDetail.setHomeTown("");
		persDetail.setHomeTownCode("");
		persDetail.setReligionCode("");
		persDetail.setCastCode("");
		persDetail.setCastCategoryCode("");
		persDetail.setAbbrMarkId("");
		persDetail.setAbbrHobbies("");
		persDetail.setAbbrDiseases("");
		persDetail.setAbbrAilments("");
		persDetail.setAbbrAllergies("");
		persDetail.setAbbrCriminalActs("");
		return "success";
	}
}
