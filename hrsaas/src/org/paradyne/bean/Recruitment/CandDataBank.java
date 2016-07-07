/**
 * @author Pradeep Kumar Sahoo
 * @date 24/01/2009
 */
package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;
import java.util.ArrayList;

public class CandDataBank extends BeanBase {
	
	private String requisitionTitleCode="";
	private String requisitionTitle="";
	private String resumeSrc="";
	private String resSource="";
	private String totalPage="";
	private String pageNoField="";
	private String spl="";
	private String qua="";
	private String hiddenCandCode="";
	private String hiddenCode="";
	private String otherInfo="";
	private String expView="";
	private String marriageView="";
	private String radioEmp="";
	private String pathPhoto; 
	private String uploadPhoto;
	private String radioFlag="false";
	private String radioFlag1="false";
	private String radioFlag2="false";
	private String uploadFileName;
	private String dataPath;
	private String cancelSecond="false";
	private String cancelFirst="false";
	private String updateFirst="false";
	private String updateSecond="false";
	private String chkExpAll;
	private String candName;
	private String rowId;
	private String candCode;
	private String titleCode;
	private String titleName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private String gender;
	private String maritalStatus;
	private String minExp;
	private String maxExp;
	private String addCheck;
	private String address;
	private String address1;
	private String cityCode;
	private String cityCode1;
	private String stateCode;
	private String stateCode1;
	private String countryCode;
	private String countryCode1;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String city1;
	private String state1;
	private String country1;
	private String pincode1;
	private String resPhone;
	private String mobile;
	private String offPhone;
	private String email;
	private String doYou;
	private String wheYouEmp;
	private String relocate;
	private String srNo;
	private String refName;
	private String refEmpId;
	private String refEmpTok;
	private String refEmpName;
	private String profession;
	private String contactDet;
	private String refComment;
	private String experience;
	private String currCTC;
	private String expCTC;
	private String currentCtc;
	private String expectedCtc;
	
	private String refType;
	private String refDtlCode;
	
	private String postingDate;
	private String shortStatus;
	
	private String listFlag;
	private String myPage;
	private String noData;
	private String genderView;
	private String maritalView;
	private String selectname;
	private String show;
	
	private String qualName;
	private String qualDtlId;
	private String spelDtlId;
	private String deleteQuali;
	private String qualLevel;
	private String spelName;
	private String estbName;
	private String yearofPass;
	private String percMarks;
	private String qualDetCode;
	
	private String emprName;
	private String expDtlId;
	private String indsId;
	private String deleteExp;
	private String lastJobPro;
	private String joinDate;
	private String relvDate;
	private String specAch;
	private String ctcExp;
	
	
	private String skillDtlId;
	private String skillCode;
	private String deleteSkill;
	private String skillExp;
	private String chkSkillAll;
	private String skillName;
		
	private String funcDtlId;
	private String funcCode;
	private String deleteFunc;
	private String funcExp;
	private String funcName;
	private String funcChkAll;
	private String funcChk;
	
	private ArrayList loadList=null;
	private ArrayList refList=null;
	private ArrayList qualList=null;
	private ArrayList skillList=null;
	private ArrayList expList=null;
	private ArrayList funcList=null;
	private String qualiFlag="false";
	private String expFlag="false";
	private String skillFlag="false";
	private String domFlag="false";
	private String refFlag="false";
	private String indsName="";
	
	private String dupChkFlag="false";
	
	
	private String panNo;
	private String passport;
	private String result="";
	private String searchCandName="";
	private String searchCandId="";
	private String expMonth="";
	private String expYear="";
	private String fromDate="";
	private String toDate="";
	private String candStatus="";
	private boolean mode=false;
	private String totRecord="";
	private String candFlag="false";
	
	private String searchFlag="false";
	private String pageFlag="false";
	private String viewEditFlag="false";
	private String fromCandidateScreening = "";
	
	//ADDED BY DHANASHREE
	private String candidateCompany;
	private String candidatePosition;
	private String currentLocation="";
	private String referedByEmp = "";
	private String noticePeriod = "";
	
