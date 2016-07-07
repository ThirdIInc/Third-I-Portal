package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.srd.PersonnelDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * @author lakichand
 * @date 03 May 2007
 */

/** Updated By
 * @author Prajakta.Bhandare
 * @date 12 Dec 2012
 */
public class PersonnelDetailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonnelDetailModel.class);

	AuditTrail trial = null;

	public PersonnelDetailModel() {
	}
	/**
	 * Method to add personal details of the employee
	 * 
	 * @param bean
	 * @param request 
	 * @return
	 */
	public boolean addPersonnelDetails(PersonnelDetail bean, HttpServletRequest request) {
		Object[][] addObj = new Object[1][21];
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE	**/
		trial.initiate(context, session);
		addObj[0][0] = bean.getEmployeeId().trim();
		addObj[0][1] = bean.getCastCategoryCode();
		addObj[0][2] = bean.getCastCode();
		addObj[0][3] = bean.getSubCast();
		addObj[0][4] = bean.getHeight().trim();
		addObj[0][5] = bean.getWeight().trim();
		addObj[0][6] = bean.getMarkId().trim();
		addObj[0][7] = bean.getBloodGroup().trim();
		addObj[0][8] = bean.getDesc().trim();
		addObj[0][9] = bean.getHobbies();
		addObj[0][10] = bean.getMaritalStatus();
		if(bean.getMaritalStatus().equals("U")|| bean.getMaritalStatus().equals("")||bean.getMaritalStatus()==null){
		 bean.setMarriageDate("");	
		}
		addObj[0][11] = bean.getMarriageDate();
		addObj[0][12] = bean.getHomeTownCode();
		addObj[0][13] = bean.getNationality();
		addObj[0][14] = bean.getReligionCode();
		if(bean.getIsHandiCap().equals("N")||bean.getIsHandiCap().equals("")||bean.getIsHandiCap()==null ){
			bean.setDesc("");
		}
		addObj[0][15] = bean.getIsHandiCap();
		if(bean.getIsConvicted().equals("N")||bean.getIsConvicted().equals("")||bean.getIsConvicted()==null){
		bean.setCriminalActs("");	
		}
		addObj[0][16] = bean.getIsConvicted();
		addObj[0][17] = bean.getDiseases();
		addObj[0][18] = bean.getAilments();
		addObj[0][19] = bean.getAllergies();
		addObj[0][20] =bean.getCriminalActs();
		boolean res = getSqlModel().singleExecute(getQuery(1), addObj);
		if(res)
		{
			trial.setParameters("HRMS_EMP_PERS", new String[] { "EMP_ID" },
				new String[] { bean.getEmployeeId() }, bean.getEmployeeId());
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeADDTrail(request);
		}
		return res;
	}

	/**
	 * Method to modify Personal details of an employee
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean updatePersonnelDetails(PersonnelDetail bean,HttpServletRequest request) {
		boolean flag = false;
		try {
			Object[][] addObj = new Object[1][21];
			//trial = new AuditTrail(bean.getUserEmpId());
			//** AUDIT TRIAL INITIALIZE	**/
			//trial.initiate(context, session);
			/*trial.setParameters("HRMS_EMP_PERS", new String[] { "EMP_ID" },	new String[] { bean.getEmployeeId() }, bean.getEmployeeId());
			trial.beginTrail();*/
			addObj[0][0] = bean.getCastCategoryCode();
			addObj[0][1] = bean.getCastCode();
			addObj[0][2] = bean.getSubCast();
			addObj[0][3] = bean.getHeight().trim();
			addObj[0][4] = bean.getWeight().trim();
			addObj[0][5] = bean.getMarkId().trim();
			addObj[0][6] = bean.getBloodGroup().trim();
			addObj[0][8] = bean.getHobbies().trim();
			addObj[0][9] = bean.getMaritalStatus();
			if(bean.getMaritalStatus().equals("U")|| bean.getMaritalStatus().equals("")||bean.getMaritalStatus()==null){
				 bean.setMarriageDate("");	
				}
			addObj[0][10] = bean.getMarriageDate().trim();
			addObj[0][11] = bean.getHomeTownCode();
			addObj[0][12] = bean.getNationality();
			addObj[0][13] = bean.getReligionCode();
			if(bean.getIsConvicted().equals("N")||bean.getIsConvicted().equals("") || bean.getIsConvicted()==null){
				bean.setCriminalActs("");	
				}
			addObj[0][15] = bean.getIsHandiCap();
			addObj[0][14] = bean.getIsConvicted();
			addObj[0][15] = bean.getDiseases();
			addObj[0][16] = bean.getAilments();
			addObj[0][17] = bean.getAllergies();
			addObj[0][18]=bean.getCriminalActs();
			if(bean.getIsHandiCap().equals("N")||bean.getIsHandiCap().equals("")||bean.getIsHandiCap()==null ){
				bean.setDesc("");
			}
			addObj[0][19] = bean.getIsHandiCap();
			addObj[0][7] = bean.getDesc().trim();
			addObj[0][20] = bean.getEmployeeId();
			flag = getSqlModel().singleExecute(getQuery(2), addObj);
			/**	AUDIT TRAIL	EXECUTION **/
			//trial.executeMODTrail(request);
		} catch (Exception e) {
			logger.error("Exception in updatePersonnelDetails-------------"	+ e);
		}
		return flag;
	}

	/** Method to modify language details of Employee
	 * @param bean
	 * @param request
	 * 
	 */
	public void updateEmpLang(PersonnelDetail bean,HttpServletRequest request) {
		boolean flag = false;
		try {
			String empID=bean.getEmployeeId();
			String languageCode=bean.getLanguageCode();
			String deleteQuery="DELETE FROM HRMS_EMP_LANGUAGE WHERE EMP_ID="+empID;
		    getSqlModel().singleExecute(deleteQuery);
			String[] langCodeObj=request.getParameterValues("langCode");
			String[] readLangObj=request.getParameterValues("hiddenItReadLang");
			String[] writeLangObj=request.getParameterValues("hiddenItWriteLang");
			String[] speakLangObj=request.getParameterValues("hiddenItSpeakLang");
			String[] mptherLangObj=request.getParameterValues("hiddenItMotherLang");
				
			if (langCodeObj != null && langCodeObj.length > 0) {
				Object[][] addObj = new Object[langCodeObj.length][6];
				for(int i = 0; i < langCodeObj.length; i++){
					addObj[i][0] =empID;
					addObj[i][1] =langCodeObj[i];
					addObj[i][2] = String.valueOf(readLangObj[i]).equals("Y")? "Y" : "N";
					addObj[i][3] = String.valueOf(writeLangObj[i]).equals("Y")? "Y" : "N";
					addObj[i][4] = String.valueOf(speakLangObj[i]).equals("Y")? "Y" : "N";
					addObj[i][5] = String.valueOf(mptherLangObj[i]).equals("Y")? "Y" : "N";
					
					System.out.println("lang code:" +addObj[i][4]);	
						
				}	
				flag = getSqlModel().singleExecute(getQuery(7), addObj);
			}
		} catch (Exception e) {
			logger.error("Exception in updateEmpLang--"	+ e);
		}
	}
	/**
	 * Method to set the Personal details of an employee while form loading.
	 * 
	 * @param bean
	 * @return Object
	 */
	public PersonnelDetail getPersonnelDetailRecord(PersonnelDetail bean,
			HttpServletRequest request) {
		Object[] addObj = new Object[1];
		if (!(bean.getEmployeeId()).equals("")) {
			addObj[0] = bean.getEmployeeId();
		} else {
			addObj[0] = bean.getUserEmpId();
		}
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		if (data != null && data.length > 0) {
			bean.setEmployeeId(checkNull(String.valueOf(data[0][0])));
			bean.setBloodGroup(checkNull(String.valueOf(data[0][1])));
			bean.setMaritalStatus(checkNull(String.valueOf(data[0][2])));

			if (checkNull(String.valueOf(data[0][2])).equals("M")) {
				bean.setMaritalStatus("Married");
			} else if (checkNull(String.valueOf(data[0][2])).equals("U")) {
				bean.setMaritalStatus("UnMarried");
			} else if (checkNull(String.valueOf(data[0][2])).equals("W")) {
				bean.setMaritalStatus("Widow");
			} else if (checkNull(String.valueOf(data[0][2])).equals("A")) {
				bean.setMaritalStatus("Widower");
			} else if (checkNull(String.valueOf(data[0][2])).equals("D")) {
				bean.setMaritalStatus("Divorce");
			}else{
				bean.setMaritalStatus("");
			}

			bean.setMarriageDate(checkNull(String.valueOf(data[0][3])));
			bean.setReligion(checkNull(String.valueOf(data[0][4])));
			bean.setCastCategory(checkNull(String.valueOf(data[0][5])));
			bean.setCastName(checkNull(String.valueOf(data[0][6])));
			bean.setSubCast(checkNull(String.valueOf(data[0][7])));
			bean.setDesc(checkNull(String.valueOf(data[0][8])));
			if (bean.getDesc().length() > 25) {
				bean.setAbbrDesc(bean.getDesc().substring(0, 25)+"...");
			} else {
				bean.setAbbrDesc(bean.getDesc());
			}
			bean.setNationality(checkNull(String.valueOf(data[0][9])));
			bean.setHeight(checkNull(String.valueOf(data[0][10])));
			bean.setWeight(checkNull(String.valueOf(data[0][11])));
			bean.setMarkId(checkNull(String.valueOf(data[0][12])));
			if (bean.getMarkId().length() > 25) {
				bean.setAbbrMarkId(bean.getMarkId().substring(0, 25)+"...");
			} else {
				bean.setAbbrMarkId(bean.getMarkId());
			}
			bean.setHobbies(checkNull(String.valueOf(data[0][13])));
			if (bean.getHobbies().length() > 25) {
				bean.setAbbrHobbies(bean.getHobbies().substring(0, 24)+"...");
			} else {
				bean.setAbbrHobbies(bean.getHobbies());
			}
			bean.setIsHandiCap(checkNull(String.valueOf(data[0][14]).trim()));

			if (checkNull(String.valueOf(data[0][14]).trim()).equals("Y")) {
				bean.setIsHandiCap("Yes");
			} else if (checkNull(String.valueOf(data[0][14]).trim())
					.equals("N")) {
				bean.setIsHandiCap("No");
			}else{
				bean.setIsHandiCap("");
			}

			if (checkNull(String.valueOf(data[0][15]).trim()).equals("Y")) {
				bean.setIsConvicted("Yes");
			} else if (checkNull(String.valueOf(data[0][15]).trim())
					.equals("N")) {
				bean.setIsConvicted("No");
			}else{
				bean.setIsConvicted("");
			}		
			bean.setDiseases(checkNull(String.valueOf(data[0][16]).trim()));
			if (bean.getDiseases().length() > 25) {
				bean.setAbbrDiseases(bean.getDiseases().substring(0, 24)+"...");
			} else {
				bean.setAbbrDiseases(bean.getDiseases());
			}
			bean.setAilments(checkNull(String.valueOf(data[0][17]).trim()));
			if (bean.getAilments().length() > 25) {
				bean.setAbbrAilments(bean.getAilments().substring(0, 24)+"...");
			} else {
				bean.setAbbrAilments(bean.getAilments());
			}
			bean.setAllergies(checkNull(String.valueOf(data[0][18]).trim()));
			if (bean.getAllergies().length() > 25) {
				bean.setAbbrAllergies(bean.getAllergies().substring(0, 24)+"...");
			} else {
				bean.setAbbrAllergies(bean.getAllergies());
			}
			bean.setCriminalActs(checkNull(String.valueOf(data[0][19]).trim()));
			if (bean.getCriminalActs().length() > 25) {
				bean.setAbbrCriminalActs(bean.getCriminalActs()
						.substring(0, 24)+"...");
			} else {
				bean.setAbbrCriminalActs(bean.getCriminalActs());
			}
			bean.setHomeTown(checkNull(String.valueOf(data[0][20])));
			bean.setHomeTownCode(checkNull(String.valueOf(data[0][21])));
			bean.setReligionCode(checkNull(String.valueOf(data[0][22])));
			bean.setCastCode(checkNull(String.valueOf(data[0][23])));
			bean.setCastCategoryCode(checkNull(String.valueOf(data[0][24])));
		}
		else {
			bean.setBloodGroup("");
			bean.setMaritalStatus("");
			bean.setMarriageDate("");
			bean.setReligion("");
			bean.setCastCategory("");
			bean.setCastName("");
			bean.setSubCast("");
			bean.setDesc("");
			bean.setNationality("");
			bean.setHeight("");
			bean.setWeight("");
			bean.setMarkId("");
			bean.setHobbies("");
			bean.setIsHandiCap("");
			bean.setIsConvicted("");
			bean.setDiseases("");
			bean.setAilments("");
			bean.setAllergies("");
			bean.setCriminalActs("");
			bean.setHomeTown("");
			bean.setHomeTownCode("");
			bean.setReligionCode("");
			bean.setCastCode("");
			bean.setCastCategoryCode("");
			bean.setAbbrMarkId("");
			bean.setAbbrHobbies("");
			bean.setAbbrDiseases("");
			bean.setAbbrAilments("");
			bean.setAbbrAllergies("");
			bean.setAbbrCriminalActs("");
			bean.setAbbrDesc("");
			bean.setHandicapDesc("");
		}
		return bean;
	}
	
	public void getEditPersonnelDetailRecord(PersonnelDetail bean,
			HttpServletRequest request) {

		Object[] addObj = new Object[1];
		if (!(bean.getEmployeeId()).equals("")) {
			addObj[0] = bean.getEmployeeId();
		} else {
			addObj[0] = bean.getUserEmpId();
		}
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		if (data != null && data.length > 0) {
			bean.setEmployeeId(checkNull(String.valueOf(data[0][0])));
			bean.setBloodGroup(checkNull(String.valueOf(data[0][1])));
			bean.setMaritalStatus(checkNull(String.valueOf(data[0][2])));			
			bean.setMarriageDate(checkNull(String.valueOf(data[0][3])));
			bean.setReligion(checkNull(String.valueOf(data[0][4])));
			bean.setCastCategory(checkNull(String.valueOf(data[0][5])));
			bean.setCastName(checkNull(String.valueOf(data[0][6])));
			bean.setSubCast(checkNull(String.valueOf(data[0][7])));
			bean.setDesc(checkNull(String.valueOf(data[0][8])));
			if (bean.getDesc().length() > 25) {
				bean.setAbbrDesc(bean.getDesc().substring(0, 25)+"...");
			} else {
				bean.setAbbrDesc(bean.getDesc());
			}
			bean.setNationality(checkNull(String.valueOf(data[0][9])));
			bean.setHeight(checkNull(String.valueOf(data[0][10])));
			bean.setWeight(checkNull(String.valueOf(data[0][11])));
			bean.setMarkId(checkNull(String.valueOf(data[0][12])));
			if (bean.getMarkId().length() > 25) {
				bean.setAbbrMarkId(bean.getMarkId().substring(0, 25)+"...");
			} else {
				bean.setAbbrMarkId(bean.getMarkId());
			}
			bean.setHobbies(checkNull(String.valueOf(data[0][13])));
			if (bean.getHobbies().length() > 25) {
				bean.setAbbrHobbies(bean.getHobbies().substring(0, 24)+"...");
			} else {
				bean.setAbbrHobbies(bean.getHobbies());
			}
			bean.setIsHandiCap(checkNull(String.valueOf(data[0][14]).trim()));			
			bean.setIsConvicted(checkNull(String.valueOf(data[0][15]).trim()));
			bean.setDiseases(checkNull(String.valueOf(data[0][16]).trim()));
			if (bean.getDiseases().length() > 25) {
				bean.setAbbrDiseases(bean.getDiseases().substring(0, 24)+"...");
			} else {
				bean.setAbbrDiseases(bean.getDiseases());
			}
			bean.setAilments(checkNull(String.valueOf(data[0][17]).trim()));
			if (bean.getAilments().length() > 25) {
				bean.setAbbrAilments(bean.getAilments().substring(0, 24)+"...");
			} else {
				bean.setAbbrAilments(bean.getAilments());
			}
			bean.setAllergies(checkNull(String.valueOf(data[0][18]).trim()));
			if (bean.getAllergies().length() > 25) {
				bean.setAbbrAllergies(bean.getAllergies().substring(0, 24)+"...");
			} else {
				bean.setAbbrAllergies(bean.getAllergies());
			}
			bean.setCriminalActs(checkNull(String.valueOf(data[0][19]).trim()));
			if (bean.getCriminalActs().length() > 25) {
				bean.setAbbrCriminalActs(bean.getCriminalActs()
						.substring(0, 24)+"...");
			} else {
				bean.setAbbrCriminalActs(bean.getCriminalActs());
			}
			bean.setHomeTown(checkNull(String.valueOf(data[0][20])));
			bean.setHomeTownCode(checkNull(String.valueOf(data[0][21])));
			bean.setReligionCode(checkNull(String.valueOf(data[0][22])));
			bean.setCastCode(checkNull(String.valueOf(data[0][23])));
			bean.setCastCategoryCode(checkNull(String.valueOf(data[0][24])));
		}else{
			bean.setBloodGroup("");
			bean.setCastCategory("");
			bean.setCastCode("");
			bean.setCastName("");
			bean.setChkTest("");
			bean.setCheckTabFlag("");
			bean.setAbbrAilments("");
			bean.setAbbrAllergies("");
			bean.setAbbrCriminalActs("");
			bean.setAbbrDesc("");
			bean.setAbbrDiseases("");
			bean.setAbbrHobbies("");
			bean.setAbbrMarkId("");
			bean.setCriminalActs("");
			bean.setDesc("");
			bean.setDiseases("");
			bean.setHandicapDesc("");
			bean.setHobbies("");
			bean.setHomeTown("");
			bean.setHomeTownCode("");
			bean.setIsConvicted("");
			bean.setIsHandiCap("");
			bean.setMaritalStatus("");
			bean.setMarkId("");
			bean.setMarriageDate("");
			bean.setNationality("");
			bean.setReligion("");
			bean.setReligionCode("");
			bean.setSubCast("");
			bean.setWeight("");
			bean.setHeight("");
			bean.setAilments("");
			bean.setAllergies("");
		}	
		
}
	
	
	/** Method to get Language Details of Employee
	 * @param personDetails
	 * @param request
	 */
	public void  getLangDetails(PersonnelDetail personDetails,HttpServletRequest request){
		System.out.println("EMP ID"+personDetails.getEmployeeId());
		Object[] addObj= new Object[1];
		if(!(personDetails.getEmployeeId()).equals("")){
			addObj[0] = personDetails.getEmployeeId();
		}else{
			addObj[0] = personDetails.getUserEmpId();
		}
		Object[][] data = getSqlModel().getSingleResult(getQuery(6), addObj);
		ArrayList<Object> lst = new ArrayList<Object>();
		if (data != null && data.length > 0) {
			personDetails.setModeLength("true");
			for (int i=0;i<data.length;i++) { 		
				PersonnelDetail bean = new PersonnelDetail();
				bean.setLangCode(String.valueOf(data[i][0]).trim());
				bean.setLangName((String.valueOf(data[i][1])).trim());
				bean.setHiddenItLangType((String.valueOf(data[i][1])).trim());
				bean.setHiddenItReadLang(String.valueOf(data[i][2]));
				bean.setHiddenItWriteLang(String.valueOf(data[i][3]));
				bean.setHiddenItSpeakLang(String.valueOf(data[i][4]));
				bean.setHiddenItMotherLang(String.valueOf(data[i][5]));
				bean.setReadLang(bean.getHiddenItReadLang().equals("Y") ? "Y" : "N");
				if(bean.getReadLang().equals("Y")){
					bean.setReadFlag(true);
				}else{
					bean.setReadFlag(false);
				}
				bean.setWriteLang(bean.getHiddenItWriteLang().equals("Y") ? "Y" : "N");
				if(bean.getWriteLang().equals("Y")){
					bean.setWriteFlag(true);
				}else{
					bean.setWriteFlag(false);
				}
				bean.setSpeakLang(bean.getHiddenItSpeakLang().equals("Y") ? "Y" : "N");
				if(bean.getSpeakLang().equals("Y")){
					bean.setSpeakFlag(true);
				}else{
					bean.setSpeakFlag(false);
				}
				bean.setMotherLang(bean.getHiddenItMotherLang().equals("Y") ? "Y" : "N");
				if(bean.getMotherLang().equals("Y")){
					bean.setMotherFlag(true);
				}else{
					bean.setMotherFlag(false);
				}
				personDetails.setNoData("false");
			    lst.add(bean);
			    personDetails.setLangList(lst);
			}
		}else{
			personDetails.setNoData("true");
			personDetails.setLangList(null);
		}
		
	}
	


	/** Method to delete language details 
	 * @param bean
	 * @return
	 */
	public boolean delLangDetails (PersonnelDetail bean){
		boolean result = false;
		try {
			Object[][] addObj= new Object[1][2];
			addObj[0][0]= bean.getEmployeeId();
			addObj[0][1] = bean.getParaId();
			result= getSqlModel().singleExecute(getQuery(8),addObj);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return result;
	} 
	/**
	 * Method to check PersonalDetails of an employee whether it is saved or
	 * not.
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkID(PersonnelDetail bean) {
		boolean flag;
		Object addObj[] = new Object[1];
		addObj[0] = bean.getEmployeeId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		if (data.length == 0) {
			flag = false;
		}// end of if
		else {
			flag = true;
		}// end of else
		return flag;
	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	/** Method to add Language details
	 * @param bean
	 * @param request
	 * @return boolean flag
	 */
	public boolean addToList(PersonnelDetail bean,HttpServletRequest request) {
		boolean flag=false;
		try {
			Object[][] addObj = new Object[1][6];
			addObj[0][0]= bean.getEmployeeId();
			addObj[0][1]= bean.getLanguageCode();
			addObj[0][2]= bean.getReadLang().equalsIgnoreCase("on") ? "Y" : "N";
			addObj[0][3]= bean.getWriteLang().equalsIgnoreCase("on") ? "Y" : "N";
			addObj[0][4]= bean.getSpeakLang().equalsIgnoreCase("on") ? "Y" : "N";
			addObj[0][5]= bean.getMotherLang().equalsIgnoreCase("on") ? "Y" : "N";
			flag = getSqlModel().singleExecute(getQuery(7), addObj);

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return flag;
	}	
		
public boolean	modToList(PersonnelDetail bean, HttpServletRequest request){
	boolean flag=false;
	try {
		
		Object[][] addObj = new Object[1][7];
		
		if(bean.getLanguageCode().equals("") || bean.getLanguageCode()==null){
			addObj[0][0]= bean.getParaId();
		}else{
			addObj[0][0]= bean.getLanguageCode();
		}
		addObj[0][1]= bean.getReadLang().equalsIgnoreCase("on") ? "Y" : "N";
		addObj[0][2]= bean.getWriteLang().equalsIgnoreCase("on") ? "Y" : "N";
		addObj[0][3]= bean.getSpeakLang().equalsIgnoreCase("on") ? "Y" : "N";
		addObj[0][4]= bean.getMotherLang().equalsIgnoreCase("on") ? "Y" : "N";
		addObj[0][5]= bean.getEmployeeId();
		addObj[0][6]= bean.getParaId();
		flag = getSqlModel().singleExecute(getQuery(5), addObj);
	} catch (RuntimeException e) {
		
		e.printStackTrace();
	}
return flag;
}

public void getImage(PersonnelDetail persDetail) {
		
			try {
				
				String empID=persDetail.getEmployeeId();
				if(empID.equals("") || empID.length()==0){
					empID=persDetail.getUserEmpId();
				}
				String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+empID;
				
				Object[][] myPics = getSqlModel().getSingleResult(query);
					
				persDetail.setUploadFileName( String.valueOf(myPics[0][0]));	
				persDetail.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}


