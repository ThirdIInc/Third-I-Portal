package org.paradyne.lib;

/**
 * @ SqlBase.java 
 * @ author : Sachin Hegde
 * @ purpose : To provide a common platform to all Sql Queries
**/

public class BeanBase {

	private String loginPartnerCode="";
	private String userEmpId;
	private String candidateLoginCode = "";
	private String customerLoginCode = "";
	private String userLoginCode;
	private String userProfileId;
	private String userAccessProfileId;
	private String userLoginDate;
	
	private String userVisitorNo;
	private String candidateUserEmpId="";
	private String customerUserEmpId="";
	private boolean insertFlag = false;
	private boolean updateFlag = false;
	private boolean deleteFlag = false;
	private boolean viewFlag = false;
	private boolean generalFlag = true;

	private String userProfileCenters;
	private String userProfileEmpType;
	private String userProfilePaybill;
	private String userProfileDivision;

	private String emailUserId = "";
	private String emailPwd = "";
	private int menuCode = -1;

	private int recordCount = 0;
	private boolean previousFlag = false;

	private String f9query = "";
	private String f9SearchText;
	private String f9SearchSelect;
	private String f9SearchCompare;
	private String f9SearchValue;

	private String f9SearchIndex1;
	private String f9SearchSelect1;
	private String f9SearchText1;
	private String f9SearchCodition;

	private int f9SearchIndex;
	private int hdF9SearchIndex = 0;
	private String chatLogin = "false";
	private String pageNo; // -added
	private String hdadvanced;// -added
	private String hdClick; // -added
	private String actionMessage = "";
	private boolean vasFlag = false;
	private int navigationLevelCode;
	private String enableAll;
	private String pageNo1; // -added
	private int recordCount1;
	private int masterMenuCode;
	private String currentMode = "1";
	
	//Added by prashant for Kiosk
	private String loginTime = "";	
	private String loginDate = "";
	private String loginCode = "";

	
	//Added by Prajakta B(4 Jun 2013) for multiple profile
	private String multipleProfileCode = ""; 

	private String screenWidth="";// getting screen resolution for DashBoard
	 
