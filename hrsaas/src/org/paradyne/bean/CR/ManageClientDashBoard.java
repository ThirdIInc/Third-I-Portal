package org.paradyne.bean.CR;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ManageClientDashBoard extends BeanBase{
	
	private ArrayList iteratorList = null;
	private ArrayList keepInformedList=null;
	private String srno="";
	private String dashBoardID="";
	private String dashBoardName="";
	private String employeeMappaing="";
	private String clientMapping="";
	private String isAccountApplicatble="";
	private String isActive="";
	private String myPage;
	private boolean listLength = false;
	private String informCode = "";
	private String informToken = "";
	private String informName = "";
	private String meassage="";
	private String accountID="";
	private String accountName="";
	private String searchMessagestr="";
	private String isParent="";
	private String backToAccountLsit="";
	/**
	 * parameter name for user filter
	 */
	private String parameterName="";
	ArrayList filterList = null;
	/**
	 * Text field value for filters
	 */
	private String defaultValue ="";
	private String reportName = "";
	private String reportId = "";
	ArrayList reportList = null;
	private String graphName ="";
	private String componentId ="";
	ArrayList graphList = null;
	private String userType ="";
	private String report="";
	private String reportHidden="";
	private String graphHidden="";
	private String autoIdForGraph="";
	private String showParameterFlag="";
	private String showReportFlag="";
	private String showGraphFlag="";
	ArrayList documentList=null;
	private String documentFileName=null;
	private String docHidden="";
	private String autoidDoc="";
	private String applUserCode=null;
	private ArrayList dashBoardReportList=null;
	private String dashBoardreportName="";
	private String hiddenSearchMessage = "";
	private String searchMessage = "";
	private String crmName = "";
	private String crmToken = "";
	private String crmCode = "";
	

	



	

	/**
	 * @return the hiddenSearchMessage
	 */
	public String getHiddenSearchMessage() {
		return hiddenSearchMessage;
	}

	/**
	 * @param hiddenSearchMessage the hiddenSearchMessage to set
	 */
	public void setHiddenSearchMessage(String hiddenSearchMessage) {
		this.hiddenSearchMessage = hiddenSearchMessage;
	}

	/**
	 * @return the searchMessage
	 */
	public String getSearchMessage() {
		return searchMessage;
	}

	/**
	 * @param searchMessage the searchMessage to set
	 */
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}

	/**
	 * @return the crmName
	 */
	public String getCrmName() {
		return crmName;
	}

	/**
	 * @param crmName the crmName to set
	 */
	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}

	/**
	 * @return the crmToken
	 */
	public String getCrmToken() {
		return crmToken;
	}

	/**
	 * @param crmToken the crmToken to set
	 */
	public void setCrmToken(String crmToken) {
		this.crmToken = crmToken;
	}

	/**
	 * @return the crmCode
	 */
	public String getCrmCode() {
		return crmCode;
	}

	/**
	 * @param crmCode the crmCode to set
	 */
	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}

	public String getDashBoardreportName() {
		return dashBoardreportName;
	}

	public void setDashBoardreportName(String dashBoardreportName) {
		this.dashBoardreportName = dashBoardreportName;
	}

	public String getAutoidDoc() {
		return autoidDoc;
	}

	public void setAutoidDoc(String autoidDoc) {
		this.autoidDoc = autoidDoc;
	}

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	public ArrayList getDocumentList() {
		return documentList;
	}

	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}

	public String getAutoIdForGraph() {
		return autoIdForGraph;
	}

	public void setAutoIdForGraph(String autoIdForGraph) {
		this.autoIdForGraph = autoIdForGraph;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public ArrayList getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public ArrayList getFilterList() {
		return filterList;
	}

	public void setFilterList(ArrayList filterList) {
		this.filterList = filterList;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getSearchMessagestr() {
		return searchMessagestr;
	}

	public void setSearchMessagestr(String searchMessagestr) {
		this.searchMessagestr = searchMessagestr;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMeassage() {
		return meassage;
	}

	public void setMeassage(String meassage) {
		this.meassage = meassage;
	}

	public String getInformCode() {
		return informCode;
	}

	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}

	public String getInformToken() {
		return informToken;
	}

	public void setInformToken(String informToken) {
		this.informToken = informToken;
	}

	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getIsAccountApplicatble() {
		return isAccountApplicatble;
	}

	public void setIsAccountApplicatble(String isAccountApplicatble) {
		this.isAccountApplicatble = isAccountApplicatble;
	}

	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	public String getDashBoardID() {
		return dashBoardID;
	}

	public void setDashBoardID(String dashBoardID) {
		this.dashBoardID = dashBoardID;
	}

	public String getDashBoardName() {
		return dashBoardName;
	}

	public void setDashBoardName(String dashBoardName) {
		this.dashBoardName = dashBoardName;
	}

	public String getEmployeeMappaing() {
		return employeeMappaing;
	}

	public void setEmployeeMappaing(String employeeMappaing) {
		this.employeeMappaing = employeeMappaing;
	}

	public String getClientMapping() {
		return clientMapping;
	}

	public void setClientMapping(String clientMapping) {
		this.clientMapping = clientMapping;
	}

	public ArrayList getIteratorList() {
		return iteratorList;
	}

	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}

	public boolean isListLength() {
		return listLength;
	}

	public void setListLength(boolean listLength) {
		this.listLength = listLength;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getBackToAccountLsit() {
		return backToAccountLsit;
	}

	public void setBackToAccountLsit(String backToAccountLsit) {
		this.backToAccountLsit = backToAccountLsit;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public ArrayList getGraphList() {
		return graphList;
	}

	public void setGraphList(ArrayList graphList) {
		this.graphList = graphList;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getReportHidden() {
		return reportHidden;
	}

	public void setReportHidden(String reportHidden) {
		this.reportHidden = reportHidden;
	}

	public String getGraphHidden() {
		return graphHidden;
	}

	public void setGraphHidden(String graphHidden) {
		this.graphHidden = graphHidden;
	}

	public String getShowParameterFlag() {
		return showParameterFlag;
	}

	public void setShowParameterFlag(String showParameterFlag) {
		this.showParameterFlag = showParameterFlag;
	}

	public String getShowReportFlag() {
		return showReportFlag;
	}

	public void setShowReportFlag(String showReportFlag) {
		this.showReportFlag = showReportFlag;
	}

	public String getShowGraphFlag() {
		return showGraphFlag;
	}

	public void setShowGraphFlag(String showGraphFlag) {
		this.showGraphFlag = showGraphFlag;
	}

	public String getDocHidden() {
		return docHidden;
	}

	public void setDocHidden(String docHidden) {
		this.docHidden = docHidden;
	}

	public String getApplUserCode() {
		return applUserCode;
	}

	public void setApplUserCode(String applUserCode) {
		this.applUserCode = applUserCode;
	}

	public ArrayList getDashBoardReportList() {
		return dashBoardReportList;
	}

	public void setDashBoardReportList(ArrayList dashBoardReportList) {
		this.dashBoardReportList = dashBoardReportList;
	}
	
	
	
	

}
