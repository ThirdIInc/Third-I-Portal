package org.paradyne.model.recruitment;

import java.util.ArrayList;

import org.paradyne.bean.Recruitment.ApplicationForm;
import org.paradyne.lib.ModelBase;

public class ApplicationFormModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	public void showCountryState(ApplicationForm bean){
		logger.info("inside showCountryState model");
		logger.info("location name    "+bean.getCityName());
		Object [] parentCode = new Object [1];
		parentCode [0]	     = bean.getCityCode();	
		logger.info("Parent Code    "+bean.getCityCode());
		Object [][] result   = getSqlModel().getSingleResult(getQuery(1), parentCode);
		if(result.length!=0){
			Object [] stateCode = new Object[1];
			stateCode [0]		= String.valueOf(result[0][2]);
			Object [][] result1 = getSqlModel().getSingleResult(getQuery(1), stateCode);
			if(result1.length!=0){
				bean.setStateCode(String.valueOf(result1[0][0]));
				bean.setState(String.valueOf(result1[0][1]));
				Object [] countryCode = new Object[1];
				countryCode [0] 	  = String.valueOf(result1[0][2]);
				Object [][] result2 = getSqlModel().getSingleResult(getQuery(1), countryCode);
				if(result2.length!=0){
					bean.setCountryCode(String.valueOf(result2[0][0]));
					bean.setCountry(String.valueOf(result2[0][1]));
				}
			}
		}
		//return bean;
	}
	
	public boolean saveApplication(ApplicationForm bean){
		Object [][] saveHdrData  = new Object[1][25];
		
		saveHdrData [0][0]		 = bean.getPostCode();
		//saveHdrData [0][1]		 = bean.getJobName();
		saveHdrData [0][1]		 = bean.getFirstName();
		saveHdrData [0][2]		 = bean.getLastName();
		saveHdrData [0][3]		 = bean.getGender();
		saveHdrData [0][4]		 = bean.getBirthDate();
		saveHdrData [0][5]		 = bean.getAddress1();
		saveHdrData [0][6]		 = bean.getAddress2();
		saveHdrData [0][7]		 = bean.getAddress3();
		saveHdrData [0][8]		 = bean.getCityCode();
		saveHdrData [0][9]	 	 = bean.getStateCode();
		saveHdrData [0][10]		 = bean.getCountryCode();
		saveHdrData [0][11]	 	 = bean.getPinCode();
		saveHdrData [0][12]		 = bean.getEmailName();
		saveHdrData [0][13]		 = bean.getMobile();
		saveHdrData [0][14]		 = bean.getPhoneNo();
		saveHdrData [0][15]		 = bean.getTotExpYear();
		saveHdrData [0][16]		 = bean.getTotExpMonth();
		logger.info("month----------"+bean.getTotExpMonth());
		saveHdrData [0][17]		 = bean.getcurrentIndustry();
		saveHdrData [0][18]		 = bean.getCurrentFunArea();
		saveHdrData [0][19]		 = bean.getCurrentJobRole();
		saveHdrData [0][20] 	 = bean.getKeySkill();
		saveHdrData [0][21]		 = bean.getUploadFileName();
		saveHdrData [0][22] 	 = bean.getResumePaste();
		saveHdrData [0][23]		 = setStatus(bean);
		saveHdrData [0][24]		 = bean.getAppCode();
		
		return getSqlModel().singleExecute(getQuery(2), saveHdrData);
	}
	
	public boolean addQualification(ApplicationForm bean){
		boolean result;
		Object [][] qualificationData = new Object [1][8];
		qualificationData [0][1]	  = bean.getQualificationCode();
		qualificationData [0][2]	  = bean.getQualificationType();
		qualificationData [0][3]	  = bean.getYearofPassing();
		qualificationData [0][4]	  = bean.getPercentage();
		qualificationData [0][5]	  = bean.getCollege();
		qualificationData [0][6]	  = bean.getUniversity();
		qualificationData [0][7]	  = bean.getSpecialization();
		
		logger.info("application code================= "+bean.getAppCode());
		if(bean.getAppCode().equals("")){
			logger.info("application code------------- "+bean.getAppCode());
			getSqlModel().singleExecute(getQuery(3));
		}
		Object [][] hdrCode = getSqlModel().getSingleResult(getQuery(4));
		Object [] hdrCode1  = new Object [1];
		hdrCode1 [0]		= hdrCode [0][0];
		qualificationData [0][0]	  = hdrCode [0][0];
		bean.setAppCode(String.valueOf(hdrCode[0][0]));
		setStatus(bean);
		result = getSqlModel().singleExecute(getQuery(5), qualificationData);
		
		Object [][] tableData = getSqlModel().getSingleResult(getQuery(6), hdrCode1);
		logger.info("tableData.length-------------------"+tableData.length);
		ArrayList quaList = new ArrayList();
		if(tableData.length!=0){
			for(int i=0; i<tableData.length; i++){
				ApplicationForm bean1 = new ApplicationForm();
				bean1.setQualificationCode(String.valueOf(tableData[i][0]));
				logger.info("String.valueOf(tableData[i][0])---------"+String.valueOf(tableData[i][0]));
				bean1.setQualificationName(String.valueOf(tableData[i][1]));
				bean1.setQualificationType(String.valueOf(tableData[i][2]));
				bean1.setYearofPassing(String.valueOf(tableData[i][3]));
				bean1.setPercentage(String.valueOf(tableData[i][4]));
				bean1.setCollege(String.valueOf(tableData[i][5]));
				bean1.setUniversity(String.valueOf(tableData[i][6]));
				bean1.setSpecialization(String.valueOf(tableData[i][7]));
				quaList.add(bean1);
			}
			bean.setQualificationList(quaList);
		}
		return result;
	}
	
	public String setStatus(ApplicationForm bean){
		logger.info("----------------setStatus-------------------");
		Object [] checkStatus     	  = new Object [1];
		checkStatus [0]				  = bean.getExperience();
		String status				  = "";
		logger.info("Fresher================= "+bean.getFresher());
		logger.info("Experience================= "+bean.getExperience());
		if(checkStatus [0]!=null){
			logger.info("Experience  "+bean.getExperience());
			status 					  = "E";
			logger.info("Experience status-------- "+status);
			bean.setExpFlag("true");
		}else{
			logger.info("Fresher  "+bean.getFresher());
			status 					  = "F";
			logger.info("Fresher status-------- "+status);
			bean.setFresherFlag("true");
		}
		return status;
	}
}
