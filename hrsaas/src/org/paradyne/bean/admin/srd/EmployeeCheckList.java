package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author priyanka.kumbhar
 *
 */
public class EmployeeCheckList extends BeanBase implements Serializable {

	private String empToken = "";
	private String empID = "";
	private String empName = "";
	private String deptName = "";
	private String center = "";
	private String empTrade = "";
	private String empRank = "";
	private String chkName = "";
	private String chkId = "";
	private String chkDesc = "";
	private String chkSubmit = "";
	private String chkSubmitTest = "";
	private String chk = "";
	private String uploadFileName = "";
	private String uploadFileNameNew = "";
	boolean newFlag;
	private boolean editFlag=false;
	String IsNotGeneralUser="";
	String profileUploadFileName="";
	String flag="";
	String ProfileEmpName="";
	String deleteImg="";
	String abbrUploadFileName="";
	
	private ArrayList<EmployeeCheckList> uploadList = null;


	private ArrayList<EmployeeCheckList> chkList = null;

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
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *            the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
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
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}

	/**
	 * @return the empTrade
	 */
	public String getEmpTrade() {
		return empTrade;
	}

	/**
	 * @param empTrade
	 *            the empTrade to set
	 */
	public void setEmpTrade(String empTrade) {
		this.empTrade = empTrade;
	}

	/**
	 * @return the empRank
	 */
	public String getEmpRank() {
		return empRank;
	}

	/**
	 * @param empRank
	 *            the empRank to set
	 */
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	/**
	 * @return the chkName
	 */
	public String getChkName() {
		return chkName;
	}

	/**
	 * @param chkName
	 *            the chkName to set
	 */
	public void setChkName(String chkName) {
		this.chkName = chkName;
	}

	/**
	 * @return the chkId
	 */
	public String getChkId() {
		return chkId;
	}

	/**
	 * @param chkId
	 *            the chkId to set
	 */
	public void setChkId(String chkId) {
		this.chkId = chkId;
	}

	/**
	 * @return the chkDesc
	 */
	public String getChkDesc() {
		return chkDesc;
	}

	/**
	 * @param chkDesc
	 *            the chkDesc to set
	 */
	public void setChkDesc(String chkDesc) {
		this.chkDesc = chkDesc;
	}

	/**
	 * @return the chkSubmit
	 */
	public String getChkSubmit() {
		return chkSubmit;
	}

	/**
	 * @param chkSubmit
	 *            the chkSubmit to set
	 */
	public void setChkSubmit(String chkSubmit) {
		this.chkSubmit = chkSubmit;
	}

	/**
	 * @return the chkSubmitTest
	 */
	public String getChkSubmitTest() {
		return chkSubmitTest;
	}

	/**
	 * @param chkSubmitTest
	 *            the chkSubmitTest to set
	 */
	public void setChkSubmitTest(String chkSubmitTest) {
		this.chkSubmitTest = chkSubmitTest;
	}

	/**
	 * @return the chk
	 */
	public String getChk() {
		return chk;
	}

	/**
	 * @param chk
	 *            the chk to set
	 */
	public void setChk(String chk) {
		this.chk = chk;
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
	 * @return the chkList
	 */
	public ArrayList<EmployeeCheckList> getChkList() {
		return chkList;
	}

	/**
	 * @param chkList
	 *            the chkList to set
	 */
	public void setChkList(ArrayList<EmployeeCheckList> chkList) {
		this.chkList = chkList;
	}
	/**
	 * 
	 * @return editFlag
	 */

	public boolean isEditFlag() {
		return editFlag;
	}
    /**
     * 
     * @param editFlag
     *          the editFlag to set
     */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * 
	 * @return IsNotGeneralUser
	 */

	public String getIsNotGeneralUser() {
		return IsNotGeneralUser;
	}
	/**
	 * 
	 * @param isNotGeneralUser
	 *                the isNotGeneralUser to set
	 */

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		IsNotGeneralUser = isNotGeneralUser;
	}
	/**
	 * 
	 * @return newFlag
	 */

	public boolean isNewFlag() {
		return newFlag;
	}
/**
 * 
 * @param newFlag
 *             the newFlag to set
 */    
	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}
/**
 * 
 * @return profileUploadFileName
 */
	public String getProfileUploadFileName() {
		return profileUploadFileName;
	}
/**
 * 
 * @param profileUploadFileName
 *          the profileUploadFileName to set
 */
	public void setProfileUploadFileName(String profileUploadFileName) {
		this.profileUploadFileName = profileUploadFileName;
	}
/**
 * 
 * @return flag
 */
	public String getFlag() {
		return flag;
	}
/**
 * 
 * @param flag
 *         the flag to set
 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
/**
 * 
 * @return ProfileEmpName
 */
	public String getProfileEmpName() {
		return ProfileEmpName;
	}
/**
 * 
 * @param profileEmpName
 *          the ProfileEmpName to set
 */
	public void setProfileEmpName(String profileEmpName) {
		ProfileEmpName = profileEmpName;
	}
/**
 * 
 * @return deleteImg
 */
	public String getDeleteImg() {
		return deleteImg;
	}
/**
 * 
 * @param deleteImg
 *              the deleteImg to set
 */
	public void setDeleteImg(String deleteImg) {
		this.deleteImg = deleteImg;
	}
/**
 * 
 * @return uploadList
 */
	public ArrayList<EmployeeCheckList> getUploadList() {
		return uploadList;
	}
/**
 * 
 * @param uploadList
 *       the uploadList to set
 */
	public void setUploadList(ArrayList<EmployeeCheckList> uploadList) {
		this.uploadList = uploadList;
	}
/**
 * 
 * @return uploadFileNameNew
 */
	public String getUploadFileNameNew() {
		return uploadFileNameNew;
	}
/**
 * 
 * @param uploadFileNameNew
 *          the uploadFileNameNew to set
 */
	public void setUploadFileNameNew(String uploadFileNameNew) {
		this.uploadFileNameNew = uploadFileNameNew;
	}
/**
 * 
 * @return abbrUploadFileName
 */
	public String getAbbrUploadFileName() {
		return abbrUploadFileName;
	}
	/**
	 * 
	 * @param abbrUploadFileName
	 * the abbrUploadFileName to set
	 */

	public void setAbbrUploadFileName(String abbrUploadFileName) {
		this.abbrUploadFileName = abbrUploadFileName;
	}




}
