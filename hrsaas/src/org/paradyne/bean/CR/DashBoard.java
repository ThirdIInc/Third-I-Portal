package org.paradyne.bean.CR;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.paradyne.lib.BeanBase;

public class DashBoard extends BeanBase{
	private String nRows;
	private String nCols;
	private String dataPath;
	ArrayList menuList=null;
	private String reportName;
	private Map objMap; 
	ArrayList matrixList;
	private String chartCode;
	private String attachmentPath="";
	private String mailToEmployeeId="";
	private String mailToEmployeeToken="";
	private String mailToEmployeeName="";
	private String mailToEmployeeEmail="";
	private String ccEmailIds="";
	private String emailReport="";
	private String subject="";
	private String description="";
	private String returnMessage="";
	private String actionBack="";
	private String fileName="";
	private String htmlcode="";
	private String sentFlag="";
	private String mailToListEmpId="";
	private String mailToListEmpToken="";
	private String mailToListEmpName="";
	private String mailToListEmpEmail="";
	private String DefoultSubject="";
	/* for Scheduler */
	private String jobQueryType="";
	private String hiddenJobCode ="";
	private String moduleName ="";
	private ArrayList<Object>list =null;
	private String jobDuration ="";
	private String jobDayOfWeek ="";
	private String jobDayOfMonth ="";
	private String jobStartTime ="";
	private String emailCheck ="";
	private String alertCheck ="";
	private String hiddenEdit ="";
	private String employeeToken ="";
	private String employeeName ="";
	private String employeeCode ="";
	private String addFlag="false";
	private String showFlag="false" ;
	private String srNo ="";
	private String empToken =""; 
	private String employeeId="";
	private String empName ="";
	private boolean startShedulerFlag =false ;
	private boolean stopShedulerFlag =false ;
	private String dashBoardID="";
	private String accountID="";
	private String accountName="";
	private String descriptionBr="";
	private String compoNo="";
	private String componentName="";
	private String accountNo="";
	private String nextGraph="";
	private String preComponentId="";
	private String preNextParam;
	private String preGraphParameter;
	private String userType;
	private String reportID;
	private String ismail;
	private String userID;
	ArrayList documentList=null;
	private String documentFile="";
	private String documentFileloc="";
	private String dashBoardWidth="";
	private String isDataInternal="";
	private String dataURL="";
	private String dashBoardName="";
	private String dashBoardAccountName="";
	private String isDashBoard="";
	private String mailSendMsg="";
	private String textF9MIS="";
	private String scenarioId="";
	private String scenario_name="";
	LinkedHashMap<String , String> SenerioMap;
	private String dataEnable="";
	private String graphEnable="";
	private String graphType="";
	private String dashMonth="";
	private String dashYear="";
	private String dashAutoId="";
	private String dashscFlag="";
	
	
	/**
	 * @return the dashAutoId
	 */
	public String getDashAutoId() {
		return dashAutoId;
	}
	/**
	 * @param dashAutoId the dashAutoId to set
	 */
	public void setDashAutoId(String dashAutoId) {
		this.dashAutoId = dashAutoId;
	}
	/**
	 * @return the dataEnable
	 */
	public String getDataEnable() {
		return dataEnable;
	}
	/**
	 * @param dataEnable the dataEnable to set
	 */
	public void setDataEnable(String dataEnable) {
		this.dataEnable = dataEnable;
	}
	/**
	 * @return the graphEnable
	 */
	public String getGraphEnable() {
		return graphEnable;
	}
	/**
	 * @param graphEnable the graphEnable to set
	 */
	public void setGraphEnable(String graphEnable) {
		this.graphEnable = graphEnable;
	}
	/**
	 * @return the graphType
	 */
	public String getGraphType() {
		return graphType;
	}
	/**
	 * @param graphType the graphType to set
	 */
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	/**
	 * @return the senerioMap
	 */
	public LinkedHashMap<String, String> getSenerioMap() {
		return SenerioMap;
	}
	/**
	 * @param senerioMap the senerioMap to set
	 */
	public void setSenerioMap(LinkedHashMap<String, String> senerioMap) {
		SenerioMap = senerioMap;
	}
	
