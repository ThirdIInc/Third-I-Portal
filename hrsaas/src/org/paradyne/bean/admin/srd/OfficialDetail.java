package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * 
 * Bean class for Official Details
 * 
 */

public class OfficialDetail extends BeanBase implements Serializable {

	String empToken = "";
	String empCode = "";
	String title = "";
	String titleName = "";
	String firstName = "";
	String lastName = "";
	String middleName = "";

	String gender = "";
	String genderProperty="";
	String statusProperty="";
	String birthDate = "";
	String deptCode = "";
	String deptName = "";
	String desgCode = "";
	
	
	
	String roleName = "";

	String desgName = "";
	String rankCode = "";
	String divCode = "";
	String rankName = "";
	String centerCode = "";
	String centerName = "";

	String tradeCode = "";
	String tradeName = "";
	String typeCode = "";
	String typeName = "";
	String disciplineCode = "";
	String disciplineName = "";

	String cadreCode = "";
	String cadreName = "";
	String shiftCode = "";
	String shiftType = "";
	String status = "";
	String regularDate = "";
	String leaveDate = "";
	String divName = "";
	String reportingTo = "";
	String reportingID = "";
	String bornFlag = "";
	String reportingToken = "";
	String uploadFileName = "";
	String payBillId = "";
	String payBillName = "";
	String grade = "";
	String seniDate = "";
	String lastIncrDt = "";
	String flag = "false";
	String serviceTenureValue="";
	// debjani
	TreeMap assetmap;
	TreeMap gendermap;
	TreeMap statusmap;
	String empName="";
	String ProfileEmpName="";
	
	//updated by Anantha lakshmi
	private boolean editFlag=false;
	private  boolean editDetail=false;
	private String isNotGeneralUser="false";
	private boolean newFlag=false;// added by prajakta for add function
	// -------by shashikant
	private ArrayList showTab;
	String hrmName;
	
	//----by vishwambhar
	private String confirmDate="";
	private String groupjoinDate ="";
	
	
	public String getGroupjoinDate() {
		return groupjoinDate;
	}

