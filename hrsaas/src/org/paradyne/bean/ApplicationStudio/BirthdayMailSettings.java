/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba
 * 
 */
public class BirthdayMailSettings extends BeanBase {

	private String autoFlag;
	private String indDptFlag;
	private String allDptFlag;
	private String indBrnFlag;
	private String allBrnFlag;
	private String indDesgFlag;
	private String allDesgFlag;
	private String indDivFlag;
	private String allDivFlag;
	private String tempCode;
	private String tempName;

	private String ittRandomTemp="";
	private String ittTempCode="";
	private String ittCheckBox="";
	private String hiddRandomCode="";
	private String randCheckBox="";
	private String tempCheckBox="";
	ArrayList randItt=null;
	
	private String allEmpFlag;
	private String designationFlag="";
	private String departmentFlag ="";
	private String emailIDFlag ="";
	private String phoneNumberFlag = "";
	private String titleFlag= "";
	
	//Updated By Anantha alkshmi
	private String  groupId="";
	private String empName=""; 
	private String empTokenNo ="";
	private String empCode="";
	private String empNameIt=""; 
	private String empTokenNoIt ="";
	private String empCodeIt="";
	private String paraId="";
	private String tableLength="";
	private String groupMailId="";
	ArrayList birthdayMailList=null;
	
	private String mailSentOnTiming = "";
	

	/**
	 * @return the autoFlag
	 */
	public String getAutoFlag() {
		return this.autoFlag;
	}

	/**
	 * @param autoFlag the autoFlag to set
	 */
	public void setAutoFlag(String autoFlag) {
		this.autoFlag = autoFlag;
	}

	/**
	 * @return the indDptFlag
	 */
	public String getIndDptFlag() {
		return this.indDptFlag;
	}

	/**
	 * @param indDptFlag the indDptFlag to set
	 */
	public void setIndDptFlag(String indDptFlag) {
		this.indDptFlag = indDptFlag;
	}

	/**
	 * @return the allDptFlag
	 */
	public String getAllDptFlag() {
		return this.allDptFlag;
	}

	/**
	 * @param allDptFlag the allDptFlag to set
	 */
	public void setAllDptFlag(String allDptFlag) {
		this.allDptFlag = allDptFlag;
	}

	/**
	 * @return the indBrnFlag
	 */
	public String getIndBrnFlag() {
		return this.indBrnFlag;
	}

	/**
	 * @param indBrnFlag the indBrnFlag to set
	 */
	public void setIndBrnFlag(String indBrnFlag) {
		this.indBrnFlag = indBrnFlag;
	}

	/**
	 * @return the allBrnFlag
	 */
	public String getAllBrnFlag() {
		return this.allBrnFlag;
	}

	/**
	 * @param allBrnFlag the allBrnFlag to set
	 */
	public void setAllBrnFlag(String allBrnFlag) {
		this.allBrnFlag = allBrnFlag;
	}

	/**
	 * @return the indDesgFlag
	 */
	public String getIndDesgFlag() {
		return this.indDesgFlag;
	}

	/**
	 * @param indDesgFlag the indDesgFlag to set
	 */
	public void setIndDesgFlag(String indDesgFlag) {
		this.indDesgFlag = indDesgFlag;
	}

	/**
	 * @return the allDesgFlag
	 */
	public String getAllDesgFlag() {
		return this.allDesgFlag;
	}

	/**
	 * @param allDesgFlag the allDesgFlag to set
	 */
	public void setAllDesgFlag(String allDesgFlag) {
		this.allDesgFlag = allDesgFlag;
	}

	/**
	 * @return the indDivFlag
	 */
	public String getIndDivFlag() {
		return this.indDivFlag;
	}

	/**
	 * @param indDivFlag the indDivFlag to set
	 */
	public void setIndDivFlag(String indDivFlag) {
		this.indDivFlag = indDivFlag;
	}

	/**
	 * @return the allDivFlag
	 */
	public String getAllDivFlag() {
		return this.allDivFlag;
	}

	/**
	 * @param allDivFlag the allDivFlag to set
	 */
	public void setAllDivFlag(String allDivFlag) {
		this.allDivFlag = allDivFlag;
	}

	/**
	 * @return the tempCode
	 */
	public String getTempCode() {
		return this.tempCode;
	}

