package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.paradyne.lib.BeanBase;

public class FamilyDetails extends BeanBase implements Serializable {

	String NoImage = "false";
	String familyCode = "";
	String empToken = "";
	String empID = "";
	String empName = "";
	String deptName = "";
	String centerName = "";
	String rankName = "";
	String firstName = "";
	String lastName = "";
	String middleName = "";
	String relCode = "";
	String relName = "";
	String maritalStatus = "";
	String sex = "";
	String phone = "";
	String profession = "";
	String email = "";
	String identification = "";
	String birthday = "";
	String address = "";
	String paracode = "";
	ArrayList famList = null;
	String alive = "";
	String employmentSts="";
	String dependent = "";
	String otherInfo = "";
	String spouse_photo = "";
	String rankNo;
	String design;
	String uploadFileName = "";
	String flag = "false";
	String isNotGeneralUser = "";
	String photoUploadFileName = "";
	String profileEmpName = "";
	String noData = "false";
	TreeMap assetmap;
	TreeMap marriagemap;
	String abbrAddress="";
	String abbrProfession="";
	String abbrIdentification="";
	String abbrEmail="";
	String abbrOtherInfo="";
	private boolean editFlag=false;
	private boolean editDetail=false;
	
	
	public String getAbbrAddress() {
		return abbrAddress;
	}

      
	public void setAbbrAddress(String abbrAddress) {
		this.abbrAddress = abbrAddress;
	}


	public String getAbbrProfession() {
		return abbrProfession;
	}


	public void setAbbrProfession(String abbrProfession) {
		this.abbrProfession = abbrProfession;
	}


	public String getAbbrIdentification() {
		return abbrIdentification;
	}


	public void setAbbrIdentification(String abbrIdentification) {
		this.abbrIdentification = abbrIdentification;
	}


	public String getAbbrEmail() {
		return abbrEmail;
	}


	public void setAbbrEmail(String abbrEmail) {
		this.abbrEmail = abbrEmail;
	}


	public String getAbbrOtherInfo() {
		return abbrOtherInfo;
	}


	public void setAbbrOtherInfo(String abbrOtherInfo) {
		this.abbrOtherInfo = abbrOtherInfo;
	}


	/**
	 * Constructor for FamilyDetails
	 * 
	 * @param empToken
	 * @param empID
	 * @param empName
	 * @param deptName
	 * @param centerName
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param relCode
	 * @param relName
	 * @param maritalStatus
	 * @param sex
	 * @param phone
	 * @param profession
	 * @param email
	 * @param identification
	 * @param birthday
	 * @param address
	 */
	public FamilyDetails(String empToken, String empID, String empName,
			String deptName, String centerName, String firstName,
			String lastName, String middleName, String relCode, String relName,
			String maritalStatus, String sex, String phone, String profession,
			String email, String identification, String birthday,
			String address, String familyCode, String dependent,
			String otherInfo, String paracode, String spouse_photo) {

		this.empToken = empToken;
		this.empID = empID;
		this.empName = empName;
		this.deptName = deptName;
		this.centerName = centerName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.relCode = relCode;
		this.relName = relName;
		this.maritalStatus = maritalStatus;
		this.sex = sex;
		this.phone = phone;
		this.profession = profession;
		this.email = email;
		this.identification = identification;
		this.birthday = birthday;
		this.address = address;
		this.familyCode = familyCode;
		this.dependent = dependent;
		this.otherInfo = otherInfo;
		this.paracode = paracode;
	    this.spouse_photo = spouse_photo;
	}
	

	public FamilyDetails() {

	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * * @param address
	 *  to set the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *         to set the birthday 
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *         to set   the email 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *         to set the empID 
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *           to set the empToken 
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *          to set  the firstName 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification
	 *           to set the identification 
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *           to set the lastName 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *           to set the maritalStatus 
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *          to set  the middleName 
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *          to set  the phone 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession
	 *         to set   the profession 
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the relCode
	 */
	public String getRelCode() {
		return relCode;
	}

	/**
	 * @param relCode
	 *         to set   the relCode 
	 */
	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}

	/**
	 * @return the relName
	 */
	public String getRelName() {
		return relName;
	}

	/**
	 * @param relName
	 *          to set  the relName 
	 */
	public void setRelName(String relName) {
		this.relName = relName;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *          to set  the sex 
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *           to set the centerName 
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *          to set  the deptName 
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *          to set  the empName 
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the familyCode
	 */
	public String getFamilyCode() {
		return familyCode;
	}

	/**
	 * @param familyCode
	 *          to set  the familyCode 
	 */
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	/**
	 * @return the famList
	 */
	public ArrayList getFamList() {
		return famList;
	}

	/**
	 * @param famList
	 *           to set  the famList
	 */
	public void setFamList(ArrayList famList) {
		this.famList = famList;
	}

	/**
	 * @param paracode
	 *           to set the paracode 
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}

	/**
	 * @return the alive
	 */
	public String getAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *           to set the alive 
	 */
	public void setAlive(String alive) {
		this.alive = alive;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName
	 *          to set  the rankName 
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the dependent
	 */
	public String getDependent() {
		return dependent;
	}

	/**
	 * @param dependent
	 *          to set  the dependent 
	 */
	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	/**
	 * @return the otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}

	/**
	 * @param otherInfo
	 *           to set the otherInfo 
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	/**
	 * @return the rankNo
	 */
	public String getRankNo() {
		return rankNo;
	}

	/**
	 * @param rankNo
	 *           to set the rankNo 
	 */
	public void setRankNo(String rankNo) {
		this.rankNo = rankNo;
	}

	/**
	 * @return the design
	 */
	public String getDesign() {
		return design;
	}

	/**
	 * @param design
	 *         to set  the design 
	 */
	public void setDesign(String design) {
		this.design = design;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *           to set the uploadFileName 
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
    /**
     * @return employmentSts
     */
    public String getEmploymentSts() {
		return employmentSts;
	}
   /**
	 * @param employmentSts
	 *           to set the employmentSts 
	 */
	public void setEmploymentSts(String employmentSts) {
		this.employmentSts = employmentSts;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *           to set the flag 
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the assetmap
	 */
	public TreeMap getAssetmap() {
		return assetmap;
	}

	/**
	 * @param assetmap
	 *          to set  the assetmap 
	 */
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
	}

	/**
	 * @return the marriagemap
	 */
	public TreeMap getMarriagemap() {
		return marriagemap;
	}

	/**
	 * @param marriagemap
	 *           to set  the marriagemap
	 */
	public void setMarriagemap(TreeMap marriagemap) {
		this.marriagemap = marriagemap;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}


	public String getSpouse_photo() {
		return spouse_photo;
	}


	public void setSpouse_photo(String spouse_photo) {
		this.spouse_photo = spouse_photo;
	}


	public String getNoImage() {
		return NoImage;
	}


	public void setNoImage(String noImage) {
		NoImage = noImage;
	}


	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}


	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}


	public String getPhotoUploadFileName() {
		return photoUploadFileName;
	}


	public void setPhotoUploadFileName(String photoUploadFileName) {
		this.photoUploadFileName = photoUploadFileName;
	}


	public String getNoData() {
		return noData;
	}


	public void setNoData(String noData) {
		this.noData = noData;
	}


	public String getProfileEmpName() {
		return profileEmpName;
	}


	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}


	public boolean isEditDetail() {
		return editDetail;
	}


	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

}