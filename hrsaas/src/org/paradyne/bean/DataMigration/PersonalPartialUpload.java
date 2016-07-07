/**
 * 
 */
package org.paradyne.bean.DataMigration;

import org.paradyne.lib.BeanBase;

/**
 * @author Prakash Shetkar
 * Date 29 April 2010
 *
 */
public class PersonalPartialUpload extends BeanBase {

	private String dataPath;
	//filters
	private String divName;
	private String divCode;
	private String branchName;
	private String branchCode;
	private String deptName;
	private String deptCode;
	private String desgName;
	private String desgCode;
	private String empType;
	private String empTypeCode;
	private String gradeName;
	private String gradeCode;
	
	//column defination
	private String bloodGroupChk;
	private String weightChk;
	private String heightChk;
	private String religionChk;
	private String nationalityChk;
	private String hobbiesChk;
	private String casteCatChk;
	private String casteChk;
	private String subCasteChk;
	private String isHandicapChk;
	
	private String handicapDescChk;
	private String idMarkChk;
	private String passportNoChk;
	private String passExpDateChk;
	private String homeTownChk;
	private String maritalStatusChk;
	private String marriageDateChk;
	private String pfNoChk;
	private String panNoChk;
	private String esicNoChk;
	private String gratuityNoChk;
	
	private String salACNoChk;
	private String salBankChk;
	private String pensionACNoChk;
	private String pensionBankChk;
	private String pensionableChk;
	private String reimbAcNoChk;
	private String reimbBankChk;
	private String pfTrustApplChk;
	private String epfApplChk;
	private String gpfApplChk;
	private String vpfApplChk;
	private String costCenterChk;
	
	private String accountCatgChk;
	private String subCostCenterChk;
	private String payModeChk;
	
	private String uploadFileName;
	private String status;
	private String note;
	private boolean fileUploaded=false;
	private String recordsPerPage;
	private boolean dataExists=false;
	private String custReferenceNo ="";
	
