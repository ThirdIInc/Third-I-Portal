package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.ApplicationForm;
import org.paradyne.model.recruitment.ApplicationFormModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * 
 * Action class for Application Form
 *
 */
public class ApplicationFormAction extends ParaActionSupport {

	ApplicationForm applicationForm;
	
	public ApplicationForm getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
	}

	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		applicationForm = new ApplicationForm();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return applicationForm;
	}
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public String save(){
		logger.info("in side save method");
		ApplicationFormModel model = new ApplicationFormModel();
		model.initiate(context,session);
		boolean result;
		result = model.saveApplication(applicationForm);
		model.terminate();
		if(result){
			addActionMessage(getText("addMessage", ""));
		}else {
			addActionMessage("Your application can not be saved");
		}
		reset();
		return "success";
	}
	
	public void reset(){
		applicationForm.setAppCode("");
		applicationForm.setPostCode("");
		applicationForm.setPostName("");
		applicationForm.setJobCode("");
		applicationForm.setJobName("");
		applicationForm.setFirstName("");
		applicationForm.setLastName("");
		applicationForm.setGender("");
		applicationForm.setBirthDate("");
		applicationForm.setAddress1("");
		applicationForm.setAddress2("");
		applicationForm.setAddress3("");
		applicationForm.setEmailName("");
		applicationForm.setMobile("");
		applicationForm.setPhoneNo("");
		applicationForm.setPinCode("");
		applicationForm.setCityCode("");
		applicationForm.setCityName("");
		applicationForm.setCountry("");
		applicationForm.setCountryCode("");
		applicationForm.setState("");
		applicationForm.setStateCode("");
		applicationForm.setFresherFlag("false");
		applicationForm.setExpFlag("flag");
		applicationForm.setQualificationCode("");
		applicationForm.setQualificationName("");
		applicationForm.setQualificationType("");
		applicationForm.setQualificationList(null);
		applicationForm.setYearofPassing("");
		applicationForm.setPercentage("");
		applicationForm.setCollege("");
		applicationForm.setUniversity("");
		applicationForm.setSpecialization("");
		applicationForm.setTotExpYear("");
		applicationForm.setTotExpMonth("");
		applicationForm.setcurrentIndustry("");
		applicationForm.setCurrentFunArea("");
		applicationForm.setCurrentJobRole("");
		applicationForm.setKeySkill("");
		applicationForm.setUploadFileName("");
		applicationForm.setResumePaste("");
	}
	
	public String addQualification(){
		logger.info("in side addQualification method");
		ApplicationFormModel model = new ApplicationFormModel();
		model.initiate(context,session);
		boolean result;
		result = model.addQualification(applicationForm);
		model.terminate();
		if(!result){
			addActionMessage("You have already added this qualification");
		}
		return "success";
	}
	
	public String showCountryState(){
		logger.info("inside showCountryState");
		ApplicationFormModel model = new ApplicationFormModel();
		model.initiate(context,session);
		model.showCountryState(applicationForm);
		model.terminate();
		return "success";
	}
	
	public String f9Postaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT POST_CODE, POST_NAME FROM HRMS_POST_APP "
						+"ORDER BY POST_CODE";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Post Code", "Post Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"applicationForm.postCode","applicationForm.postName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9Cityaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION "
						+"WHERE LOCATION_LEVEL_CODE = 2 "
						+"ORDER BY LOCATION_CODE";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"City Code", "City Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"applicationForm.cityCode","applicationForm.cityName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="ApplicationForm_showCountryState.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9Courseaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT QUA_ID, QUA_NAME, QUA_GRADE FROM HRMS_QUA "
						+"ORDER BY QUA_ID";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Qualification Code", "Qualification Name", "Qualification Type"};
		
		String[] headerWidth={"20", "40", "40"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"applicationForm.qualificationCode","applicationForm.qualificationName", "applicationForm.qualificationType"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
}