	public String getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}


	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public int getHdF9SearchIndex() {
		return hdF9SearchIndex;
	}

	public void setHdF9SearchIndex(int hdF9SearchIndex) {
		this.hdF9SearchIndex = hdF9SearchIndex;
	}

	public boolean isPreviousFlag() {
		return previousFlag;
	}

	public void setPreviousFlag(boolean previousFlag) {
		this.previousFlag = previousFlag;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public boolean isGeneralFlag() {
		return generalFlag;
	}

	public void setGeneralFlag(boolean generalFlag) {
		this.generalFlag = generalFlag;
	}

	public boolean isInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	
	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getUserEmpId() {
		return userEmpId;
	}

	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}

	public String getUserLoginCode() {
		return userLoginCode;
	}

	public void setUserLoginCode(String userLoginCode) {
		this.userLoginCode = userLoginCode;
	}

	public String getUserProfileCenters() {
		return userProfileCenters;
	}

	public void setUserProfileCenters(String userProfileCenters) {
		this.userProfileCenters = userProfileCenters;
	}

	public String getUserProfileEmpType() {
		return userProfileEmpType;
	}

	public void setUserProfileEmpType(String userProfileEmpType) {
		this.userProfileEmpType = userProfileEmpType;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getUserProfilePaybill() {
		return userProfilePaybill;
	}

	public void setUserProfilePaybill(String userProfilePaybill) {
		this.userProfilePaybill = userProfilePaybill;
	}

	public boolean isViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getF9SearchIndex() {
		return f9SearchIndex;
	}

	public String getF9SearchText() {
		return f9SearchText;
	}

	public void setF9SearchText(String searchText) {
		f9SearchText = searchText;
	}

	public void setF9SearchIndex(int searchIndex) {
		f9SearchIndex = searchIndex;
	}

	public String getUserLoginDate() {
		return userLoginDate;
	}

	public void setUserLoginDate(String userLoginDate) {
		this.userLoginDate = userLoginDate;
	}

	public String getUserVisitorNo() {
		return userVisitorNo;
	}

	public void setUserVisitorNo(String userVisitorNo) {
		this.userVisitorNo = userVisitorNo;
	}

	public String getChatLogin() {
		return chatLogin;
	}

	public void setChatLogin(String chatLogin) {
		this.chatLogin = chatLogin;
	}

	public String getEmailUserId() {
		return emailUserId;
	}

	public void setEmailUserId(String emailUserId) {
		this.emailUserId = emailUserId;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

	public String getF9SearchSelect() {
		return f9SearchSelect;
	}

	public void setF9SearchSelect(String searchSelect) {
		f9SearchSelect = searchSelect;
	}

	public String getF9SearchCompare() {
		return f9SearchCompare;
	}

	public void setF9SearchCompare(String searchCompare) {
		f9SearchCompare = searchCompare;
	}

	public String getF9SearchValue() {
		return f9SearchValue;
	}

	public void setF9SearchValue(String searchValue) {
		f9SearchValue = searchValue;
	}

	public String getF9SearchIndex1() {
		return f9SearchIndex1;
	}

	public void setF9SearchIndex1(String searchIndex1) {
		f9SearchIndex1 = searchIndex1;
	}

	public String getF9SearchSelect1() {
		return f9SearchSelect1;
	}

	public void setF9SearchSelect1(String searchSelect1) {
		f9SearchSelect1 = searchSelect1;
	}

	public String getF9SearchText1() {
		return f9SearchText1;
	}

	public void setF9SearchText1(String searchText1) {
		f9SearchText1 = searchText1;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getHdadvanced() {
		return hdadvanced;
	}

	public void setHdadvanced(String hdadvanced) {
		this.hdadvanced = hdadvanced;
	}

	public String getHdClick() {
		return hdClick;
	}

	public void setHdClick(String hdClick) {
		this.hdClick = hdClick;
	}

	public boolean isVasFlag() {
		return vasFlag;
	}

	public void setVasFlag(boolean vasFlag) {
		this.vasFlag = vasFlag;
	}

	public String getF9SearchCodition() {
		return f9SearchCodition;
	}

	public void setF9SearchCodition(String searchCodition) {
		f9SearchCodition = searchCodition;
	}

	public String getF9query() {
		return f9query;
	}

	public void setF9query(String f9query) {
		this.f9query = f9query;
	}

	public String getCandidateLoginCode() {
		return candidateLoginCode;
	}

	public void setCandidateLoginCode(String candidateLoginCode) {
		this.candidateLoginCode = candidateLoginCode;
	}
	
	public int getNavigationLevelCode() {
		return navigationLevelCode;
	}

	public void setNavigationLevelCode(int navigationLevelCode) {
		this.navigationLevelCode = navigationLevelCode;
	}

	public String getEnableAll() {
		return enableAll;
	}

	public void setEnableAll(String enableAll) {
		this.enableAll = enableAll;
	}

	public String getPageNo1() {
		return pageNo1;
	}

	public void setPageNo1(String pageNo1) {
		this.pageNo1 = pageNo1;
	}

	public int getRecordCount1() {
		return recordCount1;
	}

	public void setRecordCount1(int recordCount1) {
		this.recordCount1 = recordCount1;
	}

	public String getCandidateUserEmpId() {
		return candidateUserEmpId;
	}

	public void setCandidateUserEmpId(String candidateUserEmpId) {
		this.candidateUserEmpId = candidateUserEmpId;
	}

	public int getMasterMenuCode() {
		return masterMenuCode;
	}

	public void setMasterMenuCode(int masterMenuCode) {
		this.masterMenuCode = masterMenuCode;
	}
	
	public String getCurrentMode() {
		return this.currentMode;
	}
	
	public void setCurrentMode(String currentMode) {
		this.currentMode = currentMode;
	}

	public String getUserAccessProfileId() {
		return userAccessProfileId;
	}

	public void setUserAccessProfileId(String userAccessProfileId) {
		this.userAccessProfileId = userAccessProfileId;
	}

	public String getUserProfileDivision() {
		return userProfileDivision;
	}

	public void setUserProfileDivision(String userProfileDivision) {
		this.userProfileDivision = userProfileDivision;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getLoginPartnerCode() {
		return loginPartnerCode;
	}

	public void setLoginPartnerCode(String loginPartnerCode) {
		this.loginPartnerCode = loginPartnerCode;
	}

	/**
	 * @return the customerLoginCode
	 */
	public String getCustomerLoginCode() {
		return customerLoginCode;
	}

	/**
	 * @param customerLoginCode the customerLoginCode to set
	 */
	public void setCustomerLoginCode(String customerLoginCode) {
		this.customerLoginCode = customerLoginCode;
	}

	/**
	 * @return the customerUserEmpId
	 */
	public String getCustomerUserEmpId() {
		return customerUserEmpId;
	}

	/**
	 * @param customerUserEmpId the customerUserEmpId to set
	 */
	public void setCustomerUserEmpId(String customerUserEmpId) {
		this.customerUserEmpId = customerUserEmpId;
	}

	/**
	 * @return the multipleProfileCode
	 */
	public String getMultipleProfileCode() {
		return multipleProfileCode;
	}

	/** 
	 * @param multipleProfileCode the multipleProfileCode to set
	 */
	public void setMultipleProfileCode(String multipleProfileCode) {
		this.multipleProfileCode = multipleProfileCode;
	}
}