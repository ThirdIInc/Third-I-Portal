package org.paradyne.bean.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * Bhushan Dasare Feb 14, 2011
 */

public class ApprovalSettings extends BeanBase {

	private String searchHrEmpId;
	private String hrApproverEmpId;
	private String hrApproverEmpToken;
	private String hrApproverEmpName;
	private List hrApproverList;

	/**Nilesh D **/
	//added by Nilesh Start
	private String approvalDivision;
	private String divId="";
	private String divisionCode=""; 
	

	private String myPage1  = "" ;
	private String modeLength  = "" ;
	private String totalNoOfRecords  = "" ;
	private ArrayList divisionList=null;	
	public String hdeleteCode="";
	
	
	private String searchAdminEmpId;
	private String adminApproverEmpId;
	private String adminApproverEmpToken;
	private String adminApproverEmpName;
	private List adminApproverList;

	/*Added New by--Nilesh */
	/*paging names,flags & iterator fields*/
	private String myFinanaceATRPAge="";//Finace ATR paging 
	//private String  myCorporateProGroupPage="";
	
	private boolean financeATRApproverListPage=false;//Finance ATR flag
	
	private String myCorporatePAge="";//Corporate paging 
	
	private boolean corporateApproverListPage=false;//Finance ATR flag
	
	
private String myWorldTravelPage="";//Corporate paging 
	
	private boolean worldTravelListPage=false;//Finance ATR flag
	
	private boolean ConfigureOptionListPage = false;
	private String authEmpId = "";
	private String authEmpToken = "";
	private String authEmpName = "";
	private String isActive = "";
	private String authEmailAddress = "";
	private String myConfigureOptionPage = "";	
	private String configureGroupType = "";
	private boolean configureOptionTypeFlag = false;
	private boolean worldTravelFlag = false;
	private String configEmailId = "";
	private String configEmail="";
	private String configHiddenEmailId="";
	private String searchConfigAuthId="";
	private String searchConfigAuthName="";
	private String searchConfigAuthToken = "";
	private String configReceivedFlag = "";
	private String configAuthId ="";
	private String configEmpId="";
	private String configEmpToken="";
	private String configEmpName="";
	private String configAuthHiddenId="";
	private String hiddenEdit= "";
	private String searchConfigEmpAuthId = "";
	
	//added ganesh start
	
	private String searchFinanceEmpId;
	private String financeApproverEmpId;
	private String financeApproverEmpToken;
	private String financeApproverEmpName;
	private List financeApproverList;
	
	/*added by Nilesh for Finance Atr*/
	
	private String searchFinanceATREmpId;//search hidden
	private String financeATRApproverEmpId;//delete hidden
	private String financeATRApproverEmpToken;
	private String financeATRApproverEmpName;
	private List financeATRApproverList;//Array list
	
	
	private String searchCorporateEmpId1;//search hidden
	private String corporateApproverEmpId;//delete hidden
	private String corporateEmpToken;
	private String corporateEmpName;
	private List corporateProcGroupList;//Array list*/
	
	
	/*World Travel*/
	private String emailId="";
	private String worldId="";
	private String travelId="";
	private List worldTravelList;//Array list
	
	
	
	
	private String searchPayrollEmpId;
	private String payrollApproverEmpId;
	private String payrollApproverEmpToken;
	private String payrollApproverEmpName;
	private List payrollApproverList;
	
	private String searchLogisticsEmpId;
	private String logisticsApproverEmpId;
	private String logisticsApproverEmpToken;
	private String logisticsApproverEmpName;
	private List logisticsApproverList;
	
	private String searchItEmpId;
	private String itApproverEmpId;
	private String itApproverEmpToken;
	private String itApproverEmpName;
	private List itApproverList;
	
	private String searchTrainingAuthId;
	private String trainingAuthEmpId;
	private String searchTrainingAuthToken;
	private String searchTrainingAuthName;
	
	private String trainingAuthEmpToken = "";
	private String trainingAuthEmpName = "";
	private List trainingAuthList;
	
	private String searchEducationMinistryId="";
	private String searchEducationMinistryToken="";
	private String searchEducationMinistryName ="";
	private String educationMinistryEmpId;
		
	private String educationMinistryEmpToken = "";
	private String educationMinistryEmpName = "";
	private List educationMinistryList;
	
	private boolean trainingAuthListPage = false;
	private String myTrainingAuthPage = "";
	
	private boolean hrApproveList = false;
	private String myPage = "";
	private boolean adminApproverListPage = false;
	private String myAdminPage = "";
	
	private boolean financeApproverListPage = false;
	private String myFinancePage = "";
	
