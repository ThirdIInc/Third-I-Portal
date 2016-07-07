package org.paradyne.bean.Recruitment;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:24-06-2009
 *
 */
public class CandProfile extends BeanBase {
	private String spl="";
	private String qua="";
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
	private String candCode="";
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
	
	private String dupFlag="false";
	private String emailFlag="false";
	private String dobFlag="false";
	private String mobileFlag="false";
	private String passportFlag="false";
	private String pannoFlag="false";
	
	private String panNo;
	private String passport;
	private String duplicateFlag="false";
	private String result="";
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
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getExpView() {
		return expView;
	}
	public void setExpView(String expView) {
		this.expView = expView;
	}
	public String getMarriageView() {
		return marriageView;
	}
	public void setMarriageView(String marriageView) {
		this.marriageView = marriageView;
	}
	public String getRadioEmp() {
		return radioEmp;
	}
	public void setRadioEmp(String radioEmp) {
		this.radioEmp = radioEmp;
	}
	public String getPathPhoto() {
		return pathPhoto;
	}
	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}
	public String getUploadPhoto() {
		return uploadPhoto;
	}
	public void setUploadPhoto(String uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
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
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getCancelSecond() {
		return cancelSecond;
	}
	public void setCancelSecond(String cancelSecond) {
		this.cancelSecond = cancelSecond;
	}
	public String getCancelFirst() {
		return cancelFirst;
	}
	public void setCancelFirst(String cancelFirst) {
		this.cancelFirst = cancelFirst;
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
	public String getChkExpAll() {
		return chkExpAll;
	}
	public void setChkExpAll(String chkExpAll) {
		this.chkExpAll = chkExpAll;
	}
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getCandCode() {
		return candCode;
	}
	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMinExp() {
		return minExp;
	}
	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}
	public String getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}
	public String getAddCheck() {
		return addCheck;
	}
	public void setAddCheck(String addCheck) {
		this.addCheck = addCheck;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	public String getResPhone() {
		return resPhone;
	}
	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOffPhone() {
		return offPhone;
	}
	public void setOffPhone(String offPhone) {
		this.offPhone = offPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDoYou() {
		return doYou;
	}
	public void setDoYou(String doYou) {
		this.doYou = doYou;
	}
	public String getWheYouEmp() {
		return wheYouEmp;
	}
	public void setWheYouEmp(String wheYouEmp) {
		this.wheYouEmp = wheYouEmp;
	}
	public String getRelocate() {
		return relocate;
	}
	public void setRelocate(String relocate) {
		this.relocate = relocate;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getRefEmpId() {
		return refEmpId;
	}
	public void setRefEmpId(String refEmpId) {
		this.refEmpId = refEmpId;
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
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getContactDet() {
		return contactDet;
	}
	public void setContactDet(String contactDet) {
		this.contactDet = contactDet;
	}
	public String getRefComment() {
		return refComment;
	}
	public void setRefComment(String refComment) {
		this.refComment = refComment;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
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
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getRefDtlCode() {
		return refDtlCode;
	}
	public void setRefDtlCode(String refDtlCode) {
		this.refDtlCode = refDtlCode;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getShortStatus() {
		return shortStatus;
	}
	public void setShortStatus(String shortStatus) {
		this.shortStatus = shortStatus;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
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
	public String getPercMarks() {
		return percMarks;
	}
	public void setPercMarks(String percMarks) {
		this.percMarks = percMarks;
	}
	public String getQualDetCode() {
		return qualDetCode;
	}
	public void setQualDetCode(String qualDetCode) {
		this.qualDetCode = qualDetCode;
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
	public ArrayList getLoadList() {
		return loadList;
	}
	public void setLoadList(ArrayList loadList) {
		this.loadList = loadList;
	}
	public ArrayList getRefList() {
		return refList;
	}
	public void setRefList(ArrayList refList) {
		this.refList = refList;
	}
	public ArrayList getQualList() {
		return qualList;
	}
	public void setQualList(ArrayList qualList) {
		this.qualList = qualList;
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
	public ArrayList getFuncList() {
		return funcList;
	}
	public void setFuncList(ArrayList funcList) {
		this.funcList = funcList;
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
	public String getDupFlag() {
		return dupFlag;
	}
	public void setDupFlag(String dupFlag) {
		this.dupFlag = dupFlag;
	}
	public String getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getDobFlag() {
		return dobFlag;
	}
	public void setDobFlag(String dobFlag) {
		this.dobFlag = dobFlag;
	}
	public String getMobileFlag() {
		return mobileFlag;
	}
	public void setMobileFlag(String mobileFlag) {
		this.mobileFlag = mobileFlag;
	}
	public String getPassportFlag() {
		return passportFlag;
	}
	public void setPassportFlag(String passportFlag) {
		this.passportFlag = passportFlag;
	}
	public String getPannoFlag() {
		return pannoFlag;
	}
	public void setPannoFlag(String pannoFlag) {
		this.pannoFlag = pannoFlag;
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
	public String getDuplicateFlag() {
		return duplicateFlag;
	}
	public void setDuplicateFlag(String duplicateFlag) {
		this.duplicateFlag = duplicateFlag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