	public void setGroupjoinDate(String groupjoinDate) {
		this.groupjoinDate = groupjoinDate;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public OfficialDetail() {
	}

	public OfficialDetail(String empID, String title, String firstName,
			String lastName, String middleName, String gender,
			String birthDate, String deptCode, String deptName,
			String desgCode, String desgName, String rankCode, String rankName,
			String centerCode, String centerName, String tradeCode,
			String tradeName, String typeCode, String typeName,
			String disciplineCode, String disciplineName, String cadreCode,
			String cadreName, String shiftCode, String shiftType,
			String status, String regularDate, String empToken,String confirmDate) {

		this.empCode = empID;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.rankCode = rankCode;
		this.rankName = rankName;
		this.centerCode = centerCode;
		this.centerName = centerName;
		this.tradeCode = tradeCode;
		this.tradeName = tradeName;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.disciplineCode = disciplineCode;
		this.disciplineName = disciplineName;
		this.cadreCode = cadreCode;
		this.cadreName = cadreName;
		this.shiftCode = shiftCode;
		this.shiftType = shiftType;
		this.status = status;
		this.regularDate = regularDate;
		this.empToken = empToken;
		this.confirmDate=confirmDate;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName
	 *            the titleName to set
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the desgCode
	 */
	public String getDesgCode() {
		return desgCode;
	}

	/**
	 * @param desgCode
	 *            the desgCode to set
	 */
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	/**
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}

	/**
	 * @param desgName
	 *            the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	/**
	 * @return the rankCode
	 */
	public String getRankCode() {
		return rankCode;
	}

	/**
	 * @param rankCode
	 *            the rankCode to set
	 */
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode
	 *            the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName
	 *            the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the centerCode
	 */
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * @param centerCode
	 *            the centerCode to set
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *            the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the tradeCode
	 */
	public String getTradeCode() {
		return tradeCode;
	}

	/**
	 * @param tradeCode
	 *            the tradeCode to set
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	/**
	 * @return the tradeName
	 */
	public String getTradeName() {
		return tradeName;
	}

	/**
	 * @param tradeName
	 *            the tradeName to set
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the disciplineCode
	 */
	public String getDisciplineCode() {
		return disciplineCode;
	}

	/**
	 * @param disciplineCode
	 *            the disciplineCode to set
	 */
	public void setDisciplineCode(String disciplineCode) {
		this.disciplineCode = disciplineCode;
	}

	/**
	 * @return the disciplineName
	 */
	public String getDisciplineName() {
		return disciplineName;
	}

	/**
	 * @param disciplineName
	 *            the disciplineName to set
	 */
	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}

	/**
	 * @return the cadreCode
	 */
	public String getCadreCode() {
		return cadreCode;
	}

	/**
	 * @param cadreCode
	 *            the cadreCode to set
	 */
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}

	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}

	/**
	 * @param cadreName
	 *            the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}

	/**
	 * @return the shiftCode
	 */
	public String getShiftCode() {
		return shiftCode;
	}

	/**
	 * @param shiftCode
	 *            the shiftCode to set
	 */
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	/**
	 * @return the shiftType
	 */
	public String getShiftType() {
		return shiftType;
	}

	/**
	 * @param shiftType
	 *            the shiftType to set
	 */
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the regularDate
	 */
	public String getRegularDate() {
		return regularDate;
	}

	/**
	 * @param regularDate
	 *            the regularDate to set
	 */
	public void setRegularDate(String regularDate) {
		this.regularDate = regularDate;
	}

	/**
	 * @return the leaveDate
	 */
	public String getLeaveDate() {
		return leaveDate;
	}

	/**
	 * @param leaveDate
	 *            the leaveDate to set
	 */
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}

	/**
	 * @param divName
	 *            the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}

	/**
	 * @return the reportingTo
	 */
	public String getReportingTo() {
		return reportingTo;
	}

	/**
	 * @param reportingTo
	 *            the reportingTo to set
	 */
	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	/**
	 * @return the reportingID
	 */
	public String getReportingID() {
		return reportingID;
	}

	/**
	 * @param reportingID
	 *            the reportingID to set
	 */
	public void setReportingID(String reportingID) {
		this.reportingID = reportingID;
	}

	/**
	 * @return the bornFlag
	 */
	public String getBornFlag() {
		return bornFlag;
	}

	/**
	 * @param bornFlag
	 *            the bornFlag to set
	 */
	public void setBornFlag(String bornFlag) {
		this.bornFlag = bornFlag;
	}

	/**
	 * @return the reportingToken
	 */
	public String getReportingToken() {
		return reportingToken;
	}

	/**
	 * @param reportingToken
	 *            the reportingToken to set
	 */
	public void setReportingToken(String reportingToken) {
		this.reportingToken = reportingToken;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the payBillId
	 */
	public String getPayBillId() {
		return payBillId;
	}

	/**
	 * @param payBillId
	 *            the payBillId to set
	 */
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}

	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}

	/**
	 * @param payBillName
	 *            the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the seniDate
	 */
	public String getSeniDate() {
		return seniDate;
	}

	/**
	 * @param seniDate
	 *            the seniDate to set
	 */
	public void setSeniDate(String seniDate) {
		this.seniDate = seniDate;
	}

	/**
	 * @return the lastIncrDt
	 */
	public String getLastIncrDt() {
		return lastIncrDt;
	}

	/**
	 * @param lastIncrDt
	 *            the lastIncrDt to set
	 */
	public void setLastIncrDt(String lastIncrDt) {
		this.lastIncrDt = lastIncrDt;
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
	 * @return the gendermap
	 */
	public TreeMap getGendermap() {
		return gendermap;
	}

	/**
	 * @param gendermap the gendermap to set
	 */
	public void setGendermap(TreeMap gendermap) {
		this.gendermap = gendermap;
	}

	/**
	 * @return the showTab
	 */
	public ArrayList getShowTab() {
		return showTab;
	}

	/**
	 * @param showTab
	 *            the showTab to set
	 */
	public void setShowTab(ArrayList showTab) {
		this.showTab = showTab;
	}

	/**
	 * @return the hrmName
	 */
	public String getHrmName() {
		return hrmName;
	}

	/**
	 * @param hrmName
	 *            the hrmName to set
	 */
	public void setHrmName(String hrmName) {
		this.hrmName = hrmName;
	}

	public String getServiceTenureValue() {
		return serviceTenureValue;
	}

	public void setServiceTenureValue(String serviceTenureValue) {
		this.serviceTenureValue = serviceTenureValue;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}



	public boolean isEditDetail() {
		return editDetail;
	}

	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getProfileEmpName() {
		return ProfileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		ProfileEmpName = profileEmpName;
	}

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}

	/**
	 * @return the genderProperty
	 */
	public String getGenderProperty() {
		return genderProperty;
	}

	/**
	 * @param genderProperty the genderProperty to set
	 */
	public void setGenderProperty(String genderProperty) {
		this.genderProperty = genderProperty;
	}

	/**
	 * @return the statusProperty
	 */
	public String getStatusProperty() {
		return statusProperty;
	}

	/**
	 * @param statusProperty the statusProperty to set
	 */
	public void setStatusProperty(String statusProperty) {
		this.statusProperty = statusProperty;
	}
	/**
	 * @return the statusmap
	 */
	public TreeMap getStatusmap() {
		return statusmap;
	}

	/**
	 * @param statusmap the statusmap to set
	 */
	public void setStatusmap(TreeMap statusmap) {
		this.statusmap = statusmap;
	}

	
	
}