	/**
	 * @param tempCode the tempCode to set
	 */
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return this.tempName;
	}

	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @return the ittRandomTemp
	 */
	public String getIttRandomTemp() {
		return this.ittRandomTemp;
	}

	/**
	 * @param ittRandomTemp the ittRandomTemp to set
	 */
	public void setIttRandomTemp(String ittRandomTemp) {
		this.ittRandomTemp = ittRandomTemp;
	}

	/**
	 * @return the ittTempCode
	 */
	public String getIttTempCode() {
		return this.ittTempCode;
	}

	/**
	 * @param ittTempCode the ittTempCode to set
	 */
	public void setIttTempCode(String ittTempCode) {
		this.ittTempCode = ittTempCode;
	}

	/**
	 * @return the ittCheckBox
	 */
	public String getIttCheckBox() {
		return this.ittCheckBox;
	}

	/**
	 * @param ittCheckBox the ittCheckBox to set
	 */
	public void setIttCheckBox(String ittCheckBox) {
		this.ittCheckBox = ittCheckBox;
	}

	/**
	 * @return the hiddRandomCode
	 */
	public String getHiddRandomCode() {
		return this.hiddRandomCode;
	}

	/**
	 * @param hiddRandomCode the hiddRandomCode to set
	 */
	public void setHiddRandomCode(String hiddRandomCode) {
		this.hiddRandomCode = hiddRandomCode;
	}

	/**
	 * @return the randCheckBox
	 */
	public String getRandCheckBox() {
		return this.randCheckBox;
	}

	/**
	 * @param randCheckBox the randCheckBox to set
	 */
	public void setRandCheckBox(String randCheckBox) {
		this.randCheckBox = randCheckBox;
	}

	/**
	 * @return the tempCheckBox
	 */
	public String getTempCheckBox() {
		return this.tempCheckBox;
	}

	/**
	 * @param tempCheckBox the tempCheckBox to set
	 */
	public void setTempCheckBox(String tempCheckBox) {
		this.tempCheckBox = tempCheckBox;
	}

	/**
	 * @return the randItt
	 */
	public ArrayList getRandItt() {
		return this.randItt;
	}

	/**
	 * @param randItt the randItt to set
	 */
	public void setRandItt(ArrayList randItt) {
		this.randItt = randItt;
	}

	/**
	 * @return the allEmpFlag
	 */
	public String getAllEmpFlag() {
		return this.allEmpFlag;
	}

	/**
	 * @param allEmpFlag the allEmpFlag to set
	 */
	public void setAllEmpFlag(String allEmpFlag) {
		this.allEmpFlag = allEmpFlag;
	}

	/**
	 * @return the designationFlag
	 */
	public String getDesignationFlag() {
		return this.designationFlag;
	}

	/**
	 * @param designationFlag the designationFlag to set
	 */
	public void setDesignationFlag(String designationFlag) {
		this.designationFlag = designationFlag;
	}

	/**
	 * @return the departmentFlag
	 */
	public String getDepartmentFlag() {
		return this.departmentFlag;
	}

	/**
	 * @param departmentFlag the departmentFlag to set
	 */
	public void setDepartmentFlag(String departmentFlag) {
		this.departmentFlag = departmentFlag;
	}

	/**
	 * @return the emailIDFlag
	 */
	public String getEmailIDFlag() {
		return this.emailIDFlag;
	}

	/**
	 * @param emailIDFlag the emailIDFlag to set
	 */
	public void setEmailIDFlag(String emailIDFlag) {
		this.emailIDFlag = emailIDFlag;
	}

	/**
	 * @return the phoneNumberFlag
	 */
	public String getPhoneNumberFlag() {
		return this.phoneNumberFlag;
	}

	/**
	 * @param phoneNumberFlag the phoneNumberFlag to set
	 */
	public void setPhoneNumberFlag(String phoneNumberFlag) {
		this.phoneNumberFlag = phoneNumberFlag;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return this.groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return this.empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empTokenNo
	 */
	public String getEmpTokenNo() {
		return this.empTokenNo;
	}

	/**
	 * @param empTokenNo the empTokenNo to set
	 */
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return this.empCode;
	}

	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empNameIt
	 */
	public String getEmpNameIt() {
		return this.empNameIt;
	}

	/**
	 * @param empNameIt the empNameIt to set
	 */
	public void setEmpNameIt(String empNameIt) {
		this.empNameIt = empNameIt;
	}

	/**
	 * @return the empTokenNoIt
	 */
	public String getEmpTokenNoIt() {
		return this.empTokenNoIt;
	}

	/**
	 * @param empTokenNoIt the empTokenNoIt to set
	 */
	public void setEmpTokenNoIt(String empTokenNoIt) {
		this.empTokenNoIt = empTokenNoIt;
	}

	/**
	 * @return the empCodeIt
	 */
	public String getEmpCodeIt() {
		return this.empCodeIt;
	}

	/**
	 * @param empCodeIt the empCodeIt to set
	 */
	public void setEmpCodeIt(String empCodeIt) {
		this.empCodeIt = empCodeIt;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return this.paraId;
	}

	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the tableLength
	 */
	public String getTableLength() {
		return this.tableLength;
	}

	/**
	 * @param tableLength the tableLength to set
	 */
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}

	/**
	 * @return the groupMailId
	 */
	public String getGroupMailId() {
		return this.groupMailId;
	}

	/**
	 * @param groupMailId the groupMailId to set
	 */
	public void setGroupMailId(String groupMailId) {
		this.groupMailId = groupMailId;
	}

	/**
	 * @return the birthdayMailList
	 */
	public ArrayList getBirthdayMailList() {
		return this.birthdayMailList;
	}

	/**
	 * @param birthdayMailList the birthdayMailList to set
	 */
	public void setBirthdayMailList(ArrayList birthdayMailList) {
		this.birthdayMailList = birthdayMailList;
	}

	/**
	 * @return the mailSentOnTiming
	 */
	public String getMailSentOnTiming() {
		return this.mailSentOnTiming;
	}

	/**
	 * @param mailSentOnTiming the mailSentOnTiming to set
	 */
	public void setMailSentOnTiming(String mailSentOnTiming) {
		this.mailSentOnTiming = mailSentOnTiming;
	}

	public String getTitleFlag() {
		return titleFlag;
	}

	public void setTitleFlag(String titleFlag) {
		this.titleFlag = titleFlag;
	}
	
}
