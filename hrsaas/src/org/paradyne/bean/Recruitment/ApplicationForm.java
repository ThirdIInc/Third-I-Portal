package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApplicationForm extends BeanBase {
		private String appCode;
		private String postCode;
		private String postName;
		private String jobCode;
		private String jobName;
		private String firstName;
		private String lastName;
		private String gender;
		private String birthDate;
		private String address1;
		private String address2;
		private String address3;
		private String emailName;
		private String mobile;
		private String phoneNo;
		private String cityName;
		private String cityCode;
		private String pinCode;
		private String state;
		private String stateCode;
		private String country;
		private String countryCode;
		/*private String yearCompX;
		private String percentageX;
		private String markSheetNoX;
		private String yearCompXII;
		private String percentageXII;
		private String yearCompDip;
		private String percentageDip;
		private String course;
		private String courseCode;
		private String specialization;
		private String college;
		private String university;
		private String firstSemester;
		private String secondSemester;
		private String thirdSemester;
		private String forthSemester;
		private String overallPerc;*/
		private String totExpYear;
		private String totExpMonth;
		private String currentIndustry;
		private String currentFunArea;
		private String currentJobRole;
		private String keySkill;
		private String uploadFileName;
		private String resumePaste;
		//private String status;
		private String fresher;
		private String experience;
		private String fresherFlag = "false";
		private String expFlag = "false"; 
		private String qualificationName;
		private String qualificationCode;
		private String qualificationType;
		private String yearofPassing;
		private String percentage;
		private String college;
		private String university;
		private String specialization;
		private ArrayList qualificationList;
		
		
		public String getPostCode() {
			return postCode;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		public String getJobCode() {
			return jobCode;
		}
		public void setJobCode(String jobCode) {
			this.jobCode = jobCode;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
		public String getAddress3() {
			return address3;
		}
		public void setAddress3(String address3) {
			this.address3 = address3;
		}
		public String getEmailName() {
			return emailName;
		}
		public void setEmailName(String emailName) {
			this.emailName = emailName;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getPinCode() {
			return pinCode;
		}
		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		/*public String getYearCompX() {
			return yearCompX;
		}
		public void setYearCompX(String yearCompX) {
			this.yearCompX = yearCompX;
		}
		public String getPercentageX() {
			return percentageX;
		}
		public void setPercentageX(String percentageX) {
			this.percentageX = percentageX;
		}
		public String getMarkSheetNoX() {
			return markSheetNoX;
		}
		public void setMarkSheetNoX(String markSheetNoX) {
			this.markSheetNoX = markSheetNoX;
		}
		public String getYearCompXII() {
			return yearCompXII;
		}
		public void setYearCompXII(String yearCompXII) {
			this.yearCompXII = yearCompXII;
		}
		public String getPercentageXII() {
			return percentageXII;
		}
		public void setPercentageXII(String percentageXII) {
			this.percentageXII = percentageXII;
		}
		public String getYearCompDip() {
			return yearCompDip;
		}
		public void setYearCompDip(String yearCompDip) {
			this.yearCompDip = yearCompDip;
		}
		public String getPercentageDip() {
			return percentageDip;
		}
		public void setPercentageDip(String percentageDip) {
			this.percentageDip = percentageDip;
		}
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
		public String getSpecialization() {
			return specialization;
		}
		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}
		public String getCollege() {
			return college;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		public String getUniversity() {
			return university;
		}
		public void setUniversity(String university) {
			this.university = university;
		}
		public String getFirstSemester() {
			return firstSemester;
		}
		public void setFirstSemester(String firstSemester) {
			this.firstSemester = firstSemester;
		}
		public String getSecondSemester() {
			return secondSemester;
		}
		public void setSecondSemester(String secondSemester) {
			this.secondSemester = secondSemester;
		}
		public String getThirdSemester() {
			return thirdSemester;
		}
		public void setThirdSemester(String thirdSemester) {
			this.thirdSemester = thirdSemester;
		}
		public String getForthSemester() {
			return forthSemester;
		}
		public void setForthSemester(String forthSemester) {
			this.forthSemester = forthSemester;
		}
		public String getOverallPerc() {
			return overallPerc;
		}
		public void setOverallPerc(String overallPerc) {
			this.overallPerc = overallPerc;
		}*/
		public String getTotExpYear() {
			return totExpYear;
		}
		public void setTotExpYear(String totExpYear) {
			this.totExpYear = totExpYear;
		}
		public String getTotExpMonth() {
			return totExpMonth;
		}
		public void setTotExpMonth(String totExpMonth) {
			this.totExpMonth = totExpMonth;
		}
		public String getcurrentIndustry() {
			return currentIndustry;
		}
		public void setcurrentIndustry(String currentIndustry) {
			this.currentIndustry = currentIndustry;
		}
		public String getCurrentFunArea() {
			return currentFunArea;
		}
		public void setCurrentFunArea(String currentFunArea) {
			this.currentFunArea = currentFunArea;
		}
		public String getCurrentJobRole() {
			return currentJobRole;
		}
		public void setCurrentJobRole(String currentJobRole) {
			this.currentJobRole = currentJobRole;
		}
		public String getKeySkill() {
			return keySkill;
		}
		public void setKeySkill(String keySkill) {
			this.keySkill = keySkill;
		}
		public String getUploadFileName() {
			return uploadFileName;
		}
		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}
		public String getResumePaste() {
			return resumePaste;
		}
		public void setResumePaste(String resumePaste) {
			this.resumePaste = resumePaste;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getStateCode() {
			return stateCode;
		}
		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		/*public String getCourseCode() {
			return courseCode;
		}
		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}*/
		public String getPostName() {
			return postName;
		}
		public void setPostName(String postName) {
			this.postName = postName;
		}
		public String getJobName() {
			return jobName;
		}
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		/*public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}*/
		public String getCurrentIndustry() {
			return currentIndustry;
		}
		public void setCurrentIndustry(String currentIndustry) {
			this.currentIndustry = currentIndustry;
		}
		public String getQualificationName() {
			return qualificationName;
		}
		public void setQualificationName(String qualificationName) {
			this.qualificationName = qualificationName;
		}
		public String getQualificationCode() {
			return qualificationCode;
		}
		public void setQualificationCode(String qualificationCode) {
			this.qualificationCode = qualificationCode;
		}
		public String getQualificationType() {
			return qualificationType;
		}
		public void setQualificationType(String qualificationType) {
			this.qualificationType = qualificationType;
		}
		public String getYearofPassing() {
			return yearofPassing;
		}
		public void setYearofPassing(String yearofPassing) {
			this.yearofPassing = yearofPassing;
		}
		public String getPercentage() {
			return percentage;
		}
		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}
		public String getCollege() {
			return college;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		public String getUniversity() {
			return university;
		}
		public void setUniversity(String university) {
			this.university = university;
		}
		public String getSpecialization() {
			return specialization;
		}
		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}
		public ArrayList getQualificationList() {
			return qualificationList;
		}
		public void setQualificationList(ArrayList qualificationList) {
			this.qualificationList = qualificationList;
		}
		public String getAppCode() {
			return appCode;
		}
		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}
		public String getFresherFlag() {
			return fresherFlag;
		}
		public void setFresherFlag(String fresherFlag) {
			this.fresherFlag = fresherFlag;
		}
		public String getExpFlag() {
			return expFlag;
		}
		public void setExpFlag(String expFlag) {
			this.expFlag = expFlag;
		}
		public String getFresher() {
			return fresher;
		}
		public void setFresher(String fresher) {
			this.fresher = fresher;
		}
		public String getExperience() {
			return experience;
		}
		public void setExperience(String experience) {
			this.experience = experience;
		}

}