	/**
	 * @return the scenarioId
	 */
	public String getScenarioId() {
		return scenarioId;
	}
	/**
	 * @param scenarioId the scenarioId to set
	 */
	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}
	/**
	 * @return the mailSendMsg
	 */
	public String getMailSendMsg() {
		return mailSendMsg;
	}
	/**
	 * @param mailSendMsg the mailSendMsg to set
	 */
	public void setMailSendMsg(String mailSendMsg) {
		this.mailSendMsg = mailSendMsg;
	}
	/**
	 * @return the isDashBoard
	 */
	public String getIsDashBoard() {
		return isDashBoard;
	}
	/**
	 * @param isDashBoard the isDashBoard to set
	 */
	public void setIsDashBoard(String isDashBoard) {
		this.isDashBoard = isDashBoard;
	}
	public String getDashBoardAccountName() {
		return dashBoardAccountName;
	}
	public void setDashBoardAccountName(String dashBoardAccountName) {
		this.dashBoardAccountName = dashBoardAccountName;
	}
	public String getDashBoardName() {
		return dashBoardName;
	}
	public void setDashBoardName(String dashBoardName) {
		this.dashBoardName = dashBoardName;
	}
	public String getIsDataInternal() {
		return isDataInternal;
	}
	public void setIsDataInternal(String isDataInternal) {
		this.isDataInternal = isDataInternal;
	}
	public String getDataURL() {
		return dataURL;
	}
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}
	public String getDashBoardWidth() {
		return dashBoardWidth;
	}
	public void setDashBoardWidth(String dashBoardWidth) {
		this.dashBoardWidth = dashBoardWidth;
	}
	public String getDocumentFileloc() {
		return documentFileloc;
	}
	public void setDocumentFileloc(String documentFileloc) {
		this.documentFileloc = documentFileloc;
	}
	public String getDocumentFile() {
		return documentFile;
	}
	public void setDocumentFile(String documentFile) {
		this.documentFile = documentFile;
	}
	public ArrayList getDocumentList() {
		return documentList;
	}
	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getIsmail() {
		return ismail;
	}
	public void setIsmail(String ismail) {
		this.ismail = ismail;
	}
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	public String getPreNextParam() {
		return preNextParam;
	}
	public void setPreNextParam(String preNextParam) {
		this.preNextParam = preNextParam;
	}
	public String getPreGraphParameter() {
		return preGraphParameter;
	}
	public void setPreGraphParameter(String preGraphParameter) {
		this.preGraphParameter = preGraphParameter;
	}
	public String getPreComponentId() {
		return preComponentId;
	}
	public void setPreComponentId(String preComponentId) {
		this.preComponentId = preComponentId;
	}
	public String getCompoNo() {
		return compoNo;
	}
	public void setCompoNo(String compoNo) {
		this.compoNo = compoNo;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getNextGraph() {
		return nextGraph;
	}
	public void setNextGraph(String nextGraph) {
		this.nextGraph = nextGraph;
	}
	public String getDescriptionBr() {
		return descriptionBr;
	}
	public void setDescriptionBr(String descriptionBr) {
		this.descriptionBr = descriptionBr;
	}
	public String getDashBoardID() {
		return dashBoardID;
	}
	public void setDashBoardID(String dashBoardID) {
		this.dashBoardID = dashBoardID;
	}
	public String getDefoultSubject() {
		return DefoultSubject;
	}
	public void setDefoultSubject(String defoultSubject) {
		DefoultSubject = defoultSubject;
	}
	public String getCcEmailIds() {
		return ccEmailIds;
	}
	public void setCcEmailIds(String ccEmailIds) {
		this.ccEmailIds = ccEmailIds;
	}
	public String getMailToEmployeeEmail() {
		return mailToEmployeeEmail;
	}
	public void setMailToEmployeeEmail(String mailToEmployeeEmail) {
		this.mailToEmployeeEmail = mailToEmployeeEmail;
	}
	public String getChartCode() {
		return chartCode;
	}
	public void setChartCode(String chartCode) {
		this.chartCode = chartCode;
	}
	public DashBoard(){
		
	}
	public String getNRows() {
		return nRows;
	}
	public void setNRows(String rows) {
		nRows = rows;
	}
	public String getNCols() {
		return nCols;
	}

	public ArrayList getMatrixList() {
		return matrixList;
	}
	public void setMatrixList(ArrayList matrixList) {
		this.matrixList = matrixList;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public void setNCols(String cols) {
		nCols = cols;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public ArrayList getMenuList() {
		return menuList;
	}
	public void setMenuList(ArrayList menuList) {
		this.menuList = menuList;
	}
	public Map getObjMap() {
		return objMap;
	}
	public void setObjMap(Map objMap) {
		this.objMap = objMap;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getMailToEmployeeId() {
		return mailToEmployeeId;
	}
	public void setMailToEmployeeId(String mailToEmployeeId) {
		this.mailToEmployeeId = mailToEmployeeId;
	}
	public String getMailToEmployeeToken() {
		return mailToEmployeeToken;
	}
	public void setMailToEmployeeToken(String mailToEmployeeToken) {
		this.mailToEmployeeToken = mailToEmployeeToken;
	}
	public String getMailToEmployeeName() {
		return mailToEmployeeName;
	}
	public void setMailToEmployeeName(String mailToEmployeeName) {
		this.mailToEmployeeName = mailToEmployeeName;
	}
	public String getEmailReport() {
		return emailReport;
	}
	public void setEmailReport(String emailReport) {
		this.emailReport = emailReport;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getActionBack() {
		return actionBack;
	}
	public void setActionBack(String actionBack) {
		this.actionBack = actionBack;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getHtmlcode() {
		return htmlcode;
	}
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	public String getSentFlag() {
		return sentFlag;
	}
	public void setSentFlag(String sentFlag) {
		this.sentFlag = sentFlag;
	}
	public String getMailToListEmpId() {
		return mailToListEmpId;
	}
	public void setMailToListEmpId(String mailToListEmpId) {
		this.mailToListEmpId = mailToListEmpId;
	}
	public String getMailToListEmpToken() {
		return mailToListEmpToken;
	}
	public void setMailToListEmpToken(String mailToListEmpToken) {
		this.mailToListEmpToken = mailToListEmpToken;
	}
	public String getMailToListEmpName() {
		return mailToListEmpName;
	}
	public void setMailToListEmpName(String mailToListEmpName) {
		this.mailToListEmpName = mailToListEmpName;
	}
	public String getMailToListEmpEmail() {
		return mailToListEmpEmail;
	}
	public void setMailToListEmpEmail(String mailToListEmpEmail) {
		this.mailToListEmpEmail = mailToListEmpEmail;
	}
	/* getter setter for sheduler*/
	public String getJobQueryType() {
		return jobQueryType;
	}
	public void setJobQueryType(String jobQueryType) {
		this.jobQueryType = jobQueryType;
	}
	public String getHiddenJobCode() {
		return hiddenJobCode;
	}
	public void setHiddenJobCode(String hiddenJobCode) {
		this.hiddenJobCode = hiddenJobCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public ArrayList<Object> getList() {
		return list;
	}
	public void setList(ArrayList<Object> list) {
		this.list = list;
	}
	public String getJobDuration() {
		return jobDuration;
	}
	public void setJobDuration(String jobDuration) {
		this.jobDuration = jobDuration;
	}
	public String getJobDayOfWeek() {
		return jobDayOfWeek;
	}
	public void setJobDayOfWeek(String jobDayOfWeek) {
		this.jobDayOfWeek = jobDayOfWeek;
	}
	public String getJobDayOfMonth() {
		return jobDayOfMonth;
	}
	public void setJobDayOfMonth(String jobDayOfMonth) {
		this.jobDayOfMonth = jobDayOfMonth;
	}
	public String getJobStartTime() {
		return jobStartTime;
	}
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
	public String getEmailCheck() {
		return emailCheck;
	}
	public void setEmailCheck(String emailCheck) {
		this.emailCheck = emailCheck;
	}
	public String getAlertCheck() {
		return alertCheck;
	}
	public void setAlertCheck(String alertCheck) {
		this.alertCheck = alertCheck;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public boolean isStartShedulerFlag() {
		return startShedulerFlag;
	}
	public void setStartShedulerFlag(boolean startShedulerFlag) {
		this.startShedulerFlag = startShedulerFlag;
	}
	public boolean isStopShedulerFlag() {
		return stopShedulerFlag;
	}
	public void setStopShedulerFlag(boolean stopShedulerFlag) {
		this.stopShedulerFlag = stopShedulerFlag;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the textF9MIS
	 */
	public String getTextF9MIS() {
		return textF9MIS;
	}
	/**
	 * @param textF9MIS the textF9MIS to set
	 */
	public void setTextF9MIS(String textF9MIS) {
		this.textF9MIS = textF9MIS;
	}
	/**
	 * @return the scenario_name
	 */
	public String getScenario_name() {
		return scenario_name;
	}
	/**
	 * @param scenario_name the scenario_name to set
	 */
	public void setScenario_name(String scenario_name) {
		this.scenario_name = scenario_name;
	}
	/**
	 * @return the dashMonth
	 */
	public String getDashMonth() {
		return dashMonth;
	}
	/**
	 * @param dashMonth the dashMonth to set
	 */
	public void setDashMonth(String dashMonth) {
		this.dashMonth = dashMonth;
	}
	/**
	 * @return the dashYear
	 */
	public String getDashYear() {
		return dashYear;
	}
	/**
	 * @param dashYear the dashYear to set
	 */
	public void setDashYear(String dashYear) {
		this.dashYear = dashYear;
	}
	/**
	 * @return the dashscFlag
	 */
	public String getDashscFlag() {
		return dashscFlag;
	}
	/**
	 * @param dashscFlag the dashscFlag to set
	 */
	public void setDashscFlag(String dashscFlag) {
		this.dashscFlag = dashscFlag;
	}
	
	
	

}