	public boolean isDataExists() {
		return dataExists;
	}
	public void setDataExists(boolean dataExists) {
		this.dataExists = dataExists;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getBloodGroupChk() {
		return bloodGroupChk;
	}
	public void setBloodGroupChk(String bloodGroupChk) {
		this.bloodGroupChk = bloodGroupChk;
	}
	public String getWeightChk() {
		return weightChk;
	}
	public void setWeightChk(String weightChk) {
		this.weightChk = weightChk;
	}
	public String getHeightChk() {
		return heightChk;
	}
	public void setHeightChk(String heightChk) {
		this.heightChk = heightChk;
	}
	public String getReligionChk() {
		return religionChk;
	}
	public void setReligionChk(String religionChk) {
		this.religionChk = religionChk;
	}
	public String getNationalityChk() {
		return nationalityChk;
	}
	public void setNationalityChk(String nationalityChk) {
		this.nationalityChk = nationalityChk;
	}
	public String getHobbiesChk() {
		return hobbiesChk;
	}
	public void setHobbiesChk(String hobbiesChk) {
		this.hobbiesChk = hobbiesChk;
	}
	public String getCasteCatChk() {
		return casteCatChk;
	}
	public void setCasteCatChk(String casteCatChk) {
		this.casteCatChk = casteCatChk;
	}
	public String getCasteChk() {
		return casteChk;
	}
	public void setCasteChk(String casteChk) {
		this.casteChk = casteChk;
	}
	public String getSubCasteChk() {
		return subCasteChk;
	}
	public void setSubCasteChk(String subCasteChk) {
		this.subCasteChk = subCasteChk;
	}
	public String getIsHandicapChk() {
		return isHandicapChk;
	}
	public void setIsHandicapChk(String isHandicapChk) {
		this.isHandicapChk = isHandicapChk;
	}
	public String getHandicapDescChk() {
		return handicapDescChk;
	}
	public void setHandicapDescChk(String handicapDescChk) {
		this.handicapDescChk = handicapDescChk;
	}
	public String getIdMarkChk() {
		return idMarkChk;
	}
	public void setIdMarkChk(String idMarkChk) {
		this.idMarkChk = idMarkChk;
	}
	public String getPassportNoChk() {
		return passportNoChk;
	}
	public void setPassportNoChk(String passportNoChk) {
		this.passportNoChk = passportNoChk;
	}
	public String getPassExpDateChk() {
		return passExpDateChk;
	}
	public void setPassExpDateChk(String passExpDateChk) {
		this.passExpDateChk = passExpDateChk;
	}
	public String getHomeTownChk() {
		return homeTownChk;
	}
	public void setHomeTownChk(String homeTownChk) {
		this.homeTownChk = homeTownChk;
	}
	public String getMaritalStatusChk() {
		return maritalStatusChk;
	}
	public void setMaritalStatusChk(String maritalStatusChk) {
		this.maritalStatusChk = maritalStatusChk;
	}
	public String getMarriageDateChk() {
		return marriageDateChk;
	}
	public void setMarriageDateChk(String marriageDateChk) {
		this.marriageDateChk = marriageDateChk;
	}
	public String getPfNoChk() {
		return pfNoChk;
	}
	public void setPfNoChk(String pfNoChk) {
		this.pfNoChk = pfNoChk;
	}
	public String getPanNoChk() {
		return panNoChk;
	}
	public void setPanNoChk(String panNoChk) {
		this.panNoChk = panNoChk;
	}
	public String getEsicNoChk() {
		return esicNoChk;
	}
	public void setEsicNoChk(String esicNoChk) {
		this.esicNoChk = esicNoChk;
	}
	public String getSalACNoChk() {
		return salACNoChk;
	}
	public void setSalACNoChk(String salACNoChk) {
		this.salACNoChk = salACNoChk;
	}
	public String getSalBankChk() {
		return salBankChk;
	}
	public void setSalBankChk(String salBankChk) {
		this.salBankChk = salBankChk;
	}
	public String getPensionableChk() {
		return pensionableChk;
	}
	public void setPensionableChk(String pensionableChk) {
		this.pensionableChk = pensionableChk;
	}
	public String getReimbAcNoChk() {
		return reimbAcNoChk;
	}
	public void setReimbAcNoChk(String reimbAcNoChk) {
		this.reimbAcNoChk = reimbAcNoChk;
	}
	public String getReimbBankChk() {
		return reimbBankChk;
	}
	public void setReimbBankChk(String reimbBankChk) {
		this.reimbBankChk = reimbBankChk;
	}
	public String getPfTrustApplChk() {
		return pfTrustApplChk;
	}
	public void setPfTrustApplChk(String pfTrustApplChk) {
		this.pfTrustApplChk = pfTrustApplChk;
	}
	public String getEpfApplChk() {
		return epfApplChk;
	}
	public void setEpfApplChk(String epfApplChk) {
		this.epfApplChk = epfApplChk;
	}
	public String getGpfApplChk() {
		return gpfApplChk;
	}
	public void setGpfApplChk(String gpfApplChk) {
		this.gpfApplChk = gpfApplChk;
	}
	public String getVpfApplChk() {
		return vpfApplChk;
	}
	public void setVpfApplChk(String vpfApplChk) {
		this.vpfApplChk = vpfApplChk;
	}
	public String getCostCenterChk() {
		return costCenterChk;
	}
	public void setCostCenterChk(String costCenterChk) {
		this.costCenterChk = costCenterChk;
	}
	public String getSubCostCenterChk() {
		return subCostCenterChk;
	}
	public void setSubCostCenterChk(String subCostCenterChk) {
		this.subCostCenterChk = subCostCenterChk;
	}
	public String getPayModeChk() {
		return payModeChk;
	}
	public void setPayModeChk(String payModeChk) {
		this.payModeChk = payModeChk;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGratuityNoChk() {
		return gratuityNoChk;
	}
	public void setGratuityNoChk(String gratuityNoChk) {
		this.gratuityNoChk = gratuityNoChk;
	}
	public String getPensionACNoChk() {
		return pensionACNoChk;
	}
	public void setPensionACNoChk(String pensionACNoChk) {
		this.pensionACNoChk = pensionACNoChk;
	}
	public String getPensionBankChk() {
		return pensionBankChk;
	}
	public void setPensionBankChk(String pensionBankChk) {
		this.pensionBankChk = pensionBankChk;
	}
	public String getAccountCatgChk() {
		return accountCatgChk;
	}
	public void setAccountCatgChk(String accountCatgChk) {
		this.accountCatgChk = accountCatgChk;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public boolean isFileUploaded() {
		return fileUploaded;
	}
	public void setFileUploaded(boolean fileUploaded) {
		this.fileUploaded = fileUploaded;
	}
	public String getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public String getCustReferenceNo() {
		return custReferenceNo;
	}
	public void setCustReferenceNo(String custReferenceNo) {
		this.custReferenceNo = custReferenceNo;
	}
}
