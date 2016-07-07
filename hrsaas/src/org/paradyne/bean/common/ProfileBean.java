/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author sunil
 *
 */
public class ProfileBean extends BeanBase {
	
	String profile = "";
	String check = "false";
	String profileId = "";
	String copyProfileId = "";
	String copyProfile   = "";
	String copyProId   = "";
	String copyProName  = "";
	String centerId = "";
	String centerName = "";
	String centerFlag = "" ;
	
	String paybillId = "";
	String paybillName = "";
	String paybillFlag = "";
	
	String emptypeId = "";
	String emptype = "";
	String emptypeFlag = "";
	
	private String locationType="";
	private String locationType1="";
	
	/************  FOR REPORT (start)  ***********/
	String menuName = "";
	String insert = "";
	String update = "";
	
	String delete = "";
	String view = "";
	String general = "";
	
	String loginID = "";
	String empID = "";
	String empToken = "";
	String empName = "";
	String empDesg = "";
	String loginName = "";
	//String hiddenUpdateId="false";
	int  selMenuCode;
	//Flags
	private boolean copyFlag=false;
	
	private boolean createFlag=false;
	private boolean updFlag=false;
	private boolean goFlag=false;
	private boolean chk=false;
	private boolean copyChk=false;
	private boolean onloadChk=false;
	private boolean copyName=false;
	
	ArrayList<Object> menuList;
	ArrayList<Object> employeeList;
	
	/************  FOR REPORT (end)  ***********/
	
	
	
	ArrayList<Object> centerList ;
	ArrayList<Object> paybillList ;
	ArrayList<Object> empList ;
	
	//Added by Prajakta B
	private String profileListLength="";
	ArrayList<Object> profileList;
	private String myPage="";
	private String totalNoOfRecords="";
	private String hiddenProfileId="";
	private String hiddenProfile="";
	private String listType="";
	private String noConf="false";
	private String configuration="";
	private String process="";
	private String report="";
	private String paraId="";
	//Ends Added by Prajakta B	
	
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	/**
	 * @return hiddenProfile
	 */
	public String getHiddenProfile() {
		return hiddenProfile;
	}
	/**
	 * @param hiddenProfile the hiddenProfile to set
	 */
	public void setHiddenProfile(String hiddenProfile) {
		this.hiddenProfile = hiddenProfile;
	}
	/**
	 * @return myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage
	 * the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords
	 * the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return profileListLength
	 */
	public String getProfileListLength() {
		return profileListLength;
	}
	/**
	 * @param profileListLength
	 * the profileListLength to set
	 */
	public void setProfileListLength(String profileListLength) {
		this.profileListLength = profileListLength;
	}
	/**
	 * @return profileList
	 */
	public ArrayList<Object> getProfileList() {
		return profileList;
	}
	/**
	 * @param profileList
	 * the profileList to set
	 */
	public void setProfileList(ArrayList<Object> profileList) {
		this.profileList = profileList;
	}
	/**
	 * @param profile
	 * @param profileId
	 * @param copyProfileId
	 * @param copyProfile
	 * @param centerId
	 * @param centerName
	 * @param centerFlag
	 * @param paybillId
	 * @param paybillName
	 * @param paybillFlag
	 * @param emptypeId
	 * @param emptype
	 * @param emptypeFlag
	 * @param centerList
	 * @param paybillList
	 * @param empList
	 */
	public ProfileBean(String profile, String profileId, String copyProfileId, String copyProfile, String centerId, String centerName, String centerFlag, String paybillId, String paybillName, String paybillFlag, String emptypeId, String emptype, String emptypeFlag, ArrayList<Object> centerList, ArrayList<Object> paybillList, ArrayList<Object> empList) {
		super();
		this.profile = profile;
		this.profileId = profileId;
		this.copyProfileId = copyProfileId;
		this.copyProfile = copyProfile;
		this.centerId = centerId;
		this.centerName = centerName;
		this.centerFlag = centerFlag;
		this.paybillId = paybillId;
		this.paybillName = paybillName;
		this.paybillFlag = paybillFlag;
		this.emptypeId = emptypeId;
		this.emptype = emptype;
		this.emptypeFlag = emptypeFlag;
		this.centerList = centerList;
		this.paybillList = paybillList;
		this.empList = empList;
	}
	public ProfileBean(){
		
	}
	public ArrayList<Object> getCenterList() {
		return centerList;
	}

	public void setCenterList(ArrayList<Object> centerList) {
		this.centerList = centerList;
	}