	public String getViewEditFlag() {
		return viewEditFlag;
	}
	public void setViewEditFlag(String viewEditFlag) {
		this.viewEditFlag = viewEditFlag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	public String getRefFlag() {
		return refFlag;
	}
	public void setRefFlag(String refFlag) {
		this.refFlag = refFlag;
	}
	public String getIndsName() {
		return indsName;
	}
	public void setIndsName(String indsName) {
		this.indsName = indsName;
	}
	public ArrayList getQualList() {
		return qualList;
	}
	public void setQualList(ArrayList qualList) {
		this.qualList = qualList;
	}
	/**
	 * @return the loadList
	 */
	public ArrayList getLoadList() {
		return loadList;
	}
	/**
	 * @param loadList the loadList to set
	 */
	public void setLoadList(ArrayList loadList) {
		this.loadList = loadList;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the minExp
	 */
	public String getMinExp() {
		return minExp;
	}
	/**
	 * @param minExp the minExp to set
	 */
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}
	/**
	 * @return the addCheck
	 */
	public String getAddCheck() {
		return addCheck;
	}
	/**
	 * @param addCheck the addCheck to set
	 */
	public void setAddCheck(String addCheck) {
		this.addCheck = addCheck;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the resPhone
	 */
	public String getResPhone() {
		return resPhone;
	}
	/**
	 * @param resPhone the resPhone to set
	 */
	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the offPhone
	 */
	public String getOffPhone() {
		return offPhone;
	}
	/**
	 * @param offPhone the offPhone to set
	 */
	public void setOffPhone(String offPhone) {
		this.offPhone = offPhone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the doYou
	 */
	public String getDoYou() {
		return doYou;
	}
	/**
	 * @param doYou the doYou to set
	 */
	public void setDoYou(String doYou) {
		this.doYou = doYou;
	}
	/**
	 * @return the wheYouEmp
	 */
	public String getWheYouEmp() {
		return wheYouEmp;
	}
	/**
	 * @param wheYouEmp the wheYouEmp to set
	 */
	public void setWheYouEmp(String wheYouEmp) {
		this.wheYouEmp = wheYouEmp;
	}
	/**
	 * @return the relocate
	 */
	public String getRelocate() {
		return relocate;
	}
	/**
	 * @param relocate the relocate to set
	 */
	public void setRelocate(String relocate) {
		this.relocate = relocate;
	}
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the refName
	 */
	public String getRefName() {
		return refName;
	}
	/**
	 * @param refName the refName to set
	 */
	public void setRefName(String refName) {
		this.refName = refName;
	}
	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * @return the contactDet
	 */
	public String getContactDet() {
		return contactDet;
	}
	/**
	 * @param contactDet the contactDet to set
	 */
	public void setContactDet(String contactDet) {
		this.contactDet = contactDet;
	}
	/**
	 * @return the refComment
	 */
	public String getRefComment() {
		return refComment;
	}
	/**
	 * @param refComment the refComment to set
	 */
	public void setRefComment(String refComment) {
		this.refComment = refComment;
	}
	/**
	 * @return the listFlag
	 */
	public String getListFlag() {
		return listFlag;
	}
	/**
	 * @param listFlag the listFlag to set
	 */
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the candCode
	 */
	public String getCandCode() {
		return candCode;
	}
	/**
	 * @param candCode the candCode to set
	 */
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the postingDate
	 */
	public String getPostingDate() {
		return postingDate;
	}
	/**
	 * @param postingDate the postingDate to set
	 */
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	/**
	 * @return the shortStatus
	 */
	public String getShortStatus() {
		return shortStatus;
	}
	/**
	 * @param shortStatus the shortStatus to set
	 */
	public void setShortStatus(String shortStatus) {
		this.shortStatus = shortStatus;
	}
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getRefList() {
		return refList;
	}
	public void setRefList(ArrayList refList) {
		this.refList = refList;
	}
	public String getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}
	public String getCurrCTC() {
		return currCTC;
	}
	public void setCurrCTC(String currCTC) {
		this.currCTC = currCTC;
	}
	public String getExpCTC() {
		return expCTC;
	}
	public void setExpCTC(String expCTC) {
		this.expCTC = expCTC;
	}
	public String getRefEmpId() {
		return refEmpId;
	}
	public void setRefEmpId(String refEmpId) {
		this.refEmpId = refEmpId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getGenderView() {
		return genderView;
	}
	public void setGenderView(String genderView) {
		this.genderView = genderView;
	}
	public String getMaritalView() {
		return maritalView;
	}
	public void setMaritalView(String maritalView) {
		this.maritalView = maritalView;
	}
	public String getRefDtlCode() {
		return refDtlCode;
	}
	public void setRefDtlCode(String refDtlCode) {
		this.refDtlCode = refDtlCode;
	}
	public String getQualName() {
		return qualName;
	}
	public void setQualName(String qualName) {
		this.qualName = qualName;
	}
	public String getQualDtlId() {
		return qualDtlId;
	}
	public void setQualDtlId(String qualDtlId) {
		this.qualDtlId = qualDtlId;
	}
	public String getSpelDtlId() {
		return spelDtlId;
	}
	public void setSpelDtlId(String spelDtlId) {
		this.spelDtlId = spelDtlId;
	}
	public String getDeleteQuali() {
		return deleteQuali;
	}
	public void setDeleteQuali(String deleteQuali) {
		this.deleteQuali = deleteQuali;
	}
	public String getQualLevel() {
		return qualLevel;
	}
	public void setQualLevel(String qualLevel) {
		this.qualLevel = qualLevel;
	}
	public String getSpelName() {
		return spelName;
	}
	public void setSpelName(String spelName) {
		this.spelName = spelName;
	}
	public String getEstbName() {
		return estbName;
	}
	public void setEstbName(String estbName) {
		this.estbName = estbName;
	}
	public String getYearofPass() {
		return yearofPass;
	}
	public void setYearofPass(String yearofPass) {
		this.yearofPass = yearofPass;
	}
	public String getEmprName() {
		return emprName;
	}
	public void setEmprName(String emprName) {
		this.emprName = emprName;
	}
	public String getExpDtlId() {
		return expDtlId;
	}
	public void setExpDtlId(String expDtlId) {
		this.expDtlId = expDtlId;
	}
	public String getIndsId() {
		return indsId;
	}
	public void setIndsId(String indsId) {
		this.indsId = indsId;
	}
	public String getDeleteExp() {
		return deleteExp;
	}
	public void setDeleteExp(String deleteExp) {
		this.deleteExp = deleteExp;
	}
	public String getLastJobPro() {
		return lastJobPro;
	}
	public void setLastJobPro(String lastJobPro) {
		this.lastJobPro = lastJobPro;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getRelvDate() {
		return relvDate;
	}
	public void setRelvDate(String relvDate) {
		this.relvDate = relvDate;
	}
	public String getSpecAch() {
		return specAch;
	}
	public void setSpecAch(String specAch) {
		this.specAch = specAch;
	}
	public String getCtcExp() {
		return ctcExp;
	}
	public void setCtcExp(String ctcExp) {
		this.ctcExp = ctcExp;
	}
	public String getSkillDtlId() {
		return skillDtlId;
	}
	public void setSkillDtlId(String skillDtlId) {
		this.skillDtlId = skillDtlId;
	}
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getDeleteSkill() {
		return deleteSkill;
	}
	public void setDeleteSkill(String deleteSkill) {
		this.deleteSkill = deleteSkill;
	}
	public String getSkillExp() {
		return skillExp;
	}
	public void setSkillExp(String skillExp) {
		this.skillExp = skillExp;
	}
	public ArrayList getSkillList() {
		return skillList;
	}
	public void setSkillList(ArrayList skillList) {
		this.skillList = skillList;
	}
	public ArrayList getExpList() {
		return expList;
	}
	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}
	public String getFuncDtlId() {
		return funcDtlId;
	}
	public void setFuncDtlId(String funcDtlId) {
		this.funcDtlId = funcDtlId;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getDeleteFunc() {
		return deleteFunc;
	}
	public void setDeleteFunc(String deleteFunc) {
		this.deleteFunc = deleteFunc;
	}
	public String getFuncExp() {
		return funcExp;
	}
	public void setFuncExp(String funcExp) {
		this.funcExp = funcExp;
	}
	public ArrayList getFuncList() {
		return funcList;
	}
	public void setFuncList(ArrayList funcList) {
		this.funcList = funcList;
	}
	public String getPercMarks() {
		return percMarks;
	}
	public void setPercMarks(String percMarks) {
		this.percMarks = percMarks;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getCountry1() {
		return country1;
	}
	public void setCountry1(String country1) {
		this.country1 = country1;
	}
	public String getPincode1() {
		return pincode1;
	}
	public void setPincode1(String pincode1) {
		this.pincode1 = pincode1;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityCode1() {
		return cityCode1;
	}
	public void setCityCode1(String cityCode1) {
		this.cityCode1 = cityCode1;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getQualDetCode() {
		return qualDetCode;
	}
	public void setQualDetCode(String qualDetCode) {
		this.qualDetCode = qualDetCode;
	}
	public String getChkExpAll() {
		return chkExpAll;
	}
	public void setChkExpAll(String chkExpAll) {
		this.chkExpAll = chkExpAll;
	}
	public String getChkSkillAll() {
		return chkSkillAll;
	}
	public void setChkSkillAll(String chkSkillAll) {
		this.chkSkillAll = chkSkillAll;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncChkAll() {
		return funcChkAll;
	}
	public void setFuncChkAll(String funcChkAll) {
		this.funcChkAll = funcChkAll;
	}
	public String getFuncChk() {
		return funcChk;
	}
	public void setFuncChk(String funcChk) {
		this.funcChk = funcChk;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateCode1() {
		return stateCode1;
	}
	public void setStateCode1(String stateCode1) {
		this.stateCode1 = stateCode1;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryCode1() {
		return countryCode1;
	}
	public void setCountryCode1(String countryCode1) {
		this.countryCode1 = countryCode1;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getUpdateFirst() {
		return updateFirst;
	}
	public void setUpdateFirst(String updateFirst) {
		this.updateFirst = updateFirst;
	}
	public String getUpdateSecond() {
		return updateSecond;
	}
	public void setUpdateSecond(String updateSecond) {
		this.updateSecond = updateSecond;
	}
	public String getCancelFirst() {
		return cancelFirst;
	}
	public void setCancelFirst(String cancelFirst) {
		this.cancelFirst = cancelFirst;
	}
	public String getCancelSecond() {
		return cancelSecond;
	}
	public void setCancelSecond(String cancelSecond) {
		this.cancelSecond = cancelSecond;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getRadioFlag() {
		return radioFlag;
	}
	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}
	public String getRadioFlag1() {
		return radioFlag1;
	}
	public void setRadioFlag1(String radioFlag1) {
		this.radioFlag1 = radioFlag1;
	}
	public String getRadioFlag2() {
		return radioFlag2;
	}
	public void setRadioFlag2(String radioFlag2) {
		this.radioFlag2 = radioFlag2;
	}
	public String getUploadPhoto() {
		return uploadPhoto;
	}
	public void setUploadPhoto(String uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}
	public String getPathPhoto() {
		return pathPhoto;
	}
	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}
	public String getRefEmpTok() {
		return refEmpTok;
	}
	public void setRefEmpTok(String refEmpTok) {
		this.refEmpTok = refEmpTok;
	}
	public String getRefEmpName() {
		return refEmpName;
	}
	public void setRefEmpName(String refEmpName) {
		this.refEmpName = refEmpName;
	}
	public String getRadioEmp() {
		return radioEmp;
	}
	public void setRadioEmp(String radioEmp) {
		this.radioEmp = radioEmp;
	}
	public String getMarriageView() {
		return marriageView;
	}
	public void setMarriageView(String marriageView) {
		this.marriageView = marriageView;
	}
	public String getCurrentCtc() {
		return currentCtc;
	}
	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}
	public String getExpectedCtc() {
		return expectedCtc;
	}
	public void setExpectedCtc(String expectedCtc) {
		this.expectedCtc = expectedCtc;
	}
	public String getExpView() {
		return expView;
	}
	public void setExpView(String expView) {
		this.expView = expView;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getHiddenCandCode() {
		return hiddenCandCode;
	}
	public void setHiddenCandCode(String hiddenCandCode) {
		this.hiddenCandCode = hiddenCandCode;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	public String getQua() {
		return qua;
	}
	public void setQua(String qua) {
		this.qua = qua;
	}
	public String getQualiFlag() {
		return qualiFlag;
	}
	public void setQualiFlag(String qualiFlag) {
		this.qualiFlag = qualiFlag;
	}
	public String getExpFlag() {
		return expFlag;
	}
	public void setExpFlag(String expFlag) {
		this.expFlag = expFlag;
	}
	public String getSkillFlag() {
		return skillFlag;
	}
	public void setSkillFlag(String skillFlag) {
		this.skillFlag = skillFlag;
	}
	public String getDomFlag() {
		return domFlag;
	}
	public void setDomFlag(String domFlag) {
		this.domFlag = domFlag;
	}
	
	public String getSearchCandName() {
		return searchCandName;
	}
	public void setSearchCandName(String searchCandName) {
		this.searchCandName = searchCandName;
	}
	public String getSearchCandId() {
		return searchCandId;
	}
	public void setSearchCandId(String searchCandId) {
		this.searchCandId = searchCandId;
	}
	public String getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	public String getExpYear() {
		return expYear;
	}
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCandStatus() {
		return candStatus;
	}
	public void setCandStatus(String candStatus) {
		this.candStatus = candStatus;
	}
	public boolean isMode() {
		return mode;
	}
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	public String getTotRecord() {
		return totRecord;
	}
	public void setTotRecord(String totRecord) {
		this.totRecord = totRecord;
	}
	public String getCandFlag() {
		return candFlag;
	}
	public void setCandFlag(String candFlag) {
		this.candFlag = candFlag;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	
	
	public String getDupChkFlag() {
		return dupChkFlag;
	}
	public void setDupChkFlag(String dupChkFlag) {
		this.dupChkFlag = dupChkFlag;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getResSource() {
		return resSource;
	}
	public void setResSource(String resSource) {
		this.resSource = resSource;
	}
	public String getResumeSrc() {
		return resumeSrc;
	}
	public void setResumeSrc(String resumeSrc) {
		this.resumeSrc = resumeSrc;
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getCandidateCompany() {
		return candidateCompany;
	}
	public void setCandidateCompany(String candidateCompany) {
		this.candidateCompany = candidateCompany;
	}
	public String getCandidatePosition() {
		return candidatePosition;
	}
	public void setCandidatePosition(String candidatePosition) {
		this.candidatePosition = candidatePosition;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getFromCandidateScreening() {
		return fromCandidateScreening;
	}
	public void setFromCandidateScreening(String fromCandidateScreening) {
		this.fromCandidateScreening = fromCandidateScreening;
	}
	public String getReferedByEmp() {
		return referedByEmp;
	}
	public void setReferedByEmp(String referedByEmp) {
		this.referedByEmp = referedByEmp;
	}
	public String getRequisitionTitle() {
		return requisitionTitle;
	}
	public void setRequisitionTitle(String requisitionTitle) {
		this.requisitionTitle = requisitionTitle;
	}
	public String getRequisitionTitleCode() {
		return requisitionTitleCode;
	}
	public void setRequisitionTitleCode(String requisitionTitleCode) {
		this.requisitionTitleCode = requisitionTitleCode;
	}
	/**
	 * @return the noticePeriod
	 */
	public final String getNoticePeriod() {
		return this.noticePeriod;
	}
	/**
	 * @param noticePeriod the noticePeriod to set
	 */
	public final void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	
	
}