package org.paradyne.bean.admin.srd;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

import com.businessobjects.reports.crprompting.CRPromptValue.Str;

/**
 * @author lakkichand
 * @date 03 May 2007
 */

/** Updated By
 * @author Prajakta.Bhandare
 * @date 12 Dec 2012
 */
public class PersonnelDetail extends BeanBase {

	private String employeeId = "";
	private String desc = "";
	private String isHandiCap = "";
	private String bloodGroup = "";
	private String maritalStatus = "";
	private String religion = "";
	private String religionCode = "";
	private String castName = "";
	private String castCode = "";
	private String castCategory = "";
	private String castCategoryCode = "";
	private String subCast = "";
	private String handicapDesc = "";
	private String nationality = "";
	private String height = "";
	private String weight = "";
	private String markId = "";
	private String homeTown = "";
	private String homeTownCode = "";
	private String marriageDate = "";
	private String hobbies = "";
	private String ailments=""; 
	private String allergies="";
	private String diseases="";
	private String isConvicted="";
	private String criminalActs=""; 
	private String empName=""; 
	private String profileEmpName=""; 
	private String uploadFileName="";
	private String abbrHobbies="";
	private String abbrCriminalActs="";
	private String abbrAilments="";
	private String abbrAllergies="";
	private String abbrDiseases="";
	private String abbrMarkId="";
	private String abbrDesc="";
	private String noData="";
	TreeMap assetmap;
	TreeMap bgmap;
	private String flag;
	//updated by Anantha Lakshmi
	
	ArrayList langList;
	ArrayList List;
	private String modeLength="false";
	private String langCode="";
	private String langType="";
	private String readLang="";
	private String writeLang="";
	private String speakLang="";
	private String motherLang="";
	private String checkTabFlag="";
	private String chkTest = "";
	
	private String hiddenItLangType="";
	private String hiddenItReadLang="";
	private String hiddenItWriteLang="";
	private String hiddenItSpeakLang="";
	private String hiddenItMotherLang="";
	private boolean editFlag=false;
	
	private String langName="";
	private String languageCode="";
	private boolean readFlag=false;
	private boolean writeFlag=false; 
	private boolean speakFlag=false;
	private boolean motherFlag=false;
	private String paraId="";
	private String langEditFlag="";
	private String languageName="";
	private String isNotGeneralUser="false";
	
