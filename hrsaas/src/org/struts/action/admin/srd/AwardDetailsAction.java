package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.AwardDetails;
import org.paradyne.model.admin.srd.AwardDetailsModel;

import com.lowagie.text.Image;

/**
 * 
 * @modified by priyanka.kumbhar
 * 
 */
public class AwardDetailsAction extends org.struts.lib.ParaActionSupport {

	AwardDetails awdDetails;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AwardDetailsAction.class);

	/*
	 * @return the awdDetails
	 */
	public AwardDetails getAwdDetails() {
		return awdDetails;
	}

	/**
	 * @param awdDetails
	 *            the awdDetails to set
	 */
	public void setAwdDetails(AwardDetails awdDetails) {
		this.awdDetails = awdDetails;
	}

	public Object getModel() {

		return awdDetails;
	}

	/**
	 * Method to create instance of bean.
	 */

	public void prepare_local() throws Exception {
		awdDetails = new AwardDetails();
		awdDetails.setMenuCode(50);
	}

	public String AwardDetailsRecord() {
		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		awdDetails = model.getRecord(awdDetails);
		model.terminate();
		return "success";
	}

	/**
	 * To set the record below after search
	 * 
	 * @return String
	 */
	public String empDetailRecord() {
		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		//model.getEmpRecord(awdDetails);
		model.getAwardRecord(awdDetails);
		getProfileImage();
		model.terminate();
		return "success";
	}

	/**
	 * for saving and modifying the records.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {

		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		String str = "";
		if (awdDetails.getParaId().equals("")) {
			str = model.addAwdDtl(awdDetails);
		}// end of if
		else {
			str = model.modAwdDtl(awdDetails, request);
			System.out.println("Record Updated Successfully");
			
		}// end of else
		addActionMessage(getText(str));
        awdDetails.setEditFlag(false);
        reset();
        model.getAwardRecord(awdDetails);
        awdDetails.setParaId("");
        getProfileImage();
		model.terminate();
		
		return SUCCESS;
	}

	/**
	 * To clear all the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {			
			awdDetails.setAuth("");
			awdDetails.setAuthDt("");
			awdDetails.setAwardType("");
			awdDetails.setAwdDesc("");
			awdDetails.setAwdDt("");
			awdDetails.setAwdCode("");
			awdDetails.setAwdId("");
			awdDetails.setRecordId("");
			awdDetails.setSlNo("");
			awdDetails.setUploadFileName("");
			awdDetails.setAwardList(null);
			awdDetails.setUploadMyFileName("");
			awdDetails.setUploadMyFileNameTxt("");
			awdDetails.setActiveInDashlet("");
			awdDetails.setUploadImageName("");
		}// end of try
		catch (Exception e) {
			logger.error("Exception" + e);
		} // end of catch
		return "success";
	}

	/**
	 * To delete particular record of an employee after clicking respective
	 * Delete button.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		boolean result = model.deleteAward(awdDetails);	
		if (result) {
			addActionMessage(getText("delMessage",
					"Record deleted Successfully"));
			awdDetails.setAuth("");
			awdDetails.setAuthDt("");
			awdDetails.setAwardType("");
			awdDetails.setAwdDesc("");
			awdDetails.setUploadMyFileName("");
			awdDetails.setAwdDt("");
			awdDetails.setUploadImageName("");
			awdDetails.setUploadMyFileNameTxt("");
			//awdDetails.getActiveInDashlet();
		}
		else {
			addActionMessage("Award cannot be deleted");
		}
		awdDetails.setParaId("");
		model.getAwardRecord(awdDetails);
		
		getProfileImage();
		model.terminate();
		return SUCCESS;
	}

	/**
	 * following method is for general user login who can view only his/her
	 * records.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		
		if (awdDetails.isGeneralFlag()) {
			awdDetails.setIsNotGeneralUser("false");	
			awdDetails.setEmpId(awdDetails.getUserEmpId());
		}
		else {
			awdDetails.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if(str!=null && str!=""){
				awdDetails.setEmpId(str);
			}else{
				awdDetails.setEmpId(awdDetails.getUserEmpId());
			}
		}
		model.getAwardRecord(awdDetails);
		getProfileImage();
		awdDetails.setEditFlag(false);		
		model.terminate();
	}
	/**
	 * To set the particular record of an employee after clicking respective
	 * Edit button.
	 * 
	 * @return String
	 */
	public String edit() throws Exception {
		try {
			AwardDetailsModel model = new AwardDetailsModel();
			model.initiate(context, session);
			model.getRecord(awdDetails);
			model.getAwardRecord(awdDetails);
			awdDetails.setEditFlag(true);
			getProfileImage();		
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * To generate report
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String report() throws Exception {

		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		model.getAwardReport(awdDetails);
		model.terminate();
		return "report";
	}

	/**
	 * Method to select the employee details.
	 * 
	 * @return
	 * @throws Exception
	 */

	public String f9emplaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+ " (HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') "
				+ "  FROM HRMS_EMP_OFFC  "
				+ "  LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "  INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ "  INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  ";
		query += getprofileQuery(awdDetails);
		query += " ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "20", "30", "27", "28" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "empName", "empCent",
				"empRank", "empId", "empName" };

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
		String submitToMethod = "AwardDetails_getEmpRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To select Award Code and Award Type
	 * 
	 * @return String
	 */
	public String f9awardaction() throws Exception {
		String query = "SELECT AWARD_CODE,AWARD_TYPE FROM HRMS_AWARD_MASTER WHERE IS_ACTIVE='Y' ORDER BY AWARD_CODE ";
		String[] headers = { getMessage("award.code"),
				getMessage("award.type") };

		String[] headerWidth = { "20", "35" };
		String[] fieldNames = { "awdId", "awardType" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		awdDetails.setMasterMenuCode(207);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String getEmpRecord(){
		try {
			AwardDetailsModel model = new AwardDetailsModel();
			model.initiate(context, session);
			model.getAwardRecord(awdDetails);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String addNew(){
		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		model.getAwardRecord(awdDetails);
		awdDetails.setEditFlag(true);
		getProfileImage();
		model.terminate();
		return "success";
	}
	
	public String cancelFunc() throws Exception {
		try {
			AwardDetailsModel model = new AwardDetailsModel();
			model.initiate(context, session);
			if (awdDetails.isGeneralFlag()) {
				model.getAwardRecord(awdDetails);
			}
			else {
				String str = (String) request.getSession().getAttribute(
						"srdEmpCode");
				model.getAwardRecord(awdDetails);
			}
			awdDetails.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public void getProfileImage(){
		AwardDetailsModel model = new AwardDetailsModel();
		model.initiate(context, session);
		model.getImage(awdDetails);
		String photo = awdDetails.getPhotoUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		awdDetails.setFlag("false");
		String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}
}