	public String getCenterFlag() {
		return centerFlag;
	}
	public void setCenterFlag(String centerFlag) {
		this.centerFlag = centerFlag;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public ArrayList<Object> getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList<Object> empList) {
		this.empList = empList;
	}
	public String getEmptype() {
		return emptype;
	}
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}
	public String getEmptypeFlag() {
		return emptypeFlag;
	}
	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}
	public String getEmptypeId() {
		return emptypeId;
	}
	public void setEmptypeId(String emptypeId) {
		this.emptypeId = emptypeId;
	}
	public String getPaybillFlag() {
		return paybillFlag;
	}
	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	public ArrayList<Object> getPaybillList() {
		return paybillList;
	}
	public void setPaybillList(ArrayList<Object> paybillList) {
		this.paybillList = paybillList;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @return the profileId
	 */
	public String getProfileId() {
		return profileId;
	}
	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getCopyProfile() {
		return copyProfile;
	}
	public void setCopyProfile(String copyProfile) {
		this.copyProfile = copyProfile;
	}
	public String getCopyProfileId() {
		return copyProfileId;
	}
	public void setCopyProfileId(String copyProfileId) {
		this.copyProfileId = copyProfileId;
	}
	/**
	 * @return the delete
	 */
	public String getDelete() {
		return delete;
	}
	/**
	 * @param delete the delete to set
	 */
	public void setDelete(String delete) {
		this.delete = delete;
	}
	/**
	 * @return the general
	 */
	public String getGeneral() {
		return general;
	}
	/**
	 * @param general the general to set
	 */
	public void setGeneral(String general) {
		this.general = general;
	}
	/**
	 * @return the insert
	 */
	public String getInsert() {
		return insert;
	}
	/**
	 * @param insert the insert to set
	 */
	public void setInsert(String insert) {
		this.insert = insert;
	}
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the update
	 */
	public String getUpdate() {
		return update;
	}
	/**
	 * @param update the update to set
	 */
	public void setUpdate(String update) {
		this.update = update;
	}
	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}
	/**
	 * @return the menuList
	 */
	public ArrayList<Object> getMenuList() {
		return menuList;
	}
	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(ArrayList<Object> menuList) {
		this.menuList = menuList;
	}
	/**
	 * @return the empDesg
	 */
	public String getEmpDesg() {
		return empDesg;
	}
	/**
	 * @param empDesg the empDesg to set
	 */
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	/**
	 * @return the employeeList
	 */
	public ArrayList<Object> getEmployeeList() {
		return employeeList;
	}
	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(ArrayList<Object> employeeList) {
		this.employeeList = employeeList;
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
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the loginID
	 */
	public String getLoginID() {
		return loginID;
	}
	/**
	 * @param loginID the loginID to set
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
	
	public boolean isCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}
	
	public boolean isUpdFlag() {
		return updFlag;
	}
	public void setUpdFlag(boolean updFlag) {
		this.updFlag = updFlag;
	}
	public boolean isGoFlag() {
		return goFlag;
	}
	public void setGoFlag(boolean goFlag) {
		this.goFlag = goFlag;
	}
	public boolean isCopyFlag() {
		return copyFlag;
	}
	public void setCopyFlag(boolean copyFlag) {
		this.copyFlag = copyFlag;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	public boolean isCopyChk() {
		return copyChk;
	}
	public void setCopyChk(boolean copyChk) {
		this.copyChk = copyChk;
	}
	public boolean isOnloadChk() {
		return onloadChk;
	}
	public void setOnloadChk(boolean onloadChk) {
		this.onloadChk = onloadChk;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getLocationType1() {
		return locationType1;
	}
	public void setLocationType1(String locationType1) {
		this.locationType1 = locationType1;
	}
	public int getSelMenuCode() {
		return selMenuCode;
	}
	public void setSelMenuCode(int selMenuCode) {
		this.selMenuCode = selMenuCode;
	}
	public String getCopyProId() {
		return copyProId;
	}
	public void setCopyProId(String copyProId) {
		this.copyProId = copyProId;
	}
	public String getCopyProName() {
		return copyProName;
	}
	public void setCopyProName(String copyProName) {
		this.copyProName = copyProName;
	}
	public boolean isCopyName() {
		return copyName;
	}
	public void setCopyName(boolean copyName) {
		this.copyName = copyName;
	}
	/**
	 * @return hiddenProfileId
	 */
	public String getHiddenProfileId() {
		return hiddenProfileId;
	}
	/**
	 * @param hiddenProfileId the hiddenProfileId to set
	 */
	public void setHiddenProfileId(String hiddenProfileId) {
		this.hiddenProfileId = hiddenProfileId;
	}
	/**
	 * @return listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return noConf
	 */
	public String getNoConf() {
		return noConf;
	}
	/**
	 * @param noConf the noConf to set
	 */
	public void setNoConf(String noConf) {
		this.noConf = noConf;
	}
		
}