	private boolean payrollApproverListPage = false;
	private String myPayrollPage = "";
	
	private boolean logisticsApproverListPage = false;
	private String myLogisticsPage = "";
	
	private boolean itApproverListPage = false;
	private String myItPage = "";
	
	private boolean educationMinistryListPage = false;
	private String myEducationMinistryPage = "";
	
	private String listType = "";
	
	private String searchAdminEmpToken = "";
	private String searchAdminEmpName = "";
	private String searchHrEmpToken = "";
	private String searchHrEmpName = "";
	private String searchFinanceEmpToken= "";
	private String searchFinanceEmpName= "";
	private String searchPayrollEmpToken = "";
	private String searchPayrollEmpName = "";
	private String searchLogisticsEmpToken = "";
	private String searchLogisticsEmpName = "";
	private String searchItEmpToken = "";
	private String searchItEmpName = "";
	private String searchATREmpToken = "";
	private String searchATREmpName = "";
	private String searchCorporateToken1 = "";
	private String searchCorporateEmpName1 = "";
	
	//added ganesh end
	
	
	public String getSearchItEmpToken() {
		return searchItEmpToken;
	}

	public void setSearchItEmpToken(String searchItEmpToken) {
		this.searchItEmpToken = searchItEmpToken;
	}

	public String getSearchItEmpName() {
		return searchItEmpName;
	}

	public void setSearchItEmpName(String searchItEmpName) {
		this.searchItEmpName = searchItEmpName;
	}

	public String getSearchLogisticsEmpToken() {
		return searchLogisticsEmpToken;
	}

	public void setSearchLogisticsEmpToken(String searchLogisticsEmpToken) {
		this.searchLogisticsEmpToken = searchLogisticsEmpToken;
	}

	public String getSearchLogisticsEmpName() {
		return searchLogisticsEmpName;
	}

	public void setSearchLogisticsEmpName(String searchLogisticsEmpName) {
		this.searchLogisticsEmpName = searchLogisticsEmpName;
	}

	public String getSearchPayrollEmpToken() {
		return searchPayrollEmpToken;
	}

	public void setSearchPayrollEmpToken(String searchPayrollEmpToken) {
		this.searchPayrollEmpToken = searchPayrollEmpToken;
	}

	public String getSearchPayrollEmpName() {
		return searchPayrollEmpName;
	}

	public void setSearchPayrollEmpName(String searchPayrollEmpName) {
		this.searchPayrollEmpName = searchPayrollEmpName;
	}

	public String getSearchFinanceEmpToken() {
		return searchFinanceEmpToken;
	}

	public void setSearchFinanceEmpToken(String searchFinanceEmpToken) {
		this.searchFinanceEmpToken = searchFinanceEmpToken;
	}

	public String getSearchFinanceEmpName() {
		return searchFinanceEmpName;
	}

	public void setSearchFinanceEmpName(String searchFinanceEmpName) {
		this.searchFinanceEmpName = searchFinanceEmpName;
	}

	public String getSearchHrEmpToken() {
		return searchHrEmpToken;
	}

	public void setSearchHrEmpToken(String searchHrEmpToken) {
		this.searchHrEmpToken = searchHrEmpToken;
	}

	public String getSearchHrEmpName() {
		return searchHrEmpName;
	}

	public void setSearchHrEmpName(String searchHrEmpName) {
		this.searchHrEmpName = searchHrEmpName;
	}

	public String getSearchAdminEmpToken() {
		return searchAdminEmpToken;
	}

	public void setSearchAdminEmpToken(String searchAdminEmpToken) {
		this.searchAdminEmpToken = searchAdminEmpToken;
	}

	public String getSearchAdminEmpName() {
		return searchAdminEmpName;
	}

