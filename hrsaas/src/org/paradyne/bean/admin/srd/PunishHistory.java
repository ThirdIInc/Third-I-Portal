package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 *modified by priyanka kumbhar.
 * 
 */
public class PunishHistory extends BeanBase implements Serializable {
	private String dispAction;
	private String dispActionId;
	private String authority;
	private String letterNo;
	private String letterDate;
	private String ceoOrder;
	private String ceoDate;
	private String period;
	private String wef;
	private String serialNo;
	private String uploadFileName = "";
	private String uploadFil = "";
	private String punishId;
	private String empId;
	private String empName;
	private String tokenNo;
	private String rank = "";
	private String center = "";;
	private ArrayList punishList;
	private ArrayList keepInformedList;
	private ArrayList imageList;
     String paracode = "";
	private String description = "";
	private String punishStatus="";
	private String hSuspensionType="";
	private String hSuspensionId="";
	private String listpunishid="";
	private String hAuth="";
	private String hEffDate="";
	private String hPeriod="";
	private String hView="";
	private String status="";
	private String hDesc="";
	private String effFromDate="";
	private String effToDate="";
	private String hEffToDate="";
	private boolean itrFlag=false;
	private String update="false";
	private boolean deleteFlag=false;
	private String editFlag="";
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String checkRemoveUpload="";
	private String dataPath="";
	private String flag="";
	private String ittproofName="";
	private String uploadLocFileName = "";
	private String profileUploadFileName = "";
	private String uploadFileNameTxt = "";
	String abbrPunishHistory="";
	String IsNotGeneralUser="";
	String editableFlag="";
	String listOffncDesc="";
	String noData="";
	String profileEmpName="";
	ArrayList proofList;

	public String getUploadLocFileName() {
		return uploadLocFileName;
	}

	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}

	public ArrayList getProofList() {
		return proofList;
	}

	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}

	public String getProofSrNo() {
		return proofSrNo;
	}

	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}

	public String getProofName() {
		return proofName;
	}

	public void setProofName(String proofName) {
		this.proofName = proofName;
	}

	public String getProofFileName() {
		return proofFileName;
	}

	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}

	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}

	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getIttproofName() {
		return ittproofName;
	}

	public void setIttproofName(String ittproofName) {
		this.ittproofName = ittproofName;
	}

	public String getListOffncDesc() {
		return listOffncDesc;
	}

	public void setListOffncDesc(String listOffncDesc) {
		this.listOffncDesc = listOffncDesc;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsNotGeneralUser() {
		return IsNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		IsNotGeneralUser = isNotGeneralUser;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getCeoDate() {
		return ceoDate;
	}

	public void setCeoDate(String ceoDate) {
		this.ceoDate = ceoDate;
	}

	public String getCeoOrder() {
		return ceoOrder;
	}

	public void setCeoOrder(String ceoOrder) {
		this.ceoOrder = ceoOrder;
	}

	public String getDispAction() {
		return dispAction;
	}

	public void setDispAction(String dispAction) {
		this.dispAction = dispAction;
	}

	public String getDispActionId() {
		return dispActionId;
	}

	public void setDispActionId(String dispActionId) {
		this.dispActionId = dispActionId;
	}

	public String getPunishId() {
		return punishId;
	}

	public void setPunishId(String punishId) {
		this.punishId = punishId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(String letterDate) {
		this.letterDate = letterDate;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public ArrayList getPunishList() {
		return punishList;
	}

	public void setPunishList(ArrayList punishList) {
		this.punishList = punishList;
	}

	public String getWef() {
		return wef;
	}

	public void setWef(String wef) {
		this.wef = wef;
	}

	public String getParacode() {
		return paracode;
	}


	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getPunishStatus() {
		return punishStatus;
	}

	public void setPunishStatus(String punishStatus) {
		this.punishStatus = punishStatus;
	}

	public String getHSuspensionType() {
		return hSuspensionType;
	}

	public void setHSuspensionType(String suspensionType) {
		hSuspensionType = suspensionType;
	}

	public String getHSuspensionId() {
		return hSuspensionId;
	}

	public void setHSuspensionId(String suspensionId) {
		hSuspensionId = suspensionId;
	}

	public String getHAuth() {
		return hAuth;
	}

	public void setHAuth(String auth) {
		hAuth = auth;
	}

	public String getHEffDate() {
		return hEffDate;
	}

	public void setHEffDate(String effDate) {
		hEffDate = effDate;
	}

	public String getHPeriod() {
		return hPeriod;
	}

	public void setHPeriod(String period) {
		hPeriod = period;
	}

	public String getHView() {
		return hView;
	}

	public void setHView(String view) {
		hView = view;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHDesc() {
		return hDesc;
	}

	public void setHDesc(String desc) {
		hDesc = desc;
	}

	public String getEffToDate() {
		return effToDate;
	}

	public void setEffToDate(String effToDate) {
		this.effToDate = effToDate;
	}

	

	public boolean isItrFlag() {
		return itrFlag;
	}

	public void setItrFlag(boolean itrFlag) {
		this.itrFlag = itrFlag;
	}

	public String getHEffToDate() {
		return hEffToDate;
	}

	public void setHEffToDate(String effToDate) {
		hEffToDate = effToDate;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getEditableFlag() {
		return editableFlag;
	}

	public void setEditableFlag(String editableFlag) {
		this.editableFlag = editableFlag;
	}

	public String getListpunishid() {
		return listpunishid;
	}

	public void setListpunishid(String listpunishid) {
		this.listpunishid = listpunishid;
	}

	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public String getUploadFil() {
		return uploadFil;
	}

	public void setUploadFil(String uploadFil) {
		this.uploadFil = uploadFil;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProfileUploadFileName() {
		return profileUploadFileName;
	}

	public void setProfileUploadFileName(String profileUploadFileName) {
		this.profileUploadFileName = profileUploadFileName;
	}

	public ArrayList getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList imageList) {
		this.imageList = imageList;
	}

	public String getUploadFileNameTxt() {
		return uploadFileNameTxt;
	}

	public void setUploadFileNameTxt(String uploadFileNameTxt) {
		this.uploadFileNameTxt = uploadFileNameTxt;
	}

	public String getEffFromDate() {
		return effFromDate;
	}

	public void setEffFromDate(String effFromDate) {
		this.effFromDate = effFromDate;
	}

	public void setAbbrPunishHistory(String abbrPunishHistory) {
		this.abbrPunishHistory = abbrPunishHistory;
	}

	public String getAbbrPunishHistory() {
		return abbrPunishHistory;
	}


}
