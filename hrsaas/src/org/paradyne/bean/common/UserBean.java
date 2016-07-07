package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class UserBean extends BeanBase {

	String userName = null;
	String userPassword = null;
	String userToken = null;
	String userActive = null;
	String userProfile = null;
	String userEmpID = null;
	String userProfileID = null;
	private String userEmpName = null;
	private String loginCode = "";
	private String lockFlag;
	private String hiddenLockStatus = "false";

	private String accessProfile = "";
	private String accessProfileId = "";

	// Added by Nilesh 4th May 11
	public String hdeleteCode = "";
	private String myPage = "";
	private String modeLength = "";
	private String totalNoOfRecords = "";
	private ArrayList userList = null;
	private boolean userFlag = false;
	
	private String validUpto= ""; //Date field which restrict user to use  
	
	/*Profile Id's for Multiselect Search Option*/
	
	private String login_profile= null;
	private String login_profile_ID= null;	
	private String login_access_profile = null;
	private String login_access_profile_ID = null;

	public String getAccessProfile() {
		return accessProfile;
	}

	public void setAccessProfile(String accessProfile) {
		this.accessProfile = accessProfile;
	}

	public String getHiddenLockStatus() {
		return hiddenLockStatus;
	}

	public void setHiddenLockStatus(String hiddenLockStatus) {
		this.hiddenLockStatus = hiddenLockStatus;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getUserEmpName() {
		return userEmpName;
	}

	public void setUserEmpName(String userEmpName) {
		this.userEmpName = userEmpName;
	}

	public String getUserProfileID() {
		return userProfileID;
	}

	public void setUserProfileID(String userProfileID) {
		this.userProfileID = userProfileID;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserEmpID() {
		return userEmpID;
	}

	public void setUserEmpID(String userEmpID) {
		this.userEmpID = userEmpID;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getAccessProfileId() {
		return accessProfileId;
	}

	public void setAccessProfileId(String accessProfileId) {
		this.accessProfileId = accessProfileId;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	public ArrayList getUserList() {
		return userList;
	}

	public void setUserList(ArrayList userList) {
		this.userList = userList;
	}

	public boolean isUserFlag() {
		return userFlag;
	}

	public void setUserFlag(boolean userFlag) {
		this.userFlag = userFlag;
	}

	public String getLogin_profile_ID() {
		return login_profile_ID;
	}

	public void setLogin_profile_ID(String login_profile_ID) {
		this.login_profile_ID = login_profile_ID;
	}

	public String getLogin_access_profile_ID() {
		return login_access_profile_ID;
	}

	public void setLogin_access_profile_ID(String login_access_profile_ID) {
		this.login_access_profile_ID = login_access_profile_ID;
	}
	public String getLogin_profile() {
		return login_profile;
	}

	public void setLogin_profile(String login_profile) {
		this.login_profile = login_profile;
	}

	public String getLogin_access_profile() {
		return login_access_profile;
	}

	public void setLogin_access_profile(String login_access_profile) {
		this.login_access_profile = login_access_profile;
	}
	public String getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}

}