	public PersonnelDetail() {
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the isHandiCap
	 */
	public String getIsHandiCap() {
		return isHandiCap;
	}
	/**
	 * @param isHandiCap
	 *            the isHandiCap to set
	 */
	public void setIsHandiCap(String isHandiCap) {
		this.isHandiCap = isHandiCap;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup
	 *            the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion
	 *            the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the religionCode
	 */
	public String getReligionCode() {
		return religionCode;
	}

	/**
	 * @param religionCode
	 *            the religionCode to set
	 */
	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	/**
	 * @return the castName
	 */
	public String getCastName() {
		return castName;
	}

	/**
	 * @param castName
	 *            the castName to set
	 */
	public void setCastName(String castName) {
		this.castName = castName;
	}

	/**
	 * @return the castCode
	 */
	public String getCastCode() {
		return castCode;
	}

	/**
	 * @param castCode
	 *            the castCode to set
	 */
	public void setCastCode(String castCode) {
		this.castCode = castCode;
	}

	/**
	 * @return the castCategory
	 */
	public String getCastCategory() {
		return castCategory;
	}

	/**
	 * @param castCategory
	 *            the castCategory to set
	 */
	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	/**
	 * @return the castCategoryCode
	 */
	public String getCastCategoryCode() {
		return castCategoryCode;
	}

	/**
	 * @param castCategoryCode
	 *            the castCategoryCode to set
	 */
	public void setCastCategoryCode(String castCategoryCode) {
		this.castCategoryCode = castCategoryCode;
	}

	/**
	 * @return the subCast
	 */
	public String getSubCast() {
		return subCast;
	}

	/**
	 * @param subCast the subCast to set
	 */
	public void setSubCast(String subCast) {
		this.subCast = subCast;
	}

	/**
	 * @return the handicapDesc
	 */
	public String getHandicapDesc() {
		return handicapDesc;
	}

	/**
	 * @param handicapDesc
	 *            the handicapDesc to set
	 */
	public void setHandicapDesc(String handicapDesc) {
		this.handicapDesc = handicapDesc;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the markId
	 */
	public String getMarkId() {
		return markId;
	}

	/**
	 * @param markId
	 *            the markId to set
	 */
	public void setMarkId(String markId) {
		this.markId = markId;
	}

	/**
	 * @return the homeTown
	 */
	public String getHomeTown() {
		return homeTown;
	}

	/**
	 * @param homeTown
	 *            the homeTown to set
	 */
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	/**
	 * @return the homeTownCode
	 */
	public String getHomeTownCode() {
		return homeTownCode;
	}

	/**
	 * @param homeTownCode
	 *            the homeTownCode to set
	 */
	public void setHomeTownCode(String homeTownCode) {
		this.homeTownCode = homeTownCode;
	}

	/**
	 * @return the marriageDate
	 */
	public String getMarriageDate() {
		return marriageDate;
	}

	/**
	 * @param marriageDate
	 *            the marriageDate to set
	 */
	public void setMarriageDate(String marriageDate) {
		this.marriageDate = marriageDate;
	}

	/**
	 * @return the hobbies
	 */
	public String getHobbies() {
		return hobbies;
	}

	/**
	 * @param hobbies
	 *            the hobbies to set
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	/**
	 * @return the assetmap
	 */
	public TreeMap getAssetmap() {
		return assetmap;
	}

	/**
	 * @param assetmap
	 *            the assetmap to set
	 */
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
	}

	/**
	 * @return the bgmap
	 */
	public TreeMap getBgmap() {
		return bgmap;
	}

	/**
	 * @param bgmap
	 *            the bgmap to set
	 */
	public void setBgmap(TreeMap bgmap) {
		this.bgmap = bgmap;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the ailments
	 */
	public String getAilments() {
		return ailments;
	}

	/**
	 * @param ailments the ailments to set
	 */
	public void setAilments(String ailments) {
		this.ailments = ailments;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the diseases
	 */
	public String getDiseases() {
		return diseases;
	}

	/**
	 * @param diseases the diseases to set
	 */
	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	/**
	 * @return the isconvicted
	 */
	public String getIsConvicted() {
		return isConvicted;
	}

	/**
	 * @param isconvicted the isconvicted to set
	 */
	public void setIsConvicted(String isconvicted) {
		this.isConvicted = isconvicted;
	}

	/**
	 * @return the criminalActs
	 */
	public String getCriminalActs() {
		return criminalActs;
	}

	/**
	 * @param criminalActs the criminalActs to set
	 */
	public void setCriminalActs(String criminalActs) {
		this.criminalActs = criminalActs;
	}
	
	/**
	 * @return the langList
	 */
	public ArrayList getLangList() {
		return langList;
	}

	/**
	 * @param langList the langList to set
	 */
	public void setLangList(ArrayList langList) {
		this.langList = langList;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return the langType
	 */
	public String getLangType() {
		return langType;
	}

	/**
	 * @param langType the langType to set
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}

	/**
	 * @return the readLang
	 */
	public String getReadLang() {
		return readLang;
	}

	/**
	 * @param readLang the readLang to set
	 */
	public void setReadLang(String readLang) {
		this.readLang = readLang;
	}

	/**
	 * @return the writeLang
	 */
	public String getWriteLang() {
		return writeLang;
	}

	/**
	 * @param writeLang the writeLang to set
	 */
	public void setWriteLang(String writeLang) {
		this.writeLang = writeLang;
	}

	/**
	 * @return the speakLang
	 */
	public String getSpeakLang() {
		return speakLang;
	}

	/**
	 * @param speakLang the speakLang to set
	 */
	public void setSpeakLang(String speakLang) {
		this.speakLang = speakLang;
	}

	/**
	 * @return motherLang
	 */
	public String getMotherLang() {
		return motherLang;
	}

	/**
	 * @param motherLang the motherLang to set
	 */
	public void setMotherLang(String motherLang) {
		this.motherLang = motherLang;
	}

	/**
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	/**
	 * @return the checkTabFlag
	 */
	public String getCheckTabFlag() {
		return checkTabFlag;
	}

	/**
	 * @param checkTabFlag the checkTabFlag to set
	 */
	public void setCheckTabFlag(String checkTabFlag) {
		this.checkTabFlag = checkTabFlag;
	}

	public String getChkTest() {
		return chkTest;
	}

	public void setChkTest(String chkTest) {
		this.chkTest = chkTest;
	}

	public String getHiddenItLangType() {
		return hiddenItLangType;
	}

	public void setHiddenItLangType(String hiddenItLangType) {
		this.hiddenItLangType = hiddenItLangType;
	}

	public String getHiddenItReadLang() {
		return hiddenItReadLang;
	}

	public void setHiddenItReadLang(String hiddenItReadLang) {
		this.hiddenItReadLang = hiddenItReadLang;
	}

	public String getHiddenItWriteLang() {
		return hiddenItWriteLang;
	}

	public void setHiddenItWriteLang(String hiddenItWriteLang) {
		this.hiddenItWriteLang = hiddenItWriteLang;
	}

	public String getHiddenItSpeakLang() {
		return hiddenItSpeakLang;
	}

	public void setHiddenItSpeakLang(String hiddenItSpeakLang) {
		this.hiddenItSpeakLang = hiddenItSpeakLang;
	}

	public String getHiddenItMotherLang() {
		return hiddenItMotherLang;
	}

	public void setHiddenItMotherLang(String hiddenItMotherLang) {
		this.hiddenItMotherLang = hiddenItMotherLang;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	public boolean isWriteFlag() {
		return writeFlag;
	}

	public void setWriteFlag(boolean writeFlag) {
		this.writeFlag = writeFlag;
	}
	
	/**
	 * @return the speakFlag
	 */
	public boolean isSpeakFlag() {
		return speakFlag;
	}

	/**
	 * @param speakFlag the speakFlag to set
	 */
	public void setSpeakFlag(boolean speakFlag) {
		this.speakFlag = speakFlag;
	}

	/**
	 * @return the motherFlag
	 */
	public boolean isMotherFlag() {
		return motherFlag;
	}

	/**
	 * @param motherFlag the motherFlag to set
	 */
	public void setMotherFlag(boolean motherFlag) {
		this.motherFlag = motherFlag;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}

	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the langEditFlag
	 */
	public String getLangEditFlag() {
		return langEditFlag;
	}

	/**
	 * @param langEditFlag the langEditFlag to set
	 */
	public void setLangEditFlag(String langEditFlag) {
		this.langEditFlag = langEditFlag;
	}

	/**
	 * @return the languageName
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * @param languageName the languageName to set
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * @return the isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	/**
	 * @param isNotGeneralUser the isNotGeneralUser to set
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}

	/**
	 * @param profileEmpName the profileEmpName to set
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the abbrHobbies
	 */
	public String getAbbrHobbies() {
		return abbrHobbies;
	}

	/**
	 * @param abbrHobbies the abbrHobbies to set
	 */
	public void setAbbrHobbies(String abbrHobbies) {
		this.abbrHobbies = abbrHobbies;
	}

	/**
	 * @return the abbrCriminalActs
	 */
	public String getAbbrCriminalActs() {
		return abbrCriminalActs;
	}

	/**
	 * @param abbrCriminalActs the abbrCriminalActs to set
	 */
	public void setAbbrCriminalActs(String abbrCriminalActs) {
		this.abbrCriminalActs = abbrCriminalActs;
	}

	/**
	 * @return abbrAilments
	 */
	public String getAbbrAilments() {
		return abbrAilments;
	}

	/**
	 * @param abbrAilments the abbrAilments to set
	 */
	public void setAbbrAilments(String abbrAilments) {
		this.abbrAilments = abbrAilments;
	}

	/**
	 * @return abbrAllergies
	 */
	public String getAbbrAllergies() {
		return abbrAllergies;
	}

	/**
	 * @param abbrAllergies the abbrAllergies to set
	 */
	public void setAbbrAllergies(String abbrAllergies) {
		this.abbrAllergies = abbrAllergies;
	}

	/**
	 * @return the abbrDiseases
	 */
	public String getAbbrDiseases() {
		return abbrDiseases;
	}

	/**
	 * @param abbrDiseases the abbrDiseases to set
	 */
	public void setAbbrDiseases(String abbrDiseases) {
		this.abbrDiseases = abbrDiseases;
	}

	public String getAbbrMarkId() {
		return abbrMarkId;
	}

	public void setAbbrMarkId(String abbrMarkId) {
		this.abbrMarkId = abbrMarkId;
	}

	public String getAbbrDesc() {
		return abbrDesc;
	}

	public void setAbbrDesc(String abbrDesc) {
		this.abbrDesc = abbrDesc;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public ArrayList getList() {
		return List;
	}

	public void setList(ArrayList list) {
		List = list;
	}

	
}