	public void setSearchAdminEmpName(String searchAdminEmpName) {
		this.searchAdminEmpName = searchAdminEmpName;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getSearchItEmpId() {
		return searchItEmpId;
	}

	public void setSearchItEmpId(String searchItEmpId) {
		this.searchItEmpId = searchItEmpId;
	}

	public String getItApproverEmpId() {
		return itApproverEmpId;
	}

	public void setItApproverEmpId(String itApproverEmpId) {
		this.itApproverEmpId = itApproverEmpId;
	}

	public String getItApproverEmpToken() {
		return itApproverEmpToken;
	}

	public void setItApproverEmpToken(String itApproverEmpToken) {
		this.itApproverEmpToken = itApproverEmpToken;
	}

	public String getItApproverEmpName() {
		return itApproverEmpName;
	}

	public void setItApproverEmpName(String itApproverEmpName) {
		this.itApproverEmpName = itApproverEmpName;
	}

	public List getItApproverList() {
		return itApproverList;
	}

	public void setItApproverList(List itApproverList) {
		this.itApproverList = itApproverList;
	}

	public boolean isItApproverListPage() {
		return itApproverListPage;
	}

	public void setItApproverListPage(boolean itApproverListPage) {
		this.itApproverListPage = itApproverListPage;
	}

	public String getMyItPage() {
		return myItPage;
	}

	public void setMyItPage(String myItPage) {
		this.myItPage = myItPage;
	}

	public boolean isLogisticsApproverListPage() {
		return logisticsApproverListPage;
	}

	public void setLogisticsApproverListPage(boolean logisticsApproverListPage) {
		this.logisticsApproverListPage = logisticsApproverListPage;
	}

	public String getMyLogisticsPage() {
		return myLogisticsPage;
	}

	public void setMyLogisticsPage(String myLogisticsPage) {
		this.myLogisticsPage = myLogisticsPage;
	}

	public boolean isFinanceApproverListPage() {
		return financeApproverListPage;
	}

	public void setFinanceApproverListPage(boolean financeApproverListPage) {
		this.financeApproverListPage = financeApproverListPage;
	}

	public String getMyFinancePage() {
		return myFinancePage;
	}

	public void setMyFinancePage(String myFinancePage) {
		this.myFinancePage = myFinancePage;
	}

	public boolean isAdminApproverListPage() {
		return adminApproverListPage;
	}

	public void setAdminApproverListPage(boolean adminApproverListPage) {
		this.adminApproverListPage = adminApproverListPage;
	}

	public String getMyAdminPage() {
		return myAdminPage;
	}

	public void setMyAdminPage(String myAdminPage) {
		this.myAdminPage = myAdminPage;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getSearchFinanceEmpId() {
		return searchFinanceEmpId;
	}

	public void setSearchFinanceEmpId(String searchFinanceEmpId) {
		this.searchFinanceEmpId = searchFinanceEmpId;
	}

	public String getFinanceApproverEmpId() {
		return financeApproverEmpId;
	}

	public void setFinanceApproverEmpId(String financeApproverEmpId) {
		this.financeApproverEmpId = financeApproverEmpId;
	}

	public String getFinanceApproverEmpToken() {
		return financeApproverEmpToken;
	}

	public void setFinanceApproverEmpToken(String financeApproverEmpToken) {
		this.financeApproverEmpToken = financeApproverEmpToken;
	}

	public String getFinanceApproverEmpName() {
		return financeApproverEmpName;
	}

	public void setFinanceApproverEmpName(String financeApproverEmpName) {
		this.financeApproverEmpName = financeApproverEmpName;
	}

	public List getFinanceApproverList() {
		return financeApproverList;
	}

	public void setFinanceApproverList(List financeApproverList) {
		this.financeApproverList = financeApproverList;
	}

	//added ganesh end
	/**
	 * @return the adminApproverEmpId
	 */
	public String getAdminApproverEmpId() {
		return adminApproverEmpId;
	}

	/**
	 * @return the adminApproverEmpName
	 */
	public String getAdminApproverEmpName() {
		return adminApproverEmpName;
	}

	/**
	 * @return the adminApproverEmpToken
	 */
	public String getAdminApproverEmpToken() {
		return adminApproverEmpToken;
	}

	/**
	 * @return the adminApproverList
	 */
	public List getAdminApproverList() {
		return adminApproverList;
	}

	/**
	 * @return the hrApproverEmpId
	 */
	public String getHrApproverEmpId() {
		return hrApproverEmpId;
	}

	/**
	 * @return the hrApproverEmpName
	 */
	public String getHrApproverEmpName() {
		return hrApproverEmpName;
	}

	/**
	 * @return the hrApproverEmpToken
	 */
	public String getHrApproverEmpToken() {
		return hrApproverEmpToken;
	}

	/**
	 * @return the hrApproverList
	 */
	public List getHrApproverList() {
		return hrApproverList;
	}

	/**
	 * @return the searchAdminEmpId
	 */
	public String getSearchAdminEmpId() {
		return searchAdminEmpId;
	}

	/**
	 * @return the searchHrEmpId
	 */
	public String getSearchHrEmpId() {
		return searchHrEmpId;
	}

	/**
	 * @param adminApproverEmpId the adminApproverEmpId to set
	 */
	public void setAdminApproverEmpId(String adminApproverEmpId) {
		this.adminApproverEmpId = adminApproverEmpId;
	}

	/**
	 * @param adminApproverEmpName the adminApproverEmpName to set
	 */
	public void setAdminApproverEmpName(String adminApproverEmpName) {
		this.adminApproverEmpName = adminApproverEmpName;
	}

	/**
	 * @param adminApproverEmpToken the adminApproverEmpToken to set
	 */
	public void setAdminApproverEmpToken(String adminApproverEmpToken) {
		this.adminApproverEmpToken = adminApproverEmpToken;
	}

	/**
	 * @param adminApproverList the adminApproverList to set
	 */
	public void setAdminApproverList(List adminApproverList) {
		this.adminApproverList = adminApproverList;
	}

	/**
	 * @param hrApproverEmpId the hrApproverEmpId to set
	 */
	public void setHrApproverEmpId(String hrApproverEmpId) {
		this.hrApproverEmpId = hrApproverEmpId;
	}

	/**
	 * @param hrApproverEmpName the hrApproverEmpName to set
	 */
	public void setHrApproverEmpName(String hrApproverEmpName) {
		this.hrApproverEmpName = hrApproverEmpName;
	}

	/**
	 * @param hrApproverEmpToken the hrApproverEmpToken to set
	 */
	public void setHrApproverEmpToken(String hrApproverEmpToken) {
		this.hrApproverEmpToken = hrApproverEmpToken;
	}

	/**
	 * @param hrApproverList the hrApproverList to set
	 */
	public void setHrApproverList(List hrApproverList) {
		this.hrApproverList = hrApproverList;
	}

	/**
	 * @param searchAdminEmpId the searchAdminEmpId to set
	 */
	public void setSearchAdminEmpId(String searchAdminEmpId) {
		this.searchAdminEmpId = searchAdminEmpId;
	}

	/**
	 * @param searchHrEmpId the searchHrEmpId to set
	 */
	public void setSearchHrEmpId(String searchHrEmpId) {
		this.searchHrEmpId = searchHrEmpId;
	}

	public String getSearchPayrollEmpId() {
		return searchPayrollEmpId;
	}

	public void setSearchPayrollEmpId(String searchPayrollEmpId) {
		this.searchPayrollEmpId = searchPayrollEmpId;
	}

	public String getPayrollApproverEmpId() {
		return payrollApproverEmpId;
	}

	public void setPayrollApproverEmpId(String payrollApproverEmpId) {
		this.payrollApproverEmpId = payrollApproverEmpId;
	}

	public String getPayrollApproverEmpToken() {
		return payrollApproverEmpToken;
	}

	public void setPayrollApproverEmpToken(String payrollApproverEmpToken) {
		this.payrollApproverEmpToken = payrollApproverEmpToken;
	}

	public String getPayrollApproverEmpName() {
		return payrollApproverEmpName;
	}

	public void setPayrollApproverEmpName(String payrollApproverEmpName) {
		this.payrollApproverEmpName = payrollApproverEmpName;
	}

	public List getPayrollApproverList() {
		return payrollApproverList;
	}

	public void setPayrollApproverList(List payrollApproverList) {
		this.payrollApproverList = payrollApproverList;
	}

	public boolean isHrApproveList() {
		return hrApproveList;
	}

	public void setHrApproveList(boolean hrApproveList) {
		this.hrApproveList = hrApproveList;
	}

	public boolean isPayrollApproverListPage() {
		return payrollApproverListPage;
	}

	public void setPayrollApproverListPage(boolean payrollApproverListPage) {
		this.payrollApproverListPage = payrollApproverListPage;
	}

	public String getMyPayrollPage() {
		return myPayrollPage;
	}

	public void setMyPayrollPage(String myPayrollPage) {
		this.myPayrollPage = myPayrollPage;
	}

	public String getSearchLogisticsEmpId() {
		return searchLogisticsEmpId;
	}

	public void setSearchLogisticsEmpId(String searchLogisticsEmpId) {
		this.searchLogisticsEmpId = searchLogisticsEmpId;
	}

	public String getLogisticsApproverEmpId() {
		return logisticsApproverEmpId;
	}

	public void setLogisticsApproverEmpId(String logisticsApproverEmpId) {
		this.logisticsApproverEmpId = logisticsApproverEmpId;
	}

	public String getLogisticsApproverEmpToken() {
		return logisticsApproverEmpToken;
	}

	public void setLogisticsApproverEmpToken(String logisticsApproverEmpToken) {
		this.logisticsApproverEmpToken = logisticsApproverEmpToken;
	}

	public String getLogisticsApproverEmpName() {
		return logisticsApproverEmpName;
	}

	public void setLogisticsApproverEmpName(String logisticsApproverEmpName) {
		this.logisticsApproverEmpName = logisticsApproverEmpName;
	}

	public List getLogisticsApproverList() {
		return logisticsApproverList;
	}

	public void setLogisticsApproverList(List logisticsApproverList) {
		this.logisticsApproverList = logisticsApproverList;
	}

	public String getSearchTrainingAuthId() {
		return searchTrainingAuthId;
	}

	public void setSearchTrainingAuthId(String searchTrainingAuthId) {
		this.searchTrainingAuthId = searchTrainingAuthId;
	}

	public String getTrainingAuthEmpId() {
		return trainingAuthEmpId;
	}

	public void setTrainingAuthEmpId(String trainingAuthEmpId) {
		this.trainingAuthEmpId = trainingAuthEmpId;
	}

	public String getSearchTrainingAuthToken() {
		return searchTrainingAuthToken;
	}

	public void setSearchTrainingAuthToken(String searchTrainingAuthToken) {
		this.searchTrainingAuthToken = searchTrainingAuthToken;
	}

	public String getSearchTrainingAuthName() {
		return searchTrainingAuthName;
	}

	public void setSearchTrainingAuthName(String searchTrainingAuthName) {
		this.searchTrainingAuthName = searchTrainingAuthName;
	}

	public List getTrainingAuthList() {
		return trainingAuthList;
	}

	public void setTrainingAuthList(List trainingAuthList) {
		this.trainingAuthList = trainingAuthList;
	}

	public boolean isTrainingAuthListPage() {
		return trainingAuthListPage;
	}

	public void setTrainingAuthListPage(boolean trainingAuthListPage) {
		this.trainingAuthListPage = trainingAuthListPage;
	}

	public String getMyTrainingAuthPage() {
		return myTrainingAuthPage;
	}

	public void setMyTrainingAuthPage(String myTrainingAuthPage) {
		this.myTrainingAuthPage = myTrainingAuthPage;
	}

	public String getTrainingAuthEmpToken() {
		return trainingAuthEmpToken;
	}

	public void setTrainingAuthEmpToken(String trainingAuthEmpToken) {
		this.trainingAuthEmpToken = trainingAuthEmpToken;
	}

	public String getTrainingAuthEmpName() {
		return trainingAuthEmpName;
	}

	public void setTrainingAuthEmpName(String trainingAuthEmpName) {
		this.trainingAuthEmpName = trainingAuthEmpName;
	}

	public String getSearchEducationMinistryId() {
		return searchEducationMinistryId;
	}

	public void setSearchEducationMinistryId(String searchEducationMinistryId) {
		this.searchEducationMinistryId = searchEducationMinistryId;
	}

	public String getSearchEducationMinistryToken() {
		return searchEducationMinistryToken;
	}

	public void setSearchEducationMinistryToken(String searchEducationMinistryToken) {
		this.searchEducationMinistryToken = searchEducationMinistryToken;
	}

	public String getSearchEducationMinistryName() {
		return searchEducationMinistryName;
	}

	public void setSearchEducationMinistryName(String searchEducationMinistryName) {
		this.searchEducationMinistryName = searchEducationMinistryName;
	}

	public String getEducationMinistryEmpId() {
		return educationMinistryEmpId;
	}

	public void setEducationMinistryEmpId(String educationMinistryEmpId) {
		this.educationMinistryEmpId = educationMinistryEmpId;
	}

	public String getEducationMinistryEmpToken() {
		return educationMinistryEmpToken;
	}

	public void setEducationMinistryEmpToken(String educationMinistryEmpToken) {
		this.educationMinistryEmpToken = educationMinistryEmpToken;
	}

	public String getEducationMinistryEmpName() {
		return educationMinistryEmpName;
	}

	public void setEducationMinistryEmpName(String educationMinistryEmpName) {
		this.educationMinistryEmpName = educationMinistryEmpName;
	}

	public List getEducationMinistryList() {
		return educationMinistryList;
	}

	public void setEducationMinistryList(List educationMinistryList) {
		this.educationMinistryList = educationMinistryList;
	}

	public boolean isEducationMinistryListPage() {
		return educationMinistryListPage;
	}

	public void setEducationMinistryListPage(boolean educationMinistryListPage) {
		this.educationMinistryListPage = educationMinistryListPage;
	}

	public String getMyEducationMinistryPage() {
		return myEducationMinistryPage;
	}

	public void setMyEducationMinistryPage(String myEducationMinistryPage) {
		this.myEducationMinistryPage = myEducationMinistryPage;
	}

	/**
	 * @return the approvalDivision
	 */
	public String getApprovalDivision() {
		return approvalDivision;
	}

	/**
	 * @param approvalDivision the approvalDivision to set
	 */
	public void setApprovalDivision(String approvalDivision) {
		this.approvalDivision = approvalDivision;
	}

	/**
	 * @return the divId
	 */
	public String getDivId() {
		return divId;
	}

	/**
	 * @param divId the divId to set
	 */
	public void setDivId(String divId) {
		this.divId = divId;
	}

	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	/**
	 * @return the myPage1
	 */
	public String getMyPage1() {
		return myPage1;
	}

	/**
	 * @param myPage1 the myPage1 to set
	 */
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
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
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the divisionList
	 */
	public ArrayList getDivisionList() {
		return divisionList;
	}

	/**
	 * @param divisionList the divisionList to set
	 */
	public void setDivisionList(ArrayList divisionList) {
		this.divisionList = divisionList;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @return the myFinanaceATRPAge
	 */
	public String getMyFinanaceATRPAge() {
		return myFinanaceATRPAge;
	}

	/**
	 * @param myFinanaceATRPAge the myFinanaceATRPAge to set
	 */
	public void setMyFinanaceATRPAge(String myFinanaceATRPAge) {
		this.myFinanaceATRPAge = myFinanaceATRPAge;
	}

	/**
	 * @return the financeATRApproverListPage
	 */
	public boolean isFinanceATRApproverListPage() {
		return financeATRApproverListPage;
	}

	/**
	 * @param financeATRApproverListPage the financeATRApproverListPage to set
	 */
	public void setFinanceATRApproverListPage(boolean financeATRApproverListPage) {
		this.financeATRApproverListPage = financeATRApproverListPage;
	}

	/**
	 * @return the searchFinanceATREmpId
	 */
	public String getSearchFinanceATREmpId() {
		return searchFinanceATREmpId;
	}

	/**
	 * @param searchFinanceATREmpId the searchFinanceATREmpId to set
	 */
	public void setSearchFinanceATREmpId(String searchFinanceATREmpId) {
		this.searchFinanceATREmpId = searchFinanceATREmpId;
	}

	/**
	 * @return the financeATRApproverEmpId
	 */
	public String getFinanceATRApproverEmpId() {
		return financeATRApproverEmpId;
	}

	/**
	 * @param financeATRApproverEmpId the financeATRApproverEmpId to set
	 */
	public void setFinanceATRApproverEmpId(String financeATRApproverEmpId) {
		this.financeATRApproverEmpId = financeATRApproverEmpId;
	}

	/**
	 * @return the financeATRApproverEmpToken
	 */
	public String getFinanceATRApproverEmpToken() {
		return financeATRApproverEmpToken;
	}

	/**
	 * @param financeATRApproverEmpToken the financeATRApproverEmpToken to set
	 */
	public void setFinanceATRApproverEmpToken(String financeATRApproverEmpToken) {
		this.financeATRApproverEmpToken = financeATRApproverEmpToken;
	}

	/**
	 * @return the financeATRApproverEmpName
	 */
	public String getFinanceATRApproverEmpName() {
		return financeATRApproverEmpName;
	}

	/**
	 * @param financeATRApproverEmpName the financeATRApproverEmpName to set
	 */
	public void setFinanceATRApproverEmpName(String financeATRApproverEmpName) {
		this.financeATRApproverEmpName = financeATRApproverEmpName;
	}

	/**
	 * @return the financeATRApproverList
	 */
	public List getFinanceATRApproverList() {
		return financeATRApproverList;
	}

	/**
	 * @param financeATRApproverList the financeATRApproverList to set
	 */
	public void setFinanceATRApproverList(List financeATRApproverList) {
		this.financeATRApproverList = financeATRApproverList;
	}

	/**
	 * @return the myCorporatePAge
	 */
	public String getMyCorporatePAge() {
		return myCorporatePAge;
	}

	/**
	 * @param myCorporatePAge the myCorporatePAge to set
	 */
	public void setMyCorporatePAge(String myCorporatePAge) {
		this.myCorporatePAge = myCorporatePAge;
	}

	/**
	 * @return the corporateApproverListPage
	 */
	public boolean isCorporateApproverListPage() {
		return corporateApproverListPage;
	}

	/**
	 * @param corporateApproverListPage the corporateApproverListPage to set
	 */
	public void setCorporateApproverListPage(boolean corporateApproverListPage) {
		this.corporateApproverListPage = corporateApproverListPage;
	}

	
	/**
	 * @return the corporateApproverEmpId
	 */
	public String getCorporateApproverEmpId() {
		return corporateApproverEmpId;
	}

	/**
	 * @param corporateApproverEmpId the corporateApproverEmpId to set
	 */
	public void setCorporateApproverEmpId(String corporateApproverEmpId) {
		this.corporateApproverEmpId = corporateApproverEmpId;
	}

	/**
	 * @return the corporateEmpToken
	 */
	public String getCorporateEmpToken() {
		return corporateEmpToken;
	}

	/**
	 * @param corporateEmpToken the corporateEmpToken to set
	 */
	public void setCorporateEmpToken(String corporateEmpToken) {
		this.corporateEmpToken = corporateEmpToken;
	}

	/**
	 * @return the corporateEmpName
	 */
	public String getCorporateEmpName() {
		return corporateEmpName;
	}

	/**
	 * @param corporateEmpName the corporateEmpName to set
	 */
	public void setCorporateEmpName(String corporateEmpName) {
		this.corporateEmpName = corporateEmpName;
	}

	/**
	 * @return the corporateProcGroupList
	 */
	public List getCorporateProcGroupList() {
		return corporateProcGroupList;
	}

	/**
	 * @param corporateProcGroupList the corporateProcGroupList to set
	 */
	public void setCorporateProcGroupList(List corporateProcGroupList) {
		this.corporateProcGroupList = corporateProcGroupList;
	}

	/**
	 * @return the searchATREmpToken
	 */
	public String getSearchATREmpToken() {
		return searchATREmpToken;
	}

	/**
	 * @param searchATREmpToken the searchATREmpToken to set
	 */
	public void setSearchATREmpToken(String searchATREmpToken) {
		this.searchATREmpToken = searchATREmpToken;
	}

	/**
	 * @return the searchATREmpName
	 */
	public String getSearchATREmpName() {
		return searchATREmpName;
	}

	/**
	 * @param searchATREmpName the searchATREmpName to set
	 */
	public void setSearchATREmpName(String searchATREmpName) {
		this.searchATREmpName = searchATREmpName;
	}

	/**
	 * @return the searchCorporateEmpId1
	 */
	public String getSearchCorporateEmpId1() {
		return searchCorporateEmpId1;
	}

	/**
	 * @param searchCorporateEmpId1 the searchCorporateEmpId1 to set
	 */
	public void setSearchCorporateEmpId1(String searchCorporateEmpId1) {
		this.searchCorporateEmpId1 = searchCorporateEmpId1;
	}

	/**
	 * @return the searchCorporateToken1
	 */
	public String getSearchCorporateToken1() {
		return searchCorporateToken1;
	}

	/**
	 * @param searchCorporateToken1 the searchCorporateToken1 to set
	 */
	public void setSearchCorporateToken1(String searchCorporateToken1) {
		this.searchCorporateToken1 = searchCorporateToken1;
	}

	/**
	 * @return the searchCorporateEmpName1
	 */
	public String getSearchCorporateEmpName1() {
		return searchCorporateEmpName1;
	}

	/**
	 * @param searchCorporateEmpName1 the searchCorporateEmpName1 to set
	 */
	public void setSearchCorporateEmpName1(String searchCorporateEmpName1) {
		this.searchCorporateEmpName1 = searchCorporateEmpName1;
	}

	/**
	 * @return the myWorldTravelPage
	 */
	public String getMyWorldTravelPage() {
		return myWorldTravelPage;
	}

	/**
	 * @param myWorldTravelPage the myWorldTravelPage to set
	 */
	public void setMyWorldTravelPage(String myWorldTravelPage) {
		this.myWorldTravelPage = myWorldTravelPage;
	}

	/**
	 * @return the worldTravelListPage
	 */
	public boolean isWorldTravelListPage() {
		return worldTravelListPage;
	}

	/**
	 * @param worldTravelListPage the worldTravelListPage to set
	 */
	public void setWorldTravelListPage(boolean worldTravelListPage) {
		this.worldTravelListPage = worldTravelListPage;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the worldId
	 */
	public String getWorldId() {
		return worldId;
	}

	/**
	 * @param worldId the worldId to set
	 */
	public void setWorldId(String worldId) {
		this.worldId = worldId;
	}

	/**
	 * @return the travelId
	 */
	public String getTravelId() {
		return travelId;
	}

	/**
	 * @param travelId the travelId to set
	 */
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	/**
	 * @return the worldTravelList
	 */
	public List getWorldTravelList() {
		return worldTravelList;
	}

	/**
	 * @param worldTravelList the worldTravelList to set
	 */
	public void setWorldTravelList(List worldTravelList) {
		this.worldTravelList = worldTravelList;
	}

	public boolean isConfigureOptionListPage() {
		return ConfigureOptionListPage;
	}

	public void setConfigureOptionListPage(boolean configureOptionListPage) {
		ConfigureOptionListPage = configureOptionListPage;
	}

	public String getAuthEmpId() {
		return authEmpId;
	}

	public void setAuthEmpId(String authEmpId) {
		this.authEmpId = authEmpId;
	}

	public String getAuthEmpToken() {
		return authEmpToken;
	}

	public void setAuthEmpToken(String authEmpToken) {
		this.authEmpToken = authEmpToken;
	}

	public String getAuthEmpName() {
		return authEmpName;
	}

	public void setAuthEmpName(String authEmpName) {
		this.authEmpName = authEmpName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAuthEmailAddress() {
		return authEmailAddress;
	}

	public void setAuthEmailAddress(String authEmailAddress) {
		this.authEmailAddress = authEmailAddress;
	}

	public String getMyConfigureOptionPage() {
		return myConfigureOptionPage;
	}

	public void setMyConfigureOptionPage(String myConfigureOptionPage) {
		this.myConfigureOptionPage = myConfigureOptionPage;
	}

	public String getConfigureGroupType() {
		return configureGroupType;
	}

	public void setConfigureGroupType(String configureGroupType) {
		this.configureGroupType = configureGroupType;
	}

	public boolean isConfigureOptionTypeFlag() {
		return configureOptionTypeFlag;
	}

	public void setConfigureOptionTypeFlag(boolean configureOptionTypeFlag) {
		this.configureOptionTypeFlag = configureOptionTypeFlag;
	}

	public boolean isWorldTravelFlag() {
		return worldTravelFlag;
	}

	public void setWorldTravelFlag(boolean worldTravelFlag) {
		this.worldTravelFlag = worldTravelFlag;
	}

	public String getConfigEmailId() {
		return configEmailId;
	}

	public void setConfigEmailId(String configEmailId) {
		this.configEmailId = configEmailId;
	}

	public String getConfigEmail() {
		return configEmail;
	}

	public void setConfigEmail(String configEmail) {
		this.configEmail = configEmail;
	}

	public String getConfigHiddenEmailId() {
		return configHiddenEmailId;
	}

	public void setConfigHiddenEmailId(String configHiddenEmailId) {
		this.configHiddenEmailId = configHiddenEmailId;
	}

	public String getSearchConfigAuthId() {
		return searchConfigAuthId;
	}

	public void setSearchConfigAuthId(String searchConfigAuthId) {
		this.searchConfigAuthId = searchConfigAuthId;
	}

	public String getSearchConfigAuthName() {
		return searchConfigAuthName;
	}

	public void setSearchConfigAuthName(String searchConfigAuthName) {
		this.searchConfigAuthName = searchConfigAuthName;
	}

	public String getSearchConfigAuthToken() {
		return searchConfigAuthToken;
	}

	public void setSearchConfigAuthToken(String searchConfigAuthToken) {
		this.searchConfigAuthToken = searchConfigAuthToken;
	}

	public String getConfigReceivedFlag() {
		return configReceivedFlag;
	}

	public void setConfigReceivedFlag(String configReceivedFlag) {
		this.configReceivedFlag = configReceivedFlag;
	}

	public String getConfigAuthId() {
		return configAuthId;
	}

	public void setConfigAuthId(String configAuthId) {
		this.configAuthId = configAuthId;
	}

	public String getConfigEmpId() {
		return configEmpId;
	}

	public void setConfigEmpId(String configEmpId) {
		this.configEmpId = configEmpId;
	}

	

	public String getConfigEmpToken() {
		return configEmpToken;
	}

	public void setConfigEmpToken(String configEmpToken) {
		this.configEmpToken = configEmpToken;
	}

	public String getConfigEmpName() {
		return configEmpName;
	}

	public void setConfigEmpName(String configEmpName) {
		this.configEmpName = configEmpName;
	}

	public String getConfigAuthHiddenId() {
		return configAuthHiddenId;
	}

	public void setConfigAuthHiddenId(String configAuthHiddenId) {
		this.configAuthHiddenId = configAuthHiddenId;
	}

	public String getHiddenEdit() {
		return hiddenEdit;
	}

	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	public String getSearchConfigEmpAuthId() {
		return searchConfigEmpAuthId;
	}

	public void setSearchConfigEmpAuthId(String searchConfigEmpAuthId) {
		this.searchConfigEmpAuthId = searchConfigEmpAuthId;
	}	
}